package tw.com.riko.andrew.DAO.factories;

import tw.com.riko.andrew.DAO.IOrderInfo;
import tw.com.riko.andrew.DAO.proxies.OutfacInfoDaoProxy;
import tw.com.riko.andrew.util.DBUtil.DBNames;

public class OutfacInfoDaoFactory {
	
	public static IOrderInfo getOutfacInfoDao() {
		return new OutfacInfoDaoProxy();
	}
	
	public static IOrderInfo getOutfacInfoDao(String DB) {
		switch (DB) {
			case "LJ":	return new OutfacInfoDaoProxy(DBNames.RIKO);
			case "RIKO":	return new OutfacInfoDaoProxy(DBNames.RIKO);
			case "YC":	return new OutfacInfoDaoProxy(DBNames.YC);
			case "力科":	return new OutfacInfoDaoProxy(DBNames.RIKO);
			case "永鉅":	return new OutfacInfoDaoProxy(DBNames.YC);
			default : return null;
		}
		
	}

}
