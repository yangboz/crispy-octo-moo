package crispy_octo_moo.dto.drools;

/**
 * Created by yangboz on 2/1/16.
 */

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Customer customer;
    private List<CartItem> cartItems = new ArrayList();
    private double discount;

    public Cart(Customer customer) {
        this.customer = customer;
    }

    public void addItem(Product p, int qty) {
        CartItem cartItem = new CartItem(this, p, qty);
        cartItems.add(cartItem);
    }

    public double getDiscount() {
        return discount;
    }

    public void addDiscount(double discount) {
        this.discount += discount;
    }

    public int getTotalPrice() {
        int total = 0;
        for (CartItem item : cartItems) {
            total += item.getProduct().getPrice() * item.getQty();
        }
        return total;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List getCartItems() {
        return cartItems;
    }

    public int getFinalPrice() {
        return getTotalPrice() - (int) getDiscount();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (CartItem cartItem : cartItems) {
            sb.append(cartItem)
                    .append("\n");
        }
        sb.append("Discount: ")
                .append(getDiscount())
                .append("\nTotal: ")
                .append(getTotalPrice())
                .append("\nTotal After Discount: ")
                .append(getFinalPrice());
        return sb.toString();
    }
}
