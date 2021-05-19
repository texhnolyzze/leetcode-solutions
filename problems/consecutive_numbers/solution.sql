select distinct(l1.Num) as ConsecutiveNums
from logs l1
join logs l2 on l2.Id = l1.Id - 1
join logs l3 on l3.Id = l1.Id + 1
where l1.Num = l2.Num and l2.Num = l3.Num