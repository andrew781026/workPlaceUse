package tw.com.riko.andrew.util;

import java.sql.Connection;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;


public class DBUtil {
	
	public static final String JDBC_URL = "" ;
	
	public enum DBNames{		
		RIKO("LJ"),YC("YC");
		
		private String DB ;	
		
		DBNames(String DB){
			this.DB = DB ;
		}
		
		public String getDBString(){
			return DB ;
		}
		
	}
		
	public static Connection getConnection() throws SQLServerException{		                        
		return getConnection(DBNames.RIKO);		
	}
	
	public static Connection getConnection(DBNames DB) throws SQLServerException{
		
        SQLServerDataSource ds = new SQLServerDataSource();  
        ds.setUser("zc_own");  
        ds.setPassword("Z334");  
        ds.setServerName("riko-erp");  
        ds.setPortNumber(1433);   
        ds.setDatabaseName(DB.getDBString());         
        
		return ds.getConnection();
		
	}
	
}
