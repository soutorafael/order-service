package com.test.ambev.order_service.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class PedidoDTO implements Serializable {

    private Long idPedido;
    private String status ;
    private BigDecimal valorTotal;
    private List<ProdutoDTO> produtos;

    public PedidoDTO(){}

    public  PedidoDTO(Long pedidoId, String status, BigDecimal valorTotal, ArrayList<ProdutoDTO> produtos) {
        this.idPedido = pedidoId;
        this.status = status;
        this.produtos = produtos;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ProdutoDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoDTO> produtos) {
        this.produtos = produtos;
    }
}
