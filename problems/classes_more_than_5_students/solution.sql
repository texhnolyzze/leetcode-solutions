# Write your MySQL query statement below
SELECT class FROM (SELECT COUNT(DISTINCT student) AS cnt, class FROM courses GROUP BY class) AS grouped_classes WHERE grouped_classes.cnt >= 5; 