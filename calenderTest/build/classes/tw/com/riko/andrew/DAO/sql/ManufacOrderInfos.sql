select 
RAA001 as '製令單號' ,
RAA003 as '開單日期' ,
RAA007 as '預計完工日' ,
RAA015 as '主件品號' ,
RAA018 as '生產數量' ,
RAA019 as '已生產量' ,
DEA002 as '主件品名' ,
DEA003 as '庫存單位',
'自家工廠' as '廠商'

from SGMRAA
left join TPADEA on RAA015 = DEA001

where RAA020 = 'N'
and RAA007 like ? ;