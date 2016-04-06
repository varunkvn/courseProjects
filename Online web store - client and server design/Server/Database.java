import java.sql.*;
 

public final class Database {
 
  private static Database _instance = null;
  private Connection _con = null;
 
  public Database() {
    //Connect to Ms Access
    _con = getMySQLConnection();
  }
 
  //Thread safe instatiate method
  public static synchronized Database getInstance() {
    if (_instance == null) {
      _instance = new Database();
    }
    return _instance;
  }
 
  public Connection getConnection() {
    return _con;
  }
  private static Connection getMySQLConnection() {
    Connection con = null;
 
    //	Statement stmt1=null;
	Connection conn = null;
	
	try {
		 
		Class.forName("com.mysql.jdbc.Driver");
		
			   // Registers the given driver with the DriverManager.
			   DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			   // here the class is loaded

		}
			catch (SQLException e) {
			   e.printStackTrace();
			}

	 catch (ClassNotFoundException e) {

		
		e.printStackTrace();
		

	}
    try{
    	
String url = "jdbc:mysql://localhost:3306/";

conn = DriverManager.getConnection(url+"bookstore","root","Kamala@1");
  //  stmt1 = conn.createStatement();
DatabaseMetaData meta = conn.getMetaData();

    }
    catch(SQLException e) {
		System.out.println("Connection Failed! Check output console");
		e.printStackTrace();
		
	}
    return conn;
  }
 
}