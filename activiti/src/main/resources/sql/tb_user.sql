create table tb_user
(
    id        int auto_increment
        primary key,
    age       int          null,
    username  varchar(100) null,
    password  varchar(100) null,
    email     varchar(100) null,
    gender    int          null,
    role      varchar(100) null,
    rolegroup varchar(100) null
)
    charset = utf8;

INSERT INTO act_spring_boot_web.tb_user (id, age, username, password, email, gender, role, rolegroup) VALUES (1, 20, 'jack', '$2a$10$9FIX76XUZgIyiOTjy5iswe9fJrWuaxAwTLsJb0QFJAcu5OqTb/TJS', '123@123.com', 1, 'user', 'activiti_user');
INSERT INTO act_spring_boot_web.tb_user (id, age, username, password, email, gender, role, rolegroup) VALUES (2, 25, 'rose', '$2a$10$9FIX76XUZgIyiOTjy5iswe9fJrWuaxAwTLsJb0QFJAcu5OqTb/TJS', '234@234.com', 2, 'user', 'activiti_user');
INSERT INTO act_spring_boot_web.tb_user (id, age, username, password, email, gender, role, rolegroup) VALUES (3, 22, 'tom', '$2a$10$9FIX76XUZgIyiOTjy5iswe9fJrWuaxAwTLsJb0QFJAcu5OqTb/TJS', '345@35.com', 1, 'user', 'activiti_user');
INSERT INTO act_spring_boot_web.tb_user (id, age, username, password, email, gender, role, rolegroup) VALUES (4, 30, 'jerry', '$2a$10$9FIX76XUZgIyiOTjy5iswe9fJrWuaxAwTLsJb0QFJAcu5OqTb/TJS', '456@456.com', 2, 'user', 'activiti_user');