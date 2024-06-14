#13
SELECT `department_id` , MIN(`salary`) AS `minimum_salary` FROM `employees`
WHERE `department_id` IN (2,5,7)
GROUP BY `department_id`
ORDER BY `department_id`;

#14

#15
SELECT `department_id` , MAX(`salary`) AS `max_salary` FROM `employees`
GROUP BY `department_id`
HAVING `max_salary` NOT BETWEEN 30000 AND 70000
ORDER BY `department_id`;

#16
SELECT COUNT(`salary`) AS ' ' FROM `employees`
WHERE `manager_id` IS NULL;

#17
SELECT emp1.`department_id` , (
			SELECT DISTINCT emp2.`salary` FROM `employees` AS `emp2`
			WHERE emp1.`department_id` = emp2.`department_id`
			ORDER BY emp2.`salary` DESC
			LIMIT 2, 1) AS 'ths'
FROM `employees` AS `emp1`
WHERE 'ths' IS NOT NULL
GROUP BY emp1.`department_id`
HAVING `ths` IS NOT NULL
ORDER BY `department_id`;


#18
SELECT emp1.`first_name` , emp1.`last_name` , emp1.`department_id` FROM `employees` AS `emp1`
WHERE emp1.`salary` > (SELECT AVG(`salary`) AS `avg` FROM `employees` AS `emp2` 
										WHERE emp1.`department_id` = emp2.`department_id` 
                                        GROUP BY `department_id`)
ORDER BY `department_id` , `employee_id`
LIMIT 10;

#19
SELECT `department_id` , SUM(`salary`) AS `total_salary` FROM `employees`
GROUP BY `department_id`
ORDER BY `department_id`;