#10
SELECT `country_name` , `iso_code` FROM `countries`
WHERE `country_name` LIKE '%A%A%A%'
ORDER BY `iso_code`;

#11
SELECT p.`peak_name` , r.`river_name` , lower(CONCAT(`peak_name` , SUBSTRING(`river_name` , 2))) AS 'mix'
FROM `peaks` AS p, `rivers` AS r
WHERE RIGHT(`peak_name` , 1) = LEFT(`river_name` , 1)
ORDER BY `mix`;


