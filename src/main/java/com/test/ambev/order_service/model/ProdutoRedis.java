package com.test.ambev.order_service.model;

import com.test.ambev.order_service.dto.ProdutoDTO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProdutoRedis implements Serializable {

    private Long idProduto;
    private String produtoNome;
    private Integer quantidade;
    private BigDecimal valorProduto;

    public ProdutoRedis() {
    }

    public ProdutoRedis(Long idProduto, BigDecimal valorProduto, String produtoNome) {
        this.idProduto = idProduto;
        this.valorProduto = valorProduto;
        this.produtoNome = produtoNome;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(BigDecimal valorProduto) {
        this.valorProduto = valorProduto;
    }

    public static ProdutoRedis mapper(ProdutoDTO produtoDTO) {
        return new ProdutoRedis(produtoDTO.getIdProduto(), produtoDTO.getValorProduto(), produtoDTO.getProdutoNome());
    }
}
