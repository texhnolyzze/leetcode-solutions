select 
	id as id, 
	case 
		when 
			id % 2 = 0 
			then (select student from seat s2 where s2.id = s1.id - 1)
		when 
			exists(
				select 1 from seat s2 where s2.id = s1.id + 1
			) then (
				select student from seat s2 where s2.id = s1.id + 1
			)
		else student
	end as student
from seat s1