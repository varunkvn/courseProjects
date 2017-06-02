<!-- #include file ="../db/adovbs.inc" -->
<% 
	dim questions(20)
	dim txtType(20)
	dim txtMcnum
	
	numofquestions = session("numofquestions")
	
	Dim DB_CONN_STRING
	DB_CONN_STRING = "DBQ=" & Server.MapPath("../db/quiz.mdb") & ";"
	DB_CONN_STRING = DB_CONN_STRING & "Driver={Microsoft Access Driver (*.mdb)};"
	DB_CONN_STRING = DB_CONN_STRING & "DriverId=25;FIL=MS Access;"
	
	' Create DB connection and connect to the DB
	Set conntemp = Server.CreateObject("ADODB.Connection")
	conntemp.Open DB_CONN_STRING
	
	Set rstemp = Server.CreateObject("ADODB.Recordset")
	sqltemp = "SELECT * FROM quizzes" 
	rstemp.Open sqltemp,conntemp,adOpendynamic, adLockPessimistic, adCMDText
	
	rstemp.AddNew
	rstemp("quiz_name") = session("filename")
	rstemp("AuthorName") = session("authorsname")
	rstemp("number_of_questions") = session("numofquestions")
	rstemp("percentage_to_pass") = 70
	rstemp("DateCreated") = Now()
	rstemp("instruction") = request.form("instruction")
	rstemp.Update
	
	set rstemp2 = server.createobject("ADODB.Recordset")
	sqltemp2 = "select * from questions"
	rstemp2.open sqltemp2, conntemp,adOpendynamic, adLockPessimistic, adCMDText
	
	set rstMax = server.createobject("ADODB.Recordset")
	sqlMax = "select max(quiz_id) from quizzes"
	rstMax.open sqlMax, conntemp
	
	Q_ID = rstMax.fields(0)
	
	for i = 1 to numofquestions
		questions(i) = request.form("txt_Q_"&i)
		txtType(i) = request.form("txtType"&i)
		if txtType(i)="T2" then
			txtMcnum = request.form("mc"&i)
			rstemp2.AddNew
			rstemp2("quiz_id") = Q_ID
			rstemp2("question_number") = i
			rstemp2("question_text") = questions(i)
			if request.form("Answer"&i) = 1 then
				correct_answer = "a"
			elseif request.form("Answer"&i) = 2 then
				correct_answer = "b"
			elseif request.form("Answer"&i) = 3 then
				correct_answer = "c"
			elseif request.form("Answer"&i) = 4 then
				correct_answer = "d"
			elseif request.form("Answer"&i) = 5 then
				correct_answer = "e"
			elseif request.form("Answer"&i) = 6 then
				correct_answer = "f"
			end if
			
			rstemp2("correct_answer") = correct_answer
			for j = 1 to txtMcnum
				rstemp2.fields(j+2) = Request.form("txtans"&i&"_"&j)
			next
			rstemp2("Type") = 2
			rstemp2("NumofChoices") = txtMcnum
			rstemp2.Update
		else
			rstemp2.AddNew
			rstemp2("quiz_id") = Q_ID
			rstemp2("question_number") = i
			rstemp2("question_text") = questions(i)
			rstemp2("answer_a") = "True"
			rstemp2("answer_b") = "False"
			if request.form("Answer"&i) = "True" then
				correct_answer = "a"
			elseif request.form("Answer"&i) = "False" then 
				correct_answer = "b"
			end if
			
			rstemp2("correct_answer") = correct_answer
			rstemp2("Type") = 1
			rstemp2.Update
		end if
	next
	
%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Save Quiz File</title>
</head>

<body topmargin="10" background="../images/GENERIC.gif">

<p align="center">&nbsp;</p>

<p align="center"><font face="Arial,helvetica,verdana,geneva" size="3"><b>Your
Quiz information had been correctly recorded in the database!!</b></font></p>

</body>

</html>
