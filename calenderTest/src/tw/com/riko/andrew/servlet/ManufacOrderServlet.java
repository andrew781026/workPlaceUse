package tw.com.riko.andrew.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import tw.com.riko.andrew.DAO.IManufacInfo;
import tw.com.riko.andrew.DAO.ManufacInfoDaoFactory;
import tw.com.riko.andrew.VO.ManufacOrderInfo;

/**
 * Servlet implementation class ManufacOrderServlet
 */
@WebServlet("/ManufacOrderServlet")
public class ManufacOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManufacOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String monthDate = request.getParameter("date");
		List<ManufacOrderInfo> mInfos;
		IManufacInfo dao = ManufacInfoDaoFactory.getManufacInfoDao();
		
		

		try {
			mInfos = dao.listMonthlyManufacOrderInfos(monthDate);
			
			out.append("["+this.ManufacOrderInfosToJson(mInfos)+"]");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.close();
	}

	
	
	public static String ManufacOrderInfosToJson(List<ManufacOrderInfo> mInfos){
		StringBuffer retrunJson = new StringBuffer() ;
		int infoAmounts = mInfos.size();
		int counter = 0 ;
		
		if(infoAmounts == 0){
			retrunJson.append("{}");
			return retrunJson.toString();
		}
		
		for (ManufacOrderInfo manufacOrderInfo : mInfos) {
			
			Gson gson = new Gson(); // Or use new GsonBuilder().create();
			retrunJson.append(gson.toJson(manufacOrderInfo)); // serializes target to Json
			if(infoAmounts != ++counter){
				retrunJson.append(",");
			}
			 
		}
		return retrunJson.toString();
	}
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
