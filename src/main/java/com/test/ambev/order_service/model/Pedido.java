package com.test.ambev.order_service.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Pedido implements Serializable {

        private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String status;
        private BigDecimal valorTotal;
        @ElementCollection
        @CollectionTable(name = "pedido_produtos", joinColumns = @JoinColumn(name = "pedido_id"))
        @Column(name = "produto_id")
        private List<Long> produtosIds = new ArrayList<>();

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
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

        public List<Long> getProdutosIds() {
                return produtosIds;
        }

        public void setProdutosIds(List<Long> produtosIds) {
                this.produtosIds = produtosIds;
        }
}
