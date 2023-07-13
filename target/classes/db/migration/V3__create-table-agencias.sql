create table agencias
(
    id       bigint       not null auto_increment,
    nome     varchar(100) not null,
    banco_id bigint       not null,

    primary key (id),
    constraint fk_agencias_banco_id foreign key (banco_id) references bancos (id)
);