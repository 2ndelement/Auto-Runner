create table if not exists "user"
(
    id       serial primary key,
    email    varchar(255) not null unique,
    username varchar(50)  not null unique,
    password varchar(255) not null,
    status   smallint     not null default 0
);
comment on table "user" is '用户表';
comment on column "user".id is '用户id';
comment on column "user".email is '用户邮箱';
comment on column "user".username is '用户名';
comment on column "user".password is '用户密码';
comment on column "user".status is '用户状态';


create table if not exists proxy_user
(
    id             serial primary key,
    proxy_username varchar(50)  not null,
    proxy_password varchar(255) not null,
    verify_status  smallint     not null default 0,
    user_id        integer      not null references "user"
);

comment on table proxy_user is '代跑用户表';
comment on column proxy_user.id is '代跑用户id';
comment on column proxy_user.proxy_username is '代跑用户名';
comment on column proxy_user.proxy_password is '代跑用户密码';
comment on column proxy_user.verify_status is '代跑用户验证状态';
comment on column proxy_user.user_id is '所属用户id';


create table if not exists admin
(
    id       serial primary key,
    username varchar(50)  not null unique,
    password varchar(255) not null
);

comment on table admin is '管理员表';
comment on column admin.id is '管理员id';
comment on column admin.username is '管理员用户名';
comment on column admin.password is '管理员密码';


create table if not exists record
(
    id         serial primary key,
    proxy_user integer   not null references proxy_user,
    time       timestamp not null,
    status     smallint  not null
);

comment on table record is '记录表';
comment on column record.id is '记录id';
comment on column record.proxy_user is '所属代跑用户id';
comment on column record.time is '记录时间';
comment on column record.status is '记录状态';

create table if not exists worker
(
    id     serial primary key,
    status smallint not null default 0
);

comment on table worker is '工作机表';
comment on column worker.id is '工作机id';
comment on column worker.status is '工作机状态';