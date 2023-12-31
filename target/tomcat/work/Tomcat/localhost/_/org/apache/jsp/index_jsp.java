/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2023-06-14 08:23:33 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("\n");
      out.write("<head>\n");
      out.write("    <title>云医院HIS</title>\n");
      out.write("    <style>\n");
      out.write("        body {\n");
      out.write("            background-color: #FFFFFF;\n");
      out.write("            margin: 0;\n");
      out.write("            padding: 0;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        #header {\n");
      out.write("            background-color: #20A0FF;\n");
      out.write("            padding: 10px;\n");
      out.write("            height: 50px;\n");
      out.write("            text-align: left;\n");
      out.write("            font-weight: bold;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        h1 {\n");
      out.write("            font-family: \"微软雅黑\", sans-serif;\n");
      out.write("            font-size: 24px;\n");
      out.write("            font-weight: normal;\n");
      out.write("            margin: 0;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        #container {\n");
      out.write("            display: flex;\n");
      out.write("            height: calc(100vh - 40px);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        #sidebar {\n");
      out.write("            background-color: #E0E0E0;\n");
      out.write("            flex-basis: 200px;\n");
      out.write("            padding: 10px;\n");
      out.write("            width: 256px;\n");
      out.write("            overflow-y: auto;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        #content {\n");
      out.write("            display: flex;\n");
      out.write("            flex-grow: 1;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        #pane {\n");
      out.write("            background-color: #FFFFFF;\n");
      out.write("            flex-basis: 100%;\n");
      out.write("            padding: 10px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        iframe {\n");
      out.write("            border: none;\n");
      out.write("            width: 100%;\n");
      out.write("            height: 100%;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .nav-heading {\n");
      out.write("            cursor: pointer;\n");
      out.write("            margin-bottom: 5px;\n");
      out.write("            font-family: \"楷体\", KaiTi, serif;\n");
      out.write("            font-style: normal;\n");
      out.write("            font-size: 18px;\n");
      out.write("            line-height: 34px;\n");
      out.write("            font-weight: bold;\n");
      out.write("            text-align: left;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .nav-item {\n");
      out.write("            list-style-type: none;\n");
      out.write("            display: none;\n");
      out.write("            font-family: \"楷体\", KaiTi, serif;\n");
      out.write("            font-style: normal;\n");
      out.write("            font-size: 18px;\n");
      out.write("            line-height: 34px;\n");
      out.write("            text-align: left;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .nav-item.active {\n");
      out.write("            display: block;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .nav-item a {\n");
      out.write("            text-decoration: none;\n");
      out.write("            outline: none;\n");
      out.write("            color: #000000;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        form {\n");
      out.write("            display: grid;\n");
      out.write("            grid-template-columns: 1fr 1fr;\n");
      out.write("            gap: 10px;\n");
      out.write("            margin-top: 10px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        input[type=\"text\"],\n");
      out.write("        select {\n");
      out.write("            padding: 5px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        input[type=\"submit\"] {\n");
      out.write("            margin-top: 10px;\n");
      out.write("            padding: 5px 10px;\n");
      out.write("            background-color: #4CAF50;\n");
      out.write("            color: white;\n");
      out.write("            border: none;\n");
      out.write("            cursor: pointer;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("    <script>\n");
      out.write("        function toggleNav(navId) {\n");
      out.write("            var navItems = document.getElementsByClassName(\"nav-item\");\n");
      out.write("            var navItem = document.getElementById(navId);\n");
      out.write("            navItem.classList.toggle(\"active\");\n");
      out.write("\n");
      out.write("            for (var i = 0; i < navItems.length; i++) {\n");
      out.write("                if (navItems[i].id !== navId) {\n");
      out.write("                    navItems[i].classList.remove(\"active\");\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("    </script>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("<div id=\"header\">\n");
      out.write("    <h1>云医院HIS系统</h1>\n");
      out.write("</div>\n");
      out.write("<div id=\"container\">\n");
      out.write("    <div id=\"sidebar\">\n");
      out.write("        <div class=\"nav-heading\" onclick=\"toggleNav('registration')\">挂号收费</div>\n");
      out.write("        <ul id=\"registration\" class=\"nav-item\">\n");
      out.write("            <li><a href=\"register.jsp\" target=\"iframe_content\">窗口挂号</a></li>\n");
      out.write("            <li><a href=\"窗口退号.html\" target=\"iframe_content\">窗口退号</a></li>\n");
      out.write("            <li><a href=\"收费.html\" target=\"iframe_content\">收费</a></li>\n");
      out.write("            <li><a href=\"退费.html\" target=\"iframe_content\">退费</a></li>\n");
      out.write("            <li><a href=\"费用记录查询.html\" target=\"iframe_content\">费用记录查询</a></li>\n");
      out.write("        </ul>\n");
      out.write("        <div class=\"nav-heading\" onclick=\"toggleNav('doctor')\">医生工作</div>\n");
      out.write("        <ul id=\"doctor_work\" class=\"nav-item\">\n");
      out.write("            <li><a href=\"患者查看.html\" target=\"iframe_content\">患者查看</a></li>\n");
      out.write("            <li><a href=\"医生诊疗.html\" target=\"iframe_content\">医生诊疗</a></li>\n");
      out.write("            <li><a href=\"看诊记录.html\" target=\"iframe_content\">看诊记录</a></li>\n");
      out.write("        </ul>\n");
      out.write("        <div class=\"nav-heading\" onclick=\"toggleNav('examination')\">检查管理</div>\n");
      out.write("        <ul id=\"examination\" class=\"nav-item\">\n");
      out.write("            <li><a href=\"检查申请.html\" target=\"iframe_content\">检查申请</a></li>\n");
      out.write("            <li><a href=\"患者录入.html\" target=\"iframe_content\">患者录入</a></li>\n");
      out.write("            <li><a href=\"检查录入.html\" target=\"iframe_content\">检查录入</a></li>\n");
      out.write("            <li><a href=\"检查管理.html\" target=\"iframe_content\">检查管理</a></li>\n");
      out.write("        </ul>\n");
      out.write("        <div class=\"nav-heading\" onclick=\"toggleNav('lab')\">检验管理</div>\n");
      out.write("        <ul id=\"lab\" class=\"nav-item\">\n");
      out.write("            <li><a href=\"检验申请.html\" target=\"iframe_content\">检验申请</a></li>\n");
      out.write("            <li><a href=\"患者录入.html\" target=\"iframe_content\">患者录入</a></li>\n");
      out.write("            <li><a href=\"检验录入.html\" target=\"iframe_content\">检验录入</a></li>\n");
      out.write("            <li><a href=\"检验管理.html\" target=\"iframe_content\">检验管理</a></li>\n");
      out.write("        </ul>\n");
      out.write("        <div class=\"nav-heading\" onclick=\"toggleNav('pharmacy')\">药房管理</div>\n");
      out.write("        <ul id=\"pharmacy\" class=\"nav-item\">\n");
      out.write("            <li><a href=\"药房发药.html\" target=\"iframe_content\">药房发药</a></li>\n");
      out.write("            <li><a href=\"药房退药.html\" target=\"iframe_content\">药房退药</a></li>\n");
      out.write("            <li><a href=\"药库管理.html\" target=\"iframe_content\">药库管理</a></li>\n");
      out.write("            <li><a href=\"发退药记录管理.html\" target=\"iframe_content\">发退药记录管理</a></li>\n");
      out.write("        </ul>\n");
      out.write("        <div class=\"nav-heading\" onclick=\"toggleNav('procedure')\">处置管理</div>\n");
      out.write("        <ul id=\"procedure\" class=\"nav-item\">\n");
      out.write("            <li><a href=\"处置申请.html\" target=\"iframe_content\">处置申请</a></li>\n");
      out.write("            <li><a href=\"患者录入.html\" target=\"iframe_content\">患者录入</a></li>\n");
      out.write("            <li><a href=\"处置录入.html\" target=\"iframe_content\">处置录入</a></li>\n");
      out.write("            <li><a href=\"处置管理.html\" target=\"iframe_content\">处置管理</a></li>\n");
      out.write("        </ul>\n");
      out.write("    </div>\n");
      out.write("    <div id=\"content\">\n");
      out.write("        <div id=\"pane\">\n");
      out.write("            <iframe src=\"register.jsp\" name=\"iframe_content\"></iframe>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("</body>\n");
      out.write("\n");
      out.write("</html>");
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
