--liquibase formatted sql

--changeset devops:student
drop table if exists student;
create table student
(
    `id`   bigint primary key comment '主键ID',
    `name` varchar(32) not null comment '名称',
    `info` json        not null comment '信息'
) engine = InnoDB row_format = dynamic;

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

--changeset devops:benchmark_auto_increment
drop table if exists benchmarks_auto;
create table benchmarks_auto
(
    `id`   bigint      not null auto_increment comment 'id',
    `data` varchar(50) not null comment 'data',
    primary key (`id`)
) engine = InnoDB row_format = dynamic;

--changeset devops:benchmark_auto_snowflake
drop table if exists benchmarks_flow;
create table benchmarks_flow
(
    `id`   bigint      not null comment 'id',
    `data` varchar(50) not null comment 'data',
    primary key (`id`)
) engine = InnoDB row_format = dynamic;
