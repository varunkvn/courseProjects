<html>
<head>
<title>
jsp1
</title>
</head>
<body bgcolor="#ffffff">
  <form action="http://localhost:8080/WebModule1/servlet1">
    <input type="text" name="user">
      <input type="submit" value="CLick">
        </form>
                 <%
    	String name=(String)session.getAttribute("u");
	String add=(String)session.getAttribute("uu");
    	out.println("this is JSP1 page"+name +add );
		%>
<h1>
JBuilder Generated JSP
</h1>
</body>
</html>
