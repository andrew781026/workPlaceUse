package tw.com.riko.andrew.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class FileUtil {

	
	public String getSQLString(URL url) throws IOException{
		
		// URL url = ManufacInfoDao.class.getResource(fileName + ".sql");
		
		FileReader fr = new FileReader(url.getFile());
		BufferedReader br = new BufferedReader(fr);
		
		String tempString ;
		StringBuffer sqlString = new StringBuffer();
		
		while ( (tempString = br.readLine()) != null ) {
			sqlString.append(tempString).append("\n") ;
		}
				
		return sqlString.toString() ;
	}
}
