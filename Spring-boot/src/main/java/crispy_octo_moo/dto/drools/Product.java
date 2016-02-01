package crispy_octo_moo.dto.drools;

/**
 * Created by yangboz on 2/1/16.
 */
public class Product {
    private int price;
    private String desc;

    public Product(String desc, int price) {
        this.desc = desc;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getDesc() {
        return desc;
    }

    public String toString() {
        return "product: " + desc + ", price: " + price;
    }
}
