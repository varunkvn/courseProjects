import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class insert2 extends HttpServlet
{
 String Error,fno,category,tickets,start,end,cap;
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
 public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
 {
     PrintWriter out=res.getWriter();
     res.setContentType("text/html");
     fno=req.getParameter("fno");
     category=req.getParameter("cat");
     tickets=req.getParameter("ticket");
     start=req.getParameter("start");
     end=req.getParameter("end");
     cap=req.getParameter("cap");
     try    
     {
                pst=con.prepareStatement("insert into flight_desc values(?,?,?,?,?)");
                pst.setString(1,fno);
                pst.setString(2,category);
                pst.setString(3,tickets);
                pst.setString(4,start);
                pst.setString(5,end);
                pst.executeUpdate();
                if(cap.equals(end))
                res.sendRedirect("http://localhost:8080/servlet/add3?flight_no='"+fno+"'");
                else
                {
                out.println("<html><body>");
                out.println("<center><br><br<br><br>Do You Want To Continue?</center>");
                out.println("<center><a href=\"http://localhost:8080/servlet/add?flight_no='"+fno+"'&capacity="+cap+"&ending_seatno="+end+"\">[Yes]</a><a href=\"http://localhost:8080/servlet/add3?flight_no='"+fno+"'\">[No]</a></center>");
                out.println("</body></html>");
                }

     }
     catch(Exception e1)
     {
      out.println("Exception in connection:"+e1);
     }
    }
}
