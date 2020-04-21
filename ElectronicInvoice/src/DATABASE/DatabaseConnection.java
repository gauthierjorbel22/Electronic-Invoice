package DATABASE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DatabaseConnection {

    private Connection connection;
    public Statement stmt = null;

    public Connection getConnection() {
        return connection;
    }

    public boolean connectToDatabase() {
        boolean isLoopping = true;
        boolean connected = false;
        int count = 0;
        while (isLoopping) {
            try {
                isLoopping = false;
                connection = DriverManager.getConnection(CONSTANT.DATABASE_URL, CONSTANT.DATABASE_USERNAME, CONSTANT.DATABASE_PASSWORD);
                stmt = connection.createStatement();
//                stmt.executeUpdate("set global max_connection = 100000");
                connected = true;
                isLoopping = false;
            } catch (com.mysql.jdbc.exceptions.jdbc4.CommunicationsException e) {
                isLoopping = true;
            } catch (SQLException e) {
                isLoopping = true;
                System.err.println(e);
                count++;
                if (count > 10) {
                    return false;
                }

            }

        }
        return connected;
    }

    public boolean inserRow(String tableName, List<String> rowColName, List<String> rowValue) {
        String sql = "INSERT INTO " + tableName + " (";
        for (int i = 0; i < rowColName.size(); i++) {
            sql += rowColName.get(i) + (i < rowColName.size() - 1 ? " , " : " ");
        }
        sql += " ) VALUES ('";
        for (int i = 0; i < rowValue.size(); i++) {
            sql += rowValue.get(i) + (i < rowValue.size() - 1 ? "', '" : "')");
        }
        try {
            return stmt.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            //close(this.connection, this stmt);

        }
        return false;
    }

    public List readParticular(String tableName, List<String> whichColumn, List<String> whereColumn, List<String> whereValue) {
        String sql = "SELECT ";
        for (int i = 0; i < whichColumn.size(); i++) {
            sql += "`" + whichColumn.get(i) + (i < whichColumn.size() - 1 ? "` ," : "` ");
        }
        sql += " FROM `" + tableName + "` WHERE ";
        for (int i = 0; i < whereColumn.size(); i++) {
            sql += whereColumn.get(i) + " = '" + whereValue.get(i) + (i < whereColumn.size() - 1 ? "' and " : "'");
        }
        List result = new ArrayList<>();
        try {
            ResultSet rs;
            if (stmt.execute(sql)) {
                rs = stmt.getResultSet();
                 Map map = new LinkedHashMap<>();
                while (rs.next()) {
                   
                    for (String whichColumn1 : whichColumn) {
                        map.put(whichColumn1, rs.getString(whichColumn1));
                    }
                    result.add(map);
                }
            }
        } catch (SQLException ex) {

        }
        return result;
    }
    public String quoteFix(String sql) {
        char[] sqlchar = (sql.toCharArray());
        char[] newsqlChar = new char[sqlchar.length + 1];
        if (sql.contains("'")) {
            int indexQ = sql.indexOf('\'');

            for (int i = 0, t = 0; i < sqlchar.length; t++, i++) {
                if (i == indexQ) {
                    newsqlChar[t] = (char) (92);
                    newsqlChar[t + 1] = '\'';
                    t++;
                    continue;
                }
                newsqlChar[t] = sqlchar[i];
            }
        } else {
            return sql;
        }
        return new String(newsqlChar);
    }
    
     public boolean updateColum(String tableName, List<String> whereKeyColumnName, List<String> KeyValuewhere, List<String> colWhereToUpdate, List<String> valuesOfcolmnUpdate) {
        String sql = "UPDATE " + tableName + " SET ";
        String subSQL = "";
        for (int i = 0; i < colWhereToUpdate.size(); i++) {
            subSQL += colWhereToUpdate.get(i) + " = '" + quoteFix(valuesOfcolmnUpdate.get(i)) + (i < colWhereToUpdate.size() - 1 ? "' , " : "' ");
        }
        subSQL += " WHERE ";
        for (int i = 0; i < whereKeyColumnName.size(); i++) {
            subSQL += whereKeyColumnName.get(i) + " = '" + quoteFix(KeyValuewhere.get(i)) + (i < whereKeyColumnName.size() - 1 ? "' and " : "'");
        }
        try {
            //        System.out.println(sql + subSQL);
            return stmt.execute(sql + subSQL);
        } catch (SQLException ex) {
          
//        }finally{
////            close(this.connection, this.stmt);
        }
        return false;
    }

    public List getAccountAllData() {
        List accountList = new ArrayList<>();
        String sqlQuerry = "SELECT * FROM `account`";
        Account account;
        try {
            if (stmt.execute(sqlQuerry)) {
                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    account = new Account(
                            rs.getString("name"),
                            rs.getInt("customer_Number"),
                            rs.getDouble("payment")
                    );
                    accountList.add(account);
                }
            } else {
                System.out.println("Querry Incorrect!");
            }
        } catch (SQLException e) {
        } finally {
//            this.connection.close();
        }
        return accountList;
    }

    public List getCustomerDetailFromInvoice(int invoice) {
        List customerList = new ArrayList<>();
        //select * from customer where customer_Number = (select customer_Number from invoice where invoice_Number = 11731);
        String sqlQuerry = "SELECT * FROM customer WHERE customer_Number = (SELECT customer_Number FROM invoice WHERE invoice_Number = " + invoice + ")";
        Customer customer;
        try {
            if (stmt.execute(sqlQuerry)) {
                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    customer = new Customer(
                            rs.getInt("customer_Number"),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("city"),
                            rs.getString("province"),
                            rs.getString("zip"),
                            rs.getDouble("deposit")
                    );
                    customerList.add(customer);
                }
            } else {
                System.out.println("Querry Incorrect!");
            }
        } catch (SQLException e) {
        } finally {
//            this.connection.close();
        }
        return customerList;
    }

    public List getCustomerDetailFromCustomerNumber(String customerNumber) {
        List customerList = new ArrayList<>();
        //select * from customer where customer_Number = (select customer_Number from invoice where invoice_Number = 11731);
        String sqlQuerry = "SELECT * FROM customer WHERE customer_Number = (" + customerNumber + ")";
        Customer customer;
        try {
            if (stmt.execute(sqlQuerry)) {
                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    customer = new Customer(
                            rs.getInt("customer_Number"),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("city"),
                            rs.getString("province"),
                            rs.getString("zip"),
                            rs.getDouble("deposit")
                    );
                    customerList.add(customer);
                }
            } else {
                System.out.println("Querry Incorrect!");
            }
        } catch (SQLException e) {
        } finally {
//            this.connection.close();
        }
        return customerList;
    }

    public List getLineItemAllData() {
        List LineItemList = new ArrayList<>();
        String sqlQuerry = "SELECT * FROM `lineitem`";
        LineItem lineItem;
        try {
            if (stmt.execute(sqlQuerry)) {
                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    lineItem = new LineItem(
                            rs.getInt("invoice_Number"),
                            rs.getString("product_Code"),
                            rs.getInt("quantity")
                    );
                    LineItemList.add(lineItem);
                }
            } else {
                System.out.println("Querry Incorrect!");
            }
        } catch (SQLException e) {
        } finally {
//            this.connection.close();
        }
        return LineItemList;
    }

    public List getProductAllData() {
        List productList = new ArrayList<>();
        String sqlQuerry = "SELECT * FROM `product`";
        Product product;
        try {
            if (stmt.execute(sqlQuerry)) {
                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    product = new Product(
                            rs.getString("product_Code"),
                            rs.getString("description"),
                            rs.getDouble("price")
                    );
                    productList.add(product);
                }
            } else {
                System.out.println("Querry Incorrect!");
            }
        } catch (SQLException e) {
        } finally {
//            this.connection.close();
        }
        return productList;
    }

    public List getProductFromProductCode(String productCode) {
        List productList = new ArrayList<>();
        String sqlQuerry = "SELECT * FROM `product` WHERE product_Code = \"" + productCode + "\"";
        Product product;
        try {
            if (stmt.execute(sqlQuerry)) {
                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    product = new Product(
                            rs.getString("product_Code"),
                            rs.getString("description"),
                            rs.getDouble("price")
                    );
                    productList.add(product);
                }
            } else {
                System.out.println("Querry Incorrect!");
            }
        } catch (SQLException e) {
        } finally {
//            this.connection.close();
        }
        return productList;
    }

    public List getLineItemWithInvoiceNumber(int invoiceNumber) {
        List LineItemList = new ArrayList<>();
//        select * from lineItem where invoice_Number = ( 11731);
        String sqlQuerry = "SELECT * FROM `lineitem` WHERE invoice_Number = (" + invoiceNumber + ")";
        LineItem lineItem;
        try {
            if (stmt.execute(sqlQuerry)) {
                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    lineItem = new LineItem(
                            rs.getInt("invoice_Number"),
                            rs.getString("product_Code"),
                            rs.getInt("quantity")
                    );
                    LineItemList.add(lineItem);
                }
            } else {
                System.out.println("Querry Incorrect!");
            }
        } catch (SQLException e) {
        } finally {
//            this.connection.close();
        }
        return LineItemList;
    }

    public List getInvoiceAllData() {
        List ListInvoice = new ArrayList<>();
        String sqlQuerry = "SELECT * FROM `invoice`";
        Invoice invoice;
        try {
            if (stmt.execute(sqlQuerry)) {
                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    invoice = new Invoice(
                            rs.getInt("invoice_Number"),
                            rs.getInt("customer_Number"),
                            rs.getInt("payment")
                    );
                    ListInvoice.add(invoice);
                }
            } else {
                System.out.println("Querry Incorrect!");
            }
        } catch (SQLException e) {
        } finally {
//            this.connection.close();
        }
        return ListInvoice;
    }
    public List getInvoiceFromACustomer(String customerID) {
        List ListInvoice = new ArrayList<>();
        String sqlQuerry = "SELECT * FROM `invoice` where customer_Number = ("+ customerID+")";
        Invoice invoice;
        try {
            if (stmt.execute(sqlQuerry)) {
                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    invoice = new Invoice(
                            rs.getInt("invoice_Number"),
                            rs.getInt("customer_Number"),
                            rs.getInt("payment")
                    );
                    ListInvoice.add(invoice);
                }
            } else {
                System.out.println("Querry Incorrect!");
            }
        } catch (SQLException e) {
        } finally {
//            this.connection.close();
        }
        return ListInvoice;
    }

}
