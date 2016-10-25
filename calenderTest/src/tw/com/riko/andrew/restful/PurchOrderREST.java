package tw.com.riko.andrew.restful;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.sun.glass.ui.Application;

import tw.com.riko.andrew.DAO.IPurchInfo;
import tw.com.riko.andrew.DAO.PurchInfoDao;
import tw.com.riko.andrew.DAO.factories.PurchInfoDaoFactory;
import tw.com.riko.andrew.VO.InputData;
import tw.com.riko.andrew.VO.PurchInfo;


@Path("PurchOrders") 
public class PurchOrderREST {
	
	IPurchInfo dao = (IPurchInfo) PurchInfoDaoFactory.getPurchInfoDao("RIKO","");
	
	private String purchInfoListToJson(List<PurchInfo> pInfos) {
		
		StringBuffer retrunJson = new StringBuffer() ;
		
		int infoAmounts = pInfos.size();
		int counter = 0 ;
		
		for(PurchInfo pInfo : pInfos) {
			Gson gson = new Gson();
			retrunJson.append( gson.toJson(pInfo) );
			
			if(infoAmounts != ++counter){
				retrunJson.append(",");
			}
			
		}		
		
		return retrunJson.toString() ;
		
	}
	
	@GET
	@Path( "/{orderInfoID}" )
	@Produces( { MediaType.APPLICATION_JSON , "text/html; charset=UTF-8" })
	public String purchOrderInfo( 
			@QueryParam("DB") String DB ,
			@QueryParam("serialNO") String serialNO ,
			@PathParam("orderInfoID") String orderInfoID ){
		
		System.out.println("DB="+DB+" ,serialNO="+serialNO+" ,orderInfoID="+orderInfoID);
		
		StringBuffer retrunJson = new StringBuffer("[") ;
		
		try {
			
			List<PurchInfo> pInfos = new ArrayList<>();
			PurchInfo purchInfo = dao.getPurchOrderInfo(orderInfoID,serialNO);
			pInfos.add(purchInfo);
			
			retrunJson.append( this.purchInfoListToJson(pInfos) );
			
			retrunJson.append("]");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			retrunJson = new StringBuffer("{}") ;
		}
		
		return retrunJson.toString();
		
	}
	
	
	@POST
	@Path( "/{orderID}/{serialNO}" )
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updatePostPurchInfos( 
			@PathParam("orderID") String orderID ,
			@PathParam("serialNO") String serialNO ,
			@FormParam("deadDate") String deadDate ,
			@FormParam("password") String password ){
		
		// IPurchInfo dao = (IPurchInfo) PurchInfoDaoFactory.getPurchInfoDao("RIKO",password);
		
		// IPurchInfo dao = (IPurchInfo) PurchInfoDaoFactory.getPurchInfoDao("ACC");
		String returnString ;
		
		returnString = "orderID="+orderID+ "\t serialNO="+serialNO+ "\t deadDate="+deadDate+"\t password="+password;
		System.out.println(returnString);
		
		/*
		if(!"*********".equalsIgnoreCase(password)){
			returnString = "密碼錯誤";
			return returnString ;
		}
		
		
		try {
			returnString = dao.updatePurchOrderInfo(orderID, serialNO, deadDate);
			returnString = "資料更新資料成功!";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnString = "無法更新資料，請通知管理員";
		}
		*/
		
		return returnString ;
		
	} 
	
	
	@GET
	@Path( "/{orderID}/{serialNO}" )
	public String updatePurchInfos( 
			@PathParam("orderID") String orderID ,
			@PathParam("serialNO") String serialNO ,
			@QueryParam("deadDate") String deadDate ,
			@QueryParam("password") String password ){
		
		IPurchInfo dao = (IPurchInfo) PurchInfoDaoFactory.getPurchInfoDao("RIKO",password);
		
		// IPurchInfo dao = (IPurchInfo) PurchInfoDaoFactory.getPurchInfoDao("ACC");
		String returnString ;
		
		returnString = "orderID="+orderID+ "\t serialNO="+serialNO+ "\t deadDate="+deadDate ;
		System.out.println(returnString);
		
		
		if(!"rikoBuyer5919".equalsIgnoreCase(password)){
			returnString = "密碼錯誤";
			return returnString ;
		}
		
		
		try {
			returnString = dao.updatePurchOrderInfo(orderID, serialNO, deadDate);
			returnString = "資料更新資料成功!";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnString = "無法更新資料，請通知管理員";
		}
		
		
		return returnString ;
		
	} 
	
	@GET
	@Produces( { MediaType.APPLICATION_JSON , "text/html; charset=UTF-8" })
	public String purchOrderInfos( 
			@QueryParam("productID") @DefaultValue("") String productID ,
			@QueryParam("DB") @DefaultValue("") String DB
	){
		
		System.out.println(productID);
		
		StringBuffer retrunJson = new StringBuffer("[") ;
		
		try {
			List<PurchInfo> pInfos = dao.queryPurchOrderInfos(productID,null);
			
			retrunJson.append( this.purchInfoListToJson(pInfos) );
			
			retrunJson.append("]");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			retrunJson = new StringBuffer("{}") ;
		}
		
		return retrunJson.toString();
		
	}
	
}
