package tw.com.riko.andrew.DAO.factories;

import tw.com.riko.andrew.DAO.IManufacInfo;
import tw.com.riko.andrew.DAO.proxies.ManufacInfoDaoProxy;
import tw.com.riko.andrew.util.DBUtil.DBNames;

public class ManufacInfoDaoFactory {
	
	public static IManufacInfo getManufacInfoDao() {
		return new ManufacInfoDaoProxy();
	}
	
	public static IManufacInfo getManufacInfoDao(String DB) {
		switch (DB) {
			case "LJ":	return new ManufacInfoDaoProxy(DBNames.RIKO);
			case "RIKO":	return new ManufacInfoDaoProxy(DBNames.RIKO);
			case "YC":	return new ManufacInfoDaoProxy(DBNames.YC);
			case "力科":	return new ManufacInfoDaoProxy(DBNames.RIKO);
			case "永鉅":	return new ManufacInfoDaoProxy(DBNames.YC);
			default : return null;
		}
		
	}

}
