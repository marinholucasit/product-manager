package br.com.lm.entity;

public class Product {

    private String product;
    private String type;
    private Integer quantity;
    private Double price;
    private String industry;
    private String origin;

    public Product(String product, String type, int quantity, Double price, String industry, String origin) {
        this.product = product;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.industry = industry;
        this.origin = origin;
    }

    public boolean isSameProduct(Product other) {
        return this.product.equals(other.getProduct()) && this.type.equals(other.getType());
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product1 = (Product) o;
        return product.equals(product1.product) && type.equals(product1.type);
    }

    @Override
    public int hashCode() {
        int result = product.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
