package tw.com.riko.andrew.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.Base64.Decoder;
import java.util.HashMap;
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
import tw.com.riko.andrew.DAO.PurchInfoDao;
import tw.com.riko.andrew.DAO.factories.ManufacInfoDaoFactory;
import tw.com.riko.andrew.DAO.factories.OutfacInfoDaoFactory;
import tw.com.riko.andrew.DAO.factories.PurchInfoDaoFactory;
import tw.com.riko.andrew.VO.ManufacOrderInfo;
import tw.com.riko.andrew.VO.OrderInfo;
import tw.com.riko.andrew.util.JsonUtil;


@WebServlet("/ManufacOrderServlet")
public class ManufacOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ManufacOrderServlet() {
        super();
    }

    private Map<String, String> getRequestParams(String httpMethodType , HttpServletRequest request) {
		
    	Map<String, String> params = new HashMap<>();
    	
    	switch (httpMethodType) {
		case "doGet":
			
			// ----doGet取得 request的參數---------
			String monthDate = request.getParameter("date");
			String type = request.getParameter("type");
			String DB = request.getParameter("DB");
			params.put("monthDate", monthDate);
			params.put("type", type);
			params.put("DB", DB);
			// ------------------------------
			break;
			
		case "doPost":
			
			Map<String,String[]> map = request.getParameterMap();	
			Iterator<String> iterator = map.keySet().iterator();
			params = JsonUtil.jsonToMap(iterator.next());
			
			break;

		default:
			break;
		}
		

    	return params;	
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> params = this.getRequestParams("doGet", request);
		this.doResponse(response, params);		
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> params = this.getRequestParams("doPost", request);
		this.doResponse(response, params);
	}
	
	private void doResponse(HttpServletResponse response , Map<String, String> params) throws IOException {
		
		//----取得 request的參數---------		
		String monthDate = params.get("monthDate");
		String type = params.get("type");
		String DB = params.get("DB");
		//---------------------------
		
		
		//-----設定response-------------------
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		//----------------------------------
		
		List<? extends OrderInfo> infos;
		IOrderInfo dao = null;
		
		switch (type) {
		case "manufactory":
			dao = ManufacInfoDaoFactory.getManufacInfoDao(DB);
			break;
		case "outerfactory":
			dao = OutfacInfoDaoFactory.getOutfacInfoDao(DB);
			break;
		case "purchOrder":
			dao = PurchInfoDaoFactory.getPurchInfoDao(DB,"");
			break;

		default:
			System.out.println("Type param error");
			break;
		}
		
				
		try {
			infos = dao.listMonthlyOrderInfos(monthDate);
			out.append("["+JsonUtil.OrderInfosToJson(infos)+"]");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.close();
	}

}