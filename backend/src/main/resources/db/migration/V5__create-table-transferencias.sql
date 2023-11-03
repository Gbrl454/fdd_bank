CREATE TABLE IF NOT EXISTS transferencias
(
    id                   BIGINT    NOT NULL AUTO_INCREMENT,
    o_conta_id           BIGINT    NOT NULL,
    d_conta_id           BIGINT    NOT NULL,
    valor                BIGINT    NOT NULL,
    horario_tranferencia TIMESTAMP NOT NULL,
    sucesso              TINYINT   NOT NULL,
    motivo               VARCHAR(150),

    PRIMARY KEY (id),
    CONSTRAINT fk_transferencias_o_contas_id FOREIGN KEY (o_conta_id) REFERENCES contas (id),
    CONSTRAINT fk_transferencias_d_contas_id FOREIGN KEY (d_conta_id) REFERENCES contas (id)
);