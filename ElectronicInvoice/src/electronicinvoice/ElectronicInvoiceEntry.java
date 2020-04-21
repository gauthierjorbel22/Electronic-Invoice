package electronicinvoice;

import DATABASE.Customer;
import DATABASE.DatabaseConnection;
import DATABASE.Invoice;
import DATABASE.LineItem;
import DATABASE.Product;
import java.awt.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class ElectronicInvoiceEntry extends JFrame implements ActionListener {

    JButton button2 = new JButton("List Product");
    JButton button1 = new JButton("Add Customer");
    JButton buttonAddInvoice = new JButton("Add Invoice");
    JButton button3 = new JButton("Show Invoice");
    JButton button7 = new JButton("Exit");
    JButton button4 = new JButton("Write Invoice");
    JButton button8 = new JButton("Next");
    JButton button5 = new JButton("Find Products");
    JTextField txtName;
    JTextField txtAddress;
    JTextField txtCity;
    JTextField txtDescription;
    JLabel province;
    JTextField txtProvince;
    JLabel zip;
    JTextField txtZip;
    JTextField txtCustomerNumber;
    JTextField txtPayment;
    JTextField txtInvoiceNumber;
    JTextField txtPrice;
    JTextField txtDeposit;
    JSpinner spinner1;
    JComboBox box;
    JFrame ElectronicInvoice;
    private String customerNumber;

    public ElectronicInvoiceEntry() {

        super("Electronic Invoice Entry");
        setSize(500, 500
        );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ElectronicInvoice = new JFrame("Electronic Invoice - Transaction");

        //label and text field of all the components(name,address,city and so forth)
        JLabel name = new JLabel("Name");
        txtName = new JTextField(25);

        JLabel address = new JLabel("Address");
        txtAddress = new JTextField(25);

        JLabel city = new JLabel("City");
        txtCity = new JTextField(25);

        JLabel description = new JLabel("Description");
        txtDescription = new JTextField(25);

        province = new JLabel("Province");
        txtProvince = new JTextField(25);

        zip = new JLabel("Zip");
        txtZip = new JTextField(25);

        JLabel product = new JLabel("Product Code");
        JTextField txtProduct = new JTextField(25);

        JLabel invoiceNumber = new JLabel("Invoice Number");
        txtInvoiceNumber = new JTextField(25);

        JLabel customerNumber = new JLabel("Customer Number");
        txtCustomerNumber = new JTextField(25);

        //using JSpinner to have a field with scrolling buttons  
        JLabel productBought = new JLabel("Product Bought");
        String bought[] = {"\'Text Area\'-invoice product and quantity displayed here", "SECOND TEXT"};
        SpinnerModel model = new SpinnerListModel(bought);
        spinner1 = new JSpinner(model);

        JLabel quantity = new JLabel("Quantity");
        JTextField txtQuantity = new JTextField(25);

        JLabel payment = new JLabel("Payment");
        txtPayment = new JTextField(25);

        JLabel price = new JLabel("Price");
        txtPrice = new JTextField(25);

        JLabel deposit = new JLabel("Deposit");
        txtDeposit = new JTextField(25);

        JLabel allProducts = new JLabel("All Products: ");
        box = new JComboBox();
        //define the panel
        JPanel panel = new JPanel();
        this.setLayout(new GridLayout(1, 2));
        //set the background layout 
        panel.setBackground(Color.CYAN);
        //size of the grid layout 
        GridLayout grid = new GridLayout(19, 19);
        //define the panel of each textfield and label
        panel.setLayout(grid);
        panel.add(name);
        panel.add(name);
        panel.add(txtName);
        panel.add(address);
        panel.add(txtAddress);
        panel.add(city);
        panel.add(txtCity);
        panel.add(description);
        panel.add(txtDescription);
        panel.add(province);
        panel.add(txtProvince);
        panel.add(zip);
        panel.add(txtZip);
        panel.add(product);
        panel.add(txtProduct);
        panel.add(invoiceNumber);
        panel.add(txtInvoiceNumber);
        panel.add(customerNumber);
        panel.add(txtCustomerNumber);
        panel.add(productBought);
        panel.add(spinner1);
        panel.add(quantity);
        panel.add(txtQuantity);
        panel.add(payment);
        panel.add(txtPayment);
        panel.add(price);
        panel.add(txtPrice);
        panel.add(deposit);
        panel.add(txtDeposit);
        panel.add(allProducts);
        panel.add(box);

        //panel for buttons
        panel.add(button1);
        panel.add(button5);
        panel.add(button2);
        panel.add(buttonAddInvoice);
        panel.add(button3);
        panel.add(button7);
        panel.add(button4);
        panel.add(button8);

        //buttons with actionListener
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        buttonAddInvoice.addActionListener(this);
        button7.addActionListener(this);
        button8.addActionListener(this);
        txtCustomerNumber.addActionListener(this);
        buttonAddInvoice.setEnabled(false);

        getContentPane().add(panel);
        pack();
        setResizable(true);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ElectronicInvoiceEntry electro = new ElectronicInvoiceEntry();
        electro.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object obj = ae.getSource();
        JButton bt = (JButton) (obj);
        //text is the name of the button. We gonna use the text to retrieve the string name of the button 
        String text = bt.getText();
        System.out.println(text);
        String sqlQuerry = "";

        String invoiceNumber = txtInvoiceNumber.getText();
        switch (text) {
            case "Add Customer":
                if (checkInsertCustomer()) {
                    String name = txtName.getText();
                    String address = txtAddress.getText();
//                    String cstNum = txtCustomerNumber.getText();
                    String deposit = txtDeposit.getText();
                    String city = txtCity.getText();
                    String provinceN = txtProvince.getText();
                    String zipN = txtZip.getText();
                    String depositA = txtDeposit.getText();
                    String[] colName = {"name", "address", "city", "province", "zip", "deposit"};
                    List colList = Arrays.asList(colName);
                    String[] arrValue = {name, address, city, provinceN, zipN, depositA};
                    List colValue = Arrays.asList(arrValue);
                    DatabaseConnection con = new DatabaseConnection();
                    con.connectToDatabase();
                    String tableName = "customer";
                    boolean success = con.inserRow(tableName, colList, colValue);
                    if (!success) {
                        JOptionPane.showMessageDialog(this, "succesuful", "NEW CUSTOMER", JOptionPane.INFORMATION_MESSAGE);
                        customerNumber = getCustomerId(name, address, depositA);
                        buttonAddInvoice.setEnabled(true);
//                        txtName.setText("");txtAddress.setText("");txtDeposit.setText("");txtCity.setText("");
//                        txtProvince.setText("");txtZip.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "Error", "NEW CUSTOMER", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case "Add Invoice":
                try {
                    DatabaseConnection con = new DatabaseConnection();
                    con.connectToDatabase();

                    sqlQuerry = "INSERT INTO `invoice` (  customer_Number, payment) VALUES ("
                            + "'" + customerNumber + "', '0')";
                    boolean re = con.stmt.execute(sqlQuerry);
                    if (!re) {
                        JOptionPane.showMessageDialog(this, "succesuful!", "INVOICE INPUT", JOptionPane.INFORMATION_MESSAGE);
                        buttonAddInvoice.setEnabled(false);
                        txtName.setText("");
                        txtAddress.setText("");
                        txtDeposit.setText("");
                        txtCity.setText("");
                        txtProvince.setText("");
                        txtZip.setText("");

                    } else {
                        JOptionPane.showMessageDialog(this, "unsuccesuful!", "INVOICE INPUT", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "UNKNOW ERROR", "INVOICE INPUT", JOptionPane.ERROR);
                }

                break;
            case "Show Invoice":

                DatabaseConnection db = new DatabaseConnection();
                db.connectToDatabase();
                boolean valid = checkInvoiceInput();
                if (valid) {
                    String invoiceN = txtInvoiceNumber.getText();

                    List<Customer> customerL = db.getCustomerDetailFromInvoice(Integer.parseInt(invoiceN));
                    List<LineItem> lineItemList = db.getLineItemWithInvoiceNumber(Integer.parseInt(invoiceN));
                    txtName.setText(customerL.get(0).getName());
                    txtAddress.setText(customerL.get(0).getAddress());
                    txtCity.setText(customerL.get(0).getCity());
                    txtProvince.setText(customerL.get(0).getProvince());
                    txtZip.setText(customerL.get(0).getZip());
                    txtCustomerNumber.setText(customerL.get(0).getCustomer_Number() + "");
                    txtDeposit.setText(customerL.get(0).getDeposit() + "");
                    List<String> dta = new ArrayList<>();
                    for (LineItem it : lineItemList) {
                        String word = it.getProduct_code() + " X " + it.getQuantity();
                        dta.add(word);
                    }
                    String[] textSpinner = new String[dta.size()];
                    for (int i = 0; i < dta.size(); i++) {
                        textSpinner[i] = dta.get(i);
                    }
                    SpinnerModel model = new SpinnerListModel(textSpinner);
                    spinner1.setModel(model);
                    spinner1.repaint();
                    spinner1.revalidate();
                    this.repaint();
                    this.revalidate();
                }
                break;
            case "Write Invoice":
                // case "Exit":
                boolean valid1 = checkInvoiceInput();
                String invoiceN = txtInvoiceNumber.getText();
                if (valid1) {
                    DatabaseConnection db1 = new DatabaseConnection();
                    db1.connectToDatabase();
                    List<Customer> customerL = db1.getCustomerDetailFromInvoice(Integer.parseInt(invoiceN));
                    List<LineItem> lineItemList = db1.getLineItemWithInvoiceNumber(Integer.parseInt(invoiceN));
                    String textFile = "";
                    for (int i = 51; i > 0; i--) {
                        textFile += "*";
                    }
                    textFile += "\n";
                    textFile += "Name: " + customerL.get(0).getName() + "\n";
                    textFile += "Address: " + customerL.get(0).getAddress() + "\n";
                    textFile += "City: " + customerL.get(0).getCity() + "\n";
                    textFile += "Province: " + customerL.get(0).getProvince() + "\n";
                    textFile += "Zip: " + customerL.get(0).getZip() + "\n";
                    textFile += "Deposit: " + customerL.get(0).getDeposit() + "\n";
                    textFile += "Product Bought: ";
                    for (LineItem it : lineItemList) {
                        textFile += it.getProduct_code() + " x " + it.getQuantity() + " ";
                    }
                    textFile += "\n";
                    textFile += "Customer Number: " + customerL.get(0).getCustomer_Number() + "\n";
                    textFile += "Invoice Number: " + invoiceN + "\n";
                    for (int i = 51; i > 0; i--) {
                        textFile += "*";
                    }
                    WriteTofile w = new WriteTofile(textFile, invoiceN);
                    w.openFile();
                }
                break;

            case "List Product":
                DatabaseConnection conDb = new DatabaseConnection();
                conDb.connectToDatabase();
                List<Product> productL = conDb.getProductAllData();
                if (box.getItemCount() > 0) {
                    box.removeAllItems();
                }
                for (Product pr : productL) {
                    box.addItem(pr.getProduct_Code());//+ "  |  " + pr.getDescription()+ "  @  R"+ pr.getPrice());
                }

                break;
            case "Find Products":
                DatabaseConnection cDb = new DatabaseConnection();
                cDb.connectToDatabase();
                if (box.getItemCount() > 0) {
                    String prod = box.getSelectedItem().toString();
                    List<Product> productLis = cDb.getProductFromProductCode(prod);
                    txtDescription.setText(productLis.get(0).getDescription());
                    txtPrice.setText("R " + productLis.get(0).getPrice() + "");
                } else {
                    JOptionPane.showMessageDialog(this, "NO PRODUCT SELECTED!", "INPUT ERROR", JOptionPane.WARNING_MESSAGE);
                }

                break;
            case "Exit":
                System.exit(0);
                break;
            case "Next":

                ElectromicInvoice elect = new ElectromicInvoice(this, true);
                elect.setSize(new Dimension(500, 400));
                elect.setVisible(true);

                break;
        }

    }

    private String getCustomerId(String custName, String customerAddress, String deposit) {
        String[] colName = {"customer_Number"};
        List whCol = Arrays.asList(colName);
        String[] whereColumStr = {"name", "address", "deposit"};
        List whereColumnList = Arrays.asList(whereColumStr);
        String[] whereColVal = {custName, customerAddress, deposit};
        List whereColValue = Arrays.asList(whereColVal);
        DatabaseConnection dbc = new DatabaseConnection();
        dbc.connectToDatabase();
        whCol = dbc.readParticular("customer", whCol, whereColumnList, whereColValue);
        if (whCol.isEmpty()) {
            return "";
        }
        Map value = (Map) whCol.get(0);
        return (value.get("customer_Number")).toString();
    }

    private boolean checkInsertCustomer() {
        boolean name = true;
        String nameRej = "";
        boolean address = true;
        String addressRej = "";
        boolean city = true;
        String cityRej = "";
        boolean province = true;
        String provinceRej = "";
        boolean zip = true;
        String zipRej = "";
        boolean deposit = true;
        String depositRej = "";
        boolean exist = true;
        String existRej = "";
///
        if (txtName.getText().isEmpty()) {
            name = false;
            nameRej = "Empty Name!";
        }
        if (txtName.getText().length() > 40) {
            name = false;
            nameRej = "Too long Name!";
        }
        if (txtAddress.getText().isEmpty()) {
            address = false;
            addressRej = "Empty Address!";
        }
        if (txtAddress.getText().length() > 40) {
            address = false;
            addressRej = "Too long Address!";
        }
        if (txtCity.getText().isEmpty()) {
            city = false;
            cityRej = "Empty City!";
        }
        if (txtCity.getText().length() > 30) {
            city = false;
            cityRej = "Too long City!";
        }
        if (txtProvince.getText().isEmpty()) {
            province = false;
            provinceRej = "Empty province!";
        }
        if (txtProvince.getText().length() > 5) {
            province = false;
            provinceRej = "Too long province!";
        }
        if (txtZip.getText().isEmpty()) {
            zip = false;
            zipRej = "Empty zip!";
        }
        if (txtZip.getText().length() > 4) {
            zip = false;
            zipRej = "Too long zip!";
        }
        if (txtDeposit.getText().isEmpty()) {
            deposit = false;
            depositRej = "Empty Deposit!";
        }
        try {
            Double.parseDouble(txtDeposit.getText());
        } catch (Exception e) {
            deposit = false;
            depositRej = "The Deposit is invalid!";
        }
        if (!(txtName.getText().isEmpty() && txtAddress.getText().isEmpty() && txtDeposit.getText().isEmpty())) {
            String nt = getCustomerId(txtName.getText(), txtAddress.getText(), txtDeposit.getText());
            if (!nt.isEmpty()) {
                exist = false;
                existRej = "This customer exist!";
            }
        }

        if (!(deposit && zip && province && city && address && name && exist)) {
            String text = "";
            text = raison(text, depositRej, deposit);
            text = raison(text, zipRej, zip);
            text = raison(text, provinceRej, province);
            text = raison(text, cityRej, city);
            text = raison(text, addressRej, address);
            text = raison(text, nameRej, name);
            text = raison(text, existRej, exist);
            JOptionPane.showMessageDialog(this, text, "ERROR INPUT", JOptionPane.ERROR_MESSAGE);
        }
        return deposit && zip && province && city && address && name && exist;

    }

    private boolean checkInvoiceInput() {
        boolean invoice = true;
        String reject = "";

        if (txtInvoiceNumber.getText().isEmpty()) {
            invoice = false;
            reject = "Empty Invoice number!";
        }
        for (char chr : txtInvoiceNumber.getText().toCharArray()) {
            if (Character.isAlphabetic(chr)) {
                invoice = false;
                reject = "The invoice number contain some invalid characteres";
            }
        }
        if (!invoice) {
            String text = "";
            text = raison(text, reject, invoice);
            JOptionPane.showMessageDialog(this, text, "ERROR INPUT", JOptionPane.ERROR_MESSAGE);
        }
        return invoice;
    }

    private String raison(String text, String rs, boolean t) {
        String pass = "";
        if (!t) {
            text += rs + "\n";
        }
        return text;
    }

    private class WriteTofile {

        private String data;
        private String invoiceNumber;

        public WriteTofile(String data, String invoiceNumber) {
            this.data = data;
            this.invoiceNumber = invoiceNumber;
        }

        public void openFile() {
            try {

                File file = new File("Invoice_" + invoiceNumber + "_file.txt");
//         FileWriter fileWriter = new FileWriter("qdata.txt");
                FileOutputStream output = new FileOutputStream(file);

                BufferedWriter bfWriter = new BufferedWriter(new OutputStreamWriter(output));
                String[] token = getData().split("\n");

                for (String line : token) {
//            System.out.println(line);
                    bfWriter.write(line);
                    bfWriter.newLine();
                }
                bfWriter.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "TEXT NOT FUND", "ERROR INPUT", JOptionPane.ERROR_MESSAGE);
            }

        }

        public String getData() {
            return data;
        }

    }

    private class ElectromicInvoice extends JDialog implements ActionListener {

        private JPanel paneInvoice;
        private JButton btBalance;
        private JButton btCalcPayDep;
        private JButton btDeposit;
        private JButton btTransc;
        private JLabel transName;
        private JTextField txtName;
        private JLabel transCstNumber;
        private JTextField txtTranscCstNumber;
        private JLabel transBalance;
        private JTextField txtTransBal;
        private double depositA = 0.0;
        private int customerNumberA = 0;
        private double Deposit = 0.0;

        public ElectromicInvoice(Frame parent, boolean modal) {
            super(parent, modal);
            this.setSize(new Dimension(500, 400));
            this.setModal(true);
            paneInvoice = new JPanel();
            this.paneInvoice.setBackground(Color.GREEN);
            paneInvoice.setLayout(new GridLayout(5, 2));
            btBalance = new JButton("CheckBalance");
            btBalance.addActionListener(this);

            btCalcPayDep = new JButton("Calculate Payment and Deposit");
            btCalcPayDep.addActionListener(this);
            btDeposit = new JButton("Deposit");
            btDeposit.addActionListener(this);
            btTransc = new JButton("Transaction");
            btTransc.addActionListener(this);
            transName = new JLabel("Name");
            txtName = new JTextField(15);
            txtName.setEditable(false);
            transCstNumber = new JLabel("Customer Number");
            txtTranscCstNumber = new JTextField(15);
//            txtTranscCstNumber.setEditable(false);
            transBalance = new JLabel("Balance");
            txtTransBal = new JTextField(15);
            txtTransBal.setEditable(false);

            paneInvoice.add(transName);
            paneInvoice.add(transCstNumber);
            paneInvoice.add(transBalance);

            paneInvoice.add(transName);
            paneInvoice.add(txtName);
            paneInvoice.add(transCstNumber);
            paneInvoice.add(txtTranscCstNumber);
            paneInvoice.add(transBalance);
            paneInvoice.add(txtTransBal);

            //inseting buttons 
            paneInvoice.add(btBalance);
            paneInvoice.add(btDeposit);
            paneInvoice.add(btCalcPayDep);
            paneInvoice.add(btTransc);

            ElectronicInvoice.add(paneInvoice);
            this.getContentPane().add(paneInvoice);
        }

        public void actionPerformed(ActionEvent ae) {
            Object source = ae.getSource();
            if (source == btBalance) {
                if (checkInvoiceInput()) {
                    DatabaseConnection db = new DatabaseConnection();
                    db.connectToDatabase();
                    List<Customer> custList = db.getCustomerDetailFromCustomerNumber(txtTranscCstNumber.getText());
                    txtName.setText(custList.get(0).getName());
                    depositA = custList.get(0).getDeposit();
                    txtTransBal.setText("R " + custList.get(0).getDeposit() + "");
                }
            } else if (source == btCalcPayDep) {
                String custN = txtTranscCstNumber.getText();

                if (!custN.isEmpty()) {
                    try {
                        customerNumberA = Integer.parseInt(custN);

                        DatabaseConnection ccn = new DatabaseConnection();
                        ccn.connectToDatabase();
                        List<Invoice> invoiceList = ccn.getInvoiceFromACustomer(custN);
                        for (Invoice in : invoiceList) {
                            List<LineItem> lineT = ccn.getLineItemWithInvoiceNumber(in.getInvoice_Number());
                            int quantity = 0;
                            double price = 0;
                            String invo = in.getInvoice_Number() + "";
                            String productDesc = "";
                            double payment = 0.0;
                            double newDeposit = 0.0;
                            double newPayemnt = 0.0;
                            for (LineItem it : lineT) {
                                List<Product> prd = ccn.getProductFromProductCode(it.getProduct_code());
                                productDesc = prd.get(0).getDescription();
                                quantity = it.getQuantity();
                                price = prd.get(0).getPrice();
                                payment = quantity * price;
                                Deposit = newDeposit = depositA - payment;
                                newPayemnt = payment + (quantity * price);
                                String display = "INVOICE NUMBER:     " + invo + "\n"
                                        + "Product Description:       " + productDesc + "\n"
                                        + "Quantity:                  " + quantity + "\n"
                                        + "Price:                     " + price + "\n"
                                        + "Payment:                   R " + payment + "\n"
                                        + "New Deposit:               R " + newDeposit + "\n"
                                        + "New payment:               R" + newPayemnt + "\n";
                                JOptionPane.showMessageDialog(this, display, "STEP", JOptionPane.INFORMATION_MESSAGE);
                                String[] whereKeyColumn = {"customer_Number"};
                                List whKeyCol = Arrays.asList(whereKeyColumn);
                                String[] whereKeyValCol = {custN};
                                List whKeyValueToCol = Arrays.asList(whereKeyValCol);
                                String[] colName = {"deposit"};
                                List whereToCol = Arrays.asList(colName);
                                String[] whereColValue = {newDeposit + ""};
                                List whreToUpColumnValue = Arrays.asList(whereColValue);
                                ccn.updateColum("customer", whKeyCol, whKeyValueToCol, whereToCol, whreToUpColumnValue);
                            }
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "WRONG CUSTOMER No!", "INPUT", JOptionPane.ERROR);
                    }
                }
//                String numberOfProduct = "";
//                int NberProduct;
//                int quantityProduct;
//                double priceProduct;
//                numberOfProduct = JOptionPane.showInputDialog(this, "Enter number of product", "PRODUCT NUMBER", JOptionPane.INFORMATION_MESSAGE);
//                String quantityStr = JOptionPane.showInputDialog(this, "Enter quantity", "PRODUCT QUANTITY", JOptionPane.INFORMATION_MESSAGE);
//                String priceStr = JOptionPane.showInputDialog(this, "Enter price", "PRODUCT PRICE", JOptionPane.INFORMATION_MESSAGE);
//                try {
//                    NberProduct = Integer.parseInt(numberOfProduct);
//                    quantityProduct = Integer.parseInt(quantityStr);
//                } catch (Exception e) {
//
//                }
            } else if (source == btDeposit) {
                if (!txtName.getText().isEmpty()) {
                    String query = "INSERT INTO account (name, customer_Number, payment) VALUES ('" + fixApostrophe(txtName.getText()) + "','"
                            + txtTranscCstNumber.getText() + "','" + depositA + "')";
                    DatabaseConnection con = new DatabaseConnection();
                    con.connectToDatabase();
                    try {
                        int rs = con.stmt.executeUpdate(query);
                        if (rs == 1) {
                            JOptionPane.showMessageDialog(this, "Successful!", "INPUT", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this, "DENY!", "INPUT", JOptionPane.ERROR);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ElectronicInvoiceEntry.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Enpty value!", "BAD INPUT", JOptionPane.ERROR);
                }

            } else if (source == btTransc) {

                String[] whereKeyColumn = {"customer_Number"};
                List whKeyCol = Arrays.asList(whereKeyColumn);
                String[] whereKeyValCol = {customerNumber + ""};
                List whKeyValueToCol = Arrays.asList(whereKeyValCol);
                String[] colName = {"deposit"};
                List whereToCol = Arrays.asList(colName);
                String[] whereColValue = {Deposit + ""};
                List whreToUpColumnValue = Arrays.asList(whereColValue);
                DatabaseConnection co = new DatabaseConnection();
                co.connectToDatabase();
                co.updateColum("account", whKeyCol, whKeyValueToCol, whereToCol, whreToUpColumnValue);
                JOptionPane.showMessageDialog(this, "Successful!", "INPUT", JOptionPane.INFORMATION_MESSAGE);

            }

        }

        public String fixApostrophe(String name) {
            String nameN = "";
            if (name.contains("'")) {
                String[] token = name.split("'");
                return (token[0] + "' '" + token[1]);
            }
            return name;
        }

        private boolean checkInvoiceInput() {
            boolean customerN = true;
            String reject = "";

            if (txtTranscCstNumber.getText().isEmpty()) {
                customerN = false;
                reject = "Empty customer number!";
            }
            for (char chr : txtTranscCstNumber.getText().toCharArray()) {
                if (Character.isAlphabetic(chr)) {
                    customerN = false;
                    reject = "The customer number contain some invalid characteres";
                }
            }
            if (!customerN) {
                String text = "";
                text = raison(text, reject, customerN);
                JOptionPane.showMessageDialog(this, text, "ERROR INPUT", JOptionPane.ERROR_MESSAGE);
            }
            return customerN;
        }

    }
}
