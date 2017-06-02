import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class login1 extends HttpServlet
{
    Connection con;
    Statement st;
    ResultSet rst;
    String user,pass,str,str1;
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
       PrintWriter out=res.getWriter();
       res.setContentType("text/html");
       user=req.getParameter("user");
       pass=req.getParameter("pass");
       try
       {
          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
          con=DriverManager.getConnection("jdbc:odbc:DSN","scott","tiger");
          st=con.createStatement();
          rst=st.executeQuery("select * from admin");
          int x=0;
          while(rst.next())
          {
             str=rst.getString("userid");
             str1=rst.getString("passwd");
             if(user.equals(str) && pass.equals(str1))
             {
                 x=1;
             }
          }
          if(x == 1)
          res.sendRedirect("http://localhost:8080/servlet/admin");
          else
          {
              out.println("<html><body>");
              out.println("<center><br>");
              out.println("<h3>Password is incorrect Please check the password</h3>");
              out.println("<a href=\"http://localhost:8080/servlet/login\">[ Back ]</a>");
              out.println("</center>");
              out.println("</body></html>");
          }
       }
       catch(Exception e)
       {
          out.println("Exception in connection:"+e); 
       }
    }
}
