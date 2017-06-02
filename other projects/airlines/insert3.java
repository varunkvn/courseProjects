import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class insert3 extends HttpServlet
{
 String Error,fno,day,time;
 Connection con;
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
 public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
 {
     PrintWriter out=res.getWriter();
     res.setContentType("text/html");
     fno=req.getParameter("fno");
     day=req.getParameter("dof");
     time=req.getParameter("tof");
     try
     {
           pst=con.prepareStatement("insert into flight_details values(?,?,?)");
           pst.setString(1,fno);
           pst.setString(2,day);
           pst.setString(3,time);
           pst.executeUpdate();
           out.println("<html><body>");
           out.println("<center><br><br><br><br><br>Flight Details Have Been Inserted.<br>");
           out.println("Do You Want To Continue?<br>");
           out.println("<a href=\"http://localhost:8080/servlet/add3?flight_no='"+fno+"'\">[Yes]</a><a href=\"http://localhost:8080/servlet/admin\">[Administration]<a></center>");
      }
     catch(Exception e1)
     {
      out.println("Exception in connection:"+e1);
     }
    }

}
