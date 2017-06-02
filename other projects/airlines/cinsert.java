import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class cinsert extends HttpServlet
{
   Connection con;
   PreparedStatement pst,pst1;
   String code,des,per,d2;
   int d;
   public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
   {
      PrintWriter out=res.getWriter();
      res.setContentType("text/html");
      code=req.getParameter("code");
      des=req.getParameter("desc");
      d=des.indexOf("h");
      d2=des.substring(d,des.length());
      System.out.println("D2:"+d2);
      per=req.getParameter("percent");
      try
      {
          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
          con=DriverManager.getConnection("jdbc:odbc:DSN","psrini","ssi");
          if(d2.equals("h"))
          {
             System.out.println("Srinivas");
             pst1=con.prepareStatement("insert into cancellation_master (code,description1,percentage1)values(?,?,?)");
             pst1.setString(1,code);
             pst1.setString(2,des);
             pst1.setString(3,per);
             pst1.executeUpdate();
          }
          else
          {
             pst=con.prepareStatement("insert into cancellation_master (code,description,percentage)values(?,?,?)");
             pst.setString(1,code);
             pst.setString(2,des);
             pst.setString(3,per);
             pst.executeUpdate();
          }
          out.println("<html><body>");
          out.println("<center><br><br>");
          out.println("<h3>Do you want to continue</h3>");
          out.println("<a href=\"http://localhost:8080/servlet/cancel\">[ Yes ]</a><a href=\"http://localhost:8080/servlet/admin\">[ No ]</a>");
          out.println("</body></html>");
      }
      catch(Exception e)
      {
          out.println("Exception in connection:"+e);
      }
   }
}
