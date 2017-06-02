<%
	StudID = request.querystring("studid")
	if StudID <> "" then
	
	Dim DB_CONN_STRING
	DB_CONN_STRING = "DBQ=" & Server.MapPath("../db/quiz.mdb") & ";"
	DB_CONN_STRING = DB_CONN_STRING & "Driver={Microsoft Access Driver (*.mdb)};"
	DB_CONN_STRING = DB_CONN_STRING & "DriverId=25;FIL=MS Access;"
	
	' Create DB connection and connect to the DB
	Set conntemp = Server.CreateObject("ADODB.Connection")
	conntemp.Open DB_CONN_STRING
	
	Set rstemp = Server.CreateObject("ADODB.Recordset")
	sqltemp = "SELECT * FROM registration where StudID="&StudID 
	rstemp.Open sqltemp,conntemp
	
	Stud_ID=rstemp("StudID")
	Stud_Firstname=rstemp("FirstName")
	Stud_Lastname=rstemp("LastName")
	Stud_Email=rstemp("Email")
	Stud_Title=rstemp("Title")
	Stud_School=rstemp("School_Organisation")
	Stud_Address=rstemp("Street_Address")
	Stud_City=rstemp("City")
	Stud_Region=rstemp("Country")
	Stud_Phone=rstemp("Phone")
	Stud_Age=rstemp("Age")
	Stud_Gender=rstemp("Gender")
	Stud_DateRegistered=rstemp("DateRegistered")
	
%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Student Details</title>
</head>

<body topmargin="10" leftmargin="20" background="../images/GENERIC.gif">

<table border="0" width="540" cellspacing="1" cellpadding="2">
  <tr>
    <td width="100%">
      <p align="right"><a href="Javascript:history.go(-1);"><b><font face="Arial" size="2" color="#2C4D9C"><span style="background-color: #FFFFEA">Back
      to student List</span></font></b></a></td>
  </tr>
  <tr>
    <td width="100%">
      <p align="center"><b><font face="Arial" color="#2C4D9C" size="4">Student
      Details</font></b></p>
      <hr>
    </td>
  </tr>
  <tr>
    <td width="100%">
      <table border="0" width="100%" cellspacing="1" cellpadding="3">
        <tr>
          <td width="50%" align="left"><font face="Arial" size="2"><b>Student
            ID:</b></font></td>
          <td width="50%"><font face="Arial" size="2" color="#0000FF"><b><%=Stud_ID%></b></font></td>
        </tr>
        <tr>
          <td width="50%" align="left"><font face="Arial" size="2"><b>First
            Name:</b></font></td>
          <td width="50%"><font face="Arial" size="2" color="#0000FF"><b><%=Stud_Firstname%></b></font></td>
        </tr>
        <tr>
          <td width="50%" align="left"><font face="Arial" size="2"><b>Last
            Name:</b></font></td>
          <td width="50%"><font face="Arial" size="2" color="#0000FF"><b><%=Stud_lastname%></b></font></td>
        </tr>
        <tr>
          <td width="50%" align="left"><font face="Arial" size="2"><b>Email
            Address:</b></font></td>
          <td width="50%"><font face="Arial" size="2" color="#0000FF"><b><%=Stud_Email%></b></font></td>
        </tr>
        <tr>
          <td width="50%" align="left"><font face="Arial" size="2"><b>Title:</b></font></td>
          <td width="50%"><font face="Arial" size="2" color="#0000FF"><b><%=Stud_Title%></b></font></td>
        </tr>
        <tr>
          <td width="50%" align="left"><font face="Arial" size="2"><b>School or
            Organisation:</b></font></td>
          <td width="50%"><font face="Arial" size="2" color="#0000FF"><b><%=Stud_School%></b></font></td>
        </tr>
        <tr>
          <td width="50%" align="left"><font face="Arial" size="2"><b>Address:</b></font></td>
          <td width="50%"><font face="Arial" size="2" color="#0000FF"><b><%=Stud_Address%></b></font></td>
        </tr>
        <tr>
          <td width="50%" align="left"><font face="Arial" size="2"><b>City:</b></font></td>
          <td width="50%"><font face="Arial" size="2" color="#0000FF"><b><%=Stud_City%></b></font></td>
        </tr>
        <tr>
          <td width="50%" align="left"><font face="Arial" size="2"><b>Region:</b></font></td>
          <td width="50%"><font face="Arial" size="2" color="#0000FF"><b><%=Stud_Region%></b></font></td>
        </tr>
        <tr>
          <td width="50%" align="left"><font face="Arial" size="2"><b>Phone:</b></font></td>
          <td width="50%"><font face="Arial" size="2" color="#0000FF"><b><%=Stud_Phone%></b></font></td>
        </tr>
        <tr>
          <td width="50%" align="left"><font face="Arial" size="2"><b>Age:</b></font></td>
          <td width="50%"><font face="Arial" size="2" color="#0000FF"><b><%=Stud_Age%></b></font></td>
        </tr>
        <tr>
          <td width="50%" align="left"><font face="Arial" size="2"><b>Gender:</b></font></td>
          <td width="50%"><font face="Arial" size="2" color="#0000FF"><b><%=Stud_Gender%></b></font></td>
        </tr>
        <tr>
          <td width="50%" align="left"><font face="Arial" size="2"><b>Date
            Registered:</b></font></td>
          <td width="50%"><font face="Arial" size="2" color="#0000FF"><b><%=Stud_DateRegistered%></b></font></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td width="100%">
      <hr>
    </td>
  </tr>
</table>

</body>

</html>
<%	
rstemp.close
set rstemp=nothing
conntemp.close
set conntemp=nothing
%>
<%else%>
<%response.redirect "../createlesson/login.asp"%>
<%end if%>