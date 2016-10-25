package tw.com.riko.andrew.VO;


public class PurchInfo extends OrderInfo{

	int serialNO ;
	String factoryID ;
	
	public PurchInfo(String orderID, String productID, String unit, String productName, String factoryName,
			int orderAmount, int makedAmount, int date) {
		super(orderID, productID, unit, productName, factoryName, orderAmount, makedAmount, date);
	}
	
	public PurchInfo(String orderID, String productID, String unit, String productName, String factoryName,
			int orderAmount, int makedAmount, int date, int serialNO) {
		super(orderID, productID, unit, productName, factoryName, orderAmount, makedAmount, date);
		this.serialNO =serialNO ;
	}
	
	public PurchInfo(String orderID, String productID, String unit, String productName, String factoryName,
			int orderAmount, int makedAmount, int date, int serialNO, String factoryID) {
		this(orderID, productID, unit, productName, factoryName, orderAmount, makedAmount, date);
		this.factoryID =factoryID ;
	}

	public int getSerialNO() {
		return serialNO;
	}

	public void setSerialNO(int serialNO) {
		this.serialNO = serialNO;
	}

	public String getFactoryID() {
		return factoryID;
	}

	public void setFactoryID(String factoryID) {
		this.factoryID = factoryID;
	}

}
