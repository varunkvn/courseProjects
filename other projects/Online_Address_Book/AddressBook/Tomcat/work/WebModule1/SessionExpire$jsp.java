package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;


public class SessionExpire$jsp extends HttpJspBase {


    static {
    }
    public SessionExpire$jsp( ) {
    }

    private static boolean _jspx_inited = false;

    public final void _jspx_init() throws org.apache.jasper.runtime.JspException {
    }

    public void _jspService(HttpServletRequest request, HttpServletResponse  response)
        throws java.io.IOException, ServletException {

        JspFactory _jspxFactory = null;
        PageContext pageContext = null;
        HttpSession session = null;
        ServletContext application = null;
        ServletConfig config = null;
        JspWriter out = null;
        Object page = this;
        String  _value = null;
        try {

            if (_jspx_inited == false) {
                synchronized (this) {
                    if (_jspx_inited == false) {
                        _jspx_init();
                        _jspx_inited = true;
                    }
                }
            }
            _jspxFactory = JspFactory.getDefaultFactory();
            response.setContentType("text/html;ISO-8859-1");
            pageContext = _jspxFactory.getPageContext(this, request, response,
            			"", true, 8192, true);

            application = pageContext.getServletContext();
            config = pageContext.getServletConfig();
            session = pageContext.getSession();
            out = pageContext.getOut();

            // HTML // begin [file="/SessionExpire.jsp";from=(0,0);to=(43,0)]
                out.write("<html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns=\"http://www.w3.org/TR/REC-html40\">\r\n\r\n<head>\r\n<title>Welcome To My Address Book---Please Login Again</title>\r\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1252\">\r\n<Meta HTTP-Equiv = \"refresh\"  URL=default.asp>\r\n\r\n<link rel=\"File-List\" href=\"SessionExpire_files/filelist.xml\">\r\n\r\n<!--[if !mso]>\r\n<style>\r\nv\\:*         { behavior: url(#default#VML) }\r\no\\:*         { behavior: url(#default#VML) }\r\n.shape       { behavior: url(#default#VML) }\r\n</style>\r\n<![endif]--><!--[if gte mso 9]>\r\n<xml><o:shapedefaults v:ext=\"edit\" spidmax=\"1027\"/>\r\n</xml><![endif]-->\r\n\r\n</head>\r\n\r\n<script language=\"JavaScript\">\r\n\r\n\r\nfunction form_validator()\r\n{\r\n\r\n\tif(theForm.AdminName.value == \"\") {\r\n\t\t alert(\"Please enter your UserName!\");\r\n\t\t theForm.AdminName.focus();\r\n\t\t return(false);\r\n\t}\r\n\r\n\tif(theForm.AdminPassword.value == \"\") {\r\n\t\t alert(\"Please enter password!\");\r\n\t\t theForm.AdminPassword.focus();\r\n\t\t return(false);\r\n\t}\r\n\r\n}\r\n</script>\r\n\r\n<BODY bgcolor=\"#ffffff\">\r\n");

            // end
            // begin [file="/SessionExpire.jsp";from=(43,2);to=(46,0)]
                
                String s=(String)session.getAttribute("uname");
                System.out.println("This is a value YA ALLAH SHOW HO JAY AB"+s);
            // end
            // HTML // begin [file="/SessionExpire.jsp";from=(46,2);to=(162,14)]
                out.write("\r\n\r\n<p align=\"center\"><font size=\"4\" color=\"#FF0000\"><b>Session Has Been Expired Please ReLogin</b></font></p>\r\n<p><!--[if gte vml 1]><v:shapetype id=\"_x0000_t156\"\r\n coordsize=\"21600,21600\" o:spt=\"156\" adj=\"2809,10800\" path=\"m@25@0c@26@3@27@1@28@0m@21@4c@22@5@23@6@24@4e\">\r\n <v:formulas>\r\n  <v:f eqn=\"val #0\"/>\r\n  <v:f eqn=\"prod @0 41 9\"/>\r\n  <v:f eqn=\"prod @0 23 9\"/>\r\n  <v:f eqn=\"sum 0 0 @2\"/>\r\n  <v:f eqn=\"sum 21600 0 #0\"/>\r\n  <v:f eqn=\"sum 21600 0 @1\"/>\r\n  <v:f eqn=\"sum 21600 0 @3\"/>\r\n  <v:f eqn=\"sum #1 0 10800\"/>\r\n  <v:f eqn=\"sum 21600 0 #1\"/>\r\n  <v:f eqn=\"prod @8 2 3\"/>\r\n  <v:f eqn=\"prod @8 4 3\"/>\r\n  <v:f eqn=\"prod @8 2 1\"/>\r\n  <v:f eqn=\"sum 21600 0 @9\"/>\r\n  <v:f eqn=\"sum 21600 0 @10\"/>\r\n  <v:f eqn=\"sum 21600 0 @11\"/>\r\n  <v:f eqn=\"prod #1 2 3\"/>\r\n  <v:f eqn=\"prod #1 4 3\"/>\r\n  <v:f eqn=\"prod #1 2 1\"/>\r\n  <v:f eqn=\"sum 21600 0 @15\"/>\r\n  <v:f eqn=\"sum 21600 0 @16\"/>\r\n  <v:f eqn=\"sum 21600 0 @17\"/>\r\n  <v:f eqn=\"if @7 @14 0\"/>\r\n  <v:f eqn=\"if @7 @13 @15\"/>\r\n  <v:f eqn=\"if @7 @12 @16\"/>\r\n  <v:f eqn=\"if @7 21600 @17\"/>\r\n  <v:f eqn=\"if @7 0 @20\"/>\r\n  <v:f eqn=\"if @7 @9 @19\"/>\r\n  <v:f eqn=\"if @7 @10 @18\"/>\r\n  <v:f eqn=\"if @7 @11 21600\"/>\r\n  <v:f eqn=\"sum @24 0 @21\"/>\r\n  <v:f eqn=\"sum @4 0 @0\"/>\r\n  <v:f eqn=\"max @21 @25\"/>\r\n  <v:f eqn=\"min @24 @28\"/>\r\n  <v:f eqn=\"prod @0 2 1\"/>\r\n  <v:f eqn=\"sum 21600 0 @33\"/>\r\n  <v:f eqn=\"mid @26 @27\"/>\r\n  <v:f eqn=\"mid @24 @28\"/>\r\n  <v:f eqn=\"mid @22 @23\"/>\r\n  <v:f eqn=\"mid @21 @25\"/>\r\n </v:formulas>\r\n <v:path textpathok=\"t\" o:connecttype=\"custom\" o:connectlocs=\"@35,@0;@38,10800;@37,@4;@36,10800\"\r\n  o:connectangles=\"270,180,90,0\"/>\r\n <v:textpath on=\"t\" fitshape=\"t\" xscale=\"t\"/>\r\n <v:handles>\r\n  <v:h position=\"topLeft,#0\" yrange=\"0,4459\"/>\r\n  <v:h position=\"#1,bottomRight\" xrange=\"8640,12960\"/>\r\n </v:handles>\r\n <o:lock v:ext=\"edit\" text=\"t\" shapetype=\"t\"/>\r\n</v:shapetype><v:shape id=\"_x0000_s1038\" type=\"#_x0000_t156\" style='position:absolute;\r\n margin-left:129pt;margin-top:-19.5pt;width:567pt;height:135pt;rotation:-374249fd;\r\n z-index:-1;mso-position-vertical:absolute' adj=\"2760,10838\" fillcolor=\"#99f\"\r\n stroked=\"f\">\r\n <v:fill color2=\"#099\" focus=\"100%\" type=\"gradient\"/>\r\n <v:shadow on=\"t\" color=\"silver\" opacity=\"52429f\" offset=\"3pt,3pt\"/>\r\n <v:textpath style='font-family:\"Monotype Corsiva\";font-size:1in;v-text-kern:t'\r\n  trim=\"t\" fitpath=\"t\" xscale=\"f\" string=\"My Address Book\"/>\r\n</v:shape><![endif]--><![if !vml]><span style='mso-ignore:vglayout;position:\r\nabsolute;z-index:-1;left:182px;top:28px;width:758px;height:175px'><img\r\nwidth=758 height=175 src=\"SessionExpire_files/image001.gif\"\r\nalt=\"My Address Book\" v:shapes=\"_x0000_s1038\"></span><![endif]></p>\r\n<p>&nbsp;\r\n</p>\r\n<p align=\"center\">&nbsp;</p>\r\n<p align=\"center\">&nbsp;</p>\r\n<p align=\"center\">&nbsp;</p>\r\n<table cellSpacing=\"0\" cellPadding=\"0\" width=\"824\" align=\"center\" border=\"0\">\r\n  <tr>\r\n    <td vAlign=\"top\" align=\"left\" width=\"138\" rowSpan=\"2\">\r\n    <img height=\"465\" alt src=\"student_05.gif\" width=\"138\"></td>\r\n    <td vAlign=\"top\" align=\"left\" width=\"409\" background=\"stud_06.gif\" rowSpan=\"2\">\r\n    <br>\r\n    <strong><font face=\"Arial, Helvetica, sans-serif\" size=\"+1\"><br>\r\n    &nbsp;&nbsp;&nbsp; <br>\r\n    &nbsp;&nbsp; Welcome to My Address Book!</font></strong><br>\r\n    &nbsp;<br>\r\n    &nbsp;<strong><font face=\"Arial, Helvetica, sans-serif\">My Address Book is\r\n    very easy to use!</font></strong><br>\r\n    &nbsp;<font face=\"Verdana, arial, helvetica, sans-serif\" size=\"-1\"><br>\r\n    &nbsp;First visit to My Address Book?</font><br>\r\n    &nbsp;<strong>1</strong>. Sign up<br>\r\n    &nbsp;<strong>2</strong>. Just Fill Simple Form<br>\r\n    &nbsp;<strong>3</strong>. Register Your Self<br>\r\n    &nbsp;<strong>4</strong>. Enjoy User Friendly Interface<br>\r\n    &nbsp;<br>\r\n    &nbsp;<font face=\"Verdana, arial, helvetica, sans-serif\" size=\"-1\">Returning to\r\n    My Address Book?</font><br>\r\n    &nbsp;<strong>1</strong>. Login<br>\r\n    &nbsp;<strong>2</strong>. Enter Contect Name<br>\r\n    &nbsp;<strong>3</strong>. Enter Contect Phone<br>\r\n    &nbsp;<strong>4</strong>. View Contect Detail</td>\r\n    <td vAlign=\"top\" align=\"left\" width=\"174\" height=\"180\"> <a href=\"register.jsp\">\r\n      <img height=\"180\" alt src=\"student_07a.gif\" width=\"174\" useMap=\"#Map\" border=\"0\"></a></td>\r\n    <td vAlign=\"top\" align=\"left\" width=\"103\" rowSpan=\"2\">\r\n    <img height=\"465\" alt src=\"stud_08.gif\" width=\"50\"></td>\r\n  </tr>\r\n  <tr>\r\n    <td vAlign=\"top\" align=\"left\" width=\"174\" background=\"student_07b.gif\" height=\"285\">\r\n   <form onsubmit=\"form_validator()\" action=\"http://localhost:8080/WebModule1/server\"  name=\"theForm\">\r\n         <p><font face=\"Verdana, arial, helvetica, sans-serif\" size=\"-1\"><strong>\r\n         Username:</strong></font><input name=\"AdminName\" maxLength=20 size=\"20\"><br>\r\n         &nbsp;<strong><font face=\"Verdana, arial, helvetica, sans-serif\" size=\"-1\">Password:</font></strong><input type=\"password\" name=\"AdminPassword\"\r\n      maxLength=20 size=\"20\"> <input type=\"hidden\" value=\"0\" name=\"lg\">\r\n  <input type=\"submit\" name=\"AdminLogin\" value=\"Login\"></p>\r\n    </form>\r\n\r\n    <p><font color=\"#FF0000\">Your Session Has Been Expire Please Login Again</font></p>\r\n    <p>&nbsp;</td>\r\n  </tr>\r\n</table>\r\n\r\n</BODY></HTML>");

            // end

        } catch (Throwable t) {
            if (out != null && out.getBufferSize() != 0)
                out.clearBuffer();
            if (pageContext != null) pageContext.handlePageException(t);
        } finally {
            if (_jspxFactory != null) _jspxFactory.releasePageContext(pageContext);
        }
    }
}
