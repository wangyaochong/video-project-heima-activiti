create table tb_evection
(
    id           int auto_increment
        primary key,
    evectionName varchar(100) null,
    num          double       null,
    destination  varchar(100) null,
    begindate    date         null,
    enddate      date         null,
    reson        varchar(100) null,
    state        int          null,
    userid       int          null
)
    charset = utf8;

INSERT INTO act_spring_boot_web.tb_evection (id, evectionName, num, destination, begindate, enddate, reson, state, userid) VALUES (1, 'jack出差', 5, '北京', '2021-12-28', '2022-01-02', '面谈', 1, 1);