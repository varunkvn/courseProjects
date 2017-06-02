import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class cinsert2 extends HttpServlet
{
    Connection con;
    Statement st;
    ResultSet rst;
    PreparedStatement pst,pst1;
    String cno,code,camt,rno,sno,date,fno,pno,cate,c;
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {

       PrintWriter out=res.getWriter();
       res.setContentType("text/html");
       cno=req.getParameter("cno");
       code=req.getParameter("code");
       camt=req.getParameter("amt");
       rno=req.getParameter("rno");
       sno=req.getParameter("sno");
       date=req.getParameter("ddt");
       fno=req.getParameter("fno");
       c="y";
       try
       {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con=DriverManager.getConnection("jdbc:odbc:DSN","psrini","ssi");
            pst=con.prepareStatement("update cancellation set code=?,cancellation_amt=? where cancel_no='"+cno+"'");
            pst.setString(1,code);
            pst.setString(2,camt);
            pst.executeUpdate();

            st=con.createStatement();
            int x=st.executeUpdate("delete from passenger_details where res_no="+rno+" and seat_no="+sno);
            rst=st.executeQuery("select pass_no,category from waiting_list where date_of_departure='"+date+"' and flight_no='"+fno+"'");
            while(rst.next())
            {
               pno=rst.getString("pass_no");
               cate=rst.getString("category");
            }

            pst1=con.prepareStatement("insert into passenger_details(pass_no,category,flight_no,seat_no,doj,confirmed) values(?,?,?,?,?,?)");
            pst1.setString(1,pno);
            pst1.setString(2,cate);
            pst1.setString(3,fno);
            pst1.setString(4,sno);
            pst1.setString(5,date);
            pst1.setString(6,c);
            pst1.executeUpdate();

            int y=st.executeUpdate("delete from waiting_list from where date_of_departure='"+date+"' and flight_no='"+fno+"'");
            con.commit();
            out.println("<html><body>");
            out.println("<center><br><br>");
            out.println("<h3>Do You Want To Cancel Another Record </h3>");
            out.println("<a href=\"http://localhost:8080/servlet/cancel3\">[ Yes ]</a><a href=\"http://localhost:8080/servlet/\">[ No ]</a>");
            out.println("</center>");
            out.println("</body></html>");
       }
       catch(Exception e)
       {
           out.println("Exception in connection:"+e);
       }
    }
}

