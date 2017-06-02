import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class payment extends HttpServlet
{
   Connection con;
   Statement st;
   ResultSet rst;
   String str,rno,amt,total,date;
   String month[]={"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};
   int dt,mo,ye;
   public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
   {
       PrintWriter out=res.getWriter();
       res.setContentType("text/html");
       rno=req.getParameter("rno");
       total=req.getParameter("total");
       Calendar c=Calendar.getInstance();
       dt=c.get(Calendar.DAY_OF_MONTH);
       mo=c.get(Calendar.MONTH);
       ye=c.get(Calendar.YEAR);
       date=(dt+"-"+month[mo]+"-"+ye);
       try
       {
          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
          con=DriverManager.getConnection("jdbc:odbc:DSN","scott","tiger");
          st=con.createStatement();
          rst=st.executeQuery("select nvl(max(invoice_no),0)+1 from payment_details");
          while(rst.next())
          str=rst.getString(1);
          out.println("<html><body>");
          out.println("<center><table>");
          out.println("<caption><font size=5><b><u>Payment Details</u></b></font></caption>");
          out.println("<form method=post action=\"http://localhost:8080/servlet/pinsert\">");
          out.println("<tr><td><label>Invoice No:</label></td><td><input size=20 name=ino value="+str+" readonly></td>");
          out.println("<td><label>Res No:</label></td><td><input size=20 name=rno value="+rno+" readonly></td></tr>");
          out.println("<tr><td><label>Amount:</label></td><td><input size=20 name=amt value="+total+" readonly></td>");
          out.println("<td><label>Date:</label></td><td><input size=20 name=date value="+date+" readonly></td></tr>");
          out.println("<tr><td><label>Mode:</label></td><td><input type=radio name=mode value=credit>:Credit Card</td>");
          out.println("<td><input type=radio name=mode value=cheque>:Cheque</td>");
          out.println("<table><tr><td align=center><input type=submit value=\" Insert \"></td></tr></table>");
          out.println("</form>");
          out.println("</tabel>");
          out.println("</body></html>");
       }
       catch(Exception e)
       {
          out.println("Exception in connection:"+e);
       }
   }
}





