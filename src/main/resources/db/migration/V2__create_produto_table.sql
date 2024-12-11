CREATE TABLE produto (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(255) NOT NULL,
                         preco DECIMAL(10, 2) NOT NULL,
                         quantidade INT NOT NULL
);
