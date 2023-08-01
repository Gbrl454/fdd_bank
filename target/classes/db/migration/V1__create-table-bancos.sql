create table bancos
(
    id   bigint     not null auto_increment,
    nome varchar(100) UNIQUE,
    pais varchar(5) not null,

    primary key (id)
);