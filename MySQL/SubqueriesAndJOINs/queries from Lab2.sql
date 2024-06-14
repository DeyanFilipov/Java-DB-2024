#1
SELECT 
    e.`employee_id`,
    CONCAT(e.`first_name`, ' ', e.`last_name`) AS `full_name`,
    d.`department_id`,
    d.`name` AS `department_name`
FROM
    `employees` AS e
        JOIN `departments` AS d 
        ON e.`employee_id` = d.`manager_id`
ORDER BY e.`employee_id`
LIMIT 5;

#2
SELECT 
    a.`town_id`, t.`name` AS `town_name`, a.`address_text`
FROM
    `addresses` AS a
        JOIN
    `towns` AS t ON a.`town_id` = t.`town_id`
WHERE
    a.`town_id` IN (9 , 15, 32)
ORDER BY a.`town_id` , a.`address_id`;

#3
SELECT e.`employee_id` , e.`first_name` , e.`last_name` , e.`department_id` , e.`salary` FROM `employees` AS e
WHERE e.`manager_id` IS NULL;

#4
SELECT COUNT(*) AS `count` FROM `employees`
WHERE `salary` > (SELECT AVG(`salary`) FROM `employees`);