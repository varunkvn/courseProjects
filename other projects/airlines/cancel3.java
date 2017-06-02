import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class cancel3 extends HttpServlet
{
   Connection con;
   Statement st;
   ResultSet rst,rst1;
   String n,str,str1;
   String month[]={"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};
   int da,mo,ye,h,m,s;
   public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
   {
      PrintWriter out=res.getWriter();
      res.setContentType("text/html");
      Calendar c=Calendar.getInstance();
      da=c.get(Calendar.DAY_OF_MONTH);
      mo=c.get(Calendar.MONTH);
      ye=c.get(Calendar.YEAR);
      str=(da+"-"+month[mo]+"-"+ye);
      h=c.get(Calendar.HOUR_OF_DAY);
      m=c.get(Calendar.MINUTE);
      s=c.get(Calendar.SECOND);
      str1=(h+":"+m);
      try
      {
          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
          con=DriverManager.getConnection("jdbc:odbc:DSN","psrini","ssi");
          st=con.createStatement();
          rst=st.executeQuery("select nvl(max(cancel_no),0)+1 from cancellation");
          while(rst.next())
          n=rst.getString(1);
          out.println("<html><body>");

          out.println("<script>");
          out.println("function check()");
          out.println("{");
          out.println("var a=document.forms[0].rno.value;");
          out.println("if(a.length==null)");
          out.println("{");
          out.println("alert(\"Null Field\");");
          out.println("document.forms[0].rno.focus();");
          out.println("document.forms[0].rno.value=\"\";");
          out.println("}");
          out.println("return;");
          out.println("}");
          out.println("</script>");

          out.println("<center><table>");
//          out.println("<form method=post action=\"http://localhost:8080/servlet/cancel3.a?rno="+rno+"&cno="+n+"&cdt="+str+"&ctime="+str1+"\">");
          out.println("<form method=get action=\"http://localhost:8080/servlet/cancel6\">");
          out.println("<caption><font size=5><b><u>Cancellation</u></b></font></caption>");
          out.println("<tr><td><label>Cancel No:</label></td><td><input type=text size=20 name=cno value="+n+" readonly></td>");
          out.println("<td><label>Cancel Date:</label></td><td><input size=20 name=cdt value="+str+" readonly></td></tr>");
          out.println("<tr><td><label>Res No:</label></td><td><input size=20 name=rno onchange=check() onblur=submit()></td>");
          out.println("<td><label>Cancel Time:</label></td><td><input size=20 name=ctime value="+str1+" readonly></td></tr>");
//      out.println("<tr><td><label>Code:</label></td><td><input size=20 name=code></td>");
//          out.println("<tr><td><label>Flight No:</label></td><td><input size=20 name=fno></td>");
//          out.println("<td><label>Date Of Departure:</label></td><td><input size=20 name=ddt></td></tr>");
//        out.println("<tr><td><label>Cancellation Amount:</label></td><td><input size=20 name=amt></td></tr>");
//          out.println("<tr><td><label>Seat No:</label></td><td><input size=20 name=sno></td>");
//          out.println("<td><label>Time Of Departure:</label></td><td><input size=20 name=time></td></tr>");
//          out.println("<table><tr><td align=center><input type=submit value=\" Ok \"></td></tr></table>");
          out.println("</table>");
          out.println("</body></html>");
      }
      catch(Exception e)
      {
          out.println("Exception in connection:"+e);
      }
   }
}

