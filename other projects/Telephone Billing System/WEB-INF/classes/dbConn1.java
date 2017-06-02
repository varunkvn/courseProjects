package chandu;
import java.sql.*;

public class dbConn1
  {
    // Member Variables
    private String m_DBLoc = "jdbc:odbc:wipro";
   private String m_DBDriver = "sun.jdbc.odbc.JdbcOdbcDriver"; 
    private ResultSet m_RS = null;  // RecordSet Variable
    private Connection m_conn = null; 
    
 public String setData(String uname, String cname, String occupation, String purpose, String gender, int income, String dob, String address, String state, int pin)	{
	String sqlInsSt = "INSERT INTO NEW_CONNECTION VALUES('"+uname+"','"+cname+"','"+occupation+"','"+purpose+"','"+gender+"',"+income+",'"+dob+"','"+address+"','"+state+"',"+pin+",seq.nextval)" ;
	int n = 0;
    	if(m_conn == null) // if Connection has not been set
		return "Connection failed" ;
	
	try {
             Statement  s = m_conn.createStatement();
             n = s.executeUpdate(sqlInsSt);
            }catch (SQLException e) {
			e.printStackTrace();} // if a SQL error occurs
        
    	if(n !=0) 
		return "Data inserted successfully" ;
	else
		return "Data insertion is failed" ;
	}	

public ResultSet getData()	{
	
	String sqlStatement = "SELECT UNAME, CNAME, PURPOSE, PNO FROM NEW_CONNECTION" ;
    	ResultSet temp = executeQuery(sqlStatement);  	
	return temp;		
	}

   //-------------------------------------------------------
    // Method executeQuery -- for executing queries.  Returns 
    //  a ResultSet (RecordSet) kind of like in ADO
    //-------------------------------------------------------
    public ResultSet executeQuery(String stmt)
    { 
        if(m_conn == null) // if Connection has not been set
          m_RS = null; 
        else
        {  try {
             Statement  s = m_conn.createStatement();
             m_RS = s.executeQuery(stmt);
           }
           catch (SQLException e) {e.printStackTrace();} // if a SQL error occurs
        }
        return(m_RS);
    }
    
//-----------------------------------------------------
    // Method DBConnect -- must connect to the DB before a 
    //  query can be executed.  Returns an error message
    //  to be printed by the caller if there is an error.
    //-----------------------------------------------------
    public String DBConnect()
    { 
      String retVal = ""; // there are no errors yet
      //Connection conn = null;
      try // try to connect to the database
      {  Class.forName(m_DBDriver);
         m_conn = DriverManager.getConnection(m_DBLoc,"scott","tiger");
      } 
      // if the driver class isn't found
      catch (ClassNotFoundException e) {retVal = e.toString();
	  e.printStackTrace();}
      catch (SQLException e) {retVal = e.toString();
	  e.printStackTrace();} // if a SQL error occurs
   
      return(retVal); // returns error messages or an empty string 
                      // that the caller must print.
    }
}


