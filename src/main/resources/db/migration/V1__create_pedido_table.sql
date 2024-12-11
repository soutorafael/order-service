CREATE TABLE pedido (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        status VARCHAR(255) NOT NULL,
                        valor_total DECIMAL(10, 2) NOT NULL
);
