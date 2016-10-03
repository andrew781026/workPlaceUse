package tw.com.riko.andrew.DAO;

import java.sql.SQLException;
import java.util.List;

import tw.com.riko.andrew.VO.OrderInfo;

public interface IOrderInfo {
	public List<OrderInfo> listAllOrderInfos() throws SQLException;	
	List<OrderInfo> listMonthlyOrderInfos(String monthDate) throws SQLException;
}
