#1
DELIMITER $$
CREATE FUNCTION `ufn_count_employees_by_town` (town_name_param VARCHAR(25))
RETURNS INT
DETERMINISTIC
BEGIN
	RETURN (
		SELECT COUNT(*) FROM `employees` AS e
		JOIN `addresses` AS a ON a.`address_id` = e.`address_id`
		JOIN `towns` AS t ON t.`town_id` = a.`town_id`
        WHERE t.`name` = town_name_param);
END$$
DELIMITER ;

SELECT `ufn_count_employees_by_town`('Sofia') AS count;

#2
DELIMITER //
CREATE PROCEDURE `usp_raise_salaries`(department_name VARCHAR(20))
BEGIN
	UPDATE `employees` AS e
    JOIN `departments` AS d
    ON e.`department_id` = d.`department_id`
    SET `salary` = `salary` * 1.05
    WHERE d.`name` = department_name;
END//
DELIMITER ;

CALL `usp_raise_salaries`('Finance');

SELECT `first_name` , `salary` FROM employees
WHERE `department_id` = 10
ORDER BY `first_name` , `salary`;

#3
DELIMITER //
CREATE PROCEDURE `usp_raise_salary_by_id`(emp_id INT)
BEGIN
	UPDATE `employees`
    SET `salary` = `salary` * 1.05
    WHERE `employee_id` = emp_id;
END//
DELIMITER ;

CALL usp_raise_salary_by_id(517);

SELECT * FROM `employees`
WHERE `employee_id` = 17;

#4
CREATE TABLE `deleted_employees` (
	`employee_id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(50),
    `last_name` VARCHAR(50),
    `middle_name` VARCHAR(50),
    `job_title` VARCHAR(50),
    `department_id` INT,
    `salary` DECIMAL(19,4)
);

DROP TRIGGER IF EXISTS `employees_AFTER_DELETE`;

DELIMITER $$
CREATE TRIGGER `employees_AFTER_DELETE` AFTER DELETE ON `employees` FOR EACH ROW
BEGIN
	INSERT INTO `deleted_employees` (`first_name` , `last_name` , `middle_name` , 
		`job_title` , `department_id` , `salary`)
	VALUES
    (OLD.`first_name` , OLD.`last_name` , OLD.`middle_name` , 
		OLD.`job_title` , OLD.`department_id` , OLD.`salary`);
END$$
DELIMITER ;

DELETE FROM `employees` WHERE `employee_id` = 3;