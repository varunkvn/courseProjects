<%
	login = request.form("login")
	password = request.form("password")
	
	if not login="" then
		session("login") = login
		session("password") = password
	end if
	
	Dim DB_CONN_STRING
	DB_CONN_STRING = "DBQ=" & Server.MapPath("../db/quiz.mdb") & ";"
	DB_CONN_STRING = DB_CONN_STRING & "Driver={Microsoft Access Driver (*.mdb)};"
	DB_CONN_STRING = DB_CONN_STRING & "DriverId=25;FIL=MS Access;"
	
	' Create DB connection and connect to the DB
	Set conntemp = Server.CreateObject("ADODB.Connection")
	conntemp.Open DB_CONN_STRING
	
	Set rstemp = Server.CreateObject("ADODB.Recordset")
	sqltemp = "SELECT * FROM registration where LoginID='" & session("login") & "' and Password='" & Session("password") & "'" 
	rstemp.Open sqltemp,conntemp
	
	if rstemp.eof then
		response.redirect "../registration/login.htm"
	end if
	
	
%>
<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Courses</title>
<link rel="stylesheet" type="text/css" href="../css/stylesheets.css">
<base target="_self">
</head>

<body topmargin="10" leftmargin="20" background="../images/GENERIC.gif">

<p align="center"><!--Banner*Show Begin-->

<p align="center">
<!-- URL's used in the movie-->
<A HREF=../courses/Question 1.htm></A> <!-- text used in the movie-->
<OBJECT classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
 codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=5,0,0,0"
 WIDTH=400 HEIGHT=40>
 <PARAM NAME=movie VALUE="../banners/banners.swf"> <PARAM NAME=loop VALUE=false> <PARAM NAME=menu VALUE=false> <PARAM NAME=quality VALUE=high> <PARAM NAME=bgcolor VALUE=#FFFFFF> <EMBED src="../banners/banners.swf" loop=false menu=false quality=high bgcolor=#FFFFFF  WIDTH=400 HEIGHT=40 TYPE="application/x-shockwave-flash" PLUGINSPAGE="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash"></EMBED>
</OBJECT>
<!--Banner*Show End-->

<table border="0" width="540" cellspacing="1" cellpadding="3">
  <tr>
    <td width="100%">
      <p align="center" class="bh"><img border="0" src="../images/titles_courses.gif" width="400" height="50"></td>
  </tr>
  <tr>
    <td width="100%">
      <hr>
    </td>
  </tr>
  <tr>
    <td width="100%">
      <table border="0" width="100%" cellspacing="0" cellpadding="2">
      <tr>
          <td width="20%">
            <p align="center"><img border="0" src="../images/arrow-white.gif"></td>
          <td width="80%"><font face="Arial,helvetica,verdana,geneva" size="2"><b><a href="understandingcomputing.htm" target="_self" class=n5>Understanding Computing</a></b></font></td>
        </tr>
        <tr>
          <td width="100%" colspan="2">
            <hr>
          </td>
        </tr>
      <%
      set rstCourses = server.createobject("ADODB.Recordset")
      sqlCourses = "select * from quizzes where Status=0"
      rstCourses.open sqlCourses, conntemp
      do while not rstCourses.eof
      	if (k mod 2) = 0 then
      		bgcolor = "#DFDFDF"
      	else
      		bgcolor = "#FFFFFF"
      	end if
      	k = k + 1
      	quid_id = rstCourses("quiz_id")
      	quiz_name = rstCourses("quiz_name")
     %>
        <tr>
          <td width="20%">
            <p align="center"><img border="0" src="../images/arrow-white.gif"></td>
          <td width="80%"><font face="Arial,helvetica,verdana,geneva" size="2"><b><a href="lesson.asp?QUIZ_ID=<%=quid_id%>" target="_self" class=n5><%=quiz_name%></a></b></font></td>
        </tr>
        <tr>
          <td width="100%" colspan="2">
            <hr>
          </td>
        </tr>
        <%rstCourses.movenext
        loop%>
      </table>
    </td>
  </tr>
</table>

</body>

</html>
<%
	conntemp.close
	set conntemp = nothing
	'rstemp.close
	'set rstemp=nothing
%>
