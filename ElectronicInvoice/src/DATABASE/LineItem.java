
package DATABASE;


public class LineItem {
    private int invoice_Number;
    private String product_code;
    private int quantity;

    public LineItem(int invoice_Number, String product_code, int quantity) {
        this.invoice_Number = invoice_Number;
        this.product_code = product_code;
        this.quantity = quantity;
    }

    public void setInvoice_Number(int invoice_Number) {
        this.invoice_Number = invoice_Number;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getInvoice_Number() {
        return invoice_Number;
    }

    public String getProduct_code() {
        return product_code;
    }

    public int getQuantity() {
        return quantity;
    }
    
    
}
