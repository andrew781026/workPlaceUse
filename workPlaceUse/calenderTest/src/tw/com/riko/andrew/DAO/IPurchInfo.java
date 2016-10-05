package tw.com.riko.andrew.DAO;

import java.sql.SQLException;
import java.util.List;

import tw.com.riko.andrew.VO.ManufacOrderInfo;
import tw.com.riko.andrew.VO.PurchInfo;

public interface IPurchInfo extends IOrderInfo{
	public List<PurchInfo> listAllPurchOrderInfos() throws SQLException;	
	List<PurchInfo> listMonthlyPurchOrderInfos(String monthDate) throws SQLException;
}
