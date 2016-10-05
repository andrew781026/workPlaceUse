package tw.com.riko.andrew.VO;


public class PurchInfo extends OrderInfo{

	public PurchInfo(String orderID, String productID, String unit, String productName, String factoryName,
			int orderAmount, int makedAmount, int date) {
		super(orderID, productID, unit, productName, factoryName, orderAmount, makedAmount, date);
	}

}
