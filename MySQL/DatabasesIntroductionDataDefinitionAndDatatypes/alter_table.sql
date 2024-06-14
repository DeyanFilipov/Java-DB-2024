ALTER TABLE `employees`
ADD COLUMN `middle_name` VARCHAR(45);

ALTER TABLE `products`
ADD CONSTRAINT `fk_category_id`
	FOREIGN KEY (`category_id`)
    REFERENCES `categories` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE;
    
ALTER TABLE `employees`
MODIFY COLUMN `middle_name` VARCHAR(100);