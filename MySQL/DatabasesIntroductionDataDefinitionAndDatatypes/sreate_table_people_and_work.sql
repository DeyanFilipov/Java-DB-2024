CREATE TABLE `people`(
	`id` INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
    `name` VARCHAR(200) NOT NULL,
    `picture` BLOB,
    `height` DOUBLE(10,2),
    `weight` DOUBLE (10,2),
    `gender` CHAR(1) NOT NULL,
    `birthdate` DATE NOT NULL,
    `biography` TEXT
    );
    
INSERT INTO `people` (`name` , `height` , `weight` , `gender` , `birthdate`)
VALUES
('Hristo Manov' , 1.90 , 97.9 , 'm' , '1991-08-26'),
('Vladislava Manova' , 1.75 , 57.9 , 'f' , '1996-09-11'),
('Hrisiq Manova' , 0.68 , 8.0 , 'f' , '2020-11-30'),
('Ivan Ivanov' , 2.90 , 107.9 , 'm' , '1981-05-22'),
('Krum Manov' , 1.70 , 87.90 , 'm' , '2991-01-16');

#7    
CREATE TABLE `users` (
	`id` INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
    `username` VARCHAR(30) UNIQUE,
    `password` VARCHAR(26),
    `profile_picture` BLOB,
    `last_login_time` DATETIME,
    `is_deleted` BOOLEAN DEFAULT FALSE
);

INSERT INTO `users` (`username` , `password` , `last_login_time`)
VALUES
('henristo' , 'asdf' , '2001-08-26 03:30:00'),
('vbrbr' , 'asdga' , '2002-08-26 03:30:00'),
('asdasf' , 'asfafas' , '2001-09-26 03:30:00'),
('asfasfas' , 'safasfasas' , '2001-08-26 04:30:00'),
('jtyjytt' , 'fdhdfhdhfd' , '2001-08-26 03:50:00');

#8
ALTER TABLE `users`
DROP PRIMARY KEY,
ADD CONSTRAINT `pk_users` PRIMARY KEY (`id` , `username`);

#9
ALTER TABLE `users` 
CHANGE COLUMN `last_login_time` `last_login_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ;

INSERT INTO `users` (`username` , `password`)
VALUES
('fdsgsdgs' , 'sgdsgsd');

#10
ALTER TABLE `users` 
DROP PRIMARY KEY,
ADD CONSTRAINT pk_id
PRIMARY KEY (`id`),
ADD CONSTRAINT uq_username
UNIQUE(username);



