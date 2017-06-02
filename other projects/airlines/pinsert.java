import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class pinsert extends HttpServlet
{
    Connection con;
    PreparedStatement pst;
    String ino,rno,amt,dt,mode;
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
       PrintWriter out=res.getWriter();
       res.setContentType("text/html");
       ino=req.getParameter("ino");
       rno=req.getParameter("rno");
       amt=req.getParameter("amt");
       dt=req.getParameter("date");
       mode=req.getParameter("mode");
       try
       {
          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
          con=DriverManager.getConnection("jdbc:odbc:DSN","scott","tiger");
          pst=con.prepareStatement("insert into payment_details(invoice_no,res_no,amount,date1,mode1) values(?,?,?,?,?)");
          pst.setString(1,ino);
          pst.setString(2,rno);
          pst.setString(3,amt);
          pst.setString(4,dt);
          pst.setString(5,mode);
          pst.executeUpdate();
          if(mode.equals("credit"))
          res.sendRedirect("http://localhost:8080/servlet/credit?ino="+ino+"&rno="+rno+"&amt="+amt);
          else if(mode.equals("cheque"))
          res.sendRedirect("http://localhost:8080/servlet/cheque?ino="+ino+"&rno="+rno+"&amt="+amt);
       }
       catch(Exception e)
       {
          out.println("Exception in connection:"+e);
       }
    }
}
