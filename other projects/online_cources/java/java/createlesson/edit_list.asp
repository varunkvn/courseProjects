<%
	if Session("username") <> "" and session("password") <> "" then
	
	Dim DB_CONN_STRING
	DB_CONN_STRING = "DBQ=" & Server.MapPath("../db/quiz.mdb") & ";"
	DB_CONN_STRING = DB_CONN_STRING & "Driver={Microsoft Access Driver (*.mdb)};"
	DB_CONN_STRING = DB_CONN_STRING & "DriverId=25;FIL=MS Access;"
	
	' Create DB connection and connect to the DB
	Set conntemp = Server.CreateObject("ADODB.Connection")
	conntemp.Open DB_CONN_STRING
	
	Set rstemp = Server.CreateObject("ADODB.Recordset")
	sqltemp = "SELECT * FROM quizzes where Status=0" 
	rstemp.Open sqltemp,conntemp 
%>
<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>List of Quizzes</title>
</head>

<body topmargin="10" leftmargin="20" background="../images/GENERIC.gif">

  <table border="0" width="540" cellspacing="5" cellpadding="5">
    <tr>
      <td width="100%">
        <table border="0" width="100%" cellspacing="1" cellpadding="4">
          <tr>
            <td width="100%" valign="top" colspan="4" align="center"><b><font face="Arial,helvetica,verdana,geneva" color="#2C4D9C" size="3">Select
              a Quiz below to edit</font></b></td>
          </tr>
          <tr>
            <td width="8%" valign="top" align="center" bgcolor="#FFCC00"><font face="Arial,helvetica,verdana,geneva" size="2"><b>No.</b></font></td>
            <td width="42%" valign="top" align="center" bgcolor="#FFCC00"><font face="Arial,helvetica,verdana,geneva" size="2"><b>Quiz
              Name</b></font></td>
            <td width="35%" valign="top" align="center" bgcolor="#FFCC00"><font face="Arial,helvetica,verdana,geneva" size="2"><b>Date
              Created</b></font></td>
            <td width="15%" valign="top" align="center" bgcolor="#FFCC00">&nbsp;</td>
          </tr>
          <%dim k%>
            <%k=0%>
            <%do while not rstemp.eof
            		if (k mod 2) = 0 then
      				bgcolor = "#DFDFDF"
      			else
      				bgcolor = "#FFFFFF"
      			end if
      			k = k + 1
      			Quiz_ID = rstemp("quiz_id")
            		Quiz_Name = rstemp("quiz_name")
            		Quiz_Date_Created = rstemp("DateCreated")
            %>
          <tr>
            <td width="8%" valign="top" bgcolor="<%=bgcolor%>">
              <p align="center"><font face="Arial,helvetica,verdana,geneva" size="2"><%=k%></font></td>
            <td width="42%" valign="top" bgcolor="<%=bgcolor%>"><font face="Arial,helvetica,verdana,geneva" size="2"><%=Quiz_Name%></font></td>
            <td width="35%" valign="top" bgcolor="<%=bgcolor%>">
              <p align="center"><font face="Arial,helvetica,verdana,geneva" size="1"><%=Quiz_Date_Created%></font></td>
            <td width="15%" valign="top" bgcolor="<%=bgcolor%>">
              <p align="center"><font face="Arial,helvetica,verdana,geneva" size="2"><b><a href="edit.asp?qid=<%=Quiz_ID%>" target="main">Edit</a></b></font></td>
          </tr>
          <%rstemp.movenext
            loop%>
          <tr>
            <td width="8%" valign="top"></td>
            <td width="42%" valign="top"></td>
            <td width="35%" valign="top"></td>
            <td width="15%" valign="top"></td>
          </tr>
        </table>
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
