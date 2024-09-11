CREATE EXTENSION IF NOT EXISTS pgcrypto;

create table users
(
    id              serial        not null primary key ,
    name            varchar(255)  not null unique ,
    phone_number    varchar(255)  not null,
    email           varchar(255) not null unique,
    password        varchar(1000) not null,
    city            varchar(255),
    registration_date timestamp(6),
    activation_code varchar(255),
    active          boolean
);

insert into users (name, phone_number, email, password, city, registration_date, active)
values ('Илья Алексеев', '+79178940122', 'user_test@gmail.com', crypt('123', gen_salt('bf')), 'Самара', NOW(), true);