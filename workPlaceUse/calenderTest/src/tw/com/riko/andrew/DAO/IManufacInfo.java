package tw.com.riko.andrew.DAO;

import java.sql.SQLException;
import java.util.List;

import tw.com.riko.andrew.VO.ManufacOrderInfo;

public interface IManufacInfo extends IOrderInfo{
	public List<ManufacOrderInfo> listAllManufacOrderInfos() throws SQLException;	
	List<ManufacOrderInfo> listMonthlyManufacOrderInfos(String monthDate) throws SQLException;
}
