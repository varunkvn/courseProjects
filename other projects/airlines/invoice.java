import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class invoice extends HttpServlet
{
    Connection con;
    String rno,ino,amt,fno,ddt,time,src,dest,date,d1,d2,d3,d4;
    String month[]={"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};
    Statement st;
    ResultSet rst,rst1;
    int dt,mo,ye,x,y,z;
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
       PrintWriter out=res.getWriter();
       res.setContentType("text/html");
       rno=req.getParameter("rno");
       ino=req.getParameter("ino");
       amt=req.getParameter("amt");
       Calendar c=Calendar.getInstance();
       dt=c.get(Calendar.DAY_OF_MONTH);
       mo=c.get(Calendar.MONTH);
       ye=c.get(Calendar.YEAR);
       date=(dt+"-"+month[mo]+"-"+ye);
       try
       {
          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
          con=DriverManager.getConnection("jdbc:odbc:DSN","psrini","ssi");
          st=con.createStatement();
          rst=st.executeQuery("select flight_no,departure_date,departure_time from passenger_master where res_no="+rno);
          while(rst.next())
          {
              fno=rst.getString("flight_no");
              ddt=rst.getString("departure_date");
              time=rst.getString("departure_time");
          }
          z=ddt.indexOf(" ");
          ddt=ddt.substring(0,z);
          x=ddt.indexOf("-");
          y=ddt.lastIndexOf("-");
          d1=ddt.substring(0,x);
          d2=ddt.substring(x+1,y);
          d3=ddt.substring(y+1,ddt.length());
          d4=(d3+"-"+d2+"-"+d1);
          rst1=st.executeQuery("select source,detination from flight_master where flight_no='"+fno+"'");
          while(rst1.next())
          {
              src=rst1.getString("source");
              dest=rst1.getString("detination");
          }
          out.println("<html><body>");
          out.println("<center><table>");
          out.println("<caption><font size=5><b><u>Invoice</u></b></font></caption>");
          out.println("<tr><td><label>Invoice No:</label></td><td><input size=20 name=ino value="+ino+" readonly></td>");
          out.println("<td><label>Date:</label></td><td><input size=20 name=date value="+date+" readonly></td></tr>");
          out.println("<tr><td><label>Res No:</label></td><td><input size=20 name=rno value="+rno+" readonly></td>");
          out.println("<td><label>Amount:</label></td><td><input size=20 name=amt value="+amt+" readonly></td></tr>");
          out.println("<tr><td><label>Source:</label></td><td><input size=20 name=src value="+src+" readonly></td>");
          out.println("<td><label>Destination:</label></td><td><input size=20 name=dest value="+dest+" readonly></td></tr>");
          out.println("<tr><td><label>Date Of Departure:</label></td><td><input size=20 name=ddt value="+d4+" readonly></td>");
          out.println("<td><label>Time Of Departure:</label></td><td><input size=20 name=time value="+time+" readonly></td></tr>");
          out.println("</table></center>");
          out.println("</body></html>");
       }
       catch(Exception e)
       {
          out.println("Exception in connection:"+e);
       }
    }
}
