CREATE TABLE pedido_produtos (
                                 pedido_id BIGINT NOT NULL,
                                 produto_id BIGINT NOT NULL,
                                 PRIMARY KEY (pedido_id, produto_id),
                                 FOREIGN KEY (pedido_id) REFERENCES pedido(id) ON DELETE CASCADE,
                                 FOREIGN KEY (produto_id) REFERENCES produto(id) ON DELETE CASCADE
);
