import java.io.IOException;
import java.net.InetAddress;
import java.rmi.server.*;
import java.rmi.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;


public class Book extends UnicastRemoteObject implements RMI {
	protected Book() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	Statement st;
	Connection con;
	private static final long serialVersionUID = 1L;
	int search;
	int lookup;
	int in=0;
	int bookid;
	String bookname;
int noofitems;
	double rate;
    String	topic;
	int order1,order2,order3,order4,order;
	static int goodorder=0;
	static int failedorder=0;
	static long searchtime=0;
	static long lookuptime=0;
	static long ordertime=0;
	static int stock1,stock2,stock3,stock4;
	static int item1,item2,item3,item4;
	static int noofavailablebooks1=0;
	static int noofavailablebooks2=0;
	static int noofavailablebooks3=0;
	static int noofavailablebooks4=0;
	ArrayList al=new ArrayList();
	InetAddress ar[]=new InetAddress[10];

// Method to get data from the text file 
synchronized public ArrayList getBook() throws IOException, ClassNotFoundException,RemoteException
	{	
	Connection con=Database.getInstance().getConnection();
	
	
	
	  try {
	       	String sql="select *from book";
	      Statement st = con.createStatement();
				ResultSet rs= st.executeQuery(sql);
				while(rs.next()){
					
					
					bookid=	rs.getInt("bookid");
					bookname=rs.getString("bookname");
				     noofitems=	rs.getInt("noofitems");
					rate=rs.getDouble("cost");
					topic=rs.getString("topic");
					al.add(new Object(bookid,bookname,noofitems,rate,topic));
			
			            }
	                     }
				catch (SQLException e) {
				
				e.printStackTrace();
			}

return al;
}
	
//Search method to search a string int he txt file based on the keyword.
public synchronized ArrayList getBookByName(String name)throws IOException, ClassNotFoundException
	{
	ArrayList books=new ArrayList();
	long l1=System.nanoTime();
	
	
	try {
	      Connection con = Database.getInstance().getConnection();
	     Statement st =con.createStatement();
	    		 
	      
	      ResultSet rs = st.executeQuery("SELECT * FROM book WHERE bookname LIKE '%"+name+"%'");
	 System.out.println("resultset "+rs);
	      while (rs.next()) {
	        Object o = new Object();
	        o.setId(rs.getInt("bookid"));
	        o.setTopic(rs.getString("topic"));
	        o.setName(rs.getString("bookname"));
	        o.setNoofitems(rs.getInt("noofitems"));
	        o.setCost(rs.getDouble("cost"));
	        books.add(o);
	      }
	 
	      st.close();
	    } catch (SQLException se) {
	      System.out.println(se);
	    }
	  
	search++;

	long l2=System.nanoTime();
	searchtime=l2-l1;
	return books;

	}


public synchronized ArrayList getBookData(int itemno)throws IOException, ClassNotFoundException
{
	ArrayList books=new ArrayList();
	long l1=System.nanoTime();
	
	
	try {
	      Connection con = Database.getInstance().getConnection();
	     Statement st =con.createStatement();
	    		 
	      
	      ResultSet rs = st.executeQuery("SELECT * FROM book WHERE bookid ="+itemno);
	 System.out.println("resultset "+rs);
	      while (rs.next()) {
	        Object o = new Object();
	        o.setId(rs.getInt("bookid"));
	        o.setTopic(rs.getString("topic"));
	        o.setName(rs.getString("bookname"));
	        o.setNoofitems(rs.getInt("noofitems"));
	        o.setCost(rs.getDouble("cost"));
	        books.add(o);
	      }
	 
	      st.close();
	    } catch (SQLException se) {
	      System.out.println(se);
	    }
	  
	search++;

	long l2=System.nanoTime();
	searchtime=l2-l1;
	return books;
}


public synchronized double bill(int itmno,int noofcopies)throws IOException, ClassNotFoundException
{
	try {
		ArrayList books=new ArrayList();
	      Connection con = Database.getInstance().getConnection();
	     Statement st =con.createStatement();  
			try
			{
			ResultSet rs = st.executeQuery("SET SQL_SAFE_UPDATES=0;");
		    int rs1 = st.executeUpdate("UPDATE book SET noofitems=noofitems-"+noofcopies+" WHERE bookid="+itmno+"");
		    ResultSet rs2 = st.executeQuery("select noofcopies from book where id="+bookid);
		    System.out.println(rs2);
		    while (rs.next()) {
		        Object o = new Object();
		        o.setId(rs.getInt("bookid"));
		        o.setTopic(rs.getString("topic"));
		        o.setName(rs.getString("bookname"));
		        o.setNoofitems(rs.getInt("noofitems"));
		        o.setCost(rs.getDouble("cost"));
		        books.add(o);
		      }
		    
			}
			catch(Exception e)
			{
				
			}
	      
	}
	catch(Exception e)
	{
		
	}
	double totalbill=0;
	ArrayList sb=getBookData(itmno);
	for(int p=0;p<sb.size();p++)
	{	   
		Object i=(Object) sb.get(p);
	if(i.getId()==itmno){

	if(itmno==1)
		{
			totalbill= 10*noofcopies;
			stock1=stock1-noofcopies;
			goodorder++;
			try
			{
			ResultSet rs = st.executeQuery("SET SQL_SAFE_UPDATES=0;");
		      ResultSet rs1 = st.executeQuery("UPDATE book SET noofitems=noofitems-"+noofcopies+" WHERE bookid="+itmno+"");
			}
			catch(Exception e)
			{
				
			}
			
		}
	else if(itmno==2)
	{
		totalbill=15*noofcopies;
		stock2=stock2-noofcopies;
		goodorder++;
		try
		{
		ResultSet rs = st.executeQuery("SET SQL_SAFE_UPDATES=0;");
	      ResultSet rs1 = st.executeQuery("UPDATE book SET noofitems=noofitems-"+noofcopies+" WHERE bookid="+itmno+"");
		}
		catch(Exception e)
		{
			
		}
	}

	else if(itmno==3)
	{
		totalbill= 15*noofcopies;
		stock3=stock3-noofcopies;
		goodorder++;
		try
		{
		ResultSet rs = st.executeQuery("SET SQL_SAFE_UPDATES=0;");
	      ResultSet rs1 = st.executeQuery("UPDATE book SET noofitems=noofitems-"+noofcopies+" WHERE bookid="+itmno+"");
		}
		catch(Exception e)
		{
			
		}
	}
	else if(itmno==4)
	{
		totalbill= 14*noofcopies;
		stock4=stock4-noofcopies;
		goodorder++;
		try
		{
		ResultSet rs = st.executeQuery("SET SQL_SAFE_UPDATES=0;");
	      ResultSet rs1 = st.executeQuery("UPDATE book SET noofitems=noofitems-"+noofcopies+" WHERE bookid="+itmno+"");
		}
		catch(Exception e)
		{
			
		}
	}
	}}
	return totalbill;
	
	
}
	public String reportGoodOrds(int noofcopies)
{	
			return "No of books available "+Integer.toString(noofcopies);
	
	}
	public String reportFailOrds()
{
	return " No of Succesful Orders "+goodorder+" \n No of orders failed is = "+failedorder;	
}





}