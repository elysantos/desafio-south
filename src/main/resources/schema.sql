create table if not exists associados
(
    id      uuid        not null
    constraint associados_pk
    primary key,
    cpf     varchar(11) not null,
    enabled bool default false
    );

create table if not exists pautas
(
    id      uuid        not null
        constraint pautas_pk
            primary key,
    titulo     varchar(100) not null
);

create table if not exists sessoes
(
    id        uuid not null
        constraint sessoes_pk
            primary key,
    pauta_id  uuid,
    duracao   integer   default 1,
    dt_inicio timestamp default now(),
    status    integer   default 0
);

create table if not exists votos
(
    id           uuid not null
        constraint votos_pk
            primary key,
    associado_id uuid not null,
    sessao_id    uuid,
    valor bool not null,
    constraint votos_uk
        unique (associado_id, sessao_id)
);

