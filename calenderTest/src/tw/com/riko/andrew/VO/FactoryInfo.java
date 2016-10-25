package tw.com.riko.andrew.VO;

public class FactoryInfo {
	
	String factoryID , factoryShortName , factoryName ; 
	String officePhoneNO , cellPhoneNO , contact_Person ;
	
	public FactoryInfo(String factoryID ,String factoryShortName ,String factoryName ,	String officePhoneNO , String cellPhoneNO ,String contact_Person ) {
		this.factoryID = factoryID ; // 廠商編號
		this.factoryShortName = factoryShortName ;// 廠商簡稱 
		this.factoryName = factoryName ; // 廠商全名
		this.officePhoneNO = officePhoneNO ; // 連絡電話
		this.cellPhoneNO = cellPhoneNO ; // 聯絡人手機
		this.contact_Person = contact_Person ; // 聯絡人
	}
	
	public String getFactoryID() {
		return factoryID;
	}
	public void setFactoryID(String factoryID) {
		this.factoryID = factoryID;
	}
	public String getFactoryShortName() {
		return factoryShortName;
	}
	public void setFactoryShortName(String factoryShortName) {
		this.factoryShortName = factoryShortName;
	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	public String getOfficePhoneNO() {
		return officePhoneNO;
	}
	public void setOfficePhoneNO(String officePhoneNO) {
		this.officePhoneNO = officePhoneNO;
	}
	public String getCellPhoneNO() {
		return cellPhoneNO;
	}
	public void setCellPhoneNO(String cellPhoneNO) {
		this.cellPhoneNO = cellPhoneNO;
	}
	public String getContact_Person() {
		return contact_Person;
	}
	public void setContact_Person(String contact_Person) {
		this.contact_Person = contact_Person;
	} 
}
