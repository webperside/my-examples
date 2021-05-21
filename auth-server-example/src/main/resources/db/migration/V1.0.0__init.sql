create table if not exists user
(
    id_user                   int auto_increment primary key,
    first_name                varchar(50)                             not null,
    last_name                 varchar(50)                             not null,
    username                  varchar(50)                             not null,
    password                  varchar(256)                            not null,
    access_token              varchar(256)                            not null,
    access_token_expire_date  timestamp                               not null,
    refresh_token             varchar(256)                            not null,
    refresh_token_expire_date timestamp                               not null,
    created_at                timestamp default CURRENT_TIMESTAMP     not null,
    modified_at               timestamp                               null
);

create index ix_username_user on user (username);

create table if not exists role
(
    id_role     int auto_increment primary key,
    role_name   varchar(30)                             not null,
    created_at  timestamp default CURRENT_TIMESTAMP     not null
);

create index ux_role_name_role on role (role_name);

create table if not exists user_role
(
    id_user_role int auto_increment primary key,
    id_user      int                                     not null,
    id_role      int                                     not null,
    created_at   timestamp default CURRENT_TIMESTAMP     not null,
    constraint fk_id_user_user_role foreign key (id_user) references user(id_user),
    constraint fk_id_role_user_role foreign key (id_role) references role(id_role)
);

insert into role (role_name, created_at) values ('ADMIN', current_timestamp);
insert into role (role_name, created_at) values ('USER', current_timestamp);
insert into role (role_name, created_at) values ('MODERATOR', current_timestamp )