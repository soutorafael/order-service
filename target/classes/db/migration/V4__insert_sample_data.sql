-- Inserindo pedidos
INSERT INTO pedido (status, valor_total) VALUES
                                             ('PENDENTE', 66.50),
                                             ('PENDENTE', 42.00),
                                             ('PENDENTE', 30.00);

-- Inserindo produtos
INSERT INTO produto (nome, preco, quantidade) VALUES
                                                  ('Brahma', 12.00, 10),
                                                  ('Heineken', 15.00, 5),
                                                  ('Skol', 10.50, 7),
                                                  ('Coca-Cola', 6.50, 20);

-- Relacionando pedidos com produtos
INSERT INTO pedido_produtos (pedido_id, produto_id) VALUES
                                                        (1, 1), (1, 2), (1, 3),
                                                        (2, 2), (2, 3),
                                                        (3, 4);
