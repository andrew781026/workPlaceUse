package tw.com.riko.andrew.DAO;

import tw.com.riko.andrew.util.DBUtil.DBNames;

public class PurchInfoDaoFactory {
	
	public static IOrderInfo getPurchInfoDao() {
		return new PurchInfoDaoProxy();
	}
	
	public static IOrderInfo getPurchInfoDao(String DB) {
		switch (DB) {
			case "LJ":	return new PurchInfoDaoProxy(DBNames.RIKO);
			case "RIKO":	return new PurchInfoDaoProxy(DBNames.RIKO);
			case "YC":	return new PurchInfoDaoProxy(DBNames.YC);
			case "¤O¬ì":	return new PurchInfoDaoProxy(DBNames.RIKO);
			case "¥Ã¹d":	return new PurchInfoDaoProxy(DBNames.YC);
			default : return null;
		}
		
	}

}
