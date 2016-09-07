package tw.com.riko.andrew.DAO;

import tw.com.riko.andrew.util.DBUtil.DBNames;

public class ManufacInfoDaoFactory {
	
	public static IManufacInfo getManufacInfoDao() {
		return new ManufacInfoDaoProxy();
	}
	
	public static IManufacInfo getManufacInfoDao(String DB) {
		switch (DB) {
			case "LJ":	return new ManufacInfoDaoProxy(DBNames.RIKO);
			case "YC":	return new ManufacInfoDaoProxy(DBNames.YC);
			default : return null;
		}
		
	}

}
