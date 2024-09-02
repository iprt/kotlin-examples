-- liquibase formatted sql

-- changeset devops:student
drop table if exists student;
create table student
(
    `id`   bigint primary key comment '主键ID',
    `name` varchar(32) not null comment '名称',
    `info` json        not null comment '信息'
) engine = InnoDB
  row_format = dynamic;

insert into student (`id`, `name`, `info`)
values
    (1, 'hello', '{
      "father": "dad",
      "mother": "mom"
    }'),
    (2, 'kotlin', '{
      "lgtd": 123.10086,
      "lttd": 30.213
    }');

-- changeset devops:id_type_autoincrement
drop table if exists id_type_autoincrement;
create table id_type_autoincrement
(
    `id`        bigint      not null auto_increment comment 'id',
    `data`      varchar(50) not null comment 'data',
    `created_at` datetime    not null default current_timestamp,
    primary key (`id`)
) engine = InnoDB
  row_format = dynamic;

-- changeset devops:id_type_snowflake
drop table if exists id_type_snowflake;
create table id_type_snowflake
(
    `id`        bigint      not null comment 'id',
    `data`      varchar(50) not null comment 'data',
    `created_at` datetime    not null default current_timestamp,
    primary key (`id`)
) engine = InnoDB
  row_format = dynamic;
