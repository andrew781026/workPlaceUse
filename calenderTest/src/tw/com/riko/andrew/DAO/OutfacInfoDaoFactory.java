package tw.com.riko.andrew.DAO;

import tw.com.riko.andrew.util.DBUtil.DBNames;

public class OutfacInfoDaoFactory {
	
	public static IOrderInfo getOutfacInfoDao() {
		return new OutfacInfoDaoProxy();
	}
	
	public static IOrderInfo getOutfacInfoDao(String DB) {
		switch (DB) {
			case "LJ":	return new OutfacInfoDaoProxy(DBNames.RIKO);
			case "YC":	return new OutfacInfoDaoProxy(DBNames.YC);
			case "¤O¬ì":	return new OutfacInfoDaoProxy(DBNames.RIKO);
			case "¥Ã¹d":	return new OutfacInfoDaoProxy(DBNames.YC);
			default : return null;
		}
		
	}

}
