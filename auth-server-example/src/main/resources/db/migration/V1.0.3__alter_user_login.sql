alter table user_login
    add column created_at timestamp default CURRENT_TIMESTAMP not null