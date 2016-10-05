package tw.com.riko.andrew.DAO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import tw.com.riko.andrew.VO.ManufacOrderInfo;
import tw.com.riko.andrew.VO.OrderInfo;
import tw.com.riko.andrew.VO.PurchInfo;

public class PurchInfoDao implements IPurchInfo ,AutoCloseable{
	
	Connection con ;
	PreparedStatement preparedStatement ;
	ResultSet rs ;
	
	public PurchInfoDao(Connection con) {
		this.con = con ;
	}
	
	


	@Override
	public List<PurchInfo> listAllPurchOrderInfos() throws SQLException {
		return this.listPurchOrderInfos("",true);
	}

	@Override
	public List<PurchInfo> listMonthlyPurchOrderInfos(String monthDate) throws SQLException {
		return this.listPurchOrderInfos(monthDate,false);
	}
	
	
	
	public List<PurchInfo> listPurchOrderInfos(String monthDate,boolean allOrMonthly) throws SQLException {
		
		List<PurchInfo> pInfos = new ArrayList<>();
		
		try {
			
			String sqlString = this.getSQLString("PurchOrderInfos");	
			preparedStatement = con.prepareStatement(sqlString);
			
			if (allOrMonthly) {
				// get all data
				preparedStatement.setString(1,"%");
			} else {
				// get monthly data with monthDate
				Calendar cal = Calendar.getInstance();
				String year = monthDate.substring(0, 4);
				String month = monthDate.substring(4, 6);
				cal.set(Integer.parseInt(year), Integer.parseInt(month) - 1, 1);

				preparedStatement.setString(1, year + month + "%");
			}
			
			
			rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				String orderID = rs.getString(1) ; 
				String productID = rs.getString(4) ; 
				String unit = rs.getString(8);
				String factoryName = rs.getString(9);
				String productName = rs.getString(7);
				int orderAmount = rs.getInt(5) ; 
				int makedAmount = rs.getInt(6);				
				int date =rs.getInt(3);		// �w������	

				PurchInfo pOrderInfo = new PurchInfo(orderID , productID , unit , productName , factoryName , orderAmount , makedAmount , date );

				pInfos.add(pOrderInfo);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException ne){
			// TODO Auto-generated catch block
			ne.printStackTrace();
		}
		
		
		return pInfos;
	}
	
	private String getSQLString(String fileName) throws IOException{
		
		URL url = PurchInfoDao.class.getResource(fileName + ".sql");
		
		FileReader fr = new FileReader(url.getFile());
		BufferedReader br = new BufferedReader(fr);
		
		String tempString ;
		StringBuffer sqlString = new StringBuffer();
		
		while ( (tempString = br.readLine()) != null ) {
			sqlString.append(tempString).append("\n") ;
		}
				
		return sqlString.toString() ;
	}


	@Override
	public void close() throws Exception {
		if(preparedStatement !=null ) preparedStatement.close();
		if(rs !=null ) rs.close();
	}

	@Override
	public List<? extends OrderInfo> listMonthlyOrderInfos(String monthDate) throws SQLException {
		return this.listMonthlyPurchOrderInfos(monthDate);
	}

	@Override
	public List<? extends OrderInfo> listAllOrderInfos() throws SQLException {
		return this.listAllPurchOrderInfos();
	}



}
