package tw.com.riko.andrew.VO;


public class ManufacOrderInfo {
	private String orderID , productID , unit ,productName;
	private int orderAmount , makedAmount, date  ;
	
	public ManufacOrderInfo(String orderID ,String productID ,String unit ,String productName ,int orderAmount ,int makedAmount ,int date ) {
		this.orderID = orderID ; 
		this.productID = productID ; 
		this.unit = unit ; 
		this.productName = productName ; 
		this.orderAmount = orderAmount ; 
		this.makedAmount = makedAmount ; 
		this.date = date ; 
	}
	
	
	
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}	
	public String getProductName() {		
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}
	public int getMakedAmount() {
		return makedAmount;
	}
	public void setMakedAmount(int makedAmount) {
		this.makedAmount = makedAmount;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	

}
