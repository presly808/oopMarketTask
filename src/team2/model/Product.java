package market.model;

/**
 * Created by serhii on 25.10.15.
 */
public class Product {


    private String barCode;
    private String model;
    private double price;

    public Product() {
    }

    public Product(String barCode, String model, double price) {
        this.barCode = barCode;
        this.model = model;
        this.price = price;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("barCode='").append(barCode).append('\'');
        sb.append(", model='").append(model).append('\'');
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return !(barCode != null ? !barCode.equals(product.barCode) : product.barCode != null);

    }

}
