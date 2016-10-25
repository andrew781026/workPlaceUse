package tw.com.riko.andrew.DAO.factories;

import tw.com.riko.andrew.DAO.IOrderInfo;
import tw.com.riko.andrew.DAO.proxies.PurchInfoDaoProxy;
import tw.com.riko.andrew.util.DBUtil.DBNames;

public class PurchInfoDaoFactory {
	
	public static IOrderInfo getPurchInfoDao() {
		return new PurchInfoDaoProxy();
	}
	
	public static IOrderInfo getPurchInfoDao(String DB,String password) {
		switch (DB) {
			case "LJ":	return new PurchInfoDaoProxy(DBNames.RIKO,password);
			case "RIKO":	return new PurchInfoDaoProxy(DBNames.RIKO,password);
			case "YC":	return new PurchInfoDaoProxy(DBNames.YC,password);
			case "ACC":	return new PurchInfoDaoProxy(DBNames.ACC,password);
			case "力科":	return new PurchInfoDaoProxy(DBNames.RIKO,password);
			case "永鉅":	return new PurchInfoDaoProxy(DBNames.YC,password);
			default : return null;
		}
		
	}

}
