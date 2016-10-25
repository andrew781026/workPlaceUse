package tw.com.riko.andrew.DAO.proxies;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import tw.com.riko.andrew.DAO.IPurchInfo;
import tw.com.riko.andrew.DAO.PurchInfoDao;
import tw.com.riko.andrew.VO.ManufacOrderInfo;
import tw.com.riko.andrew.VO.OrderInfo;
import tw.com.riko.andrew.VO.PurchInfo;
import tw.com.riko.andrew.util.DBUtil;
import tw.com.riko.andrew.util.DBUtil.DBNames;

public class PurchInfoDaoProxy implements IPurchInfo{

	Connection con ;
	PurchInfoDao dao ;
	
	public PurchInfoDaoProxy() {
		this(DBNames.RIKO,"");
	}
	
	public PurchInfoDaoProxy(DBNames db,String password) {
		try {
			
			con = ( ("**********").equalsIgnoreCase(password) )
					?DBUtil.getAdminConnection(db):DBUtil.getConnection(db);
			
			dao = new PurchInfoDao(con);
		} catch (SQLServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public List<? extends OrderInfo> listAllOrderInfos() throws SQLException {
		return dao.listAllOrderInfos();
	}

	@Override
	public List<? extends OrderInfo> listMonthlyOrderInfos(String monthDate) throws SQLException {
		return dao.listMonthlyOrderInfos(monthDate);
	}

	@Override
	public List<PurchInfo> listAllPurchOrderInfos() throws SQLException {
		return dao.listAllPurchOrderInfos();
	}

	@Override
	public List<PurchInfo> listMonthlyPurchOrderInfos(String monthDate) throws SQLException {
		return dao.listMonthlyPurchOrderInfos(monthDate);
	}

	@Override
	public List<PurchInfo> queryPurchOrderInfos(String productID, String orderID) throws SQLException {
		return dao.queryPurchOrderInfos(productID,orderID);
	}

	@Override
	public PurchInfo getPurchOrderInfo(String orderID, String serialNO) throws SQLException {
		return dao.getPurchOrderInfo(orderID,serialNO);
	}

	@Override
	public String updatePurchOrderInfo(String orderID, String serialNO, String deadDate) throws SQLException {
		return dao.updatePurchOrderInfo(orderID, serialNO, deadDate);
	}

}
