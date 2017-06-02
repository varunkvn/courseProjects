import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class admpadel extends  HttpServlet
{
Statement st;
Connection con;
String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s;
public void doGet(HttpServletRequest req,HttpServletResponse res)
{
try
{
PrintWriter out = res.getWriter();
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con = DriverManager.getConnection("jdbc:odbc:reddy","chinnu","nisha");
st=con.createStatement();
ResultSet rs,rs1;
s=req.getParameter("pcode");
System.out.println(s+"patmod");
rs=st.executeQuery("select * from ppersonal where patientcode='"+s+"'");
System.out.println(s+"patmodxxx");
if(rs.next())
{
s1=rs.getString(3);
s2=rs.getString(4);
s3=rs.getString(5);
s4=rs.getString(6);
s5=rs.getString(7);
s6=rs.getString(8);
s7=rs.getString(9);
s8=rs.getString(10);
s9=rs.getString(11);
s10=rs.getString(12);
s11=rs.getString(13);
s12=rs.getString(14);
}
System.out.println(s+"patmodxxxyyy");
System.out.println(s3);
System.out.println(s4);
StringTokenizer st = new StringTokenizer(s3,"/");
 s15=st.nextToken();
 s16=st.nextToken();
 s17=st.nextToken();
System.out.println(s15);
System.out.println(s16);
System.out.println(s17);
StringTokenizer st1 = new StringTokenizer(s4,"/");
 s18=st1.nextToken();
 s19=st1.nextToken();
 s20=st1.nextToken();
System.out.println(s18);
System.out.println(s19);
System.out.println(s20);



res.setContentType("text/html");
out.println("<html>");
out.println("<body background='c:/javawebserver2.0/servlets/wedflo_15.gif'>");
out.println("<center>");
out.println("<form name=pat  action='admpdel'> ");
out.println("	<td><input type=\"hidden\"  name=\"pcode\" value="+s+" ></td>");

out.println("<font size=7 color=\"red\" face=arial black><b>+</b></font><font size=6 color=\"green\"><u><b>Patient Deletion Form</b></u></font><font size=7 color=\"red\" face=arial black><b>+</b></font>");
out.println("<br><br>");
out.println("<table>");


out.println("<tr>");
out.println("	<td><b>Patient Name:</b></td>");
out.println("	<td><input type=\"text\"  name=\"pname\"   value="+s1+" size=20></td>");
out.println("</tr>");

out.println("<tr>");
out.println("	<td><b>Age:</b></td>");
out.println("	<td><input type=\"text\" name=\"age\"   value="+s2+" size=20></td>");
out.println("</tr>");

out.println("<tr>");
out.println("	<td><b>Date Of Birth:</b></td>");
out.println("	<td>");
out.println("		<select name=\"mon\"  >");
  out.println("<option value="+s15+">"+s15+"");
  out.println("<option value=\"January\">Jan");

			  out.println("<option value=\"February\"> Feb");
out.println("			  <option value=\"March\"> Mar");
out.println("			  <option value=\"April\"> Apr");
out.println("			  <option value=\"May\"> May");
out.println("			  <option value=\"June\"> Jun");
out.println("             		  <option value=\"July\"> Jul");
out.println("			  <option value=\"August\"> Aug");
out.println("		          <option value=\"September\">Sep");
out.println(" 			  <option value=\"October\"> Oct");
out.println("            		  <option value=\"November\"> Nov");
			  out.println("<option value=\"December\"> Dec");
               
	out.println("	</select>");
out.println("	</td>");

out.println("	<td>");
out.println("		<SELECT NAME=\"day\"  >");
			  out.println("<option value="+s16+">"+s16+"");
			out.println("                    <option value=1>1");
		    out.println("<option value=2>2");
	 	    out.println("<option value=3>3");
                    out.println("<option value=4>4");
out.println("                    <option value=5>5");
out.println("         	    <option value=6>6");
out.println("			<option value=7>7");
out.println("			<option value=8>8");
out.println("			<option value=9>9");
out.println("			<option value=10>10");
out.println("			<option value=11>11");
out.println("			<option value=12>12");
out.println("			<option value=13>13");
out.println("			<option value=14>14");
out.println("			<option value=15>15");
out.println("			<option value=16>16");
out.println("			<option value=17>17");
out.println("			<option value=18>18");
out.println("			<option value=19>19");
out.println("			<option value=20>20");
out.println("			<option value=21>21");
out.println("			<option value=22>22");
out.println("			<option value=23>23");
out.println("<option value=24>24");
out.println("			<option value=25>25										");
                 out.println("       <option value=26>26");
out.println("			<option value=27>27");
out.println("			<option value=28>28");
out.println("			<option value=29>29");
out.println("                        <option value=30>30");
out.println("			<option value=31>31");
	out.println("	</select>");
out.println("	</td>");

out.println("	<td>");
out.println("		<select name=\"year\"  >");
  out.println("<option value="+s17+">"+s17+"");
out.println("	                <option value=2001>2001");
                 out.println("       <option value=2000>2000");
out.println("			<option value=1999>1999");
out.println("			<option value=1998>1998");
out.println("			<option value=1997>1997");
out.println("			<option value=1996>1996");
out.println("			<option value=1995>1995");
out.println("			<option value=1994>1994");
out.println("			<option value=1993>1993");
out.println("			<option value=1992>1992");
out.println("			<option value=1991>1991");
out.println("			<option value=1990>1990");
out.println("			<option value=1989>1989");
out.println("			<option value=1988>1988");
out.println("			<option value=1987>1987");
out.println("			<option value=1986>1986");
out.println("			<option value=1985>1985");
out.println("			<option value=1984>1984");
out.println("			<option value=1983>1983");
out.println("			<option value=1982>1982");
out.println("			<option value=1981>1981");
out.println("			<option value=1980>1980");
out.println("			<option value=1979>1979");
out.println("			<option value=1978>1978");
out.println("			<option value=1977>1977");
out.println("			<option value=1976>1976");
out.println("			<option value=1975>1975");
out.println("			<option value=1974>1974");
out.println("			<option value=1973>1973");
out.println("			<option value=1972>1972");
out.println("			<option value=1971>1971");
out.println("			<option value=1970>1970");
out.println("			<option value=1969>1969");
out.println("			<option value=1968>1968");
out.println("			<option value=1967>1967");
out.println("			<option value=1966>1966");
out.println("			<option value=1965>1965");
out.println("			<option value=1964>1964");
out.println("			<option value=1963>1963");
out.println("			<option value=1962>1962");
out.println("			<option value=1961>1961");
out.println("			<option value=1960>1960");
out.println("			<option value=1959>1959");
out.println("			<option value=1958>1958");
out.println("			<option value=1957>1957");
out.println("			<option value=1956>1956");
out.println("			<option value=1955>1955");
out.println("			<option value=1954>1954");
out.println("			<option value=1953>1953");
out.println("			<option value=1952>1952");
out.println("			<option value=1951>1951");
out.println("			<option value=1950>1950");
out.println("			<option value=1949>1949");
out.println("			<option value=1948>1948");
out.println("			<option value=1947>1947");
out.println("			<option value=1946>1946");
out.println("			<option value=1945>1945");
out.println("			<option value=1944>1944");
out.println("			<option value=1943>1943");
out.println("			<option value=1942>1942");
out.println("			<option value=1941>1941");
out.println("			<option value=1940>1940");
out.println("			<option value=1939>1939");
out.println("			<option value=1938>1938");
out.println("			<option value=1937>1937");
out.println("			<option value=1936>1936");
out.println("			<option value=1935>1935");
out.println("			<option value=1934>1934");
out.println("			<option value=1933>1933");
out.println("			<option value=1932>1932");
out.println("			<option value=1931>1931");
out.println("			<option value=1930>1930");
out.println("			<option value=1929>1929");
out.println("			<option value=1928>1928");
out.println("			<option value=1927>1927");
out.println("			<option value=1926>1926");
out.println("			<option value=1925>1925");
out.println("		</select>");
out.println("	</td>");
out.println("</tr>");

out.println("<tr>");
out.println("	<td><b>Registration Date:</b></td>");
out.println("	<td>");
out.println("		<select name=\"mont\"   >");
  out.println("<option value="+s18+">"+s18+"");
  out.println("<option value=\"January\">Jan");
			  out.println("<option value=\"February\"> Feb");
out.println("			  <option value=\"March\"> Mar");
out.println("			  <option value=\"April\"> Apr");
out.println("			  <option value=\"May\"> May");
out.println("			  <option value=\"June\"> Jun");
out.println("             		  <option value=\"July\"> Jul");
out.println("			  <option value=\"August\"> Aug");
out.println("		          <option value=\"September\">Sep");
out.println(" 			  <option value=\"October\"> Oct");
out.println("            		  <option value=\"November\"> Nov");
			  out.println("<option value=\"December\"> Dec");

out.println("		</select>");
out.println("	</td>");

out.println("	<td>");
out.println("		<SELECT NAME=\"dy\"  >");
  out.println("<option value="+s19+">"+s19+"");
	out.println("                    <option value=1>1");
		    out.println("<option value=2>2");
	 	    out.println("<option value=3>3");
                    out.println("<option value=4>4");
out.println("                    <option value=5>5");
out.println("         	    <option value=6>6");
out.println("			<option value=7>7");
out.println("			<option value=8>8");
out.println("			<option value=9>9");
out.println("			<option value=10>10");
out.println("			<option value=11>11");
out.println("			<option value=12>12");
out.println("			<option value=13>13");
out.println("			<option value=14>14");
out.println("			<option value=15>15");
out.println("			<option value=16>16");
out.println("			<option value=17>17");
out.println("			<option value=18>18");
out.println("			<option value=19>19");
out.println("			<option value=20>20");
out.println("			<option value=21>21");
out.println("			<option value=22>22");
out.println("			<option value=23>23");
			out.println("<option value=24>24");
out.println("			<option value=25>25										");
                 out.println("       <option value=26>26");
out.println("			<option value=27>27");
out.println("			<option value=28>28");
out.println("			<option value=29>29");
out.println("                        <option value=30>30");
out.println("			<option value=31>31");		
out.println("		</select>");
out.println("	</td>");

out.println("	<td>");
out.println("		<select name=\"yr\"  >");
  out.println("<option value="+s20+">"+s20+"");
                out.println("       <option value=2001>2001");
out.println("			<option value=2002>2002");
out.println("			<option value=2003>2003");
out.println("			<option value=2004>2004");
out.println("			<option value=2005>2005");
out.println("			<option value=2006>2006");
out.println("			<option value=2007>2007");
out.println("			<option value=2008>2008");
out.println("			<option value=2009>2009");
out.println("			<option value=2010>2010");
out.println("		</select>");
out.println("	</td>");
out.println("</tr>");

out.println("<tr>");
out.println("	<TD><B>House No:</B></TD>");
out.println("        <td><input type=\"TEXT\" NAME=\"hno\"   value="+s5+" size=20></td>");
out.println("</tr>");
out.println("<tr>");
out.println("	<TD><B>Street:</B></TD>");
out.println("        <td><input type=\"TEXT\" NAME=\"street\"    value="+s6+" size=20></td>");
out.println("</tr>");
out.println("<tr>");
out.println("	<TD><B>City:</B></TD>");
out.println("        <td><input type=\"TEXT\" NAME=\"city\"   value="+s7+"  size=20></td>");
out.println("</tr>");



out.println("<tr>");
out.println("	<td><B>Phone:</td>");
out.println("	<td><INPUT TYPE=\"text\" NAME=\"phone\"    value="+s8+" size=20></td>");
out.println("</tr>");

out.println("<tr> ");
out.println("	  <td><B>Fax :</td>");
out.println("	  <td><INPUT TYPE=\"text\" NAME=\"fax\"    value="+s9+" size=20></td>");
out.println("	  <td><B>E-mail(if any):</td>");
out.println("          <td><INPUT TYPE=\"text\" NAME=\"eid\"    value="+s10+" size=20></TD>");

out.println("</TR>");

out.println("<tr>");
out.println("	<td><b>State:</b></td>");
out.println("	<td><input type=\"text\" name=\"st1\"    value="+s11+" size=20></td>");
out.println("	<td><b>Country:</b></td>");
out.println("	<td><input type=\"text\" name=\"cou1\"    value="+s12+" size=20></td>");
out.println("</tr>");
out.println("</table>");
out.println("<br><br><br>");
out.println("	<td><input type=\"submit\" value=\"Delete\" onClick=\" return patcheck()\"></td>");
out.println("	<td><input type=\"button\" value=\"Back\" onClick=window.history.go(-1)></td>");
out.println("</center>");
out.println("</form>");
out.println("</body>");
out.println("</html>			");

}
catch(Exception e)
{
e.printStackTrace();
}

}

}
