package tw.com.riko.andrew.DAO;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tw.com.riko.andrew.VO.FactoryInfo;
import tw.com.riko.andrew.util.FileUtil;

public class FactoryInfoDao implements IFactoryInfo ,AutoCloseable{
	
	Connection con ;
	PreparedStatement preparedStatement ;
	ResultSet rs ;
	
	public FactoryInfoDao(Connection con) {
		this.con = con ;
	}
	

	@Override
	public void close() throws Exception {
		if(preparedStatement !=null ) preparedStatement.close();
		if(rs !=null ) rs.close();
	}


	@Override
	public FactoryInfo getFactoryInfo(String factoryID) throws SQLException {
		
		String fileName = "sql/FactoryInfos";
		URL url = OutfacInfoDao.class.getResource(fileName + ".sql");
		String sqlString = "" ;
		FactoryInfo factoryInfo = null ;
		
		try {
			sqlString = FileUtil.getSQLString(url);
			
			preparedStatement = con.prepareStatement(sqlString);
			preparedStatement.setString(1, factoryID);
						
			
			rs = preparedStatement.executeQuery();
			
			rs.next();
			
			String factoryShortName = rs.getString(2) ;
			String factoryName  = rs.getString(3) ; 
			String officePhoneNO  = rs.getString(5) ; 
			String cellPhoneNO  = rs.getString(6) ;
			String contact_Person  = rs.getString(4) ;
							
			factoryInfo = new FactoryInfo(factoryID ,factoryShortName ,factoryName ,officePhoneNO , cellPhoneNO ,contact_Person);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return factoryInfo ;
		
	}

}
