/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2023-06-20 11:29:57 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class test_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title>云医院HIS</title>\r\n");
      out.write("    <style>\r\n");
      out.write("        /* 省略其他样式，保持与你提供的模板一致 */\r\n");
      out.write("\r\n");
      out.write("        /* 用户头像样式 */\r\n");
      out.write("        .user-avatar {\r\n");
      out.write("            width: 30px;\r\n");
      out.write("            height: 30px;\r\n");
      out.write("            border-radius: 50%;\r\n");
      out.write("            background-color: #ccc;\r\n");
      out.write("            cursor: pointer;\r\n");
      out.write("            float: right;\r\n");
      out.write("            margin-top: 10px;\r\n");
      out.write("            margin-right: 20px;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        /* 下拉菜单样式 */\r\n");
      out.write("        .dropdown {\r\n");
      out.write("            position: relative;\r\n");
      out.write("            display: inline-block;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .dropdown-content {\r\n");
      out.write("            display: none;\r\n");
      out.write("            position: absolute;\r\n");
      out.write("            background-color: #f9f9f9;\r\n");
      out.write("            min-width: 120px;\r\n");
      out.write("            box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);\r\n");
      out.write("            z-index: 1;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .dropdown-content a {\r\n");
      out.write("            color: black;\r\n");
      out.write("            padding: 12px 16px;\r\n");
      out.write("            text-decoration: none;\r\n");
      out.write("            display: block;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .dropdown-content a:hover {\r\n");
      out.write("            background-color: #f1f1f1;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .dropdown:hover .dropdown-content {\r\n");
      out.write("            display: block;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .dropdown:hover .user-avatar {\r\n");
      out.write("            background-color: #999;\r\n");
      out.write("        }\r\n");
      out.write("    </style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div id=\"header\">\r\n");
      out.write("    <img class=\"logoImg\" src=\"static/images/logo.png\">\r\n");
      out.write("\r\n");
      out.write("    <!-- 用户头像和下拉菜单 -->\r\n");
      out.write("    <div class=\"dropdown\">\r\n");
      out.write("        <div class=\"user-avatar\"></div>\r\n");
      out.write("        <div class=\"dropdown-content\">\r\n");
      out.write("            <a href=\"#\">查看个人信息</a>\r\n");
      out.write("            <a href=\"logout.jsp\">退出登录</a>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("<!-- 省略其他内容，保持与你提供的模板一致 -->\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
