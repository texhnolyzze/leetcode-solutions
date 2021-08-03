select 
	case 
		when (
			select count(*) from Employee
		) <= 1 then null
		else (
			select e1.Salary from Employee e1 where e1.Salary = (
				select max(e2.Salary) 
				from Employee e2 
				where e2.Salary < (select max(e3.Salary) from Employee e3)
			) limit 1
		)
	end as SecondHighestSalary