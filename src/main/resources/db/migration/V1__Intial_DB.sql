create table account
(
    id         bigint not null primary key,
    email      varchar(255),
    "enabled"    boolean,
    first_name varchar(255),
    last_name  varchar(255),
    "locked"     boolean,
    "password"   varchar(255),
    account_role  varchar(255)
);

create table confirmation_token
(
    id           bigint       not null
        primary key,
    confirmed_at timestamp,
    created_at   timestamp    not null,
    expired_at   timestamp    not null,
    token        varchar(255) not null,
    user_id      bigint       not null
--         constraint fkah4p1rycwibwm6s9bsyeckq51
            references account
);
