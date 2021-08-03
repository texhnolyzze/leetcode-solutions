select id 
from Weather w1 
where exists (
	select 1 
	from Weather w2 
	where w2.recordDate = DATE_SUB(w1.recordDate, INTERVAL 1 DAY) 
	and w1.temperature > w2.temperature
)