delete from Person p1 
where p1.Id > (
	select min(p3.Id)
	from (
		select p2.Id 
		from Person p2  
		where p1.Email = p2.Email
	) p3
)