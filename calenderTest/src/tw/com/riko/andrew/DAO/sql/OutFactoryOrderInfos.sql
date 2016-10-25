select 
REA001 as '託工單號' ,
REA003 as '開單日期' ,
REA007 as '預計完工日' ,
REA015 as '主件品號' ,
REA018 as '託工數量' ,
REA019 as '已完工量' ,
DEA002 as '主件品名' ,
DEA003 as '庫存單位' ,
DGA002 as '廠商'

from SGMREA
left join TPADEA on REA015 = DEA001
left join TPADGA on REA005 = DGA001

where REA020 = 'N'
and REA007 like ? ;