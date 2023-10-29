package com.neuedu.his.servlet;

import com.neuedu.his.dao.DisposalRequestDao;
import com.neuedu.his.entity.DisposalRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/expenseManageServlet")
public class expenseManageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String caseNumber = request.getParameter("caseNumber");
        String realName = request.getParameter("realName");
        String expenseState = request.getParameter("expenseState");
        String medicalTechnologyId = request.getParameter("medicalTechnologyId");
        DisposalRequestDao disposalRequestDao = new DisposalRequestDao();
        List<DisposalRequest> list = null;
        try {
            if ((caseNumber == null || caseNumber.trim().equals("")) && (realName == null || realName.trim().equals("")) && (expenseState == null || expenseState.trim().equals("")) && (medicalTechnologyId == null || medicalTechnologyId.trim().equals(""))) {
                response.getWriter().print("<script>alert('请输入病人信息！');window.location.href='expenseManage.jsp'</script>");
            } else if ((expenseState == null || expenseState.trim().equals("")) && (medicalTechnologyId == null || medicalTechnologyId.trim().equals(""))) {
                byte[] bytes = realName.getBytes(StandardCharsets.ISO_8859_1);
                realName = new String(bytes, StandardCharsets.UTF_8);
                list = disposalRequestDao.selectByCR(caseNumber, realName);
                request.setAttribute("list", list);
                request.getRequestDispatcher("expenseManage.jsp").forward(request, response);
            } else {
                list = disposalRequestDao.selectByME(medicalTechnologyId, expenseState);
                request.setAttribute("list", list);
                request.getRequestDispatcher("expenseManage.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
