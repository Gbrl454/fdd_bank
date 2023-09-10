CREATE TABLE agencias
(
    id       BIGINT       NOT NULL AUTO_INCREMENT,
    nome     VARCHAR(100) NOT NULL,
    banco_id BIGINT       NOT NULL,
    PRIMARY KEY (id,
                 banco_id),
    UNIQUE INDEX nome_UNIQUE (nome ASC) VISIBLE,
    INDEX fk_agencia_banco_idx (banco_id ASC) VISIBLE,
    CONSTRAINT fk_agencia_banco FOREIGN KEY (banco_id) REFERENCES bancos (id)
)
    ENGINE = InnoDB;