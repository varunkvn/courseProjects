package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;


public class jsp1$jsp extends HttpJspBase {


    static {
    }
    public jsp1$jsp( ) {
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

            // HTML // begin [file="/jsp1.jsp";from=(0,0);to=(11,17)]
                out.write("<html>\r\n<head>\r\n<title>\r\njsp1\r\n</title>\r\n</head>\r\n<body bgcolor=\"#ffffff\">\r\n  <form action=\"http://localhost:8080/WebModule1/servlet1\">\r\n    <input type=\"text\" name=\"user\">\r\n      <input type=\"submit\" value=\"CLick\">\r\n        </form>\r\n                 ");

            // end
            // begin [file="/jsp1.jsp";from=(11,19);to=(15,2)]
                
                    	String name=(String)session.getAttribute("u");
                	String add=(String)session.getAttribute("uu");
                    	out.println("this is JSP1 page"+name +add );
                		
            // end
            // HTML // begin [file="/jsp1.jsp";from=(15,4);to=(21,0)]
                out.write("\r\n<h1>\r\nJBuilder Generated JSP\r\n</h1>\r\n</body>\r\n</html>\r\n");

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
