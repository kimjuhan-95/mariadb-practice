--
-- JDBC Test SQL
--

desc dept;

-- select 
select id, name from dept where name like '%개발%';

-- insert
insert into dept(name) values('UX팀');

-- delete
delete from dept where id = 7;

-- update
update dept set name = '서비스개발팀' where id = 2;

--
-- email application
-- 

desc email;

-- count
select count(*) from email;

-- findAll
select id, first_name, last_name, email from email order by id desc;

-- deleteByEmail
delete from email where email = 'dooly@gamil.com';

-- insert
insert into email(first_name, last_name, email) values ('둘','리', 'dooly@gmail.com');

--
-- mysite: user
--

desc user;


-- insert: author
insert into author(name) 
select last_insert_id();


--
-- guestbook
--

desc guestbook;

-- insert
insert into guestbook values(null, '둘리', '1234', 'ㅎㅇ', now());

-- findAll
select id, name, date_format(reg_date, '%Y-%m-%d %H:%i') as reg_date 
from guestbook;

-- deleteByIdAndPassword
delete from guestbook where id = 1 and password = '1234';

--
-- mysite: user
--

desc user;


-- 회원가입
insert into author (name, email, password, gender, join_date) values ('둘리', 'dooly@g.com', password('1234'), 'male', now());

-- 회원리스트
select * from user;

-- 삭제
delete from user where id = 3;

-- 로그인
select id, name from user where email = 'kickscar@gmail.com' and password = password('1234');

-- update
update user 
set name='홍길동', password = password('1234'), gender = 'male' 
where id = 10;










