select 
RAA001 as '�s�O�渹' ,
RAA003 as '�}����' ,
RAA007 as '�w�p���u��' ,
RAA015 as '�D��~��' ,
RAA018 as '�Ͳ��ƶq' ,
RAA019 as '�w�Ͳ��q' ,
DEA002 as '�D��~�W' ,
DEA003 as '�w�s���',
'�ۮa�u�t' as '�t��'

from SGMRAA
left join TPADEA on RAA015 = DEA001

where RAA020 = 'N'
and RAA007 like ? ;