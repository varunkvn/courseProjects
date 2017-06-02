import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class delete1 extends HttpServlet
{
   Connection con;
   String sql,Error,str;
   Statement st,st1,st2;
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
         Error="Exception in init "+e;
      }
   }
   public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
   {
       PrintWriter out=res.getWriter();
       res.setContentType("text/html");
       str=req.getParameter("fno");
       try
       {
           st=con.createStatement();
           int x=st.executeUpdate("delete from flight_master where flight_no='"+str+"'");
           st1=con.createStatement();
           int y=st1.executeUpdate("delete from flight_desc where flight_no='"+str+"'");
           st2=con.createStatement();
           int z=st2.executeUpdate("delete from flight_details where flight_no='"+str+"'");
           con.commit();
           out.println("<html><body>");
           out.println("<center>Flight Details Have Been Deleted</center>");
           out.println("<center><a href=\"http://localhost:8080/servlet/delete\">[ Back ] </a></center>");
           out.println("</body></html>");
        }
        catch(Exception e)
        {
           out.println("Exception in connection "+e);
        }
    }
}
