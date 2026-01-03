create table app_user (
  id bigserial primary key,
  username varchar(50) not null unique,
  password_hash varchar(200) not null
);

create table post (
  id bigserial primary key,
  user_id bigint not null references app_user(id),
  content varchar(280) not null,
  created_at timestamptz not null
);

create index idx_post_created_at on post(created_at desc);
