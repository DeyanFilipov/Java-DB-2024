#1
SELECT `title` FROM `books`
WHERE substring(`title` , 1 , 3) = 'The'
ORDER BY `id`;

#2
SELECT replace(`title` , 'The' , '***') AS 'Title' FROM `books`
WHERE substring(`title` , 1 , 3) = 'The' 
ORDER BY `id`;

#3
SELECT round(SUM(`cost`) , 2) FROM `books`;

#4
SELECT concat(`first_name` , ' ' , `last_name`) AS 'Full_Name' , 
	   TIMESTAMPDIFF(DAY , `born` , `died`) AS 'Days_Lived' 
FROM `authors`;

SELECT concat(`first_name` , ' ' , `last_name`) AS 'Full_Name' , 
	   TIMESTAMPDIFF(DAY , `born` , IFNULL(`died` , NOW())) AS 'Days_Lived' 
FROM `authors`;

SELECT concat(`first_name` , ' ' , `last_name`) AS 'Full_Name' , 
	date_format(`born` , '%D %b %Y') AS 'Born',
	date_format(`died` , '%D %b %Y') AS 'Died',
	   TIMESTAMPDIFF(DAY , `born` , IFNULL(`died` , NOW())) AS 'Days_Lived' 
FROM `authors`;

#5
SELECT `title` FROM `books`
WHERE `title` LIKE '%Harry Potter%';
