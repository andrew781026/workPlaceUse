package tw.com.riko.andrew.DAO.proxies;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import tw.com.riko.andrew.DAO.IManufacInfo;
import tw.com.riko.andrew.DAO.ManufacInfoDao;
import tw.com.riko.andrew.VO.ManufacOrderInfo;
import tw.com.riko.andrew.VO.OrderInfo;
import tw.com.riko.andrew.util.DBUtil;
import tw.com.riko.andrew.util.DBUtil.DBNames;

public class ManufacInfoDaoProxy implements IManufacInfo{

	Connection con ;
	ManufacInfoDao dao ;
	
	public ManufacInfoDaoProxy() {
		this(DBNames.RIKO);
	}
	
	public ManufacInfoDaoProxy(DBNames db) {
		try {
			con = DBUtil.getConnection(db);
			dao = new ManufacInfoDao(con);
		} catch (SQLServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public List<ManufacOrderInfo> listAllManufacOrderInfos() throws SQLException {
		return dao.listAllManufacOrderInfos();
	}

	@Override
	public List<ManufacOrderInfo> listMonthlyManufacOrderInfos(String monthDate) throws SQLException {
		return dao.listMonthlyManufacOrderInfos(monthDate);
	}

	@Override
	public List<? extends OrderInfo> listAllOrderInfos() throws SQLException {
		return dao.listAllOrderInfos();
	}

	@Override
	public List<? extends OrderInfo> listMonthlyOrderInfos(String monthDate) throws SQLException {
		return dao.listMonthlyOrderInfos(monthDate);
	}

}
