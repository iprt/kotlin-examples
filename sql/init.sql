DROP TABLE
    IF
    EXISTS student;
CREATE TABLE student
(
    id   BIGINT PRIMARY KEY COMMENT '' 主键ID '',
    NAME VARCHAR(32) NOT NULL COMMENT '' 名称 '',
    info json        NOT NULL COMMENT '' 信息 ''
);

INSERT INTO student (`id`, `name`, `info`)
VALUES (1, ''hello'', ''{\"father\": \"dad\", \"mother\": \"mom\"}'' ),
	( 2, ''kotlin'', ''{\"lgtd\": 123.10086, \"lttd\": 30.213}'' );