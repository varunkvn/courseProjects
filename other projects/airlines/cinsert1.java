import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class cinsert1 extends HttpServlet
{
    Connection con;
    String cno,cdt,dd,ct,fno,rno,sno,dt;
    PreparedStatement pst;
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
        PrintWriter out=res.getWriter();
        res.setContentType("text/html");
        cno=req.getParameter("cno");
        cdt=req.getParameter("cdt");
        dd=req.getParameter("ddt");
        ct=req.getParameter("ctime");
        fno=req.getParameter("fno");
        rno=req.getParameter("rno");
        sno=req.getParameter("sno");
        dt=req.getParameter("time");
//        System.out.println("Radhi:"+dt);
        try
        {
             Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
             con=DriverManager.getConnection("jdbc:odbc:DSN","psrini","ssi");
             pst=con.prepareStatement("insert into cancellation(cancel_no,cancel_date,date_of_departure,cancel_time,flight_no,res_no,seat_no) values(?,?,?,?,?,?,?)");
             pst.setString(1,cno);
             pst.setString(2,cdt);
             pst.setString(3,dd);
             pst.setString(4,ct);
             pst.setString(5,fno);
             pst.setString(6,rno);
             pst.setString(7,sno);
             pst.executeUpdate();
             res.sendRedirect("http://localhost:8080/servlet/cancel4?cno="+cno+"&cdt="+cdt+"&ddt="+dd+"&ct="+ct+"&fno="+fno+"&rno="+rno+"&sno="+sno+"&dt="+dt);
        }
        catch(Exception e)
        {
            out.println("Exception in connection cinsert1:"+e);
        }
    }
}
