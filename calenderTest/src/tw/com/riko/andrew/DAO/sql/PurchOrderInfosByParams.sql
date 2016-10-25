select 
HDA001 as '採購單號' ,
HDA003 as '開單日期' ,
HDB010 as '預交日期' ,
HDB003 as '品號' ,
HDB006 as '數量' ,
HDB020 as '已收料量' ,
HDB004 as '品名' ,
HDB005 as '單位' ,
DGA002 as '廠商' ,
HDB002 as '序號' 

from DCSHDB
left join DCSHDA on HDB001 = HDA001
left join TPADGA on HDA004 = DGA001

where HDB012 = 'N'
and HDB003 like ?  -- 用品號查
and HDA001 like ? ; -- 用採購單號查