import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class application extends HttpServlet
{
	Connection con;
	Statement stmt;
	ResultSet rs;
	String pname;
	int appno;
	
	public void init(ServletConfig sc)throws ServletException
	{
		super.init(sc);
		try
		    {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:advt","scott","tiger");
		    }	catch(Exception e){System.out.println(e);}
	}

	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		pname=req.getParameter("party");
		System.out.println(pname);
		try
		{
		     stmt=con.createStatement();
	             rs=stmt.executeQuery("select nvl(max(applno),1000)+1 from user_applications");
		     rs.next();
		     appno=rs.getInt(1);
		}
		catch(Exception e){pw.println(e);}
		pw.println("<html>");
		pw.println("<body background=\"/Beanco.jpg\">");
		pw.println("<head><u><font  color=maroon><h1 align=center> APPLICATION FORM</h1></font></u></head>");
		pw.println("<center><form name=f1 action="+"http://localhost:8080/servlet/subtypes"+" method=post>");
		pw.println("<u><font size=4 color=blue>Appno:<input type=text name=applno value="+appno+">");
		pw.println("Party:<input type=text name=pname value="+pname+"></font></u><br>");
		pw.println("<p><font size=4 color=blue><u>Select type here:&nbsp;&nbsp;&nbsp;</u>");
		pw.println("news:<input type=radio name=type value=news>");
		pw.println("visualmedia:<input type=radio name=type value=vmedia>");
		pw.println("Internet:<input type=radio name=type value=inet></font></p><br>");
		pw.println("<font  color=blue><h4 align=center>ENTER ANY MESSAGE<h4></font>");
		pw.println("<TEXTAREA name=msg rows=7 cols=50 MAXLENGTH=500></TEXTAREA><br>" );
		pw.println("<font size=4 color=blue>If any images added: yes<input type=radio name=img value=yes>");
pw.println("no<input type=radio name=img value=no></font>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a>"); 
pw.println("<center><input type=submit value=submit>");
pw.println("<input type=submit value=cancel></form></center>");
pw.println("</body></html>");
}
}