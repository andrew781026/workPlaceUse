
UPDATE DCSHDB
SET HDB010 = ? -- 預交日期

where HDB001 like ?  -- 用採購單號查
and HDB002 like ? ; -- 序號

