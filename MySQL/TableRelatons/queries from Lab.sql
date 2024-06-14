#1
CREATE TABLE `mountains` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL
);

CREATE TABLE `peaks` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `mountain_id` INT,
    CONSTRAINT fk_peaks_mountains
    FOREIGN KEY (`mountain_id`)
    REFERENCES `mountains` (`id`)
);

#2
SELECT `driver_id` , `vehicle_type` , CONCAT(c.`first_name` , ' ' , c.`last_name`) AS `driver_name` FROM `vehicles` AS v
JOIN `campers` AS c
ON c.`id` = v.`driver_id`;

#3
SELECT r.`starting_point` AS `route_starting_point` , r.`end_point` AS `route_ending_point` , `leader_id` ,
	CONCAT(c.`first_name` , ' ' , c.`last_name`) AS `leader_name` FROM `routes` AS r
JOIN `campers` AS c
ON c.`id` = r.`leader_id`;

#4
DROP TABLE `peaks`;
DROP TABLE `mountains`;

CREATE TABLE `mountains` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL
);

CREATE TABLE `peaks` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `mountain_id` INT,
    CONSTRAINT fk_peaks_mountains
    FOREIGN KEY (`mountain_id`)
    REFERENCES `mountains` (`id`)
    ON DELETE CASCADE
);