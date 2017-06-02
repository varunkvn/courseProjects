<%
	filename = session("filename")
	authorsname = session("authorsname")
	numofquestions = session("numofquestions")
	
	
%>
<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Enter Quiz Information</title>
<script language="Javascript">
<!--
	function validate_fields(){
		for(var i=0; i<document.form1.elements.length; i++){
			if (document.form1.elements[i].value.length < 1) {
				alert("Please Fill in the Fields!!")
				document.form1.elements[i].focus();
				return false;
			}
		}
		document.form1.submit();
	}
//-->
</script>
</head>

<body topmargin="10" leftmargin="20" background="../images/GENERIC.gif">

  <table border="0" width="540" cellspacing="5" cellpadding="5">
    <tr>
      <td width="100%">
        <form name="form1" method="POST" action="create_04.asp" onSubmit="validate_fields(); return false;">
          <table border="0" width="100%" cellspacing="0" cellpadding="2" height="158">
            <tr>
              <td width="100%">
                <p align="center"><font face="Arial,helvetica,verdana,geneva" size="2"><img border="0" src="../images/enterquizinformationheader.gif" width="232" height="23"></font></td>
            </tr>
            <tr>
              <td width="100%">
                <font face="Arial,helvetica,verdana,geneva" size="2"><b>Enter
                your own quiz instruction set:<br>
                <textarea rows="3" name="instruction" cols="40">Answer the questions below and then click "submit" to send your answers.</textarea></b></font></td>
            </tr>
            <%dim k, kk%>
            <%k = 1%>
            <%dim Ttype(100)%>
            <%dim mcnum%>
            <%for i = 1 to numofquestions%>
            <%Ttype(i) = request.form("Type"&i)%>           
            <tr>
              <td width="100%" height="21">
                <hr>
              </td>
            </tr>
            <tr>
              <td width="100%"><font face="Arial,helvetica,verdana,geneva" size="2"><b>Question
                <%=i%> (<%if Ttype(i)="T1" then%>True-False<%else%>Multiple-Choice<%end if%>):</b></font>
                <p><font face="Arial,helvetica,verdana,geneva" size="2"><b>Enter
                Question Text:</b><br>
                <input type=Hidden name="txtType<%=i%>" value="<%=Ttype(i)%>">
                <textarea rows="2" name="txt_Q_<%=i%>" cols="40"></textarea></font></p>
                <%if Ttype(i) = "T1" then%>
                <p><font face="Arial,helvetica,verdana,geneva" size="2"><b>Answer:</b>
                <input type="radio" value="True" checked name="Answer<%=k%>"> True <input type="radio" name="Answer<%=k%>" value="False">
                False</font></p>
                <%else%>
                <%mcnum = request.form("mcnum"&i)%> 
                <p><font face="Arial,helvetica,verdana,geneva" size="2"><b>Alternative answers (Indicate correct answer by selecting the button next to it) :</b></font></p>
                <p><font face="Arial,helvetica,verdana,geneva" size="2">
                <%kk = 1%>
                <%for j = 1 to mcnum%> 
                <input type="radio" name="Answer<%=k%>" value="<%=kk%>" checked> <input type="text" name="txtans<%=i%>_<%=kk%>" size="40"><br>
                <%kk = kk + 1%>
                <%next%>
                <input type=Hidden name="mc<%=i%>" value="<%=mcnum%>">
                <%end if%>
              </font>
                </td>
            </tr>
            <%k=k + 1%>
            <%next%>
            <tr>
              <td width="100%" height="21">
                <hr>
              </td>
            </tr>
            <tr>
              <td width="100%">
                <p align="center"><font face="Arial,helvetica,verdana,geneva" size="2"><input type="submit" value="Submit Information" name="Submit">&nbsp;
          <input type="reset" value="Clear Information" name="Reset"></font></td>
            </tr>
          </table>
        </form>
      </td>
    </tr>
  </table>

</body>

</html>
