package com.neuedu.his.servlet;

import com.neuedu.his.dao.DrugInfoDao;
import com.neuedu.his.entity.DrugInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/drugStorage")
public class drugStorageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String operation = request.getParameter("operation");
        String drugCode = request.getParameter("drugCode");
        String drugType = request.getParameter("drugType");
        DrugInfoDao drugInfoDao = new DrugInfoDao();
        List<DrugInfo> list = null;
        try {
            if ((operation == null || operation.trim().equals("")) && (drugCode == null || drugCode.trim().equals("")) && (drugType == null || drugType.trim().equals(""))) {
                list = drugInfoDao.selectAll();
                request.setAttribute("list", list);
                request.getRequestDispatcher("drugStorage.jsp").forward(request, response);
            } else if (operation == null || operation.trim().equals("")) {
                if (drugType != null && !drugType.trim().equals("")) {
                    byte[] bytes = drugType.getBytes(StandardCharsets.ISO_8859_1);
                    drugType = new String(bytes, StandardCharsets.UTF_8);
                }
                list = drugInfoDao.selectByCT(drugCode, drugType);
                if (list.isEmpty()) {
                    response.getWriter().print("<script>alert('未找到符合条件的药品！');window.location.href='/drugStorage'</script>");
                } else {
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("drugStorage.jsp").forward(request, response);
                }
            } else if (operation.equals("delete")) {
                Integer id = Integer.parseInt(request.getParameter("id"));
                if (drugInfoDao.delete(id)) {
                    response.getWriter().print("<script>alert('删除成功！');window.location.href='/drugStorage'</script>");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String operation = request.getParameter("operation");
        String drugCode = request.getParameter("drugCode");
        String drugName = request.getParameter("drugName");
        String drugFormat = request.getParameter("drugFormat");
        String manufacturer = request.getParameter("manufacturer");
        String drugType = request.getParameter("drugType");
        String drugPrice = request.getParameter("drugPrice");
        String creationDate = request.getParameter("creationDate");
        String quantity = request.getParameter("quantity");
        DrugInfoDao drugInfoDao = new DrugInfoDao();
        try {
            if (operation.equals("update")) {
                if (drugInfoDao.update(drugCode, drugName, drugFormat, manufacturer, drugType, drugPrice, creationDate, quantity)) {
                    response.getWriter().print("<script>alert('更改成功！');window.location.href='/drugStorage'</script>");
                }
            } else if (operation.equals("add")) {
                if (drugInfoDao.add(drugCode, drugName, drugFormat, manufacturer, drugType, drugPrice, creationDate, quantity)) {
                    response.getWriter().print("<script>alert('添加成功！');window.location.href='/drugStorage'</script>");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
