import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class insert1 extends HttpServlet
{
 String Error,fno,fname,source,destination,totalcap,duration,v;
 Connection con;
 ResultSet rst;
 Statement st;
 PreparedStatement pst;
 public void init(ServletConfig config)throws ServletException
 {
     super.init(config);
     try
     {
       Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
       con=DriverManager.getConnection("jdbc:odbc:DSN","psrini","ssi");
     }
     catch(Exception e)
     {
      Error="Exception in init:"+e;
     }
 }
 public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
 {
     PrintWriter out=res.getWriter();
     res.setContentType("text/html");
     fno=req.getParameter("fno");
     fname=req.getParameter("fname");
     source=req.getParameter("src");
     destination=req.getParameter("dest");
     totalcap=req.getParameter("cap");
     duration=req.getParameter("dur");
     try
     {
           pst=con.prepareStatement("insert into flight_master values(?,?,?,?,?,?)");
           pst.setString(1,fno);
           pst.setString(2,fname);
           pst.setString(3,source);
           pst.setString(4,destination);
           pst.setString(5,totalcap);
           pst.setString(6,duration);
           pst.executeUpdate();
           res.sendRedirect("http://localhost:8080/servlet/add2?flight_no='"+fno+"',&cap="+totalcap+"");
     }
     catch(Exception e1)
     {
      out.println("Exception in connection:"+e1);
     }
  }
}





