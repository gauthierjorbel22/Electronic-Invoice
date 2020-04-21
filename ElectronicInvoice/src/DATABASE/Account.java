/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATABASE;


public class Account {
    private String name;
    private int customer_number;
    private double payement;

    public Account(String name, int customer_number, double payement) {
        this.name = name;
        this.customer_number = customer_number;
        this.payement = payement;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCustomer_number(int customer_number) {
        this.customer_number = customer_number;
    }

    public void setPayement(double payement) {
        this.payement = payement;
    }
    
    public String getName() {
        return name;
    }

    public int getCustomer_number() {
        return customer_number;
    }

    public double getPayement() {
        return payement;
    }
    
}
