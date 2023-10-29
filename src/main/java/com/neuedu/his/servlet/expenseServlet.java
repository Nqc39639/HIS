package com.neuedu.his.servlet;

import com.neuedu.his.dao.DisposalRequestDao;
import com.neuedu.his.entity.DisposalRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/expenseServlet")
public class expenseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String caseNumber = request.getParameter("caseNumber");
        DisposalRequestDao disposalRequestDao = new DisposalRequestDao();
        List<DisposalRequest> list = null;
        try {
            if (caseNumber == null || caseNumber.trim().equals("")) {
                response.getWriter().print("<script>alert('请输需要缴费的病历号！');window.location.href='expenseCharge.jsp'</script>");
            } else {
                list = disposalRequestDao.selectByC(caseNumber);
                request.setAttribute("list", list);
                request.getRequestDispatcher("expenseCharge.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
