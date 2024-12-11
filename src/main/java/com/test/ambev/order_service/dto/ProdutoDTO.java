package com.test.ambev.order_service.dto;

import java.math.BigDecimal;

public class ProdutoDTO {

    private Long idProduto;
    private String produtoNome;
    private Integer quantidade;
    private BigDecimal valorProduto;

    public ProdutoDTO(Long produtoId, String produtoNome, BigDecimal produtoPreco) {
        this.idProduto = produtoId;
        this.produtoNome = produtoNome;
        this.valorProduto = produtoPreco;
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
}
