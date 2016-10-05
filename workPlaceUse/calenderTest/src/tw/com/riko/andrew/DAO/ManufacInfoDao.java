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

public class ManufacInfoDao implements IManufacInfo ,AutoCloseable{
	
	Connection con ;
	PreparedStatement preparedStatement ;
	ResultSet rs ;
	
	public ManufacInfoDao(Connection con) {
		this.con = con ;
	}
	
	@Override
	public List<ManufacOrderInfo> listAllManufacOrderInfos() throws SQLException {
		return this.listMonthlyManufacOrderInfos("",true);
	}
		
	@Override
	public List<ManufacOrderInfo> listMonthlyManufacOrderInfos(String monthDate) throws SQLException {
		return this.listMonthlyManufacOrderInfos(monthDate,false);
	}
	
	public List<ManufacOrderInfo> listMonthlyManufacOrderInfos(String monthDate,boolean allOrMonthly) throws SQLException {
		
		List<ManufacOrderInfo> mInfos = new ArrayList<>();
		
		try {
			
			String sqlString = this.getSQLString("ManufacOrderInfos");	
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
				int date =rs.getInt(3);		// ¹w­p§¹¤u¤é—¥	
								
				ManufacOrderInfo mOrderInfo = new ManufacOrderInfo(orderID , productID , unit , productName , factoryName , orderAmount , makedAmount , date );
				
				mInfos.add(mOrderInfo);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException ne){
			// TODO Auto-generated catch block
			ne.printStackTrace();
		}
		
		
		return mInfos;
	}
	
	private String getSQLString(String fileName) throws IOException{
		
		URL url = ManufacInfoDao.class.getResource(fileName + ".sql");
		
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
	public List<? extends OrderInfo> listAllOrderInfos() throws SQLException {
		return this.listAllManufacOrderInfos() ;
	}

	@Override
	public List<? extends OrderInfo> listMonthlyOrderInfos(String monthDate) throws SQLException {
		return this.listMonthlyManufacOrderInfos(monthDate) ;
	}

}
