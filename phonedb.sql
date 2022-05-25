--ID,PW : phonedb

--삭제
drop table person;
drop sequence seq_person_id;


--테이블 생성
create table person (
	person_id	number(5),
	name		varchar2(30)	not null,
	hp			varchar2(20),
	company		varchar2(20),
	primary key (person_id)
);

--시퀀스 생성
create sequence seq_person_id
increment by 1
start with 1;

--데이터 출력
select *
from person;

--데이터 추가
insert into person
values (seq_person_id.nextval, '이효리', '010-1111-1111', '02-1111-1111');

insert into person
values (seq_person_id.nextval, '정우성', '010-2222-2222', '02-2222-2222');

insert into person
values (seq_person_id.nextval, '유재석', '010-3333-3333', '010-3333-3333');

insert into person
values (seq_person_id.nextval, '이정재', '010-4444-4444', '010-4444-4444');

insert into person
values (seq_person_id.nextval, '서장훈', '010-5555-5555', '010-5555-5555');


--데이터 수정
update	person
set		hp = '010-1234-5678',
		company = '02-1234-5678'
where	person_id = 4;


delete from person
where person_id = 5;

--커밋
commit;

--롤백
rollback;