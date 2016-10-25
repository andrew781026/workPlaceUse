select 

DGA001 as '廠商代號' , 
DGA002 as '廠商簡稱' ,  
DGA003 as '廠商全名' ,  
DGA009 as '聯絡人' , 
DGA011 as '連絡電話' , 
DGA053 as '聯絡人手機'

from TPADGA

where DGA001 like ? 
 