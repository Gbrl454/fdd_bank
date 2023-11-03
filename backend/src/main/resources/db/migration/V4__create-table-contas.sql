CREATE TABLE IF NOT EXISTS contas
(
    id                      BIGINT      NOT NULL AUTO_INCREMENT,
    tipo                    VARCHAR(15) NOT NULL,
    saldo                   BIGINT      NULL DEFAULT 0,
    cartao_de_credito       TINYINT     NULL DEFAULT 0,
    saldo_cartao_de_credito BIGINT      NULL DEFAULT 0,
    lis                     TINYINT     NULL DEFAULT 0,
    saldo_lis               BIGINT      NULL DEFAULT 0,
    agencia_id              BIGINT      NOT NULL,
    agencia_banco_id        BIGINT      NOT NULL,
    usuario_id              BIGINT      NOT NULL,
    PRIMARY KEY (id, agencia_id, agencia_banco_id, usuario_id),
    INDEX fk_conta_agencia_idx (agencia_id ASC, agencia_banco_id ASC) VISIBLE,
    INDEX fk_conta_usuario_idx (usuario_id ASC) VISIBLE,
    CONSTRAINT fk_conta_agencia FOREIGN KEY (agencia_id, agencia_banco_id) REFERENCES agencias (id, banco_id),
    CONSTRAINT fk_conta_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
)
    ENGINE = InnoDB;