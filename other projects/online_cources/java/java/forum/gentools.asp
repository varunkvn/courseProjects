<%
'   dim admin_mode
   admin_mode = admin_mode
   
   function IsVoid (s)
      if IsEmpty(s) or IsNull(s) or s = "" then
         IsVoid = true
      else
         IsVoid = false
      end if
   end function
   
   function WriteLn (s)
      Response.Write s & vbCrLf
   end function

   function EscapeString (sStr)
      Dim s
      s = sStr
   	pos = InStr(s, "'")
	   While pos > 0
		   s = Mid(s, 1, pos) & "'" & Mid(s, pos + 1)
   		pos = InStr(pos + 2, s, "'")
	   Wend
      EscapeString = s
   end function

   function JsString (sStr)
      Dim s
      s = sStr
   	pos = InStr(s, "'")
	   While pos > 0
		   s = Mid(s, 1, pos-1) & "\" & Mid(s, pos)
   		pos = InStr(pos + 2, s, "'")
	   Wend
	   
    	pos = InStr(s, vbCrLf)
	   While pos > 0
		   s = Mid(s, 1, pos - 1) & "\" & Mid(s, pos)
   		pos = InStr(pos + 3, s, vbCrLf)
	   Wend

      JsString = s
   end function

   function HTMLString (sStr)
      Dim s
      s = sStr
    	pos = InStr(s, vbCrLf)
	   While pos > 0
		   s = Mid(s, 1, pos - 1) & "<br>" & Mid(s, pos + 2)
   		pos = InStr(pos + 3, s, vbCrLf)
	   Wend

      HTMLString = s
   end function
   
   function WriteLn (s)
      Response.Write (s) & vbCrLf
   end function
   
   function Write (s)
      Response.Write (s)
   end function

   function GetDisplaySetting (searchkey)
      val = ""
      sPrefs = Request.Cookies("preferences")
      pos = InStr (sPrefs, ";")
      lastpos = 1
      do until pos <= 0
         token = Mid (sPrefs, lastpos, pos - lastpos)
         lastpos = pos + 1
         pos = InStr (pos + 1, sPrefs, ";")

         ' Now that we have the key, process the contents
         pos2 = InStr (token, "=")
         if pos2 > 0 then
            key = Left (token, pos2 - 1)
            if key = searchkey then
               val = Mid (token, pos2 + 1)
            end if
         end if
      loop
      GetDisplaySetting = val
   end function
%>
