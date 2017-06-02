<!-- #include file="../db/adovbs.inc" -->
<%
	if Session("username") <> "" and session("password") <> "" then
	
	qid = request.querystring("qid")
	
	dim Cur_Page
	dim Page_Count
	dim row_Count
	dim mcnum

	if Request("Page") = "" then 
		Cur_Page = 1
	else
		Cur_Page = CInt(Request("Page"))
	end if
	
	Dim DB_CONN_STRING
	DB_CONN_STRING = "DBQ=" & Server.MapPath("../db/quiz.mdb") & ";"
	DB_CONN_STRING = DB_CONN_STRING & "Driver={Microsoft Access Driver (*.mdb)};"
	DB_CONN_STRING = DB_CONN_STRING & "DriverId=25;FIL=MS Access;"
	
	' Create DB connection and connect to the DB
	Set conntemp = Server.CreateObject("ADODB.Connection")
	conntemp.Open DB_CONN_STRING
	
	sMode=request.form("Submit")
	if sMode="Save Changes" then
		txt_Title = request.form("title")
		txt_Author = request.form("author")
		txt_Instruction = request.form("instruction")
		
		set rstUpdate=server.createobject("ADODB.Recordset")
		sqlUpdate = "select * from quizzes where quiz_id=" & qid
		rstUpdate.open sqlUpdate,conntemp, adopenstatic, adLockPessimistic, adCMDText
		
		if not txt_Title = "" then
			rstUpdate("quiz_name") = txt_Title
		end if
		if not txt_Author = "" then
			rstUpdate("AuthorName") = txt_Author
		end if
		if not txt_Instruction = "" then
			rstUpdate("instruction") = txt_Instruction
		end if
		rstUpdate.Update
		
		set rstUpdate = server.createobject("ADODB.Recordset")
		sqlUpdate = "select * from questions where quiz_id="&qid&" and question_number="&Cur_Page
		rstUpdate.open sqlUpdate, conntemp, adopenstatic, adLockPessimistic, adCMDText
		
		txt_Question = request.form("txt_Question")
		if not txt_Question = "" then
			rstUpdate("question_text") = txt_Question
		end if
		
		if Session("Ttype") = 1 then
			crt_ans = request.form("Answer")
			if crt_ans = "True" then
				rad_val = "a"
			else
				rad_val = "b"
			end if
			rstUpdate("correct_answer") = rad_val
		elseif session("Ttype") = 2 then
			for m = 1 to session("mcnum")
				if not Request.form("txtans"&m) = "" then
					rstUpdate.fields(m+2) = Request.form("txtans"&m)
				end if
			next
			crt_ans = request.form("Answer")
			select case crt_ans
				case 1
					rad_val = "a"
				case 2
					rad_val = "b"
				case 3
					rad_val = "c"
				case 4
					rad_val = "d"
				case 5
					rad_val = "e"
				case 6
					rad_val = "f"
			end select
			rstUpdate("correct_answer") = rad_val
		end if
		
		rstUpdate.Update
		
	end if
		
	set rstHeader = server.createobject("ADODB.Recordset")
	sqlHeader="select * from quizzes where quiz_id=" & qid
	rstHeader.open sqlHeader, conntemp
	
	txtTitle = rstHeader("quiz_name")
	txtAuthor = rstHeader("AuthorName")
	txtInstruction = rstHeader("instruction")
	
	Set rstemp = Server.CreateObject("ADODB.Recordset")
	sqltemp = "SELECT * FROM questions where quiz_id="&qid&" order by question_number"
	rstemp.CursorLocation = adUseClient
	rstemp.PageSize = 1
	rstemp.Open sqltemp,conntemp, adopenstatic, adLockPessimistic, adCMDText
	Page_Count = rstemp.PageCount
	ReCount = rstemp.Recordcount
	
	if 1 > Cur_Page then Cur_Page = 1
	if Cur_Page > Page_Count then Cur_Page = Page_Count
	
	rstemp.AbsolutePage = cINT(Cur_Page)
	row_Count =0
	
%>
<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Edit Quiz</title>
</head>

<body topmargin="10" leftmargin="20" background="../images/GENERIC.gif">

  <table border="0" width="540" cellspacing="5" cellpadding="5">
    <tr>
      <td width="100%">
        <form method="POST" action="edit.asp?Page=<%=Cur_Page%>&qid=<%=qid%>">
          <table border="0" width="100%" cellspacing="1" cellpadding="3">
            <tr>
              <td width="100%">
                <p align="center"><font face="Arial,helvetica,verdana,geneva" size="2"><img border="0" src="../images/editaquizheader.gif"></font>
                <p><font face="Arial,helvetica,verdana,geneva" size="2"><b>The
                information composing this quiz is as follows:</b></font></td>
            </tr>
            <tr>
              <td width="100%"><font face="Arial,helvetica,verdana,geneva" size="2"><b>Title:</b>
                <input type="text" name="title" size="40" value="<%=txtTitle%>"></font>
                <p><font face="Arial,helvetica,verdana,geneva" size="2"><b>Author:</b>
                <input type="text" name="author" size="40" value="<%=txtAuthor%>"></font></p>
                <p><font face="Arial,helvetica,verdana,geneva" size="2"><b>Instruction
                set:</b><br>
                <textarea rows="2" name="instruction" cols="60"><%=txtInstruction%></textarea></font></td>
            </tr>
		<%
			Ttype = rstemp("Type")
        		session("Ttype") = Ttype
        		mcnum = rstemp("NumofChoices")
        		session("mcnum") = mcnum
       	 	%>
            <tr>
              <td width="100%">
                <hr noshade size="1" color="#33CCFF">
              </td>
            </tr>
            <tr>
              <td width="100%"><font face="Arial,helvetica,verdana,geneva" size="2"><b>Question
                <%=Cur_Page%> (<%if Ttype=1 then%>True-False<%else%>Multiple-Choice<%end if%>):</b></font>
                <p><font face="Arial,helvetica,verdana,geneva" size="2"><b>Enter
                Question Text:</b><br>
                <textarea rows="2" name="txt_Question" cols="40"><%=rstemp("question_text")%></textarea></font></p>
                <%if Ttype =1 then%>
                <%select case rstemp("correct_answer")%>
                <%case "a"
                		chkVal = 1
                	case "b"
                		chkVal = 2
                	end select
                %>
                <p><font face="Arial,helvetica,verdana,geneva" size="2"><b>Answer:</b>
                <input type="radio" <%if chkVal=1 then%>checked<%end if%> name="Answer" value=True> True <input type="radio" name="Answer" <%if chkVal=2 then%>checked<%end if%> value=False>
                False</font></p>
                <%else%>
                <p><font face="Arial,helvetica,verdana,geneva" size="2"><b>Alternative answers (Indicate correct answer by selecting the button next to it) :</b></font></p>
                <p><font face="Arial,helvetica,verdana,geneva" size="2">
                <%for j = 1 to mcnum%>
                <%select case rstemp("correct_answer")%>
                <%case "a"
                		chkVal = 1
                	case "b"
                		chkVal = 2
                	case "c"
                		chkVal = 3
                	case "d"
                		chkVal = 4
                	case "e"
                		chkVal = 5
                	case "f"
                		chkVal = 6
                	end select
                %>
                <input type="radio" name="Answer" <%if j=chkVal then%>checked<%end if%> value=<%=j%>> <input type="text" name="txtans<%=j%>" size="40" value="<%=rstemp.fields(j+2)%>"><br>
                <%next%>
                <%end if%>
              </font>
                </td>
            </tr>
            <tr>
              <td width="100%">
                <hr noshade size="1" color="#33CCFF">
              </td>
            </tr>
            <tr>
              <td width="100%"><font face="Arial,helvetica,verdana,geneva" size="2"><b>Question
                <%=Cur_Page%> of <%=Page_Count%> :&nbsp; 
                <%for i = 1 to Page_Count%>
                <%if not Cur_Page = i then%>
                <a href="edit.asp?Page=<%=i%>&amp;qid=<%=qid%>" target="main"><%=i%></a> 
                <%else%>
                <%=i%>
                <%end if%>
                <%next%>
                </b></font>
                </td>
            </tr>
            <tr>
              <td width="100%">
                <hr noshade size="1" color="#33CCFF">
              </td>
            </tr>
            <tr>
              <td width="100%">
                <p align="center"><font face="Arial,helvetica,verdana,geneva" size="2"><input type="submit" value="Save Changes" name="Submit"></font></td>
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
