create table tb_flow
(
    id         int auto_increment
        primary key,
    flowname   varchar(100) null,
    flowkey    varchar(100) null,
    filepath   varchar(100) null,
    state      int          null,
    createtime date         null
)
    charset = utf8;

INSERT INTO act_spring_boot_web.tb_flow (id, flowname, flowkey, filepath, state, createtime) VALUES (1, '出差申请', 'evection', 'bpmn/evection.bpmn', 0, '2021-12-28');