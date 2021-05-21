alter table user
    drop access_token,
    drop access_token_expire_date,
    drop refresh_token,
    drop refresh_token_expire_date;

create table if not exists user_login
(
    id_user_login                   int auto_increment primary key,
    fk_id_user                      int                                     not null,
    access_token                    varchar(256)                            not null,
    access_token_expire_date        timestamp                               not null,
    refresh_token                   varchar(256)                            not null,
    refresh_token_expire_date       timestamp                               not null,
    constraint fk_id_user_user_login foreign key (fk_id_user) references user (id_user)
);