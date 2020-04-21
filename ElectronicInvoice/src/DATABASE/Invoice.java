
package DATABASE;


public class Invoice {
    private int invoice_Number;
    private int customer_Number;
    private double payment;

    public Invoice(int invoice_Number, int customer_Number, double payment) {
        this.invoice_Number = invoice_Number;
        this.customer_Number = customer_Number;
        this.payment = payment;
    }

    public int getInvoice_Number() {
        return invoice_Number;
    }

    public int getCustomer_Number() {
        return customer_Number;
    }

    public double getPayment() {
        return payment;
    }

    public void setInvoice_Number(int invoice_Number) {
        this.invoice_Number = invoice_Number;
    }

    public void setCustomer_Number(int customer_Number) {
        this.customer_Number = customer_Number;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
    
    
    
}
