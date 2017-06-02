import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class date extends HttpServlet
{
   Connection con;
   Statement st;
   ResultSet rst,rst1;
   ResultSetMetaData rmt;
   String fno,str,str1,str2,s;
   String month[]={"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};
   int da,mo,ye,n,i,j,p,q,m,ye1;
   public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
   {
       PrintWriter out=res.getWriter();
       res.setContentType("text/html");
       fno=req.getParameter("flight_no");
       Calendar c=Calendar.getInstance();
       da=c.get(Calendar.DAY_OF_MONTH);
       mo=c.get(Calendar.MONTH);
       ye=c.get(Calendar.YEAR);
       str=(da+"-"+month[mo]+"-"+ye);
       s=month[mo];
       if(s.equals("jan") || s.equals("mar") || s.equals("may") || s.equals("jul") || s.equals("aug") || s.equals("oct") || s.equals("dec"))
       n=31;
       else if(s.equals("apr") || s.equals("jun") || s.equals("sep") || s.equals("nov"))
       n=30;
       else if(s.equals("feb"))
       {
          if(ye%4==0)
          n=29;
          else
          n=28;
       }
       try
       {
           Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
           con=DriverManager.getConnection("jdbc:odbc:DSN","psrini","ssi");
           st=con.createStatement();
           rst=st.executeQuery("select source,detination from flight_master where flight_no="+fno);
           while(rst.next())
           {
              str1=rst.getString("source");
              str2=rst.getString("detination");
           }
           out.println("<html><body>");
           out.println("<br><br><br>");
           out.println("<center><table>");
           out.println("<form method=post action=\"http://localhost:8080/servlet/time1\">");
           out.println("<input type=hidden name=f value="+fno+">");
           out.println("<tr><td>Flight_No:</td><td><input size=20 name=fno value="+fno+" readonly></td>");
           out.println("<td>Source:</td><td><input type=text size=20 name=src value='"+str1+"' readonly></td></tr>");
           out.println("<tr><td>Destination:</td><td><input size=20 name=dest value='"+str2+"' readonly></td>");
           out.println("<td>Date:</td><td><select size=multiple name=date>");
           for(i=da;i<=n;i++)
           {
              out.println("<option>");
              p=i;
              out.println(p+"-"+month[mo]+"-"+ye);
              out.println("</option>");
           }
           if(p==30 || p==31 || p==28 || p==29)
           m=1;
           for(j=m;j<=da;j++)
           {
              out.println("<option>");
              q=j;
              if(month[mo].equals("dec"))
              {
                    ye1=ye+1;
                    out.println(q+"-"+month[0]+"-"+ye1);
              }
              else
              out.println(q+"-"+month[mo+1]+"-"+ye);
              out.println("</option>");
           }
           out.println("</select></td></tr>");
           out.println("<tr><td>Category:</td><td><select size=multiple name=category>");
           rst1=st.executeQuery("select category from flight_desc where flight_no="+fno);
           rmt=rst1.getMetaData();
           int count=rmt.getColumnCount();
           for(int i=1;i<=count;i++)
           out.println(rmt.getColumnName(i));
           while(rst1.next())
           {
                out.println("<option>");
                for(int i=1;i<=count;i++)
                {
                     out.println(rst.getString(i));
                }
                out.println("</option>");
           }
           out.println("</select></td></tr>");
           out.println("<table><tr><td align=center><input type=submit value=\"Get Details\"></td></tr></table>");
           out.println("</form>");
           out.println("</table></center>");
           out.println("</body></html>");  
       }
       catch(Exception e)
       {
           out.println("Exception in connection :"+e);
       }
   }
}


