desc email_log;
desc board;

select no, title, writer from board;

ALTER TABLE board ADD COLUMN user_id BIGINT;

drop table email_log;

select * from email;
select * from email_log;

delete from email_log;
delete from email;

insert into email_log values(current_date(), 1);

update email_log set count = count + 1 where reg_date = current_date();

-- select
select * from email_log;


-- insert
insert into email_log values (current_date(), 1);

-- update
UPDATE email_log SET count = count + 1 WHERE reg_date = curdate();

update email_log set count = count - 1 where reg_date = '';

select * from user;

alter table user add column role enum('USER','ADMIN') default 'USER' not null;
desc user;

insert into user values(10, '관리자','admin@mysite.com', password('1234'), 'male',now(), 'ADMIN');

insert into site values(
1,
'MySite',
'안녕하세요. 김주한의 mysite에 오신 것을 환영합니다.',
'/upload-images/default.png',
'이 사이트는  웹 프로그램밍 실습과제 예제 사이트입니다.메뉴는  사이트 소개, 방명록, 게시판이 있구요. Java 수업 + 데이터베이스 수업 + 웹프로그래밍 수업 배운 거 있는거 없는 거 다 합쳐서 만들어 놓은 사이트 입니다.'
);

ALTER TABLE user ADD COLUMN role enum('USER','ADMIN') NOT NULL DEFAULT 'USER';

DESC board;

SELECT * FROM user;

ALTER TABLE user MODIFY id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY;

DESC user;

ALTER TABLE user CHANGE id id BIGINT NOT NULL AUTO_INCREMENT;



INSERT INTO user(name, email, password, role, gender, join_date)
VALUES ('김주한', 'test@test.com', PASSWORD('1234'), 'USER', 'male', NOW());

ALTER TABLE user MODIFY join_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE guestbook 
MODIFY COLUMN id BIGINT NOT NULL AUTO_INCREMENT;

DESCRIBE board;

ALTER TABLE board MODIFY COLUMN id INT(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE board DROP COLUMN user_id1;

SELECT * FROM user WHERE email='가입한 이메일';

show tables;
select * from user;

select * from board;
select * from guestbook;

select * from user where email = '가입한 이메일';

SHOW GRANTS FOR 'webdb'@'localhost';

INSERT INTO user(name, email, password, gender, join_date, role)
VALUES ('홍길동', 'hong@test.com', PASSWORD('1234'), 'male', NOW(), 'USER');


