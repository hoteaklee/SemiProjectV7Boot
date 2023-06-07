create table member (
                        mbno     number        generated always as identity , /*자동증가*/
                        name    varchar2(15)   not null ,
                        jumin1  char(6)        not null ,    /*고정길이는 char*/
                        jumin2  char(7)        not null ,
                        userid  varchar2(18)   not null ,
                        passwd  varchar2(18)   not null ,
                        zipcode char(7)        not null ,
                        addr1   varchar2(100)  not null ,
                        addr2   varchar2(150)      not null ,
                        email   varchar2(50)      not null ,
                        phone   varchar2(15)      not null ,
                        regdate date        default current_timestamp,
                        primary key (mbno)
);


create table board (
                       bno         number     generated always as identity (nocache ),
                       title       varchar2(100)    not null,
                       userid      varchar2(18)    not null,
                       regdate     date    default current_timestamp,
                       thumbs      number    default 0,
                       views       number    default 0,
                       content     clob,
                       primary key (bno)
);


insert into board (title, userid, content)
values('영화 "슈퍼마리오 브라더스" 수익 1조원 돌파','닌텐도','총 10억 달러');
insert into board (title, userid, content)
values ('석가탄신일 대체공휴일','연합뉴스','나스닥 상장을 위해 미국 증권거래위원회(SEC)에 서류 제출을 마치고 승인을 받은 상태고, 예정대로면 우리 시간으로 오늘(4일) 밤 상장을 합니다. ');
insert into board (title, userid, content)
values ('행성 꿀꺽 삼키는 별 첫','MIT','올해로 어린이날이 101주년을 맞은 가운데 경기지역 실종 아동이 증가하 ');

select * from board order by bno desc ;

create table pds (
                     pno         number     generated always as identity (nocache ),
                     title       varchar2(100)    not null,
                     userid      varchar2(18)    not null,
                     regdate     date    default current_timestamp,
                     thumbs      number    default 0,
                     views       number    default 0,
                     content     clob,
                     uuid     char(17)    not null ,
                     primary key (pno)
);

create table pdsattach (
                           pano         number     generated always as identity (nocache ),
                           fname       varchar2(100)    not null,
                           ftype      varchar2(5)    not null,
                           fsize      number    not null,
                           fdown      number    default 0,
                           pno      number    not null ,

                           primary key (pano)
);

select ftype from pdsattach;



create table pdsreply(
                         rpno      number generated always as identity (nocache),
                         reply     varchar2(2000)   not null ,
                         userid    varchar2(18) not null ,
                         regdate   date    default current_timestamp,
                         pno       number  not null ,
                         refno     number  ,

                         primary key (rpno)
);

select * from PdsReply where pno = :pno order by refno, regdate asc ;

select "BIGDATA"."ISEQ$$_78807".nextval from dual;

select "BIGDATA"."ISEQ$$_78807".currval from dual;

create table movreply(
                         rpno      number generated always as identity (nocache),
                         reply     varchar2(2000)   not null ,
                         userid    varchar2(18) not null ,
                         regdate   date    default current_timestamp,
                         movno       number  not null ,
                         star     number  ,

                         primary key (rpno)
);

create table gallery (
                         gno         number     generated always as identity (nocache ),
                         title       varchar2(100)    not null,
                         userid      varchar2(18)    not null,
                         regdate     date    default current_timestamp,
                         thumbs      number    default 0,
                         views       number    default 0,
                         content     clob,
                         uuid     char(17)    not null ,
                         primary key (gno)
);

create table galattach (
                           gano         number     generated always as identity (nocache ),
                           fname       varchar2(100)    not null,
                           fsize      number    not null,
                           gno      number    not null ,

                           primary key (gano)
);