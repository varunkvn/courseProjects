<%
	if Session("username") <> "" and session("password") <> "" then
	
	Dim DB_CONN_STRING
	DB_CONN_STRING = "DBQ=" & Server.MapPath("../db/quiz.mdb") & ";"
	DB_CONN_STRING = DB_CONN_STRING & "Driver={Microsoft Access Driver (*.mdb)};"
	DB_CONN_STRING = DB_CONN_STRING & "DriverId=25;FIL=MS Access;"
	
	' Create DB connection and connect to the DB
	Set conntemp = Server.CreateObject("ADODB.Connection")
	conntemp.Open DB_CONN_STRING
		
	sMode=Request.form("Submit")
	if sMode="Update Changes" then
		Set rstemp2 = Server.CreateObject("ADODB.Recordset")
		sqltemp2 = "SELECT * FROM registration where Status=1" 
		rstemp2.Open sqltemp2,conntemp,1,3
		for j = 1 to rstemp2.Recordcount
			if not Request.form("status_"&j) = "" then
				rstemp2("Status") = 0
				rstemp2.Update
			end if
			rstemp2.movenext
		next
		rstemp2.close
		set rstemp2 = nothing
	end if
	
	Set rstemp = Server.CreateObject("ADODB.Recordset")
	sqltemp = "SELECT * FROM registration where Status=1" 
	rstemp.Open sqltemp,conntemp,1,3
%>
<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Student List</title>
</head>

<body topmargin="10" leftmargin="20" background="../images/GENERIC.gif">

  <table border="0" width="540" cellspacing="5" cellpadding="5">
    <tr>
      <td width="100%">
        <form method="POST" action="../registration/student_list.asp">
          <table border="0" width="100%" cellspacing="1" cellpadding="2">
            <tr>
              <td width="100%" colspan="5">
                <p align="center"><b><font face="Arial,helvetica,verdana,geneva" size="3" color="#2C4D9C">Student
                List</font></b></td>
            </tr>
            <tr>
              <td width="6%" align="center" bgcolor="#FFCC00">
                <p align="center"><b><font face="Verdana,Arial,helvetica,geneva" size="2">No.</font></b></p>
              </td>
              <td width="22%" align="center" bgcolor="#FFCC00"><b><font face="Verdana,Arial,helvetica,geneva" size="2">Student
                Name</font></b></td>
              <td width="17%" align="center" bgcolor="#FFCC00"><b><font face="Verdana,Arial,helvetica,geneva" size="2">Date
                Registered</font></b></td>
              <td width="13%" align="center" bgcolor="#FFCC00"><b><font face="Verdana,Arial,helvetica,geneva" size="2">Details</font></b></td>
              <td width="10%" align="center" bgcolor="#FFCC00"><b><font face="Verdana,Arial,helvetica,geneva" size="2">Status</font></b></td>
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
      			Stud_ID = rstemp("StudID")
            		Stud_Name = rstemp("FirstName") & ", " & rstemp("LastName")
            		Stud_Date_Registered = rstemp("DateRegistered")
            %>
            <tr>
              <td width="6%" valign="top" bgcolor="<%=bgcolor%>">
                <p align="center"><font face="Arial,helvetica,verdana,geneva" size="2"><%=k%></font></td>
              <td width="22%" valign="top" bgcolor="<%=bgcolor%>"><font face="Arial,helvetica,verdana,geneva" size="2"><%=Stud_Name%></font></td>
              <td width="17%" valign="top" bgcolor="<%=bgcolor%>">
                <p align="center"><font face="Arial,helvetica,verdana,geneva" size="2"><%=Stud_Date_Registered%></font></td>
              <td width="13%" valign="top" bgcolor="<%=bgcolor%>">
                <p align="center"><font face="Arial,helvetica,verdana,geneva" size="2"><a href="student_details.asp?studid=<%=Stud_ID%>" target="_self"><b><font color="#2C4D9C">View</font></b></a></font></td>
              <td width="10%" valign="top" bgcolor="<%=bgcolor%>">
                <p><font size="2"><input type="checkbox" name="status_<%=k%>"><font face="Arial,helvetica,verdana,geneva" size="1">Delete</font></font></td>
            </tr>
  <center>
            <%rstemp.movenext
            loop
            Session("num") = k
            %>
            <tr>
              <td width="100%" colspan="5">
                <p align="center"><font face="Arial,helvetica,verdana,geneva" size="2"><input type="submit" value="Update Changes" name="Submit"></font></td>
            </tr>
          </table>
        </form>
        </center>
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
