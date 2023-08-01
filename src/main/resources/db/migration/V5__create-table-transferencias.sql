create table transferencias
(
    id                   bigint    not null auto_increment,
    o_conta_id           bigint    not null,
    d_conta_id           bigint    not null,
    valor                bigint    not null,
    horario_tranferencia timestamp not null,
    sucesso              tinyint   not null,

    primary key (id),
    constraint fk_transferencias_o_contas_id foreign key (o_conta_id) references contas (id),
    constraint fk_transferencias_d_contas_id foreign key (d_conta_id) references contas (id)
);