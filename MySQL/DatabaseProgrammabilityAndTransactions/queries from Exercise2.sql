#1
DELIMITER //
CREATE PROCEDURE `usp_get_employees_salary_above_35000`()
BEGIN
	SELECT `first_name` , `last_name` FROM `employees`
    WHERE `salary` > 35000
    ORDER BY `first_name` , `last_name` , `employee_id`;
END//
DELIMITER ;

CALL `usp_get_employees_salary_above_35000`;

#2
DELIMITER //
CREATE PROCEDURE `usp_get_employees_salary_above`(`min_salary` DECIMAL(19,4))
BEGIN
	SELECT `first_name`, `last_name` FROM `employees`
    WHERE `salary` >= `min_salary`
    ORDER BY `first_name` , `last_name` , `employee_id`;
END//
DELIMITER ;

CALL `usp_get_employees_salary_above`(45000);

#3
DELIMITER //
CREATE PROCEDURE `usp_get_towns_starting_with`(`start_with` VARCHAR(20))
BEGIN
	SELECT `name` AS `town_name` FROM `towns`
    WHERE `name` LIKE CONCAT(`start_with` , '%')
    ORDER BY `name`;
END//
DELIMITER ;

CALL `usp_get_towns_starting_with`('b');

#4
DELIMITER //
CREATE PROCEDURE `usp_get_employees_from_town`(`from_town` VARCHAR(50))
BEGIN
	SELECT e.`first_name` , e.`last_name` FROM `employees` AS e
    JOIN `addresses` AS a USING (`address_id`)
    JOIN `towns` AS t ON a.`town_id` = t.`town_id`
    WHERE t.`name` = `from_town`
    ORDER BY e.`first_name` , e.`last_name` , e.`employee_id`;
END//
DELIMITER ;

CALL `usp_get_employees_from_town`('Sofia');

#5
DELIMITER //
CREATE FUNCTION `ufn_get_salary_level`(`emp_salary` DECIMAL)
RETURNS VARCHAR(10)
DETERMINISTIC
BEGIN
	RETURN CASE
		WHEN `emp_salary` < 30000 THEN 'Low'
		WHEN `emp_salary` BETWEEN 30000 AND 50000 THEN 'Average'
		WHEN `emp_salary` > 50000 THEN 'High'
	END;
END//
DELIMITER ;

SELECT `ufn_get_salary_level`(13000);
SELECT `ufn_get_salary_level`(33000);
SELECT `ufn_get_salary_level`(53000);

#6
DELIMITER //
CREATE PROCEDURE `usp_get_employees_by_salary_level`(`param` VARCHAR(10))
BEGIN
	SELECT `first_name`, `last_name` FROM `employees`
    WHERE `ufn_get_salary_level`(`salary`) = `param`
    ORDER BY `first_name` DESC , `last_name` DESC;
END//
DELIMITER ;

CALL `usp_get_employees_by_salary_level`('High');

#7
DELIMITER //
CREATE FUNCTION `ufn_is_word_comprised`(`set_of_letters` VARCHAR(50), `word` VARCHAR(50))
RETURNS INT
DETERMINISTIC
BEGIN
	RETURN (SELECT `word` REGEXP(CONCAT('^[' , `set_of_letters` , ']+$')));
END//
DELIMITER ;

SELECT `ufn_is_word_comprised`('oistmiahf' , 'Sofia');
SELECT `ufn_is_word_comprised`('oist' , 'Sofia');

#8
DELIMITER //
CREATE PROCEDURE `usp_get_holders_full_name`()
BEGIN
	SELECT concat(`first_name` , ' ' , `last_name`) AS `full_name` FROM `account_holders`
    ORDER BY `full_name`;
END//
DELIMITER ;

CALL `usp_get_holders_full_name`;

#9
DELIMITER //
CREATE PROCEDURE `usp_get_holders_with_balance_higher_than`(`number` DECIMAL)
BEGIN
	SELECT ah.`first_name` , ah.`last_name` FROM `account_holders` AS ah
    JOIN `accounts` AS a ON ah.`id` = a.`account_holder_id`
    GROUP BY a.`account_holder_id`
    HAVING SUM(a.`balance`) > number
    ORDER BY ah.`id`;
END//
DELIMITER ;

CALL `usp_get_holders_with_balance_higher_than`(7000);

#10
DELIMITER //
CREATE FUNCTION `ufn_calculate_future_value`(`sum` DECIMAL(19,4) , `yearly_interest_rate` DOUBLE , `years` INT)
RETURNS DECIMAL(19,4)
DETERMINISTIC
BEGIN
	RETURN `sum` * (POW((1 + `yearly_interest_rate`), `years`));
END//
DELIMITER ;

SELECT `ufn_calculate_future_value` (1000 , 0.5 , 5);

#11
DELIMITER //
CREATE PROCEDURE `usp_calculate_future_value_for_account`(`acc_id` INT , `interest_rate` DECIMAL(10,4))
BEGIN
	SELECT a.`id` AS `accounts_id` , ah.`first_name` , ah.`last_name` , a.`balance` AS `current_balance` , 
		`ufn_calculate_future_value`(a.`balance` , `interest_rate` , 5) AS `balance_in_5_years` FROM `accounts` AS a
	JOIN `account_holders` AS ah
    ON a.`account_holder_id` = ah.`id`
    WHERE `acc_id` = a.`id`;
END//
DELIMITER ;

CALL `usp_calculate_future_value_for_account`(1 , 0.1);

#12
DELIMITER //
CREATE PROCEDURE `usp_deposit_money`(`acc_id` INT , `money_amount` DECIMAL(19,4))
BEGIN
	START TRANSACTION;
    IF (`money_amount` <= 0) THEN ROLLBACK;
    ELSE
		UPDATE `accounts`
        SET `balance` = `balance` + `money_amount`
        WHERE `id` = `acc_id`;
        END IF;
END//
DELIMITER ;

CALL `usp_deposit_money`(1 , 1000);

#13
DELIMITER //
CREATE PROCEDURE `usp_withdraw_money`(`acc_id` INT , `money_amount` DECIMAL(19,4))
BEGIN
	START TRANSACTION;
    IF (`money_amount` <= 0 OR (SELECT `balance` FROM `accounts` WHERE `id` = `acc_id`) < `money_amount`) THEN ROLLBACK;
    ELSE
		UPDATE `accounts`
        SET `balance` = `balance` - `money_amount`
        WHERE `id` = `acc_id`;
        END IF;
END //
DELIMITER ;

CALL `usp_withdraw_money`(1 , 1000);

#14
DELIMITER //
CREATE PROCEDURE `usp_transfer_money`(`from_acc_id` INT , `to_acc_id` INT , `amount` DECIMAL(19,4))
BEGIN
	START TRANSACTION;
    IF (`amount` < 0 
		OR (SELECT `balance` FROM `accounts` WHERE `from_acc_id` = `id`) < `amount`
        OR `from_acc_id` NOT BETWEEN 1 AND (SELECT COUNT(*) FROM `accounts`)
        OR `to_acc_id` NOT BETWEEN 1 AND (SELECT COUNT(*) FROM `accounts`))
		THEN ROLLBACK;
	ELSE
		UPDATE `accounts`
        SET `balance` = `balance` - `amount`
        WHERE `id` = `from_acc_id`;
        UPDATE `accounts`
        SET `balance` = `balance` + `amount`
        WHERE `id` = `to_acc_id`;
	END IF;
END//
DELIMITER ;

CALL `usp_transfer_money` (1 , 2 , 10);
CALL `usp_transfer_money` (-1 , 2 , 10);

