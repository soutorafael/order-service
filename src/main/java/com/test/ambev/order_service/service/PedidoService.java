package com.test.ambev.order_service.service;

import com.test.ambev.order_service.dto.PedidoDTO;
import com.test.ambev.order_service.dto.ProdutoDTO;
import com.test.ambev.order_service.model.Pedido;
import com.test.ambev.order_service.model.PedidoRedis;
import com.test.ambev.order_service.repository.PedidoRepository;
import jakarta.persistence.LockModeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class PedidoService {


    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private RedisTemplate<String, PedidoRedis> redisTemplate;

    private static final String PEDIDO_CACHE_KEY_PREFIX = "pedido:";


    @Transactional
    @Lock(LockModeType.PESSIMISTIC_READ)
    public void processarPedido(PedidoDTO pedidoDTO) throws Exception {
         Pedido pedido = pedidoRepository.findById(pedidoDTO.getIdPedido()).orElseThrow(() -> new Exception(""));
         BigDecimal valorTotal = calcularValorTotal(pedidoDTO.getProdutos());
         pedido.setValorTotal(valorTotal);
         pedido.setStatus("PROCESSADO");
         pedidoRepository.save(pedido);
         PedidoRedis pedidoRedis = PedidoRedis.of(pedido.getId(), pedido.getStatus(), pedido.getValorTotal(), pedidoDTO.getProdutos());
        redisTemplate.opsForValue().set(PEDIDO_CACHE_KEY_PREFIX + pedido.getId(), pedidoRedis, 10, TimeUnit.MINUTES);

    }

    private BigDecimal calcularValorTotal(List<ProdutoDTO> produtos) {
        if (produtos == null){
            return null;
        }
        return produtos.stream()
                .map(ProdutoDTO::getValorProduto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    @Transactional
    @Lock(LockModeType.PESSIMISTIC_READ)
    public PedidoRedis processados(Long id) {
        PedidoRedis pedido = (PedidoRedis) redisTemplate.opsForValue().get(PEDIDO_CACHE_KEY_PREFIX + id);

        if (pedido == null) {
            Optional<Pedido> optionalPedido = pedidoRepository.findById(id);
            if (optionalPedido.isPresent()) {
                redisTemplate.opsForValue().set(PEDIDO_CACHE_KEY_PREFIX + id, pedido, 10, TimeUnit.MINUTES);
            }
        }
        return pedido;
    }

    public Page<PedidoDTO> disponibiliza(Pageable pageable) {

        Page<Object[]> pagePedidos = pedidoRepository.findPedidosPendetes("PENDENTE", pageable);

        Map<Long, PedidoDTO> pedidosMap = pagePedidos.getContent().stream()
                .collect(Collectors.toMap(
                        obj -> (Long) obj[0],
                        obj -> {
                            Long pedidoId = (Long) obj[0];
                            String status = (String) obj[1];
                            BigDecimal valorTotal = (BigDecimal) obj[2];
                            Long produtoId = (Long) obj[3];
                            String produtoNome = (String) obj[4];
                            BigDecimal produtoPreco = (BigDecimal) obj[5];

                            ProdutoDTO produtoDTO = new ProdutoDTO(produtoId, produtoNome, produtoPreco);

                            PedidoDTO pedidoDTO = new PedidoDTO(pedidoId, status, valorTotal, new ArrayList<>());
                            pedidoDTO.getProdutos().add(produtoDTO);

                            return pedidoDTO;
                        },
                        (existing, replacement) -> {
                            existing.getProdutos().addAll(replacement.getProdutos());
                            return existing;
                        }
                ));
        List<PedidoDTO> pedidosDTO = new ArrayList<>(pedidosMap.values());
        return new PageImpl<>(pedidosDTO, pageable, pagePedidos.getTotalElements());
    }

}
