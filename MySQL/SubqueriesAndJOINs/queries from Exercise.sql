#1
SELECT e.`employee_id` , e.`job_title` , e.`address_id` , a.`address_text` FROM `employees` AS e
JOIN `addresses` AS a
ON e.`address_id` = a.`address_id`
ORDER BY e.`address_id`
LIMIT 5;

#2
SELECT e.`first_name` , e.`last_name` , t.`name` AS `town` , a.`address_text` FROM `employees` AS e
JOIN `addresses` AS a
ON e.`address_id` = a.`address_id`
JOIN `towns` AS t
ON a.`town_id` = t.`town_id`
ORDER BY `first_name` , `last_name`
LIMIT 5;

#3
SELECT e.`employee_id` , e.`first_name` , e.`last_name` , d.`name` AS `department_name` FROM `employees` AS e
JOIN `departments` AS d
ON e.`department_id` = d.`department_id`
WHERE d.`name` = 'Sales'
ORDER BY e.`employee_id` DESC;

#4
SELECT e.`employee_id` , e.`first_name` , e.`salary` , d.`name` AS `department_name` FROM `employees` AS e
JOIN `departments` AS d
ON e.`department_id` = d.`department_id`
WHERE e.`salary` > 15000
ORDER BY e.`department_id` DESC , e.`salary` DESC
LIMIT 5;

#5
SELECT e.`employee_id` , e.`first_name` FROM `employees` AS e
LEFT JOIN `employees_projects` AS ep
ON e.`employee_id` = ep.`employee_id`
WHERE ep.`project_id` IS NULL
ORDER BY e.`employee_id` DESC
LIMIT 3; 

#6
SELECT e.`first_name` , e.`last_name` , e.`hire_date` , d.`name` AS `dept_name` FROM `employees` AS e
JOIN `departments` AS d
ON e.`department_id` = d.`department_id`
WHERE e.`hire_date` > '1999-01-01' AND d.`name` IN ('Sales' , 'Finance')
ORDER BY e.`hire_date`;

#7
SELECT ep.`employee_id` , e.`first_name` , p.`name` AS 'project_name' FROM `employees` AS e
JOIN `employees_projects` AS ep
ON ep.`employee_id` = e.`employee_id`
JOIN `projects` AS p
ON ep.`project_id` = p.`project_id`
WHERE p.`start_date` > '2002-08-13'
AND p.`end_date` IS NULL
ORDER BY e.`first_name` , p.`name`
LIMIT 5;

#8
SELECT e.`employee_id` , e.`first_name` ,
IF(YEAR(p.`start_date`) < 2005 , p.`name` , NULL ) AS 'project_name' FROM `employees` AS e
JOIN `employees_projects` AS ep
ON e.`employee_id` = ep.`employee_id`
JOIN `projects` AS p
ON ep.`project_id` = p.`project_id`
WHERE e.`employee_id` = 24
ORDER BY `project_name`;

#16
SELECT COUNT(*) FROM countries AS c
LEFT JOIN mountains_countries AS mc
ON c.country_code = mc.country_code
WHERE mc.mountain_id IS NULL;

#17
SELECT 
    c.`country_name`,
    p.`elevation` AS 'highest_peak_elevation',
    r.`length` AS 'longest_river_length'
FROM
    `countries` AS c
        JOIN
    `mountains_countries` AS mc ON mc.`country_code` = c.`country_code`
        JOIN
    `mountains` AS m ON mc.`mountain_id` = m.`id`
        JOIN
    `peaks` AS p ON m.`id` = p.`mountain_id`
        JOIN
    `countries_rivers` AS cr ON c.`country_code` = cr.`country_code`
        JOIN
    `rivers` AS r ON cr.`river_id` = r.`id`
GROUP BY c.`country_name`
ORDER BY p.`elevation` DESC , r.`length` DESC , c.`country_name`
LIMIT 5;



