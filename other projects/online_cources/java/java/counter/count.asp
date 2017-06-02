<%

' Every time we count a user we will put the 
' latest count value in the session variable "TotalCount"
' If Session Variable TotalCount is empty
' that mean this user is new and session variable
' But if Session Variable already has the value
' Then we will not count this user again.

IF IsEmpty(Session("TotalCount")) THEN		
  Call CountThisUser
End IF

' It is good practice to use Functions and Sub procedure
' Because all the variables being used in sub or function 
' are automatically destroyed when Sub or Function finish process
' So you can use these Variables again in other functions
SUB CountThisUser()
  Dim FSO												' FileSystemObject
  Dim TS												' TextStreamObject
  Dim strFileName								' Counter text File Name
  Dim intOldCount
  Dim intNewCount
  Dim Create

  Create = True				
  
  ' Specify the Text file to store count value
  ' Because We Set Create = True
  ' File will be Created if it does not exist
  
  strFileName = Server.MapPath("Counter.txt")
  
  Set FSO = Server.CreateObject("Scripting.FileSystemObject")
  Set TS = FSO.OpenTextFile(strFileName, 1, Create)
  		
  IF NOT TS.AtEndOfStream Then
    intoldCount = TS.ReadAll
  Else
    intoldCount = 0
  End If
  
  TS.Close
  		
  intNewCount = intOldCount + 1
  		
  ' Store the value of intNewCount in Session Variable 
  ' So you can use it on different pages
  Session("TotalCount")= intNewCount
  		
  ' Write intNewCount Value back to text file
  
  Set TS = FSO.CreateTextFile(strFileName, Create)
  TS.Write intNewCount
  TS.Close
  Set FSO = Nothing
  Set TS = Nothing
End Sub
%>