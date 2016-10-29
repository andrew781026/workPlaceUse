package tw.com.riko.andrew.VO;

import java.util.List;
import java.util.Map;


public class Product {

    String productID , productName , unit , storagePlace ;
    List<Amount> amountList ;

    public Product() {
    }

    public Product(String productID, String productName, String unit, String storagePlace, List<Amount> amountList) {
        this.productID = productID;
        this.productName = productName;
        this.unit = unit;
        this.storagePlace = storagePlace;
        this.amountList = amountList;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStoragePlace() {
        return storagePlace;
    }

    public void setStoragePlace(String storagePlace) {
        this.storagePlace = storagePlace;
    }

    public List<Amount> getAmountList() {
        return amountList;
    }

    public void setAmountList(List<Amount> amountList) {
        this.amountList = amountList;
    }
}
