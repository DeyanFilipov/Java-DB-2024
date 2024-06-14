#1
SELECT `id` , `first_name` ,  `last_name` , `job_title` FROM `employees`
ORDER BY `id`;

#2
SELECT `id` , concat(`first_name` , ' ' , `last_name`) as full_name , `job_title` , `salary` FROM `employees`
WHERE `salary` > 1000
ORDER BY `id`;

#3
UPDATE `employees`
SET `salary` = `salary` + 100
WHERE `job_title` = 'Manager';
SELECT `salary` FROM `employees`;

#4
SELECT * FROM `employees`
ORDER BY `salary` DESC
LIMIT 1;

#5
SELECT * FROM `employees`
WHERE `department_id` = 4 AND `salary` >= 1000
ORDER BY `id`;

#6
DELETE FROM `employees`
WHERE `department_id` IN (1, 2);

SELECT * FROM `employees`
ORDER BY `id`;