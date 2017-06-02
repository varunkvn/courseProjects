<%
	sMode=Request.form("Submit")
	dim errors
	dim msgerror(2)
	for i = 0 to 1
		msgerror(i) = ""
	next
	errors = 0
	
	function IsVoid (s)
      		if IsEmpty(s) or IsNull(s) or s = "" then
        		IsVoid = true
      		else
        		IsVoid = false
      		end if
   	end function
	
	if sMode="Log In" then
		txt_username=Trim(Request.form("username"))
		txt_password=Trim(Request.form("password"))
		
		if IsVoid(txt_username) then
			errors = errors+1
			msgerror(0) = "Please enter your Username"
		end if
		
		if IsVoid(txt_password) then
			errors = errors + 1
			msgerror(1) = "Please enter your Password"
		end if
		
		if errors = 0 then
			Dim DB_CONN_STRING
			DB_CONN_STRING = "DBQ=" & Server.MapPath("../db/quiz.mdb") & ";"
			DB_CONN_STRING = DB_CONN_STRING & "Driver={Microsoft Access Driver (*.mdb)};"
			DB_CONN_STRING = DB_CONN_STRING & "DriverId=25;FIL=MS Access;"
	
			' Create DB connection and connect to the DB
			Set conntemp = Server.CreateObject("ADODB.Connection")
			conntemp.Open DB_CONN_STRING
	
			Set rstemp = Server.CreateObject("ADODB.Recordset")
			sqltemp = "SELECT * FROM tbl_Admin where Username='"&txt_username&"' and Password='"&txt_password&"'" 
			rstemp.Open sqltemp,conntemp
			
			if rstemp.eof then
				errors = errors+1
				msgerror(0) = "Invalid LOGIN information!!"
			else
				session("username") = txt_username
				session("password") = txt_password
				response.redirect "quiz_center.asp"
			end if
			
		end if
		
	end if
%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Login</title>
</head>

<body topmargin="20" leftmargin="20" background="../images/GENERIC.gif">

  <table border="0" width="540" cellspacing="5" cellpadding="10">
    <tr>
      <td width="100%">
        <form method="POST" action="login.asp">
          <div align="center">
            <center>
            <table border="0" width="75%" cellpadding="4">
              <tr>
                <td width="100%" colspan="2">
                  <p align="center"><b><font face="Arial,helvetica,verdana,geneva" color="#2C4D9C" size="3">Please
                  enter your log-in information below:</font></b></p>
                </td>
              </tr>
              <%if not msgerror(0) = "" then%>
              <tr>
                <td width="100%" colspan="2">
                  <p align="center"><b><font face="Arial,helvetica,verdana,geneva" size="2" color="#FF0000"><%=msgerror(0)%></font></b></p>
                </td>
              </tr>
              <%end if%>
              <tr>
                <td width="18%" align="right"><font face="Arial,helvetica,verdana,geneva" size="2"><b>Username:</b></font></td>
                <td width="82%"><input type="text" name="username" size="20"></td>
              </tr>
              <%if not msgerror(1) = "" then%>
              <tr>
                <td width="100%" colspan="2">
                  <p align="center"><b><font face="Arial,helvetica,verdana,geneva" size="2" color="#FF0000"><%=msgerror(1)%></font></b></p>
                </td>
              </tr>
              <%end if%>
              <tr>
                <td width="18%" align="right"><font face="Arial,helvetica,verdana,geneva" size="2"><b>Password:</b></font></td>
                <td width="82%"><input type="password" name="password" size="20"></td>
              </tr>
              <tr>
                <td width="100%" colspan="2">
                  <p align="center"><font face="Arial,helvetica,verdana,geneva" size="2"><input type="submit" value="Log In" name="Submit"></font></td>
              </tr>
            </table>
            </center>
          </div>
        </form>
      </td>
    </tr>
  </table>

</body>

</html>
