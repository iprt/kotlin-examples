drop table if exists student;

create table student
(
    `id`   BIGINT PRIMARY KEY COMMENT '主键ID',
    `name` VARCHAR(32) NOT NULL COMMENT '名称',
    `info` json        NOT NULL COMMENT '信息'
)engine=InnoDB row_format=dynamic;

insert into student (`id`, `name`, `info`)
values (1, 'hello', '{
  "father": "dad",
  "mother": "mom"
}'),
       (2, 'kotlin', '{
         "lgtd": 123.10086,
         "lttd": 30.213
       }');



drop table if exists benchmarks_auto;

create table benchmarks_auto
(
    `id`   BIGINT      not null auto_increment comment 'id',
    `data` varchar(50) not null comment 'data',
    primary key (`id`)
)engine=InnoDB row_format=dynamic;


drop table if exists benchmarks_flow;

create table benchmarks_flow
(
    `id`   BIGINT      not null comment 'id',
    `data` varchar(50) not null comment 'data',
    primary key (`id`)
)engine=InnoDB row_format=dynamic;
