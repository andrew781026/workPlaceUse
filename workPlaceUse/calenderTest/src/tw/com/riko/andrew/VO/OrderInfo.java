package tw.com.riko.andrew.VO;


public class OrderInfo {
	private String orderID , productID , unit ,productName , factoryName ;
	private int orderAmount , makedAmount, date  ;
	
	public OrderInfo(String orderID ,String productID ,String unit ,String productName ,String factoryName ,int orderAmount ,int makedAmount ,int date ) {
		this.orderID = orderID ; //單據編號
		this.productID = productID ;//品號 
		this.unit = unit ; //單位
		this.productName = productName ; //品名
		this.orderAmount = orderAmount ; //批量-預計生產量
		this.makedAmount = makedAmount ; //已生產數量
		this.date = date ; // 預計完工日
		this.factoryName = factoryName ; // 廠商
	}
	
	
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
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
