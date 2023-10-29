package com.neuedu.his.servlet;

import com.neuedu.his.dao.DrugInfoLogDao;
import com.neuedu.his.entity.DrugInfoLog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/tranHistory")
public class tranHistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String operation = request.getParameter("operation");
        String drugCode = request.getParameter("drugCode");
        String Time = request.getParameter("Time");
        String operationType = request.getParameter("operationType");
        DrugInfoLogDao drugInfoLogDao = new DrugInfoLogDao();
        List<DrugInfoLog> list = null;
        try {
            if ((operation == null || operation.trim().equals("")) && (drugCode == null || drugCode.trim().equals("")) && (Time == null || Time.trim().equals(""))) {
                response.getWriter().print("<script>alert('请输入需要查找的记录信息！');window.location.href='tranHistory.jsp'</script>");
            } else if (operation == null || operation.trim().equals("")) {
                list = drugInfoLogDao.selectByCT(drugCode, Time);
                if (list.isEmpty()) {
                    response.getWriter().print("<script>alert('未找到符合条件的药品！');window.location.href='tranHistory.jsp'</script>");
                } else {
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("tranHistory.jsp").forward(request, response);
                }
            } else if (operation.equals("delete")) {
                Integer id = Integer.parseInt(request.getParameter("id"));
                if (drugInfoLogDao.delete(id)) {
                    response.getWriter().print("<script>alert('删除成功！');window.location.href='tranHistory.jsp'</script>");
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
        String caseNumber = request.getParameter("caseNumber");
        String drugCode = request.getParameter("drugCode");
        String drugNumber = request.getParameter("drugNumber");
        DrugInfoLogDao drugInfoLogDao = new DrugInfoLogDao();
        try {
            if (operation.equals("update")) {
                if (drugInfoLogDao.update(caseNumber, drugCode, drugNumber)) {
                    response.getWriter().print("<script>alert('更改成功！');window.location.href='tranHistory.jsp'</script>");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
