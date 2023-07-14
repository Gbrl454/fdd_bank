create table contas
(
    id                      bigint         not null auto_increment,
    agencia_id              bigint         not null,
    usuario_id              bigint         not null,
    saldo                   decimal(11, 2) not null,
    tipo                    varchar(15)    not null,
    cartao_de_credito       tinyint        default 0,
    saldo_cartao_de_credito decimal(11, 2) default 0.0,
    lis                     tinyint        default 0,
    saldo_lis               decimal(11, 2) default 0.0,

    primary key (id),
    constraint fk_contas_agencia_id foreign key (agencia_id) references agencias (id),
    constraint fk_contas_usuario_id foreign key (usuario_id) references usuarios (id)
);