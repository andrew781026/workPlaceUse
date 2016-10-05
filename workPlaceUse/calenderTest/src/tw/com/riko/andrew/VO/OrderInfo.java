package tw.com.riko.andrew.VO;


public class OrderInfo {
	private String orderID , productID , unit ,productName , factoryName ;
	private int orderAmount , makedAmount, date  ;
	
	public OrderInfo(String orderID ,String productID ,String unit ,String productName ,String factoryName ,int orderAmount ,int makedAmount ,int date ) {
		this.orderID = orderID ; //��ڽs��
		this.productID = productID ;//�~�� 
		this.unit = unit ; //���
		this.productName = productName ; //�~�W
		this.orderAmount = orderAmount ; //��q-�w�p�Ͳ��q
		this.makedAmount = makedAmount ; //�w�Ͳ��ƶq
		this.date = date ; // �w�p���u��
		this.factoryName = factoryName ; // �t��
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
