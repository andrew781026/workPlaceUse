package tw.com.riko.andrew.DAO.proxies;

import java.sql.Connection;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import tw.com.riko.andrew.DAO.FactoryInfoDao;
import tw.com.riko.andrew.DAO.IFactoryInfo;
import tw.com.riko.andrew.VO.FactoryInfo;
import tw.com.riko.andrew.util.DBUtil;
import tw.com.riko.andrew.util.DBUtil.DBNames;

public class FactoryInfoDaoProxy implements IFactoryInfo{

	Connection con ;
	FactoryInfoDao dao ;
	
	public FactoryInfoDaoProxy() {
		this(DBNames.RIKO);
	}
	
	public FactoryInfoDaoProxy(DBNames db) {
		try {
			con = DBUtil.getConnection(db);
			dao = new FactoryInfoDao(con);
		} catch (SQLServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public FactoryInfo getFactoryInfo(String factoryID) throws SQLException {
		return dao.getFactoryInfo(factoryID);
	}
	
	

}
