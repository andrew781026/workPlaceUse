package tw.com.riko.andrew.restful;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import tw.com.riko.andrew.DAO.FactoryInfoDao;
import tw.com.riko.andrew.DAO.IFactoryInfo;
import tw.com.riko.andrew.DAO.IPurchInfo;
import tw.com.riko.andrew.DAO.PurchInfoDao;
import tw.com.riko.andrew.DAO.factories.FactoryInfoDaoFactory;
import tw.com.riko.andrew.DAO.factories.PurchInfoDaoFactory;
import tw.com.riko.andrew.VO.FactoryInfo;
import tw.com.riko.andrew.VO.PurchInfo;


@Path("Factories") 
public class FactoryInfoREST {
	
	IFactoryInfo dao = FactoryInfoDaoFactory.getFactoryInfoDao("RIKO");
	
	private String purchInfoListToJson(List<FactoryInfo> fInfos) {
		
		StringBuffer retrunJson = new StringBuffer() ;
		
		int infoAmounts = fInfos.size();
		int counter = 0 ;
		
		for(FactoryInfo fInfo : fInfos) {
			Gson gson = new Gson();
			retrunJson.append( gson.toJson(fInfo) );
			
			if(infoAmounts != ++counter){
				retrunJson.append(",");
			}
			
		}		
		
		return retrunJson.toString() ;
		
	}
	
	@GET
	@Path( "/{factoryID}" )
	@Produces( { MediaType.APPLICATION_JSON , "text/html; charset=UTF-8" })
	public String purchOrderInfo( @PathParam("factoryID") String factoryID ){
		
		System.out.println("factoryID="+factoryID);
		
		StringBuffer retrunJson = new StringBuffer("[") ;
		
		try {
			
			List<FactoryInfo> factoryInfos = new ArrayList<>();
			FactoryInfo factoryInfo = dao.getFactoryInfo(factoryID);
			factoryInfos.add(factoryInfo);
			
			retrunJson.append( this.purchInfoListToJson(factoryInfos) );
			
			retrunJson.append("]");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			retrunJson = new StringBuffer("{}") ;
		}
		
		return retrunJson.toString();
		
	}
	

	
}
