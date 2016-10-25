package tw.com.riko.andrew.DAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import tw.com.riko.andrew.VO.OrderInfo;
import tw.com.riko.andrew.VO.PurchInfo;
import tw.com.riko.andrew.util.FileUtil;

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
			
			String sqlString = this.getSQLString("sql/PurchOrderInfos");	
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
			
			pInfos = this.resultsetToPurchInfoList(rs);		
			
			
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
		
		br.close();
		
		return sqlString.toString() ;
	}


	@Override
	public void close() throws Exception {
		if(preparedStatement !=null ) preparedStatement.close();
		if(rs !=null ) rs.close();
	}

	private List<PurchInfo> resultsetToPurchInfoList(ResultSet resultSet) throws SQLException {
		List<PurchInfo> pInfos = new ArrayList<>();
		
		while (resultSet.next()) {

			String orderID = resultSet.getString(1);
			String unit = resultSet.getString(8);
			String productID = resultSet.getString(4) ;
			String factoryName = resultSet.getString(9);
			String productName = resultSet.getString(7);
			int orderAmount = resultSet.getInt(5);
			int makedAmount = resultSet.getInt(6);
			int date = resultSet.getInt(3); // 預交日期
			int serialNO = resultSet.getInt(10); // 序號

			PurchInfo pOrderInfo = new PurchInfo(orderID, productID, unit, productName, factoryName, orderAmount,
					makedAmount, date,serialNO);

			pInfos.add(pOrderInfo);
		}

		return pInfos;
	}
	
	private String nullParamToAllSign(String param) {
		
		param = (param == null)?"%":param;
		param = (param.equals(""))?"%":param;
		param = (param.equals("undefined"))?"%":param;
		
		return param;
	}
	
	@Override
	public PurchInfo getPurchOrderInfo( String orderID , String serialNO) throws SQLException {
		
		List<PurchInfo> pInfos = new ArrayList<>();		
		
		String sqlString;
		
		try {
			sqlString = this.getSQLString("sql/PurchInfo");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}	
		
		
		orderID = this.nullParamToAllSign(orderID);
		serialNO = this.nullParamToAllSign(serialNO);	
		
		preparedStatement = con.prepareStatement(sqlString,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);	
		preparedStatement.setString(1,orderID);
		preparedStatement.setString(2,"%"+serialNO);
		rs = preparedStatement.executeQuery();		
		
		pInfos = this.resultsetToPurchInfoList(rs);
		PurchInfo pInfo = pInfos.get(0);
		
		rs.beforeFirst();		
		rs.next();
		
		pInfo.setFactoryID( rs.getString(11) );
		
		return pInfo ;
		
	}
	
	
	@Override
	public List<PurchInfo> queryPurchOrderInfos( String productID , String orderID ) throws SQLException {
		
		List<PurchInfo> pInfos = new ArrayList<>();
		
		String sqlString;
		
		try {
			sqlString = this.getSQLString("sql/PurchOrderInfosByParams");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}	
		
		productID = this.nullParamToAllSign(productID);
		orderID = this.nullParamToAllSign(orderID);		
		
		preparedStatement = con.prepareStatement(sqlString);		
		preparedStatement.setString(1,productID);
		preparedStatement.setString(2,orderID);
		rs = preparedStatement.executeQuery();		
		
		pInfos = this.resultsetToPurchInfoList(rs);
		
		return pInfos ;
	}
	
	@Override
	public List<? extends OrderInfo> listMonthlyOrderInfos(String monthDate) throws SQLException {
		return this.listMonthlyPurchOrderInfos(monthDate);
	}

	@Override
	public List<? extends OrderInfo> listAllOrderInfos() throws SQLException {
		return this.listAllPurchOrderInfos();
	}

	@Override
	public String updatePurchOrderInfo(String orderID, String serialNO, String deadDate) throws SQLException{
		
		String returnString = "" ;
		
		try {
			String sqlString = FileUtil.getSQLString(PurchInfoDao.class.getResource("sql/updatePurchInfo.sql"));
			
			preparedStatement = con.prepareStatement(sqlString);
			
			preparedStatement.setString(1, deadDate);
			preparedStatement.setString(2, orderID);
			preparedStatement.setString(3, "%"+serialNO);
			
			int affectRowNumbers = preparedStatement.executeUpdate();
			
			returnString = "有"+affectRowNumbers+"筆資料受影響";
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnString = "讀取sql檔，有狀況";
		}
		
		return returnString;
	}



}
