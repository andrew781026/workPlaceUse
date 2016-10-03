package tw.com.riko.andrew.VO;


public class ManufacOrderInfo extends OrderInfo{
	/***************************************
	private String orderID , productID , unit ,productName;
	private int orderAmount , makedAmount, date  ;
	***************************************/
	/*****************************
	public ManufacOrderInfo(String orderID ,String productID ,String unit ,String productName ,int orderAmount ,int makedAmount ,int date ) {
		this.orderID = orderID ; 
		this.productID = productID ; 
		this.unit = unit ; 
		this.productName = productName ; 
		this.orderAmount = orderAmount ; 
		this.makedAmount = makedAmount ; 
		this.date = date ; 
	}
	************************************/
	public ManufacOrderInfo(String orderID, String productID, String unit, String productName, int orderAmount,
			int makedAmount, int date) {
		super(orderID, productID, unit, productName, orderAmount, makedAmount, date);
	}
	
}
