package chandu;
import java.sql.*;

public class dbConn5
  {
    // Member Variables
    private String m_DBLoc = "jdbc:odbc:wipro";
   private String m_DBDriver = "sun.jdbc.odbc.JdbcOdbcDriver"; 
    private ResultSet m_RS = null;  // RecordSet Variable
    private Connection m_conn = null; 
    
 public String setData(String bno, int amt, String bank, String cardno)	{
	String sqlInsSt = "update bill_details set amount="+amt+",bank='"+bank+"',cardno='"+cardno+"',paiddate=sysdate where billno='"+bno+"'" ;
	//,BANK='"+bank+"',CARDNO='"+cardno+"',PAIDDATE=sysdate
	int n = 0;
    	if(m_conn == null) // if Connection has not been set
		return "Connection failed" ;
	
	try {
             Statement  s = m_conn.createStatement();
             n = s.executeUpdate(sqlInsSt);
            }catch (SQLException e) {
			e.printStackTrace();} // if a SQL error occurs
        
    	if(n !=0) 
		return "Data updation successfully" ;
	else
		return "Data insertion is failed" ;
	}	

 public String setData1(String pno, int bamt, String pdd)	{
	String sqlInsSt = "insert into bill_details(billno,pno,amount,paybydate) values(seq1.nextval,'"+pno+"',"+bamt+",'"+pdd+"')";
	//,BANK='"+bank+"',CARDNO='"+cardno+"',PAIDDATE=sysdate
	int n = 0;
    	if(m_conn == null) // if Connection has not been set
		return "Connection failed" ;
	
	try {
             Statement  s = m_conn.createStatement();
             n = s.executeUpdate(sqlInsSt);
            }catch (SQLException e) {
			e.printStackTrace();} // if a SQL error occurs
        
    	if(n !=0) 
		return "Data updation successfully" ;
	else
		return "Data insertion is failed" ;
	}	
public ResultSet getData()	{
	
	String sqlStatement = "SELECT UNAME, CNAME, PURPOSE, PNO FROM NEW_CONNECTION" ;
    	ResultSet temp = executeQuery(sqlStatement);  	
	return temp;		
	}
public ResultSet getData1()	
	{
	
	String sqlStatement = "SELECT billno,amount,paiddate FROM bill_details" ;
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


