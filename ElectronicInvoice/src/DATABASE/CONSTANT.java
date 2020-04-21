
package DATABASE;


public class CONSTANT {
    
    public static String DATABASE_USERNAME = "root";
    public static String DATABASE_PASSWORD = "";
    public static String DATABASE_NAME = "orion";
    public static String DB_PORT_LOCAL_COMP = "3306";
    public static String DB_HOST_LOCAL_COMP = "localhost";
    public static String DATABASE_URL = "jdbc:mysql://"+DB_HOST_LOCAL_COMP +":"+DB_PORT_LOCAL_COMP+ "/"+ DATABASE_NAME + "?useSSL=false";
}
