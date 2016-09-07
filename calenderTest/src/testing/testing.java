package testing;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tw.com.riko.andrew.DAO.IManufacInfo;
import tw.com.riko.andrew.DAO.ManufacInfoDaoFactory;
import tw.com.riko.andrew.VO.ManufacOrderInfo;
import tw.com.riko.andrew.servlet.ManufacOrderServlet;
import tw.com.riko.andrew.util.DBUtil.DBNames;

public class testing {

	public static void main(String[] args) {
		System.out.println("=========start================");
		List<ManufacOrderInfo> mInfos = new ArrayList<>();
		IManufacInfo dao = ManufacInfoDaoFactory.getManufacInfoDao("LJ");
		try {
			mInfos = dao.listMonthlyManufacOrderInfos("20160902");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("[\n"+ManufacOrderServlet.ManufacOrderInfosToJson(mInfos)+"\n]"); System.out.println("=========end================");
	}

}
