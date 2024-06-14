CREATE SCHEMA `minions` ;
USE `minions`;

CREATE TABLE `minions` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `age` INT NOT	NULL
  );
  
CREATE TABLE `towns` (
  `town_id` INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL
);

ALTER TABLE `minions`.`towns` 
CHANGE COLUMN `town_id` `id` INT NOT NULL AUTO_INCREMENT ;

ALTER TABLE `minions`
ADD COLUMN `town_id` INT,
ADD CONSTRAINT fk_minions_towns
FOREIGN KEY (`town_id`)
REFERENCES `towns`(`id`);

INSERT INTO `towns` (`id` , `name`)
VALUES
(1 , 'Sofia'), (2 , 'Plovdiv') , (3 , 'Varna');

INSERT INTO `minions` (`id` , `name` , `age` , `town_id`)
VALUES
(1 , 'Kevin' , 22 , 1),
(2 , 'Bob' , 15 , 3),
(3 , 'Steward' , NULL , 2);

TRUNCATE `minions`;

DROP TABLE `minions`;
DROP TABLE `towns`;
