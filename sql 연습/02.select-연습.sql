--
-- select 연습
-- 

-- 예제01 : departments 테이블의 모든 데이터를 출력.
select * from departments;

-- 프로젝션(Projection)
-- 예제-2 : employees 테이블에서 직원의 이름, 성별, 입사일을 출력.
select first_name, gender, hire_date
	from employees;