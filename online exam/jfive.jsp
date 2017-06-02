<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Online Examination</title>
<link rel="stylesheet" href="styles.css" type="text/css" />
</head>
<body>
<div id="wrap">
	
	<div id="header">
		<div id="innerheader">
			<div id="title">
				<h1>ONLINE EXAM</h1>
				<h2>A nEw WaY To LeArN</h2>
			</div>
		</div>
	</div>
<div id="content">
		<div id="page">
		<div class="innerpage">

<%@ page import="java.util.Date" %>
<%
 String fque=request.getParameter("ffour");
 System.out.println(session.getId());
 session.setAttribute("fourthanswer",fque);
  Date d=new Date();
 %>
<html><head><title>five question </title></head>
<body>
Time <%=d.getHours() %>:<%=d.getMinutes() %><br>
Time start <%=session.getAttribute("timestart")%><br>
<form  method="post" action="<%=response.encodeURL("jresult.jsp")%>">

<pre>
   5.JVM stands for
   <input type="radio" name="ffive" value="a">java video modulator
   <input type="radio" name="ffive" value="b">java video message
   <input type="radio" name="ffive" value="c">java virtual machine
   <input type="radio" name="ffive" value="d">none of the above
</pre>
  <input type="submit" value="next">
</form>



				
				</div>
		</div>
		<div id="sidebar">
			<ul>
				<li><a href="" class="selected">EXAM</a></li>
				
			</ul>
		</div>
		<div class="clear"></div>
	</div>
	
	<div id="footer">
		<p>&copy; 2009. varun site.com. Design: <a href="http://www.orkut.com">varun</a> | <a href="http://www.facebook.com">varun again</a></p>
	</div>
</div>

</body>
</html>