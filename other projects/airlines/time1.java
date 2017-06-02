import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class time1 extends HttpServlet
{
    String week[]={"sun","mon","tue","wed","thu","fri","sat"};
    Connection con;
    Statement st;
    ResultSet rst;
    String fno,dd,da,mo,ye,str,str1,category;
    Calendar c;
    int x,y,m;
    int ye1,p,mo1,da1;
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
       PrintWriter out=res.getWriter();
       res.setContentType("text/html");
       fno=req.getParameter("f");
       category=req.getParameter("category");
       out.println(category);
       dd=req.getParameter("date");
       x=dd.indexOf("-");
       da=dd.substring(0,x);
       y=dd.lastIndexOf("-");
       mo=dd.substring(x+1,y);
       ye=dd.substring(y+1,dd.length());
       if(mo.equals("jan"))
       mo1=0;
       else if(mo.equals("feb"))
       mo1=1;
       else if(mo.equals("mar"))
       mo1=2;
       else if(mo.equals("apr"))
       mo1=3;
       else if(mo.equals("may"))
       mo1=4;
       else if(mo.equals("jun"))
       mo1=5;
       else if(mo.equals("jul"))
       mo1=6;
       else if(mo.equals("aug"))
       mo1=7;
       else if(mo.equals("sep"))
       mo1=8;
       else if(mo.equals("oct"))
       mo1=9;
       else if(mo.equals("nov"))
       mo1=10;
       else if(mo.equals("dec"))
       mo1=11;
       try
       {
           da1=Integer.parseInt(da);
           ye1=Integer.parseInt(ye);
           c=Calendar.getInstance();
           c.set(ye1,mo1,da1);
           p=c.get(Calendar.DAY_OF_WEEK);
           str1=(week[p-1]);
       }
       catch(Exception e)
       {
           out.println("Number: "+e);
       }
       try
       {
           Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
           con=DriverManager.getConnection("jdbc:odbc:DSN","scott","tiger");
           st=con.createStatement();
           rst=st.executeQuery("select day_of_flight,time_of_flight from flight_details where flight_no='"+fno+"'");
           int x=0;
           while(rst.next())
           {
              str=rst.getString("day_of_flight");
              if(str1.equals(str))
              {
                 x=1;
              }
           }
           if(x == 1)
           {
              res.sendRedirect("http://localhost:8080/servlet/reserve1?fn='"+fno+"'&d='"+dd+"'&w='"+str1+"'&category="+category+"");
           }
           else
           {
                out.println("<html><body>");
                out.println("<center>");
                out.println("<br><br><br>");
                out.println("<h3>Flight is not available Please select another date</h3>");
                out.println("<a href=http://localhost:8080/servlet/date?flight_no='"+fno+"'>[ Back ] </a>");  
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
