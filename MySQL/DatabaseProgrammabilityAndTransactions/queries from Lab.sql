#1
DELIMITER //
CREATE FUNCTION ufn_count_employees_by_town (town_name VARCHAR (50)) RETURNS INT DETERMINISTIC
BEGIN
DECLARE emp_count INT;
SET emp_count := (SELECT COUNT(*) FROM `employees`
	JOIN addresses USING (address_id)
    JOIN towns t USING (town_id)
    WHERE t.`name` = town_name);
RETURN emp_count;
END//
DELIMITER ;

SELECT ufn_count_employees_by_town('Sofia');

#2
DROP PROCEDURE IF EXISTS `usp_raise_salary`()
DELIMITER $$
CREATE PROCEDURE `usp_raise_salaries`( IN department_name VARCHAR(50))
BEGIN
	UPDATE `employees` JOIN `departments` AS d USING(`department_id`)
    SET `salary` = `salary` * 1.05
    WHERE d.name = department_name;
END $$
DELIMITER ;

CALL usp_raise_salaries('Finance');

#3
DROP PROCEDURE IF EXISTS `procedure usp_raise_salary_by_id`()
DELIMITER //
CREATE PROCEDURE usp_raise_salary_by_id(emp_id INT) 
BEGIN
	START TRANSACTION;
    IF((SELECT COUNT(*) FROM employees WHERE employee_id = emp_id) = 0)
    THEN ROLLBACK;
    ELSE
		UPDATE employees
        SET salary = salary * 1.05
        WHERE employee_id = emp_id;
        COMMIT;
	END IF;
END//
DELIMITER ;

#4
