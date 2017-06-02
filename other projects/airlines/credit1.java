import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class credit1 extends HttpServlet
{
    Connection con;
    String type,cno,cname,dt,ino,rno,amt,bank;
    PreparedStatement pst;
    int i,j,k;
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
       PrintWriter out=res.getWriter();
       res.setContentType("text/html");
       ino=req.getParameter("ino");
       rno=req.getParameter("rno");
       amt=req.getParameter("amt");
       type=req.getParameter("type");
       cno=req.getParameter("no");
       cname=req.getParameter("name");
       dt=req.getParameter("edt");
       bank=req.getParameter("bank");
       try
       {
           Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
           con=DriverManager.getConnection("jdbc:odbc:DSN","scott","tiger");
           pst=con.prepareStatement("update payment_details set card_type=?,card_no=?,card_holder_name=?,expiry_date=?,bank_drawn=? where invoice_no='"+ino+"'");
           pst.setString(1,type);
           pst.setString(2,cno);
           pst.setString(3,cname);
           pst.setString(4,dt);
           pst.setString(5,bank);
           int x= pst.executeUpdate();
           con.commit();
           res.sendRedirect("http://localhost:8080/servlet/invoice?ino="+ino+"&rno="+rno+"&amt="+amt);
       }
       catch(Exception e)
       {
           out.println("Exception in connection:"+e);
       }
    }
}
