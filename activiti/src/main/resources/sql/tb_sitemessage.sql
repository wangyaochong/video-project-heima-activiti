create table tb_sitemessage
(
    id         int auto_increment
        primary key,
    userid     int          null,
    type       int          null,
    content    varchar(100) null,
    isread     int          null,
    createtime date         null,
    updatetime date         null,
    taskid     varchar(50)  null
)
    charset = utf8;

INSERT INTO act_spring_boot_web.tb_sitemessage (id, userid, type, content, isread, createtime, updatetime, taskid) VALUES (1, 2, 1, null, 1, null, null, '218f4b50-67c5-11ec-b54f-daf8834e3112');
INSERT INTO act_spring_boot_web.tb_sitemessage (id, userid, type, content, isread, createtime, updatetime, taskid) VALUES (2, 3, 1, null, 1, null, null, '05223539-686b-11ec-beaa-daf8834e3112');
INSERT INTO act_spring_boot_web.tb_sitemessage (id, userid, type, content, isread, createtime, updatetime, taskid) VALUES (3, 4, 1, null, 1, null, null, '2c8ffb09-686d-11ec-969a-daf8834e3112');