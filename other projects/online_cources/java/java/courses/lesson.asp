<%
QUIZ_ID = request.querystring("QUIZ_ID")

if not QUIZ_ID="" then
	session("QUIZ_ID") = QUIZ_ID
end if

' Takes a integer parameter and converts it to the appropriate letter
Function GetLetterFromAnswerNumber(iInput)
Dim strTemp
	Select Case iInput
		Case 0
			strTemp = "A"
		Case 1
			strTemp = "B"
		Case 2
			strTemp = "C"
		Case 3
			strTemp = "D"
		Case 4
			strTemp = "E"
		Case 5
			strTemp = "F"
	End Select
	GetLetterFromAnswerNumber = strTemp
End Function

' To simplify and streamline the code I split this line into many parts,
' wrapped it into a function and commented it so you'd have a better
' chance of figuring out what I'm doing since I usually can't!

Function GetAnswerFromAnswerString(iQuestionNumber, strAnswers)
	Dim strTemp
	Dim iOffset
	' I use InStrRev since I want to retrieve the last entered value
	' in case they went back and changed their mind. 
	
	' Find the location of the question number we want to use
	iOffset = InStrRev(strAnswers, "|" & iQuestionNumber & "|", -1, 1)
	
	' Get our answer by using the offset we just found and then moving
	' right the length of the question indicator to arrive at the
	' appropriate letter
	strTemp = Mid(strAnswers, iOffset + Len("|" & iQuestionNumber & "|"), 1)

	' There's no way it should be anything else, but to be sure we
	' convert it to a string and make sure it's uppercase
	GetAnswerFromAnswerString = UCase(CStr(strTemp))
End Function

Dim DB_CONN_STRING
DB_CONN_STRING = "DBQ=" & Server.MapPath("../db/quiz.mdb") & ";"
DB_CONN_STRING = DB_CONN_STRING & "Driver={Microsoft Access Driver (*.mdb)};"
DB_CONN_STRING = DB_CONN_STRING & "DriverId=25;FIL=MS Access;"

Dim cnnQuiz, rsQuiz     'DB objects if we use the DB for the info

Dim I                   'our standard (improperly named) looping variable
Dim iNumberOfQuestions  'the number of questions in the test
Dim iQuestionNumber     'the question we're currently on
Dim strQuestionText     'text of the question to be asked
Dim aAnswers            'array of choices for the question to be asked
                        'if we hard code, then I also use it for the
                        'correct answers when I go to grade the quiz
Dim strAnswers          'stores the question numbers and response choices
                        'seperated by |'s
Dim iScore              'so we know how well the user did
Dim bAbort              'added after I had finished to account for closed sessions
Dim strResults          'another late addition for the each question breakdown!

bAbort = False          'set it to false since we only want to abort in certain cases


' Now to all our other variables!
' If this is the first call to the quiz then init everything
' o/w retrieve values we need.  We check by looking for the
' Question ID from the querystring.
If Request.QueryString("qid") = "" Then
	' Retrieve and Set the Quiz Info
	' Code to use DB!
	' Create DB connection and connect to the DB
	Set cnnQuiz = Server.CreateObject("ADODB.Connection")
	cnnQuiz.Open DB_CONN_STRING
		
	' Create RS and query DB for quiz info
	Set rsQuiz = Server.CreateObject("ADODB.Recordset")
	rsQuiz.Open "SELECT * FROM quizzes WHERE quiz_id=" & Session("QUIZ_ID") & ";", cnnQuiz
		
	' Set our session vars
	Session("QuizName") = CStr(rsQuiz.Fields("quiz_name").Value)
	Session("NumberOfQuestions") = CInt(rsQuiz.Fields("number_of_questions").Value)
	Session("PercentageToPass") = CInt(rsQuiz.Fields("percentage_to_pass").Value)
		
	' Close and dispose of our DB objects
	rsQuiz.Close
	Set rsQuiz = Nothing
	cnnQuiz.Close
	Set cnnQuiz = Nothing

	' Set our question indicator to 1 and init our answer string
	iQuestionNumber = 1
	Session("AnswerString") = "|"
Else
	'Check to be sure we've still got a session!
	If Session("AnswerString") = "" Then
		Response.Write "I'm sorry, but you've taken too long.  You can start over by "
		Response.Write "clicking <A HREF=""" & Request.ServerVariables("URL") & """>here</A>."
		' I'd normally just do a response.end, but I can't because I'm inside of our
		' site template.  I need the script to complete so I've declared and set a Flag
		'Response.End
		bAbort = True
	End If
	
	' Get the number of the question we're processing
	iQuestionNumber = CInt(Request.QueryString("qid"))
		
	' Log selected answer to last question
	Session("AnswerString") = Session("AnswerString") & iQuestionNumber & "|" & _
		GetLetterFromAnswerNumber(CInt(Request.QueryString("sa"))) & "|"
	
	' Increment question identifier
	iQuestionNumber = iQuestionNumber + 1
End If

%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Lesson</title>
<link rel="stylesheet" type="text/css" href="../css/stylesheets.css">
</head>

<body topmargin="10" leftmargin="20" background="../images/GENERIC.gif">

<table border="0" width="540" cellspacing="5" cellpadding="10">

<%
If Not bAbort Then

	' ******* Set this to a local variable to avoid accessing the collection each time
	' ******* This isn't required, but makes it easier for me to access and
	' ******* supposedly speeds it up... I'm not sure how much, but it can't hurt!
	
	iNumberOfQuestions = Session("NumberOfQuestions")

	' ******* Check to see it the quiz is over.  If so then show results, o/w
	' ******* ask the next question
	If iQuestionNumber > iNumberOfQuestions Then
		' ******* Process results and show end quiz status report
		
		' ******* Done for the same reason as for iNumberOfQuestions a few lines above
		strAnswers = Session("AnswerString")
		
		' ******* Retrieve Correct Answers and compare to the entered ones
		' ******* Code to use DB!
		' ******* Create DB connection and connect to the DB
		Set cnnQuiz = Server.CreateObject("ADODB.Connection")
		cnnQuiz.Open DB_CONN_STRING
				
		' ******* Create RS and query DB for quiz info
		Set rsQuiz = Server.CreateObject("ADODB.Recordset")
		' ******* Specify 3, 1 (Static, Read Only)
		rsQuiz.Open "SELECT * FROM questions WHERE quiz_id=" & Session("QUIZ_ID") & _
			" ORDER BY question_number;", cnnQuiz, 3, 1
				
		iScore = 0
		I = 1
		Do While Not rsQuiz.EOF
			If UCase(CStr(rsQuiz.Fields("correct_answer").Value)) = _
				GetAnswerFromAnswerString(I, strAnswers) Then
					
				iScore = iScore + 1
				' ******* This and the Else could be used to output a
				' ******* correctness status for each question
				' ******* Also useful for bug hunting!
				' ******* Response.Write "Right" & "<BR>" & vbCrLf
			Else
				' ******* Response.Write "Wrong" & "<BR>" & vbCrLf
				strResults = strResults & I & ", "
			End If
			I = I + 1
			rsQuiz.MoveNext
		Loop

		' ******* Close and dispose of our DB objects
		rsQuiz.Close
		Set rsQuiz = Nothing
		cnnQuiz.Close
		Set cnnQuiz = Nothing

		' ******* Convert score to a percentage rounded to the whole number
		iScore = Round((iScore / iNumberOfQuestions) * 100)

%>
    <tr>
    <td width="100%">
    <%If iScore >= Session("PercentageToPass") Then%>
      <p align="center">
      <b><font face="Arial,helvetica,verdana,geneva" size="3" color="#FF6600">Congratulations!</font><font face="Arial,helvetica,verdana,geneva" size="2">  You've passed the quiz with a score of  <%=iScore%>%
      </font></b>
      <%else%>
      <p align="center">
      <font face="Arial,helvetica,verdana,geneva" size="2"><b>
      Sorry!  You needed to achieve a score of   <%=Session("PercentageToPass")%> or higher to pass.
      <br>
      Unfortunately, your score was only  <%=iScore%>
      <br>
      You can take the test again by clicking <A HREF="<%Request.ServerVariables("URL")%>" target="_parent">here</A>
      </b></font>
      <%end if%>
      <%	If Len(strResults) <> 0 Then
			Response.Write "You missed the following questions: " & Left(strResults, Len(strResults) - 2)
			Response.Write "<BR>" & vbCrLf
		End If
	%>
      </td>
  </tr>
  <%ELSE%>
  <%' Retrieve and Set the Question Info
		' Code to use DB!
		' Create DB connection and connect to the DB
		Set cnnQuiz = Server.CreateObject("ADODB.Connection")
		cnnQuiz.Open DB_CONN_STRING
				
		' Create RS and query DB for quiz info
		Set rsQuiz = Server.CreateObject("ADODB.Recordset")
		rsQuiz.Open "SELECT * FROM questions WHERE quiz_id=" _
			& Session("QUIZ_ID") & " AND question_number=" & iQuestionNumber & ";", cnnQuiz
				
		' Set our question info
		strQuestionText = CStr(rsQuiz.Fields("question_text").Value)
			
		' Get an array of answers
		aAnswers = Array( _
		CStr(rsQuiz.Fields("answer_a").Value & ""), _
		CStr(rsQuiz.Fields("answer_b").Value & ""), _
		CStr(rsQuiz.Fields("answer_c").Value & ""), _
		CStr(rsQuiz.Fields("answer_d").Value & ""), _
		CStr(rsQuiz.Fields("answer_e").Value & ""), _
		CStr(rsQuiz.Fields("answer_f").Value & ""))
				
		' This is probably bad coding style, but too bad... it works!
		For I = LBound(aAnswers) To UBound(aAnswers)
			If aAnswers(I) = "" Then
				ReDim Preserve aAnswers(I - 1)
				Exit For
			End If
		Next ' I

		' Close and dispose of our DB objects
		rsQuiz.Close
		Set rsQuiz = Nothing
		cnnQuiz.Close
		Set cnnQuiz = Nothing
		
		' Now that we've got the variables set...
		' show the appropriate question and choices
%>
    <tr>
    <td width="100%">
    <font face="Arial,helvetica,verdana,geneva" size="2">
    Progress Indicator:
		<%
		Const BAR_LENGTH = 160
		If iQuestionNumber = 1 Then
			' Since a 0 width is ignored by the browsers we need to remove the image altogether!
			Response.Write "<IMG SRC=""../images/spacer_red.gif"" HEIGHT=""10"" WIDTH="""
			Response.Write BAR_LENGTH
			Response.Write """><BR>"
		Else
			Response.Write "<IMG SRC=""../images/spacer_blue.gif"" HEIGHT=""10"" WIDTH="""
			Response.Write (BAR_LENGTH / iNumberOfQuestions) * (iQuestionNumber - 1) 
			Response.Write """>"
			Response.Write "<IMG SRC=""./images/spacer_red.gif"" HEIGHT=""10"" WIDTH="""
			Response.Write (BAR_LENGTH / iNumberOfQuestions) * (iNumberOfQuestions - (iQuestionNumber - 1))
			Response.Write """><BR>"
		End If
		%>
		Question <%= iQuestionNumber %> of <%= iNumberOfQuestions %><BR>
		
		<BR>

		<STRONG><%= iQuestionNumber %>.</STRONG>&nbsp;&nbsp;<%= strQuestionText %><BR>

		<BR>

		<STRONG>Choices:</STRONG>

    </font>

		<OL TYPE="A">
        <font face="Arial,helvetica,verdana,geneva" size="2">
		<%
		For I = LBound(aAnswers) to UBound(aAnswers)
			Response.Write "<LI><b><A HREF=""" & Request.ServerVariables("URL")
			Response.Write "?qid=" & iQuestionNumber & "&sa=" & I & """ class=n5>"
			Response.Write aAnswers(I) & "</A></b></LI>" & vbCrLf
		Next
		%>
        </font>
		</OL>
    </td>
  </tr>
  <tr>
    <td width="100%">
    <p align="right"><font face="Arial,helvetica,verdana,geneva" size="2">
    <%if not iQuestionNumber =1 then %><a href="Javascript:history.go(-1);" class=n5>Previous</a> | <%end if%><%if not iQuestionNumber=iNumberOfQuestions then%><A HREF="lesson.asp?qid=<%=iQuestionNumber%>&sa=<%=iQuestionNumber + 1%>" class=n5>Next</a><%end if%>
    </font></p>
    </td>
  </tr>
  <%
  end if
  end if 'nAbort
  %>
    
</table>

</body>

</html>













