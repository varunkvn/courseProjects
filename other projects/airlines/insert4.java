import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class insert4 extends HttpServlet
{
    Connection con;
    PreparedStatement pst;
    String rno,rdt,fno,ddt,dt,add,st,pno,mail,c1;
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
       PrintWriter out=res.getWriter();
       res.setContentType("text/html");
       rno=req.getParameter("rno");
       rdt=req.getParameter("rdt");
       fno=req.getParameter("fno");
       ddt=req.getParameter("date");
       dt=req.getParameter("date1");
       add=req.getParameter("add");
       st=req.getParameter("st");
       pno=req.getParameter("pno");
       mail=req.getParameter("mail");
       c1=req.getParameter("category");
       try
       {
           Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
           con=DriverManager.getConnection("jdbc:odbc:DSN","psrini","ssi");
           pst=con.prepareStatement("insert into passenger_master values(?,?,?,?,?,?,?,?,?)");
           pst.setString(1,rno);
           pst.setString(2,rdt);
           pst.setString(3,fno);
           pst.setString(4,ddt);
           pst.setString(5,dt);
           pst.setString(6,add);
           pst.setString(7,st);
           pst.setString(8,pno);
           pst.setString(9,mail);
           pst.executeUpdate();
           con.commit();
           res.sendRedirect("http://localhost:8080/servlet/reserve2?res_no="+rno+"&flight_no="+fno+"&category="+c1+"&date="+ddt+"");
       }
       catch(Exception e)
       {
           out.println("Exception in connection:"+e);
       }
    }
}
