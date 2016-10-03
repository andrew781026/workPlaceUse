package tw.com.riko.andrew.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import tw.com.riko.andrew.VO.ManufacOrderInfo;
import tw.com.riko.andrew.VO.OrderInfo;
import tw.com.riko.andrew.util.DBUtil;
import tw.com.riko.andrew.util.DBUtil.DBNames;

public class OutfacInfoDaoProxy implements IOrderInfo{

	Connection con ;
	OutfacInfoDao dao ;
	
	public OutfacInfoDaoProxy() {
		this(DBNames.RIKO);
	}
	
	public OutfacInfoDaoProxy(DBNames db) {
		try {
			con = DBUtil.getConnection(db);
			dao = new OutfacInfoDao(con);
		} catch (SQLServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public List<OrderInfo> listAllOrderInfos() throws SQLException {
		return dao.listAllOrderInfos();
	}

	@Override
	public List<OrderInfo> listMonthlyOrderInfos(String monthDate) throws SQLException {
		return dao.listMonthlyOrderInfos(monthDate);
	}

}
