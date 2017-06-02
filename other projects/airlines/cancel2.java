import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class cancel2 extends HttpServlet
{
   Connection con;
   Statement st;
   ResultSet rst;
   String  str;
   public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
   {
       PrintWriter out=res.getWriter();
       res.setContentType("text/html");
       try
       {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con=DriverManager.getConnection("jdbc:odbc:DSN","psrini","ssi");
            out.println("<html><body>");
            out.println("<center><form method=post action=\"http://localhost:8080/servlet/delete2\">");
            out.println("<h3><u>Select Cancellation Code To Be Deleted</u></h3>");
            out.println("Cancellation Code:<select size=multiple name=code>");
            st=con.createStatement();
            rst=st.executeQuery("select code from cancellation_master");
            while(rst.next())
            {
                str=rst.getString("code");
                out.println("<option>");
                out.println(str);
            }
            out.println("</select>");
            out.println("<br><br>");
            out.println("<input type=submit value=\" Delete \">");
            out.println("</form>");
            out.println("</body></html>");
        }
        catch(Exception e)
        {
            out.println("Exception in connection:"+e);
        }
    }
}

