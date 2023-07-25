create table transferencias
(
    id                   bigint         not null auto_increment,
    o_banco_id           bigint         not null,
    o_agencia_id         bigint         not null,
    o_conta_id           bigint         not null,
    d_banco_id           bigint         not null,
    d_agencia_id         bigint         not null,
    d_conta_id           bigint         not null,
    valor                decimal(11, 2) not null,
    horario_tranferencia timestamp      not null,
    sucesso              tinyint        not null,

    primary key (id),
    constraint fk_transferencias_o_banco_id foreign key (o_banco_id) references bancos (id),
    constraint fk_transferencias_o_agencia_id foreign key (o_agencia_id) references agencias (id),
    constraint fk_transferencias_o_contas_id foreign key (o_conta_id) references contas (id),
    constraint fk_transferencias_d_banco_id foreign key (d_banco_id) references bancos (id),
    constraint fk_transferencias_d_agencia_id foreign key (d_agencia_id) references agencias (id),
    constraint fk_transferencias_d_contas_id foreign key (d_conta_id) references contas (id)
);