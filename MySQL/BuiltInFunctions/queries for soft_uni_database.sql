#1
SELECT `first_name` , `last_name` FROM `employees`
WHERE SUBSTRING(`first_name` , 1 , 2) = 'Sa';
#second option
SELECT `first_name` , `last_name` FROM `employees`
WHERE left(`first_name` , 2) = 'Sa';

#2
SELECT `first_name` , `last_name` FROM `employees`
WHERE LOCATE('ei' , `last_name`);

#second option
SELECT `first_name` , `last_name` FROM `employees`
WHERE `last_name` LIKE '%ei%';

#3
SELECT `first_name` FROM `employees`
WHERE `department_id` IN (3,10) #WHERE `department_id` = 3 OR `department_id` = 4
AND SUBSTRING(`hire_date` , 1 , 4) >= 1995 #AND YEAR (`hire_date`) BETWEEN 1995 AND 2005
AND SUBSTRING(`hire_date` , 1 , 4) <= 2005
ORDER BY `employee_id`;

#4
SELECT `first_name` , `last_name` FROM `employees`
WHERE `job_title` NOT LIKE '%engineer%'
ORDER BY `employee_id`;

#5
SELECT `name` FROM `towns`
WHERE char_length(`name`) IN (5,6)
ORDER BY `name`;

#6
SELECT `town_id` , `name` FROM `towns`
WHERE LEFT (`name` , 1) IN ('M','K','B','E')
ORDER BY `name`;

#7
SELECT `town_id` , `name` FROM `towns`
WHERE LEFT (`name` , 1) NOT IN ('R','B','D')
ORDER BY `name`;

#8
CREATE VIEW `v_employees_hired_after_2000` AS
SELECT `first_name` , `last_name` FROM `employees`
WHERE YEAR(`hire_date`) > 2000;

SELECT * FROM `v_employees_hired_after_2000`; 

#9
SELECT `first_name` , `last_name` FROM `employees`
WHERE char_length(`last_name`) = 5;
