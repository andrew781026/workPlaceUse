select 
HDA001 as '���ʳ渹' ,
HDA003 as '�}����' ,
HDB010 as '�w����' ,
HDB003 as '�~��' ,
HDB006 as '�ƶq' ,
HDB020 as '�w���ƶq' ,
HDB004 as '�~�W' ,
HDB005 as '���' ,
DGA002 as '�t��'

from DCSHDB
left join DCSHDA on HDB001 = HDA001
left join TPADGA on HDA004 = DGA001

where HDB012 = 'N'
and HDB010 like ? ;