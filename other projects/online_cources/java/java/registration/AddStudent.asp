<!-- #include file="../db/adovbs.inc" -->
<%
	' Notes .......................................................
	dim msg
	
	firstname=request.form("fname")
	lastname=request.form("lname")
	email=request.form("email")
	title=request.form("title")
	school_organisation=request.form("school")
	street_address=request.form("address")
	city=request.form("city")
	country=request.form("country")
	phone=request.form("phone")
	age=request.form("age")
	gender=request.form("gender")
	loginID=request.form("loginID")
	password=request.form("password")
	
	Dim DB_CONN_STRING
	DB_CONN_STRING = "DBQ=" & Server.MapPath("../db/quiz.mdb") & ";"
	DB_CONN_STRING = DB_CONN_STRING & "Driver={Microsoft Access Driver (*.mdb)};"
	DB_CONN_STRING = DB_CONN_STRING & "DriverId=25;FIL=MS Access;"
	
	' Create DB connection and connect to the DB
	Set conntemp = Server.CreateObject("ADODB.Connection")
	conntemp.Open DB_CONN_STRING
	
	Set rstemp = Server.CreateObject("ADODB.Recordset")
	sqltemp = "SELECT * FROM registration where LoginID='" & loginID & "'" 
	rstemp.Open sqltemp,conntemp, adopenstatic, adLockPessimistic, adCMDText
	
	if rstemp.eof then
		rstemp.AddNew
		rstemp("FirstName") = firstname
		rstemp("LastName") = lastname
		rstemp("Email") = email
		rstemp("Title") = title
		rstemp("School_Organisation") = school_organisation
		rstemp("Street_Address") = street_address
		rstemp("City") = city
		rstemp("Country") = country
		rstemp("Phone") = phone
		rstemp("Age") = age
		rstemp("Gender") = gender
		rstemp("LoginID") = loginID
		rstemp("Password") = password
		rstemp("DateRegistered") = Now()
		rstemp("Status") = 1
		rstemp.update
		msg = 1
	else
		msg = 2
	end if

	rstemp.close
	set rstemp=nothing	
	conntemp.close
	set conntemp = nothing
	

%>
<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Adding New Student to the database list</title>
<link rel="stylesheet" type="text/css" href="../css/stylesheets.css">
</head>

<body topmargin="10" leftmargin="20" background="../images/GENERIC.gif">

<table border="0" width="540" cellspacing="5" cellpadding="10" height="350">
<%if msg=1 then%>
  <tr>
    <td width="100%">
      <p align="center"><b><font face="Arial,helvetica,verdana,geneva" size="3" color="#FF9900">Congratulations</font></b><font face="Arial,helvetica,verdana,geneva" size="2">,
      you have been added to the database list which will give you access to
      enter the course page.</font></p>
      <p align="center"><font face="Arial,helvetica,verdana,geneva" size="2"><a href="login.htm" target="_self"><font color="#2C4D9C"><b>Click
      here to log In</b></font></a>, remember to use your loginID and password
      which you submitted when registered.</font></td>
  </tr>
  <%elseif msg=2 then%>
  <tr>
    <td width="100%">
      <p align="center"><font face="Arial,helvetica,verdana,geneva" size="2"><b>Sorry</b>,
      the LoginID you have choosen is already taken by another user.<br>
      <b><a href="Javascript:history.go(-1);">Click here to hit back</a></b> and
      choose another one.</font></td>
  </tr>
  <%end if%>
</table>

</body>

</html>
