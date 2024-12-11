package com.test.ambev.order_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.ambev.order_service.dto.PedidoDTO;
import com.test.ambev.order_service.dto.ProdutoDTO;
import com.test.ambev.order_service.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PedidoControllerTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PedidoController pedidoController;

    @Autowired
    private PedidoService pedidoService;

    @Test
    public void testProcessarPedido() throws Exception {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setIdPedido(1L);
        pedidoDTO.setValorTotal(new BigDecimal(37.50));
        pedidoDTO.setStatus("PROCESSADO");

        List<ProdutoDTO> produtos = new ArrayList<>();
        ProdutoDTO produto1 = new ProdutoDTO(1l, "Skol", new BigDecimal(5.00) );
        ProdutoDTO produto2 = new ProdutoDTO(2l, "Brahma", new BigDecimal(5.00));

        produtos.add(produto1);
        produtos.add(produto2);

        pedidoDTO.setProdutos(produtos);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPedido = objectMapper.writeValueAsString(pedidoDTO);
        mockMvc.perform(post("/api/pedidos/processa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPedido))
                .andExpect(status().isOk());
    }

    @Test
    public void testProcessados() throws Exception {
        Long pedidoId = 1L;
        ResultActions result = mockMvc.perform(get("/api/pedidos/processados/{id}", pedidoId));
        result.andExpect(status().isOk())
                .andExpect(jsonPath("id").value(pedidoId));
    }

    @Test
    public void testDisponibiliza() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/pedidos/disponibilizar")
                .param("page", "0")
                .param("size", "10"));
        result.andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}
