# Write your MySQL query statement below
SELECT A.Name AS Employee FROM Employee A, Employee B WHERE A.ManagerId = B.Id AND A.Salary > B.Salary;