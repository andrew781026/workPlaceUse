package tw.com.riko.andrew.DAO.factories;

import tw.com.riko.andrew.DAO.IFactoryInfo;
import tw.com.riko.andrew.DAO.IOrderInfo;
import tw.com.riko.andrew.DAO.proxies.FactoryInfoDaoProxy;
import tw.com.riko.andrew.DAO.proxies.OutfacInfoDaoProxy;
import tw.com.riko.andrew.util.DBUtil.DBNames;

public class FactoryInfoDaoFactory {
	
	public static IFactoryInfo getFactoryInfoDao() {
		return new FactoryInfoDaoProxy();
	}
	
	public static IFactoryInfo getFactoryInfoDao(String DB) {
		switch (DB) {
			case "LJ":	return new FactoryInfoDaoProxy(DBNames.RIKO);
			case "RIKO":	return new FactoryInfoDaoProxy(DBNames.RIKO);
			case "YC":	return new FactoryInfoDaoProxy(DBNames.YC);
			case "力科":	return new FactoryInfoDaoProxy(DBNames.RIKO);
			case "永鉅":	return new FactoryInfoDaoProxy(DBNames.YC);
			default : return null;
		}
		
	}

}
