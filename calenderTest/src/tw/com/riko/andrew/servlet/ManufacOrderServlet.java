package tw.com.riko.andrew.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import tw.com.riko.andrew.DAO.IManufacInfo;
import tw.com.riko.andrew.DAO.IOrderInfo;
import tw.com.riko.andrew.DAO.ManufacInfoDaoFactory;
import tw.com.riko.andrew.DAO.OutfacInfoDaoFactory;
import tw.com.riko.andrew.VO.ManufacOrderInfo;
import tw.com.riko.andrew.VO.OrderInfo;


@WebServlet("/ManufacOrderServlet")
public class ManufacOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ManufacOrderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//----取得 request的參數---------		
		String monthDate = request.getParameter("date");
		String type = request.getParameter("type");
		String DB = request.getParameter("DB");
		//---------------------------
		
		//-----設定response-------------------
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		//----------------------------------
		
		List<OrderInfo> infos;
		IOrderInfo dao = null;
		
		switch (type) {
		case "manufactory":
			dao = ManufacInfoDaoFactory.getManufacInfoDao(DB);	
			break;
		case "outerfactory":
			dao = OutfacInfoDaoFactory.getOutfacInfoDao(DB);
			break;

		default:
			System.out.println("Type param error");
			break;
		}
		
				
		try {
			infos = dao.listMonthlyOrderInfos(monthDate);
			// System.out.println(this.OrderInfosToJson(infos));
			out.append("["+this.OrderInfosToJson(infos)+"]");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.close();
	}
	
	public static String OrderInfosToJson(List<OrderInfo> mInfos){
		StringBuffer retrunJson = new StringBuffer() ;
		int infoAmounts = mInfos.size();
		int counter = 0 ;
		
		if(infoAmounts == 0){
			retrunJson.append("{}");
			return retrunJson.toString();
		}
		
		for (OrderInfo orderInfo : mInfos) {
			
			Gson gson = new Gson(); // Or use new GsonBuilder().create();
			retrunJson.append(gson.toJson(orderInfo)); // serializes target to Json
			if(infoAmounts != ++counter){
				retrunJson.append(",");
			}
			 
		}
		return retrunJson.toString();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String,String[]> map = request.getParameterMap();
		System.out.println(map.size());
		for (String key : map.keySet()) {
			System.out.println("key="+key+";value="+map.get(key));
		}
		
		doGet(request, response);
	}

}