
package DATABASE;


public class Customer {
    private int customer_Number;
    private String name;
    private String address;
    private String city;
    private String province;
    private String zip;
    private double deposit;

    public Customer(int customer_Number, String name, String address, String city, String province, String zip, double deposit) {
        this.customer_Number = customer_Number;
        this.name = name;
        this.address = address;
        this.city = city;
        this.province = province;
        this.zip = zip;
        this.deposit = deposit;
    }

    public int getCustomer_Number() {
        return customer_Number;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getZip() {
        return zip;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setCustomer_Number(int customer_Number) {
        this.customer_Number = customer_Number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }
    
    
}
