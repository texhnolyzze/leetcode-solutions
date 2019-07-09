# Write your MySQL query statement below
SELECT * FROM cinema WHERE LOWER(description) NOT LIKE '%boring%' AND id % 2 = 1 ORDER BY rating DESC;