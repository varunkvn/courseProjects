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
	sqltemp = "SELECT * FROM quizzes" 
	rstemp.Open sqltemp,conntemp,1,3
	
	sMode=Request.form("Submit")
	if sMode="Update Changes" then
		for j = 1 to Session("num")
			if not Request.form("status_"&j) = "" then
				rstemp("Status") = 0
				rstemp.Update 
			else
				rstemp("Status") = 1
				rstemp.Update
			end if
			rstemp.movenext
		next
	end if
	
	rstemp.movefirst
	 
%>
<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Activate or Deactivate a Quiz</title>
</head>

<body topmargin="10" leftmargin="20" background="../images/GENERIC.gif">

  <table border="0" width="540" cellspacing="5" cellpadding="5">
    <tr>
      <td width="100%">
        <form method="POST" action="view_delete.asp">
          <table border="0" width="100%" cellspacing="1" cellpadding="2">
            <tr>
              <td width="100%" colspan="7">
                <p align="center"><b><font face="Arial,helvetica,verdana,geneva" size="3" color="#2C4D9C">Activate
                or Deactivate a QUIZ</font></b></td>
            </tr>
            <tr>
              <td width="6%" align="center" bgcolor="#FFCC00">
                <p align="center"><b><font face="Verdana,Arial,helvetica,geneva" size="1">No.</font></b></p>
              </td>
              <td width="22%" align="center" bgcolor="#FFCC00"><b><font face="Verdana,Arial,helvetica,geneva" size="1">Quiz
                Name</font></b></td>
              <td width="14%" align="center" bgcolor="#FFCC00"><b><font face="Verdana,Arial,helvetica,geneva" size="1">Author</font></b></td>
              <td width="20%" align="center" bgcolor="#FFCC00"><b><font face="Verdana,Arial,helvetica,geneva" size="1">No.
                of Questions</font></b></td>
              <td width="8%" align="center" bgcolor="#FFCC00"><b><font face="Verdana,Arial,helvetica,geneva" size="1">%
                to Pass</font></b></td>
              <td width="17%" align="center" bgcolor="#FFCC00"><b><font face="Verdana,Arial,helvetica,geneva" size="1">Date
                Created</font></b></td>
              <td width="15%" align="center" bgcolor="#FFCC00"><b><font face="Verdana,Arial,helvetica,geneva" size="1">Status</font></b></td>
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
            		Quiz_Name = rstemp("quiz_name")
            		Quiz_Author = rstemp("AuthorName")
            		Quiz_No_of_Questions=rstemp("number_of_questions")
            		Quiz_Percentage_to_Pass=rstemp("percentage_to_pass")
            		Quiz_Date_Created = rstemp("DateCreated")
            		Quiz_Status = rstemp("Status")
            		if Quiz_Status = 0 then
            			Status_Label = "Deactivate"
            			checked = "checked"
            		else
            			Status_Label = "Activate"
            			checked = ""
            		end if
            %>
            <tr>
              <td width="6%" valign="top" bgcolor="<%=bgcolor%>">
                <p align="center"><font face="Arial,helvetica,verdana,geneva" size="1"><%=k%></font></td>
              <td width="22%" valign="top" bgcolor="<%=bgcolor%>"><font face="Arial,helvetica,verdana,geneva" size="1"><%=Quiz_Name%></font></td>
              <td width="14%" valign="top" bgcolor="<%=bgcolor%>"><font face="Arial,helvetica,verdana,geneva" size="1"><%=Quiz_Author%></font></td>
              <td width="20%" valign="top" bgcolor="<%=bgcolor%>">
                <p align="center"><font face="Arial,helvetica,verdana,geneva" size="1"><%=Quiz_No_of_Questions%></font></td>
              <td width="8%" valign="top" bgcolor="<%=bgcolor%>">
                <p align="center"><font face="Arial,helvetica,verdana,geneva" size="1"><%=Quiz_Percentage_to_Pass%></font></td>
              <td width="17%" valign="top" bgcolor="<%=bgcolor%>">
                <p align="center"><font face="Arial,helvetica,verdana,geneva" size="1"><%=Quiz_Date_Created%></font></td>
              <td width="15%" valign="top" bgcolor="<%=bgcolor%>">
                <p><font size="1"><input type="checkbox" name="status_<%=k%>" <%=checked%>><font face="Arial,helvetica,verdana,geneva" size="1"><%=Status_Label%></font></font></td>
            </tr>
  <center>
            <%rstemp.movenext
            loop
            Session("num") = k
            %>
            <tr>
              <td width="100%" colspan="7">
                <p align="center"><font face="Arial,helvetica,verdana,geneva" size="2"><input type="submit" value="Update Changes" name="Submit"></font></td>
            </tr>
          </table>
        </form>
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
