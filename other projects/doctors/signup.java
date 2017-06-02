import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;

public class signup1 extends HttpServlet
{
public void service(HttpServletRequest req,HttpServletResponse res)
{
try
{
PrintWriter out = res.getWriter();
res.setContentType("text/html");
out.println("<HTML>");
out.println("<HEAD>");
out.println("<TITLE> SignUP </TITLE>");

out.println("</HEAD>");
out.println("<body bgcolor=pink><center>");
out.println("<form  method=post action=\"http://localhost:8080/servlet/userper\" > ");
out.println("<font size=7 face=\"arial black\" color=red><U>SignUp</U></b></font>");
out.println("<TABLE border=0  >");
out.println("<TR><td align=\"center\">UserName : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=text\" NAME=\"username\"> &nbsp;&nbsp;&nbsp;(Minimum 4 characters)</td></TR>");
out.println("<TR><td><CENTER> PassWord &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<INPUT TYPE=\"password\" NAME=\"pwd\">  &nbsp;&nbsp;&nbsp;(Minimum 4 characters)</CENTER></td></TR>");
out.println("<TR><td> <CENTER>RetypePassWord  :&nbsp;<INPUT TYPE=\"password\" NAME=\"retypepassword\"> &nbsp;&nbsp;&nbsp;(Minimum 4 characters)</CENTER></td></TR><BR>");
out.println("<TR><TD><CENTER><U><FONT SIZE=\"6\" COLOR=\"green\">Profile Information:</FONT></U></CENTER></TD></TR>");
out.println("<TR><td><CENTER> FirstName  :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<INPUT TYPE=\"text\" NAME=\"firstname\"></CENTER> </td></TR>");
out.println("<TR><td> <CENTER>MiddleName  :&nbsp;&nbsp;&nbsp;&nbsp;<INPUT TYPE=\"text\" NAME=\"middlename\"> </CENTER></td></TR>");
out.println("<TR><td><CENTER> LastName  :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<INPUT TYPE=\"text\" NAME=\"lastname\"></CENTER> </td></TR>");
out.println("<TR><TD><CENTER>&nbsp; BirthDate:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
out.println("<SELECT NAME=\"months\" >");
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
                 
out.println("</SELECT>&nbsp;&nbsp;");

out.println("<SELECT NAME=\"days\">");
out.println("                    <option value=1>1");
		    out.println("<option value=2>2");
	 	    out.println("<option value=3>3");
                    out.println("<option value=4>4");
out.println(" <option value=5>5");
out.println("     <option value=6>6");
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
out.println("</SELECT>&nbsp;&nbsp;");

out.println("<SELECT NAME=\"years\">");
out.println("	                <option >2001");
                 out.println("       <option >2000");
out.println("			<option >1999");
out.println("			<option >1998");
out.println("			<option >1997");
out.println("			<option >1996");
out.println("			<option >1995");
out.println("			<option >1994");
out.println("			<option >1993");
out.println("			<option >1992");
out.println("			<option >1991");
out.println("			<option >1990");
out.println("			<option >1989");
out.println("			<option >1988");
out.println("			<option >1987");
out.println("			<option >1986");
out.println("			<option >1985");
out.println("			<option >1984");
out.println("			<option >1983");
out.println("			<option >1982");
out.println("			<option >1981");
out.println("			<option >1980");
out.println("			<option >1979");
out.println("			<option >1978");
out.println("			<option >1977");
out.println("			<option >1976");
out.println("			<option >1975");
out.println("			<option >1974");
out.println("			<option >1973");
out.println("			<option >1972");
out.println("			<option >1971");
out.println("			<option >1970");
out.println("			<option >1969");
out.println("			<option >1968");
out.println("			<option >1967");
out.println("			<option >1966");
out.println("			<option >1965");
out.println("			<option >1964");
out.println("			<option >1963");
out.println("			<option >1962");
out.println("			<option >1961");
out.println("			<option >1960");
out.println("			<option >1959");
out.println("			<option >1958");
out.println("			<option >1957");
out.println("			<option >1956");
out.println("			<option >1955");
out.println("			<option >1954");
out.println("			<option >1953");
out.println("			<option >1952");
out.println("			<option >1951");
out.println("			<option >1950");
out.println("			<option >1949");
out.println("			<option >1948");
out.println("			<option >1947");
out.println("			<option >1946");
out.println("			<option >1945");
out.println("			<option >1944");
out.println("			<option >1943");
out.println("			<option >1942");
out.println("			<option >1941");
out.println("			<option >1940");
out.println("			<option >1939");
out.println("			<option >1938");
out.println("			<option >1937");
out.println("			<option >1936");
out.println("			<option >1935");
out.println("			<option >1934");
out.println("			<option >1933");
out.println("			<option >1932");
out.println("			<option >1931");
out.println("			<option >1930");
out.println("			<option >1929");
out.println("			<option >1928");
out.println("			<option >1927");
out.println("			<option >1926");
out.println("			<option >1925");

out.println("</SELECT>");
out.println("</TD></TR>");


out.println("<TR><TD><CENTER>Address  :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
out.println("<TEXTAREA NAME=\"add\" ROWS=\"5\" COLS=\"17\"></TEXTAREA></TD></TR>");

out.println("<TR><td><center>City:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
out.println("<INPUT TYPE=\"text\" NAME=\"city\"> </td></center></TR>");

out.println("<TR><td><CENTER>State:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
out.println("<INPUT TYPE=\"text\" NAME=\"state\"> </td></CENTER></TR>");

out.println("<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Country:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
out.println("<select name=\"country\">");
out.println("<option >India");
out.println("<option >U.S.A");
out.println("<option >Germany");
out.println("<option >Singapore");
out.println("</select>");
out.println("</td></tr>");

out.println("<TR><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pincode:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
out.println("<INPUT TYPE=\"text\" NAME=\"pcode\"> </td></TR>");

out.println("<TR><td><CENTER>Phone No  :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
out.println("<INPUT TYPE=\"text\" NAME=\"pn\"></CENTER> </td></TR>");

out.println("<TR><td><CENTER>E mail ID:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
out.println("<INPUT TYPE=\"text\" NAME=\"ed\"></CENTER> </td></TR>");

out.println("<TR><td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Gender :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
out.println("<select  NAME=\"gender\">");
out.println("		  <option value=\"Male\">Male");
out.println("		  <option value=\"Female\">Female");
out.println("</select></td></TR>");

out.println("<TR><TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Occupation  :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
out.println("<SELECT NAME=\"occ\">");
out.println("		 <option>Business");
out.println("		 <option>Student");
		 out.println("<option>employee");
		 
out.println("</SELECT></TD></TR>");

out.println("<TR><TD>How did you Know About Us? :");
out.println("<SELECT NAME=\"know\">");
out.println("		 <option>NewsPaper");
out.println("                 <option>Friends");
out.println("		 <option>Wallpaper");
out.println("</SELECT></TD></TR>");

out.println("<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Marital Status:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
out.println("<select name=\"mar\">");
out.println("<option>Married");
out.println("<option>Unmarried");
out.println("</select></td></tr><br><br>");

out.println("<TD><CENTER><INPUT TYPE=submit value=\"Submit\" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
out.println("</form>	");
out.println("<INPUT TYPE=button value=Cancel>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
out.println("<INPUT TYPE=button value=Back>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
out.println("</CENTER>");
out.println("</TD> </tr> ");
out.println("</TABLE></CENTER>");
//out.println("</form>	");
out.println("</body>");
out.println("</html>");

}

catch(Exception e)
{
e.printStackTrace();
}

}
}