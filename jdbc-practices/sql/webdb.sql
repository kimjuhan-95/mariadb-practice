

SELECT * FROM user;
delete from board;
delete from user;

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


