/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2023-07-02 15:04:09 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import com.neuedu.his.entity.Medicine;

public final class giveMedicine_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title>药房发药</title>\r\n");
      out.write("    <style>\r\n");
      out.write("        a {\r\n");
      out.write("            text-decoration: none;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        h3 {\r\n");
      out.write("            height: 45px;\r\n");
      out.write("            width: 100%;\r\n");
      out.write("            font-size: 20px;\r\n");
      out.write("            padding: 15px 0 0 25px;\r\n");
      out.write("            margin: 35px 0 30px -10px;\r\n");
      out.write("            border-right: #FFFFFF;\r\n");
      out.write("            border: 1px solid #E0E0E0;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .search-form input, select {\r\n");
      out.write("            height: 33px;\r\n");
      out.write("            padding: 5px;\r\n");
      out.write("            margin: 10px 30px 10px -5px;\r\n");
      out.write("            box-sizing: border-box;\r\n");
      out.write("            border-radius: 5px;\r\n");
      out.write("            border: 1px solid #E0E0E0;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .search-form input {\r\n");
      out.write("            padding-left: 10px;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .window-backout {\r\n");
      out.write("            padding: 0 10px;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .search-form {\r\n");
      out.write("            display: inline-block;\r\n");
      out.write("            margin: 10px 0 0 0;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .search-form h4 {\r\n");
      out.write("            margin: 0;\r\n");
      out.write("            font-size: 16px;\r\n");
      out.write("            font-weight: bold;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .search-form label {\r\n");
      out.write("            margin-left: 25px;\r\n");
      out.write("            margin-right: 5px;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .search-form button[type=\"submit\"] {\r\n");
      out.write("            height: 32px;\r\n");
      out.write("            width: 60px;\r\n");
      out.write("            font-size: 16px;\r\n");
      out.write("            margin: 10px 10px 10px 0px;\r\n");
      out.write("            padding: 0 10px;\r\n");
      out.write("            border-radius: 5px;\r\n");
      out.write("            background-color: #1890FF;\r\n");
      out.write("            color: white;\r\n");
      out.write("            border: 1px solid #1890FF;\r\n");
      out.write("            cursor: pointer;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .search-form button[type=\"reset\"] {\r\n");
      out.write("            height: 32px;\r\n");
      out.write("            width: 60px;\r\n");
      out.write("            font-size: 16px;\r\n");
      out.write("            margin: 10px 25px 10px 0px;\r\n");
      out.write("            padding: 0 10px;\r\n");
      out.write("            border-radius: 5px;\r\n");
      out.write("            background-color: white;\r\n");
      out.write("            color: black;\r\n");
      out.write("            border: 1px solid;\r\n");
      out.write("            cursor: pointer;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .search-form button a {\r\n");
      out.write("            text-decoration: none;\r\n");
      out.write("            color: black;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .get-form h4 {\r\n");
      out.write("            margin: 0 0 10px;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .get-form label {\r\n");
      out.write("            margin: -5px 150px 0 25px;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .get-form input {\r\n");
      out.write("            height: 33px;\r\n");
      out.write("            padding: 5px 5px 5px 10px;\r\n");
      out.write("            margin: 10px 30px 10px 18px;\r\n");
      out.write("            box-sizing: border-box;\r\n");
      out.write("            border-radius: 5px;\r\n");
      out.write("            border: 1px solid #E0E0E0;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .table-container h4 {\r\n");
      out.write("            margin: 10px;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .table-container table {\r\n");
      out.write("            width: 100%;\r\n");
      out.write("            border-collapse: collapse;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .table-container th,\r\n");
      out.write("        .table-container td {\r\n");
      out.write("            padding: 15px;\r\n");
      out.write("            text-align: left;\r\n");
      out.write("            border-bottom: 1px solid #ddd;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .table-container th {\r\n");
      out.write("            background-color: #f2f2f2;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .table-container td.actions {\r\n");
      out.write("            white-space: nowrap;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .giveMedicine {\r\n");
      out.write("            height: 32px;\r\n");
      out.write("            width: 90px;\r\n");
      out.write("            font-size: 16px;\r\n");
      out.write("            margin: 10px 10px 10px 0px;\r\n");
      out.write("            padding: 0 10px;\r\n");
      out.write("            border-radius: 5px;\r\n");
      out.write("            background-color: #1890FF;\r\n");
      out.write("            color: white;\r\n");
      out.write("            border: 1px solid #1890FF;\r\n");
      out.write("            cursor: pointer;\r\n");
      out.write("        }\r\n");
      out.write("    </style>\r\n");
      out.write("    <script>\r\n");
      out.write("        function toggleAllCheckboxes() {\r\n");
      out.write("            var checkboxes = document.querySelectorAll('.table-container input[type=\"checkbox\"]');\r\n");
      out.write("            var selectAllCheckbox = document.getElementById('select-all');\r\n");
      out.write("\r\n");
      out.write("            for (var i = 0; i < checkboxes.length; i++) {\r\n");
      out.write("                checkboxes[i].checked = selectAllCheckbox.checked;\r\n");
      out.write("            }\r\n");
      out.write("        }\r\n");
      out.write("    </script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
 List<Medicine> list = (List<Medicine>) request.getAttribute("list");
      out.write("\r\n");
      out.write("<h3>药房发药</h3>\r\n");
      out.write("<div class=\"window-backout\">\r\n");
      out.write("    <div class=\"search-form\">\r\n");
      out.write("        <h4>患者信息查询：</h4>\r\n");
      out.write("        <form action=\"/giveMedicine\" method=\"get\" id=\"form\">\r\n");
      out.write("            <label for=\"caseNumber\">病历号：</label>\r\n");
      out.write("            <input type=\"text\" id=\"caseNumber\" name=\"caseNumber\" placeholder=\"请输入病历号\">\r\n");
      out.write("            <button type=\"submit\" id=\"submit\">搜索</button>\r\n");
      out.write("            <button type=\"reset\"><a href=\"giveMedicine.jsp\">重置</a></button>\r\n");
      out.write("        </form>\r\n");
      out.write("    </div>\r\n");
      out.write("    <br>\r\n");
      out.write("    <div class=\"get-form\">\r\n");
      out.write("        ");

            if (list != null) {
                Medicine patient = list.get(0);
        
      out.write("\r\n");
      out.write("        <h4>患者信息</h4>\r\n");
      out.write("        <label for=\"realName\">姓名：</label>\r\n");
      out.write("        <label for=\"idNumber\">身份证号：</label>\r\n");
      out.write("        <label for=\"address\">家庭住址：</label>\r\n");
      out.write("        <br>\r\n");
      out.write("        <input type=\"text\" id=\"realName\" name=\"realName\" value=\"");
      out.print(patient.getRealName());
      out.write("\" readonly>\r\n");
      out.write("        <input type=\"text\" id=\"idNumber\" name=\"idNumber\" value=\"");
      out.print(patient.getCardNumber());
      out.write("\" readonly>\r\n");
      out.write("        <input type=\"text\" id=\"address\" name=\"address\" value=\"");
      out.print(patient.getHomeAddress());
      out.write("\" readonly>\r\n");
      out.write("        ");
}
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div class=\"table-container\">\r\n");
      out.write("    <h4>患者消费信息</h4>\r\n");
      out.write("    <form action=\"/giveMedicine\" method=\"post\">\r\n");
      out.write("        <table>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <th><input type=\"checkbox\" id=\"select-all\" onclick=\"toggleAllCheckboxes()\"></th>\r\n");
      out.write("                <th>病历号</th>\r\n");
      out.write("                <th>项目名称</th>\r\n");
      out.write("                <th>单价</th>\r\n");
      out.write("                <th>数量</th>\r\n");
      out.write("                <th>开立时间</th>\r\n");
      out.write("                <th>支付状态</th>\r\n");
      out.write("                <th>发药状态</th>\r\n");
      out.write("            </tr>\r\n");
      out.write("            ");

                if (list != null) {
                    for (Medicine medicine : list) {
            
      out.write("\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td><input type=\"checkbox\" name=\"selectedMedicine\" value=\"");
      out.print(medicine.getId());
      out.write("\"></td>\r\n");
      out.write("                <td>");
      out.print(medicine.getCaseNumber());
      out.write("\r\n");
      out.write("                </td>\r\n");
      out.write("                <td>");
      out.print(medicine.getDrugName());
      out.write("\r\n");
      out.write("                </td>\r\n");
      out.write("                <td>");
      out.print(medicine.getDrugPrice());
      out.write("\r\n");
      out.write("                </td>\r\n");
      out.write("                <td>");
      out.print(medicine.getDrugNumber());
      out.write("\r\n");
      out.write("                </td>\r\n");
      out.write("                <td>");
      out.print(medicine.getCreationTime());
      out.write("\r\n");
      out.write("                </td>\r\n");
      out.write("                ");
if (medicine.getExpenseState() == 0) {
      out.write("\r\n");
      out.write("                <td>未支付</td>\r\n");
      out.write("                ");
}
      out.write("\r\n");
      out.write("                ");
if (medicine.getExpenseState() == 1) {
      out.write("\r\n");
      out.write("                <td>已支付</td>\r\n");
      out.write("                ");
}
      out.write("\r\n");
      out.write("                ");
if (medicine.getExpenseState() == 2) {
      out.write("\r\n");
      out.write("                <td>已退款</td>\r\n");
      out.write("                ");
}
      out.write("\r\n");
      out.write("                <td>未发药</td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            ");
}
      out.write("\r\n");
      out.write("        </table>\r\n");
      out.write("        <button type=\"submit\" id=\"giveMedicine\" class=\"giveMedicine\" name=\"giveMedicine\">发药</button>\r\n");
      out.write("        ");
}
      out.write("\r\n");
      out.write("    </form>\r\n");
      out.write("</div>\r\n");
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
