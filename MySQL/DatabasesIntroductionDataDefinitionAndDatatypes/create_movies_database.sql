CREATE SCHEMA `movies`;

CREATE TABLE `directors`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `director_name` VARCHAR(100) NOT NULL,
    `notes` TEXT
);
    
CREATE TABLE `genres` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `genre_name` VARCHAR(50) NOT NULL,
    `notes` TEXT
);
    
CREATE TABLE `categories` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `category_name` VARCHAR(50) NOT NULL,
    `notes` TEXT
);    

CREATE TABLE `movies` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `title` VARCHAR(100) NOT NULL,
    `director_id` INT NOT NULL,
    CONSTRAINT fk_movies_directors
    FOREIGN KEY (`director_id`)
    REFERENCES `directors`(`id`),
    `copyright_year` DATE NOT NULL,
    `length` DOUBLE (5,2),
    `genre_id` INT,
    CONSTRAINT fk_movies_genres
    FOREIGN KEY (`genre_id`)
    REFERENCES `genres`(`id`),
    `category_id` INT,
    CONSTRAINT fk_movies_categories
    FOREIGN KEY (`category_id`)
    REFERENCES `categories`(`id`),
    `rating` DOUBLE (4,2),
    `notes` TEXT
);

INSERT INTO `directors`
VALUES
(1 , 'Georgi Manov' , 'kshjgkshgskhgskdhgk'),
(2 , 'Hristo Manov' , 'ahsfkahs'),
(3 , 'Ivan Manov' , 'asfhahgs'),
(4 , 'Pesho Manov' , 'i0asjoijcas'),
(5 , 'Iordan Manov' , 'saoijfgoiasgo');

INSERT INTO `genres`
VALUES
(1 , 'action' , 'djjener'),
(2 , 'comedy' , 'erheherh'),
(3 , 'anime' , 'asfhareherherhhgs'),
(4 , 'horer' , 'i0asjearhraerhijcas'),
(5 , 'criminale' , 'hraerhaee');

INSERT INTO `categories`
VALUES
(1 , 'dokumentalen' , 'djjener'),
(2 , 'istoricheski' , 'erheherh'),
(3 , 'biografichen' , 'asfhareherherhhgs'),
(4 , 'fantastika' , 'i0asjearhraerhijcas'),
(5 , 'izvunzemen' , 'hraerhaee');

INSERT INTO `movies`
VALUES
(1 , 'the lord of the rings' , 1 , '2001-04-12' , 240.0 , 1 , 1 , 10.0 , 'asfasfasfasf'),
(2 , 'the lord of the rings2' , 2 , '2003-04-12' , 240.0 , 2 , 2 , 9.0 , 'asfasfasfasf'),
(3 , 'the lord of the rings3' , 3 , '2005-04-12' , 240.0 , 3 , 3 , 9.50 , 'asfasfasfasf'),
(4 , 'the lord of the rings4' , 4 , '2007-04-12' , 240.0 , 4 , 4 , 8.0 , 'asfasfasfasf'),
(5 , 'the lord of the rings5' , 5 , '2009-04-12' , 240.0 , 5 , 5 , 7.0 , 'asfasfasfasf');