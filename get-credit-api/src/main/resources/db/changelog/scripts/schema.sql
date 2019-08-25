create table pessoa
(
    cpf          varchar(11) primary key,
    nome         varchar(1000)  not null,
    idade        integer        not null,
    sexo         char(1)        not null,
    estado_civil varchar(255)   not null,
    uf           char(2)        not null,
    dependentes  integer        not null,
    renda        numeric(10, 2) not null
);

create table proposta
(
    id              INT GENERATED ALWAYS AS IDENTITY,
    pessoa_cpf      varchar(11) not null references pessoa (cpf),
    resultado       varchar(255),
    motivo_negativa varchar(255),
    piso            numeric(10, 2),
    teto            numeric(10, 2)
);
