select 
REA001 as '�U�u�渹' ,
REA003 as '�}����' ,
REA007 as '�w�p���u��' ,
REA015 as '�D��~��' ,
REA018 as '�U�u�ƶq' ,
REA019 as '�w���u�q' ,
DEA002 as '�D��~�W' ,
DEA003 as '�w�s���'

from SGMREA
left join TPADEA on REA015 = DEA001

where REA020 = 'N'
and REA007 like ? ;