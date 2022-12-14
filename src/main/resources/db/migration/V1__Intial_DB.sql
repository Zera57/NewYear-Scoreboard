create table account
(
    id              bigint not null primary key,
    name            varchar(255),
    password      varchar(255),
    enabled       boolean,
    locked        boolean,
    account_role    varchar(255)
);

create table player
(
    id          bigint  not null primary key,
    nickname    varchar(255),
    name        varchar(255),
    score       int,
    user_id     bigint
            references account
);
