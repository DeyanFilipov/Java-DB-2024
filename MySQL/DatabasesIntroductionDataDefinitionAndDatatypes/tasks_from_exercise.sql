CREATE SCHEMA `minions` ;

#1
CREATE TABLE `minions`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `age` INT
);

CREATE TABLE `towns`(
	`town_id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL
);

#2
ALTER TABLE `towns` 
CHANGE COLUMN `town_id` `id` INT AUTO_INCREMENT;

ALTER TABLE `minions`
ADD COLUMN `town_id` INT;

ALTER TABLE `minions`
ADD CONSTRAINT fk_minions_towns
FOREIGN KEY (`town_id`)
REFERENCES `towns`(`id`);

#3
INSERT INTO `towns`
VALUES
(1 , 'Sofia'),
(2 , 'Plovdiv'),
(3 ,'Varna');

INSERT INTO `minions`
VALUES
(1 , 'Kevin', 22 , 1),
(2 , 'Bob', 15 , 3),
(3 , 'Steward', NULL , 2);

#4
TRUNCATE `minions`;

#5
DROP TABLE `minions`;
DROP TABLE `towns`;

#6
CREATE TABLE `people`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(200) NOT NULL,
    `picture` BLOB,
    `height` FLOAT ,
    `weight` FLOAT ,
    `gender` CHAR(1) NOT NULL,
    `birthdate` DATE NOT NULL,
    `biography` TEXT
);

INSERT INTO `people`
VALUES
(1 , 'Hristo Manov' , NULL , 1.87 , 96.96 , 'm' , '1991-08-26' , 'SAFASFAS'),
(2 , 'Georgi Manov' , NULL , 1.97 , 106.96 , 'm' , '1999-02-18' , 'SAFASFA'),
(3 , 'Vladislava Manova' , NULL , 1.57 , 56.96 , 'f' , '1996-09-11' , 'safasfa'),
(4 , 'Pesho Manov' , NULL , 1.67 , 66.96 , 'm' , '2001-02-01' , 'safsafasf'),
(5 , 'Iordan Manov' , NULL , 1.77 , 76.96 , 'm' , '1995-11-19' , NULL);

#7
CREATE TABLE `users`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `username` VARCHAR(30),
    `password` VARCHAR(26),
    `profile_picture` BLOB,
    `last_login_time` DATETIME,
    `is_deleted` BOOLEAN
);

INSERT INTO `users` (`username` , `password` , `profile_picture` , 
							`last_login_time` , `is_deleted`)
VALUES
('user1' , 'password1' , NULL , '1991-02-01' , TRUE),
('user2' , 'password2' , NULL , '2020-05-24 00:00:00' , FALSE),
('user3' , 'password3' , NULL , NOW() , TRUE),
('user4' , 'password4' , NULL , NOW() , FALSE),
('user5' , 'password5' , NULL , NOW() , TRUE);

TRUNCATE `users`;
#8
ALTER TABLE `users`
DROP PRIMARY KEY,
ADD CONSTRAINT pk_users
PRIMARY KEY (`id` , `username`);

#9
ALTER TABLE `users`
MODIFY `last_login_time` DATETIME DEFAULT NOW();

#10
ALTER TABLE `users` 
DROP PRIMARY KEY,
CHANGE COLUMN `username` `username` VARCHAR(30) NOT NULL UNIQUE ,
ADD CONSTRAINT pk_users
PRIMARY KEY (`id`, `username`);

#11
CREATE SCHEMA `movies`;

CREATE TABLE `directors`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `director_name` VARCHAR(100) NOT NULL,
    `notes` TEXT
);

CREATE TABLE `genres`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `genre_name` VARCHAR(100) NOT NULL,
    `notes` TEXT
);

CREATE TABLE `categories`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `category_name` VARCHAR(100) NOT NULL,
    `notes` TEXT
);

CREATE TABLE `movies`(
	`id` INT AUTO_INCREMENT PRIMARY KEY,
    `title` VARCHAR(100) NOT NULL,
    `director_id` INT,
    -- CONSTRAINT fk_movies_directors
--     FOREIGN KEY (`director_id`)
--     REFERENCES `directors`(`id`),
    `copyright_year` YEAR,
    `length` DOUBLE,
    `genre_id` INT,
    -- CONSTRAINT fk_movies_genres
--     FOREIGN KEY (`genre_id`)
--     REFERENCES `genres`(`id`),
    `category_id` INT,
    -- CONSTRAINT fk_movies_categories
--     FOREIGN KEY (`category_id`)
--     REFERENCES `categories`(`id`),
    `rating` DOUBLE,
    `notes` TEXT
);

INSERT INTO `directors`
VALUES
(1 , 'Ivan Ivanov' , 'asasddf'),
(2 , 'Hristo Ivanov' , 'assdf'),
(3 , 'Plamen Ivanov' , 'asddf'),
(4 , 'Georgi Ivanov' , 'sdada'),
(5 , 'Pesho Ivanov' , 'aasdasdf');

INSERT INTO `genres`
VALUES
(1 , 'acion' , 'asfasfas'),
(2 , 'comedy' , 'asfasfas'),
(3 , 'drama' , 'asfasfas'),
(4 , 'fantazy' , 'asfasfas'),
(5 , 'trilur' , 'asfasfas');

INSERT INTO `categories`
VALUES
(1 , 'brbrbrbrbr' , 'ASDAFSSA'),
(2 , 'vrvrvrvrv' , 'ASDAFSSA'),
(3 , 'vrvrvrvr' , 'ASDAFSSA'),
(4 , 'bttbtbtbt' , 'ASDAFSSA'),
(5 , 'btbtbtbt' , 'ASDAFSSA');

INSERT INTO `movies`
VALUES
(1 , 'Ted' , 5 , '2017' , 101.20 , 3 , 2 , 5.78 , 'asfasfafaf'),
(2 , 'Ted2' , 4 , '2016' , 111.20 , 2 , 3 , 6.78 , 'asfaasdASDAsfafaf'),
(3 , 'Ted3' , 3 , '2015' , 121.20 , 1 , 4 , 7.78 , 'asfaASDAsfafaf'),
(4 , 'Ted4' , 2 , '2014' , 131.20 , 5 , 5 , 8.78 , 'ASDasfasfafaf'),
(5 , 'Ted5' , 1 , '2013' , 141.20 , 4 , 1 , 9.78 , 'asfasfafafASD');

#12
CREATE SCHEMA `car_rental`;

CREATE TABLE `categories`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `category` VARCHAR(50) NOT NULL,
    `daily_rate` DOUBLE ,
    `weekly_rate` DOUBLE ,
    `monthly_rate` DOUBLE ,
    `weekend_rate` DOUBLE
);

CREATE TABLE `cars`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `plate_number` VARCHAR(30) NOT NULL UNIQUE,
    `make` VARCHAR(50) NOT NULL, 
    `model` VARCHAR(50) NOT NULL,
    `car_year` YEAR NOT NULL,
    `category_id` INT NOT NULL,
    CONSTRAINT fk_cars_categories
    FOREIGN KEY (`category_id`)
    REFERENCES `categories`(`id`),
    `doors` INT NOT NULL,
    `picture` BLOB,
    `car_condition` TEXT,
    `available` VARCHAR(50) NOT NULL
);

CREATE TABLE `employees`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(20) NOT NULL,
    `last_name` VARCHAR(20) NOT NULL,
    `title` VARCHAR(50) NOT NULL,
    `notes` TEXT
);

CREATE TABLE `customers`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `driver_licence_number` VARCHAR(50) NOT NULL UNIQUE,
    `full_name` VARCHAR (50) NOT NULL,
    `address` VARCHAR(100) NOT NULL,
    `city` VARCHAR(50) NOT NULL,
    `zip_code` INT NOT NULL,
    `notes` TEXT
);

CREATE TABLE `rental_orders`(
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `employee_id` INT NOT NULL,
    CONSTRAINT fk_rental_orders_employees
    FOREIGN KEY (`employee_id`)
    REFERENCES `employees`(`id`),
    `customer_id` INT NOT NULL,
    CONSTRAINT fk_rental_orders_customers
    FOREIGN KEY (`customer_id`)
    REFERENCES `customers`(`id`),
    `car_id` INT NOT NULL,
    CONSTRAINT fk_rental_orders_cars
    FOREIGN KEY (`car_id`)
    REFERENCES `cars` (`id`),
    `car_condition` TEXT,
    `tank_level` DOUBLE NOT NULL,
    `kilometrage_start` INT NOT NULL,
	`kilometrage_end` INT NOT NULL,
	`total_kilometrage` INT NOT NULL,
	`start_date` DATE NOT NULL,
	`end_date` DATE NOT NULL,
	`total_days` INT NOT NULL,
	`rate_applied` VARCHAR(50) NOT NULL,
	`tax_rate` DOUBLE NOT NULL,
	`order_status` VARCHAR(50),
	`notes` TEXT
);

INSERT INTO `categories`(`category`, `daily_rate`, `weekly_rate`, `monthly_rate`, `weekend_rate`)
VALUES
('category', 10.5, 10.5, 10.5, 10.5),
('category', 10.5, 10.5, 10.5, 10.5),
('category', 10.5, 10.5, 10.5, 10.5);

INSERT INTO `cars`(`plate_number`, `make`, `model`, `car_year`, `category_id`, `doors`, `picture`, `car_condition`, `available`)
VALUES 
('CO 1234 CC', 'make', 'model', '2018', 1, 3, 'picture', 'car_condition', 'Yes'),
('C 1234 CC', 'make', 'model', '2018', 1, 3, 'picture', 'car_condition', 'Yes'),
('CA 1234 CC', 'make', 'model', '2018', 1, 3, 'picture', 'car_condition', 'Yes');

INSERT INTO `employees`(`first_name`, `last_name`, `title`, `notes`)
VALUES
('first_name', 'last_name', 'title', 'notes'),
('first_name', 'last_name', 'title', 'notes'),
('first_name', 'last_name', 'title', 'notes');

INSERT INTO `customers`(`driver_licence_number`, `full_name`, `address`, `city`, `zip_code`, `notes`)
VALUES 
('1111111111111112', 'full_name', 'address', 'city', 1528, 'notes'),
('1111111111111113', 'full_name', 'address', 'city', 1528, 'notes'),
('1111111111111114', 'full_name', 'address', 'city', 1528, 'notes');

INSERT INTO `rental_orders` (`employee_id`, `customer_id`, `car_id`, `car_condition`, `tank_level`, `kilometrage_start`, `kilometrage_end`,
							`total_kilometrage`, `start_date`, `end_date`, `total_days`, `rate_applied`, `tax_rate`, `order_status`, `notes`)
VALUES 
(2, 1, 3, 'car_condition', 44.4, 100000, 100500, 100, '2018-01-02', '2018-01-18', 3, 'rate_applied', 20.5, 'order_status', 'notes'),
(2, 2, 3, 'car_condition', 44.4, 100000, 100500, 100, '2018-01-02', '2018-01-18', 3, 'rate_applied', 20.5, 'order_status', 'notes'),
(2, 3, 3, 'car_condition', 44.4, 100000, 100500, 100, '2018-01-02', '2018-01-18', 3, 'rate_applied', 20.5, 'order_status', 'notes');

#14
CREATE SCHEMA `soft_uni`;

CREATE TABLE `towns` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR (50)
);

CREATE TABLE `addresses`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `address_text` VARCHAR (100) NOT NULL,
    `town_id` INT NOT NULL,
    CONSTRAINT fk_addresses_towns
    FOREIGN KEY (`town_id`)
    REFERENCES `towns`(`id`)
);

CREATE TABLE `departments`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR (50) NOT NULL
);

CREATE TABLE `employees`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR (20) NOT NULL,
    `middle_name` VARCHAR (20) NOT NULL,
    `last_name` VARCHAR(20) NOT NULL,
    `job_title` VARCHAR(30),
    `department_id` INT NOT NULL,
    CONSTRAINT fk_employees_departments
    FOREIGN KEY (`department_id`)
    REFERENCES `departments`(`id`),
    `hire_date` DATE,
    `salary` DECIMAL (10,2),
    `address_id` INT,
    CONSTRAINT fk_employees_addresses
    FOREIGN KEY (`address_id`)
    REFERENCES `addresses`(`id`)
);

INSERT INTO `towns` (`name`)
VALUES
('Sofia'), ('Plovdiv'), ('Varna'), ('Burgas');

INSERT INTO `departments` (`name`)
VALUES
('Engineering'), ('Sales'), ('Marketing'), ('Software Development'), ('Quality Assurance');

INSERT INTO `employees` (`first_name` , `middle_name` , `last_name` , `job_title` ,
							`department_id` , `hire_date` , `salary`)
VALUES
('Ivan' , 'Ivanov' , 'Ivanov' , '.NET Developer' , 4 , '2013-02-01' , 3500.00),
('Petar' , 'Petrov' , 'Petrov' , 'Senior Engineer' , 1 , '2004-03-02' , 4000.00),
('Maria' , 'Petrova' , 'Ivanova' , 'Intern' , 5 , '2016-08-28' , 525.25),
('Georgi' , 'Terziev' , 'Ivanov' , 'CEO' , 2 , '2007-12-09' , 3000.00),
('Peter' , 'Pan' , 'Pan' , 'Intern' , 3 , '2016-08-28' , 599.88);

#15
SELECT * FROM `towns`;
SELECT * FROM `departments`;
SELECT * FROM `employees`;

#16
SELECT * FROM `towns`
ORDER BY `name`;

SELECT * FROM `departments`
ORDER BY `name`;

SELECT * FROM `employees`
ORDER BY `salary` DESC;

#17
SELECT `name` FROM `towns`
ORDER BY `name`;

SELECT `name` FROM `departments`
ORDER BY `name`;

SELECT `first_name` , `last_name` , `job_title` , `salary` FROM `employees`
ORDER BY `salary` DESC;

#18
SET SQL_SAFE_UPDATES = 0;
UPDATE `employees`
SET `salary` = `salary` * 1.10;

SELECT `salary` FROM `employees`;

#19
TRUNCATE `occupancies`;
#20
