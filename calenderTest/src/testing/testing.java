package testing;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tw.com.riko.andrew.DAO.IManufacInfo;
import tw.com.riko.andrew.DAO.IOrderInfo;
import tw.com.riko.andrew.DAO.ManufacInfoDaoFactory;
import tw.com.riko.andrew.DAO.OutfacInfoDaoFactory;
import tw.com.riko.andrew.VO.ManufacOrderInfo;
import tw.com.riko.andrew.VO.OrderInfo;
import tw.com.riko.andrew.servlet.ManufacOrderServlet;
import tw.com.riko.andrew.util.DBUtil.DBNames;

public class testing {

	public static void main(String[] args) {
		System.out.println("=========start================");
		List<OrderInfo> mInfos = new ArrayList<>();
		List<OrderInfo> infos = new ArrayList<>();
		IManufacInfo manuDao = ManufacInfoDaoFactory.getManufacInfoDao("LJ");
		IOrderInfo orderDao = OutfacInfoDaoFactory.getOutfacInfoDao("LJ");
		try {
			mInfos = manuDao.listMonthlyOrderInfos("20160902");
			infos = orderDao.listMonthlyOrderInfos("20160902");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("[\n"+ManufacOrderServlet.OrderInfosToJson(mInfos)+"\n]"); System.out.println("=========end================");
		System.out.println("[\n"+ManufacOrderServlet.OrderInfosToJson(infos)+"\n]"); System.out.println("=========end================");
	}

}
