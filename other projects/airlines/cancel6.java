import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class cancel6 extends HttpServlet
{
   Connection con;
   Statement st;
   ResultSet rst;
   String months[]={"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};
   int mno[]={1,2,3,4,5,6,7,8,9,10,11,12};
   String fno,ddt,dtime,cno,cdt,ctime,rno,d1,d2,d3,m;
   int a,b;
   public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
   {
      PrintWriter out=res.getWriter();
      res.setContentType("text/html");
      cno=req.getParameter("cno");
      cdt=req.getParameter("cdt");
      ctime=req.getParameter("ctime");
      rno=req.getParameter("rno");
      try
      {
          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
          con=DriverManager.getConnection("jdbc:odbc:DSN","psrini","ssi");
          st=con.createStatement();
          rst=st.executeQuery("select departure_date ,departure_time,flight_no from passenger_master where res_no="+rno);
          while(rst.next())
          {
               fno=rst.getString("flight_no");
               ddt=rst.getString("departure_date");
               dtime=rst.getString("departure_time");
          }
          int a1=ddt.indexOf(" ");
          ddt=ddt.substring(0,a1);
          int a=ddt.indexOf("-");
          int b=ddt.lastIndexOf("-");
          String d1=ddt.substring(0,a);
          String d2=ddt.substring(a+1,b);
          String d3=ddt.substring(b+1,ddt.length());
          int d4=Integer.parseInt(d2);
          for(int i=0;i<=mno.length;i++)
          {
             if(mno[i]==d4)
             {
                 m=months[i];
                 break ;
             }
          }

          ddt=(d3+"-"+m+"-"+d1);
          out.println("<html><body>");
          out.println("<center><table>");
          out.println("<form method=post action=\"http://localhost:8080/servlet/cinsert1\">");
          out.println("<caption><font size=5><b><u>Cancellation</u></b></font></caption>");
          out.println("<tr><td><label>Cancel No:</label></td><td><input type=text size=20 name=cno value="+cno+" readonly></td>");
          out.println("<td><label>Cancel Date:</label></td><td><input size=20 name=cdt value="+cdt+" readonly></td></tr>");
          out.println("<tr><td><label>Cancel Time:</label></td><td><input size=20 name=ctime value="+ctime+" readonly></td>");
          out.println("<td><label>Res No:</label></td><td><input size=20 name=rno value="+rno+"></td></tr>");
          out.println("<tr><td><label>Flight No:</label></td><td><input size=20 name=fno value="+fno+" readonly></td>");
          out.println("<td><label>Date Of Departure:</label></td><td><input size=20 name=ddt value="+ddt+" readonly></td></tr>");
          out.println("<tr><td><label>Seat No:</label></td><td><input size=20 name=sno></td>");
          out.println("<td><label>Time Of Departure:</label></td><td><input size=20 name=time value="+dtime+" readonly></td></tr>");
          out.println("<table><tr><td align=center><input type=submit value=\" Insert \"></td></tr></table>");
          out.println("</table>");
          out.println("</body></html>");
      }
      catch(Exception e)
      {
          out.println("Exception in connection:"+e);
      }
   }
}

