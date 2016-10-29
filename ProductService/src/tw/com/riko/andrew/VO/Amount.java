package tw.com.riko.andrew.VO;

/**
 * Created by Test on 2016/10/27.
 */
public class Amount {
    private Warehouse warehouse ;
    private double 借出數量 , 借入數量 , 實際在庫數量 ;

    public Amount() {
    }

    public Amount(Warehouse warehouse, double 借出數量, double 借入數量, double 實際在庫數量) {
        this.warehouse = warehouse;
        this.借出數量 = 借出數量;
        this.借入數量 = 借入數量;
        this.實際在庫數量 = 實際在庫數量;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public double get借出數量() {

        return 借出數量;
    }

    public void set借出數量(double 借出數量) {
        this.借出數量 = 借出數量;
    }

    public double get借入數量() {
        return 借入數量;
    }

    public void set借入數量(double 借入數量) {
        this.借入數量 = 借入數量;
    }

    public double get實際在庫數量() {
        return 實際在庫數量;
    }

    public void set實際在庫數量(double 實際在庫數量) {
        this.實際在庫數量 = 實際在庫數量;
    }
}
