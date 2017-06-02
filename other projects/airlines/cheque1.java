import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class cheque1 extends HttpServlet
{
    Connection con;
    PreparedStatement pst;
    String ino,rno,amt,chno,chdt,bank;
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
       PrintWriter out=res.getWriter();
       res.setContentType("text/html");
       ino=req.getParameter("ino");
       rno=req.getParameter("rno");
       amt=req.getParameter("amt");
       chno=req.getParameter("cno");
       chdt=req.getParameter("cdt");
       bank=req.getParameter("bank");
       try
       {
          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
          con=DriverManager.getConnection("jdbc:odbc:DSN","psrini","ssi");
          pst=con.prepareStatement("update payment_details set cheque_no=?,cheque_date=?,bank_drawn=? where invoice_no="+ino);
          pst.setString(1,chno);
          pst.setString(2,chdt);
          pst.setString(3,bank);
          pst.executeUpdate();
          con.commit();
          res.sendRedirect("http://localhost:8080/servlet/invoice?ino="+ino+"&rno="+rno+"&amt="+amt);
       }
       catch(Exception e)
       {
          out.println("Exception in connection:"+e);
       }
    }
}
