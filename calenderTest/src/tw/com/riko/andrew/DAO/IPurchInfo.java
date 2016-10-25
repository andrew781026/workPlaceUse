package tw.com.riko.andrew.DAO;

import java.sql.SQLException;
import java.util.List;

import tw.com.riko.andrew.VO.ManufacOrderInfo;
import tw.com.riko.andrew.VO.PurchInfo;

public interface IPurchInfo extends IOrderInfo{
	public List<PurchInfo> listAllPurchOrderInfos() throws SQLException;	
	public List<PurchInfo> listMonthlyPurchOrderInfos(String monthDate) throws SQLException;
	public List<PurchInfo> queryPurchOrderInfos(String productID, String orderID) throws SQLException;
	public PurchInfo getPurchOrderInfo(String orderID, String serialNO) throws SQLException;
	public String updatePurchOrderInfo(String orderID, String serialNO ,String deadDate) throws SQLException;
}
