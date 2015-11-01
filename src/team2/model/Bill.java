package market.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by serhii on 25.10.15.
 */
public class Bill {

    private int id;
    private Seller seller;
    private Date date;
    private double amountPrice;
    private String dayWish;

    private ArrayList<Product> products = new ArrayList<>();

    public Bill(int id, Seller seller) {
        this.id = id;
        this.seller = seller;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmountPrice() {
        return amountPrice;
    }

    public void setAmountPrice(double amountPrice) {
        this.amountPrice = amountPrice;
    }

    public String getDayWish() {
        return dayWish;
    }

    public void setDayWish(String dayWish) {
        this.dayWish = dayWish;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Bill{");
        sb.append("id=").append(id);
        sb.append(", seller=").append(seller);
        sb.append(", date=").append(date);
        sb.append(", amountPrice=").append(amountPrice);
        sb.append(", dayWish='").append(dayWish).append('\'');
        sb.append(", products=").append(products);
        sb.append('}');
        return sb.toString();
    }
}
