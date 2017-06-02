import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class update extends HttpServlet
{
    Connection con;
    String fno,fname,src,dest,total,dur,category,cost,start,end,day,time;
    int cost1,start1,end1;
    PreparedStatement pst,pst1,pst2;
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
       PrintWriter out=res.getWriter();
       res.setContentType("text/html");
       fno=req.getParameter("fn");
       fname=req.getParameter("fname");
       src=req.getParameter("src");
       dest=req.getParameter("dest");
       total=req.getParameter("total");
       dur=req.getParameter("dur");
       category=req.getParameter("cat");
       cost=req.getParameter("cost");
     //  cost1=Integer.parseInt(cost);
       start=req.getParameter("start");
       start1=Integer.parseInt(start);
       end=req.getParameter("end");
       end1=Integer.parseInt(end);
       day=req.getParameter("d1");
       time=req.getParameter("t1");
       try
       {
           Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
           con=DriverManager.getConnection("jdbc:odbc:DSN","scott","tiger");
           pst=con.prepareStatement("update flight_master set flight_name=?,source=?,detination=?,duration_of_flight=? where flight_no='"+fno+"'");
           pst.setString(1,fname); 
           pst.setString(2,src); 
           pst.setString(3,dest); 
           pst.setString(4,dur); 
           pst.executeUpdate();
           con.commit();
           pst1=con.prepareStatement("update flight_desc set cost_of_ticket=?,starting_seatno=?,ending_seatno=? where flight_no='"+fno+"' and category='"+category+"'");
           pst1.setString(1,cost);
           pst1.setInt(2,start1); 
           pst1.setInt(3,end1); 
           pst1.executeUpdate();
           con.commit();
           pst2=con.prepareStatement("update flight_details set time_of_flight=? where flight_no='"+fno+"' and day_of_flight='"+day+"'");
           pst2.setString(1,time); 
           pst2.executeUpdate();
           con.commit();
           out.println("<html><body>");
           out.println("<center><br><br>");
           out.println("<h3>Do You Want To Modify Another Record</h3>");
           out.println("<a href=\"http://localhost:8080/servlet/modify\">[ Yes ]</a><a href=\"http://localhost:8080/servlet/admin\">[ No ]</a>");
           out.println("</center>");
           out.println("</body></html>");
       }
       catch(Exception e)
       {
            out.println("Exception in connection:"+e);
       }
    }
}

