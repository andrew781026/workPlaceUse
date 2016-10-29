package tw.com.riko.andrew.VO;

/**
 * Created by Test on 2016/10/27.
 */
public class Warehouse {

    String id , name , note ;
    Address address ;

    public Warehouse() {
    }

    public Warehouse(String id, String name, String note, Address address) {
        this.id = id;
        this.name = name;
        this.note = note;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
