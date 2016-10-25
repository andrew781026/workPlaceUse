package tw.com.riko.andrew.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import tw.com.riko.andrew.VO.OrderInfo;
import tw.com.riko.andrew.VO.Params;

public class JsonUtil {
	
	
	public static String OrderInfosToJson(List<? extends OrderInfo> mInfos){
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
	
	public static Map<String,String> jsonToMap (String json){
		
		Map<String, String> params = new HashMap<>();
		Gson gson = new Gson();
		Params paramObject = gson.fromJson(json, Params.class);
		
		String monthDate = paramObject.getDate();
		String type = paramObject.getType();
		String DB = paramObject.getDB();
		params.put("monthDate", monthDate);
		params.put("type", type);
		params.put("DB", DB);
		
		return params ;
	}
	
}
