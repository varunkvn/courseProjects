<%
	filename= request.form("filename")
	authorsname = request.form("author")
	numofquestions = request.form("numofquestions")
	
	session("filename") = filename
	session("authorsname") = authorsname
	session("numofquestions") = numofquestions
	
%>
<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Create A Quiz</title>
</head>

<body topmargin="10" leftmargin="20" background="../images/GENERIC.gif">

  <table border="0" width="540" cellspacing="5" cellpadding="5">
    <tr>
      <td width="100%">
        <form method="POST" action="create_03.asp">
          <table border="0" width="100%" cellspacing="0" cellpadding="4">
            <tr>
              <td width="100%">
                <p align="center"><font face="Arial,helvetica,verdana,geneva" size="2"><img border="0" src="../images/createaquiz.gif" width="138" height="20"></font></p>
              </td>
            </tr>
            <%dim k%>
            <%k=1%>
            <% for i = 1 to numofquestions%>
            <tr>
              <td width="100%">
                <hr>
              </td>
            </tr>
            <tr>
              <td width="100%"><font face="Arial,helvetica,verdana,geneva" size="2"><b>Attributes
                for question <%=k%>:</b></font>
                <p><font face="Arial,helvetica,verdana,geneva" size="2"><b><u>Type:</u></b><br>
                <input type="radio" value="T1" name="Type<%=k%>">  True-False<br>
                <input type="radio" name="Type<%=k%>" value="T2" checked> Multiple
                Choice (with <select size="1" name="mcnum<%=k%>">
                  <option value="1">1</option>
                  <option value="2">2</option>
                  <option value="3">3</option>
                  <option value="4" selected>4</option>
                  <option value="5">5</option>
                  <option value="6">6</option>
                </select> choices)</font></td>
            </tr>
            <%k = k + 1%>
            <%next%>
            <tr>
              <td width="100%">
                <hr>
              </td>
            </tr>
            <tr>
              <td width="100%">
                <p align="center"><font face="Arial,helvetica,verdana,geneva" size="2"><input type="submit" value="Go to next page" name="Submit">
                <input type="reset" value="Reset this form" name="Reset"></font></td>
            </tr>
          </table>
        </form>
      </td>
    </tr>
  </table>

</body>

</html>
