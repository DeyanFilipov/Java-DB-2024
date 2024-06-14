SELECT `id` , `first_name` , `last_name` , `job_title` FROM `employees`
ORDER BY `id`;

SELECT `id` , concat(`first_name`, ' ' , `last_name`) AS `full_name` , `job_title` , `salary` FROM `employees`
WHERE `salary` > 1000.00
ORDER BY `id`;

SELECT `id` , concat_ws(' ' , `first_name` , `last_name`) AS `full_name` , `job_title` , `salary` FROM `employees`
WHERE `salary` > 1000.00
ORDER BY `id`;

SELECT * FROM `employees`
WHERE `department_id` = 4 AND `salary` >= 1000;

SELECT * FROM `employees`
WHERE `department_id` IN (1 , 4);

SELECT * FROM `employees`
WHERE `department_id` IN (1 , 4) AND `salary` >= 1000 AND `job_title` = 'Porter';

SELECT * FROM `employees`
WHERE `department_id` IN (1 , 3 , 4) AND `salary` BETWEEN 1000 AND 1500
ORDER BY `id`;

SELECT * FROM `employees`
WHERE `department_id` IN (1 , 3 , 4) AND `salary` BETWEEN 1000 AND 1500 AND `job_title` = 'Porter';

CREATE VIEW `v_top_paid_employee` AS
SELECT * FROM `employees`
ORDER BY `salary` DESC
LIMIT 1;
SELECT * FROM `v_top_paid_employee`;

SET SQL_SAFE_UPDATES = 0;
UPDATE `employees`
SET `salary` = `salary` + 100
WHERE `job_title` = 'Manager';
SELECT `salary` FROM `employees`;

DELETE FROM `employees`
WHERE `department_id` IN (1, 2);

SELECT * FROM `employees`
ORDER BY `id`;