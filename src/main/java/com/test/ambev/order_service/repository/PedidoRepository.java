package com.test.ambev.order_service.repository;

import com.test.ambev.order_service.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query(value = "SELECT " +
            "p.id AS pedidoId, " +
            "p.status as status, " +
            "p.valor_Total as valorTotal, " +
            "pr.id AS IdProduto, " +
            "pr.nome AS produtosNome, " +
            "pr.preco AS totalPreco " +
            "FROM pedido p " +
            "JOIN pedido_produtos pp ON p.id = pp.pedido_id " +
            "JOIN produto pr ON pp.produto_id = pr.id " +
            "WHERE p.status = :status ",
            nativeQuery = true)
    Page<Object[]> findPedidosPendetes(@Param("status") String status, Pageable pageable);

}
