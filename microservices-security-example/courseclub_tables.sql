create table if not exists user
(
    user_id                   int auto_increment primary key,
    first_name                varchar(50)                             not null,
    last_name                 varchar(50)                             not null,
    username                  varchar(50)                             not null,
    password                  varchar(256)                            not null,
    CONSTRAINT uc_user UNIQUE (username)
);

create table if not exists role
(
	role_id     int auto_increment primary key,
    role_name   varchar(30)                             not null
);

create table if not exists user_role
(
    user_role_id int auto_increment primary key,
    fk_user_id   int                                     not null,
    fk_role_id   int                                     not null,
    created_at   timestamp default CURRENT_TIMESTAMP     not null,
    constraint fk_user_id_user_role foreign key (fk_user_id) references user(user_id),
    constraint fk_role_id_user_role foreign key (fk_role_id) references role(role_id)
);

insert into role (role_name) values ('ADMIN');
insert into role (role_name) values ('USER');

create table if not exists user_authorization
(
    user_authorization_id           int auto_increment primary key,
    fk_user_id                      int                                     null,
    access_token                    varchar(50)                            not null,
    access_token_expire_date        timestamp                               not null,
    refresh_token                   varchar(50)                            not null,
    refresh_token_expire_date       timestamp                               not null,
    constraint fk_user_id_user_authorization foreign key (fk_user_id) references user (user_id)
);

create table if not exists course
(
	course_id	int auto_increment primary key,
    name		varchar(50)							not null,
    description varchar(300)						not null,
    price 		decimal(4,2)						not null
);

create table if not exists coupon
(
	coupon_id	int auto_increment primary key,
    code		varchar(15)							not null,
    is_used		tinyint default 0					not null
);

create table if not exists user_course
(
	user_coupon_id	int auto_increment primary key,
    fk_user_id			int 							not null,
    fk_course_id		int 							not null,
	fk_coupon_id		int								null,
    constraint fk_user_id_user_course foreign key (fk_course_id) references user(user_id),
    constraint fk_course_id_user_course foreign key (fk_course_id) references course(course_id),
    constraint fk_coupon_id_user_course foreign key (fk_coupon_id) references coupon(coupon_id)
);