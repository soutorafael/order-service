package com.test.ambev.order_service.controller;

import com.test.ambev.order_service.dto.PedidoDTO;
import com.test.ambev.order_service.model.PedidoRedis;
import com.test.ambev.order_service.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/processa")
    public ResponseEntity<Void> processar(@RequestBody PedidoDTO pedidoDTO) throws Exception {
        pedidoService.processarPedido(pedidoDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/processados/{id}")
    public ResponseEntity<PedidoRedis> processados(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.processados(id));
    }

    @GetMapping("/disponibilizar")
    public ResponseEntity<Page<PedidoDTO>> disponibiliza(@RequestParam(required = false) boolean all,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(pedidoService.disponibiliza(pageable));
    }

}
