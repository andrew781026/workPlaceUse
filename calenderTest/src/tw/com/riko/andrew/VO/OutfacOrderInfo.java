package tw.com.riko.andrew.VO;

public class OutfacOrderInfo extends OrderInfo{

	public OutfacOrderInfo(String orderID, String productID, String unit, String productName, int orderAmount,
			int makedAmount, int date) {
		super(orderID, productID, unit, productName, orderAmount, makedAmount, date);
	}
	
}
