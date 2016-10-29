package tw.com.riko.andrew.VO;

public class Address {

    private String 郵遞區號 , 地址 , 傳真 , 電話 , 聯絡人 ;

    public Address() {}

    public Address(String 郵遞區號, String 地址, String 傳真, String 電話, String 聯絡人) {
        this.郵遞區號 = 郵遞區號;
        this.地址 = 地址;
        this.傳真 = 傳真;
        this.電話 = 電話;
        this.聯絡人 = 聯絡人;
    }

    public String get郵遞區號() {
        return 郵遞區號;
    }

    public void set郵遞區號(String 郵遞區號) {
        this.郵遞區號 = 郵遞區號;
    }

    public String get地址() {
        return 地址;
    }

    public void set地址(String 地址) {
        this.地址 = 地址;
    }

    public String get傳真() {
        return 傳真;
    }

    public void set傳真(String 傳真) {
        this.傳真 = 傳真;
    }

    public String get電話() {
        return 電話;
    }

    public void set電話(String 電話) {
        this.電話 = 電話;
    }

    public String get聯絡人() {
        return 聯絡人;
    }

    public void set聯絡人(String 聯絡人) {
        this.聯絡人 = 聯絡人;
    }
}
