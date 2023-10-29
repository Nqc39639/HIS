package com.neuedu.his.servlet;

import com.neuedu.his.dao.DisposalRequestDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/expenseReturnServlet")
public class expenseReturnServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String registerId = request.getParameter("registerId");
        String medicalTechnologyId = request.getParameter("medicalTechnologyId");
        String opration = request.getParameter("opration");
        DisposalRequestDao disposalRequestDao = new DisposalRequestDao();
        if (opration.equals("delete")) {
            try {
                if (disposalRequestDao.delete(registerId, medicalTechnologyId)) {
                    response.getWriter().print("<script>alert('删除成功！');window.location.href='expenseRefund.jsp'</script>");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            double Sum = Double.parseDouble(request.getParameter("Sum"));
            try {
                if (disposalRequestDao.update(registerId, medicalTechnologyId)) {
                    response.getWriter().print("<script>alert('退给您" + Sum + "元');window.location.href='expenseRefund.jsp'</script>");
                } else {
                    response.getWriter().print("<script>alert('退费失败！');window.location.href='expenseRefund.jsp'</script>");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
