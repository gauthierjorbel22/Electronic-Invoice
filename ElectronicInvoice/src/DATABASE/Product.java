
package DATABASE;


public class Product {
    private String product_Code;
    private String description;
    private double price;

    public Product(String product_Code, String description, double price) {
        this.product_Code = product_Code;
        this.description = description;
        this.price = price;
    }

    public String getProduct_Code() {
        return product_Code;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public void setProduct_Code(String product_Code) {
        this.product_Code = product_Code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
