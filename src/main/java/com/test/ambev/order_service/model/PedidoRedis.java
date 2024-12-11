package com.test.ambev.order_service.model;

import com.test.ambev.order_service.dto.ProdutoDTO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PedidoRedis implements Serializable {

    private Long id;
    private BigDecimal valorTotal;
    private String status;
    private List<ProdutoRedis> produtos;

    public PedidoRedis() {
    }

    public PedidoRedis(Long id, String status, BigDecimal valorTotal, List<ProdutoRedis> produtos) {
        this.id = id;
        this.status = status;
        this.valorTotal = valorTotal;
        this.produtos = produtos;
    }

    public static PedidoRedis of(Long id, String status, BigDecimal valorTotal, List<ProdutoDTO> produtos) {
        return new PedidoRedis(id, status, valorTotal, produtos != null ? produtos.stream().map(ProdutoRedis::mapper).collect(Collectors.toList()) : null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProdutoRedis> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoRedis> produtos) {
        this.produtos = produtos;
    }
}
