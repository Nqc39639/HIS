package com.neuedu.his.servlet;

import com.neuedu.his.dao.MedicineDao;
import com.neuedu.his.entity.Medicine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/drugMedicine")
public class drugMedicineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String operation = request.getParameter("operation");
        String caseNumber = request.getParameter("caseNumber");
        MedicineDao medicineDao = new MedicineDao();
        List<Medicine> list = null;

        try {
            if (operation == null || operation.trim().equals("")) {
                if (caseNumber == null || caseNumber.trim().equals("")) {
                    response.getWriter().print("<script>alert('请输入需要退药的病历号！');window.location.href='drugMedicine.jsp'</script>");
                } else {
                    list = medicineDao.selectByC(caseNumber, 1);
                    if (list == null) {
                        response.getWriter().print("<script>alert('请输入正确的病历号！');window.location.href='drugMedicine.jsp'</script>");
                    } else if (list.isEmpty()) {
                        response.getWriter().print("<script>alert('该病历号暂无可退药项！');window.location.href='drugMedicine.jsp'</script>");
                    } else {
                        request.setAttribute("list", list);
                        request.getRequestDispatcher("drugMedicine.jsp").forward(request, response);
                    }
                }
            } else if (operation.equals("all")) {
                medicineDao.updateAll(caseNumber);
                response.getWriter().print("<script>alert('已退掉所有药品！');window.location.href='drugMedicine.jsp'</script>");
            } else if (operation.equals("selectedAll")) {
                String id = request.getParameter("id");
                medicineDao.updateSelectedAll(Integer.parseInt(id));
                response.getWriter().print("<script>alert('成功退掉所选药品！');window.location.href='drugMedicine.jsp'</script>");
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
        Map<String, String[]> IdsNumbers = request.getParameterMap();
        MedicineDao medicineDao = new MedicineDao();
        boolean flag = false;
        for (Map.Entry<String, String[]> IdNumbers : IdsNumbers.entrySet()) {
            String Ids = IdNumbers.getKey();
            String[] Numbers = IdNumbers.getValue();
            if (Ids.startsWith("quantity_")) {
                String medicineId = Ids.substring("quantity_".length());
                String number = Numbers[0];
                if (Integer.parseInt(number) > 0) {
                    try {
                        medicineDao.updateSelectedNumber(Integer.parseInt(medicineId), Integer.parseInt(number));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    flag = true;
                    response.getWriter().print("<script>alert('成功退药！');window.location.href='drugMedicine.jsp'</script>");
                }
            }
        }
        if (!flag) {
            response.getWriter().print("<script>alert('请选择需要退药的药品数量！');window.location.href='drugMedicine.jsp'</script>");
        }
    }
}
