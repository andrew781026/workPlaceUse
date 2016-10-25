package tw.com.riko.andrew.DAO;

import java.sql.SQLException;

import tw.com.riko.andrew.VO.FactoryInfo;

public interface IFactoryInfo {
	public FactoryInfo getFactoryInfo(String factoryID) throws SQLException;
}
