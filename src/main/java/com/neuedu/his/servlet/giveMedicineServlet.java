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

@WebServlet("/giveMedicine")
public class giveMedicineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String caseNumber = request.getParameter("caseNumber");
        MedicineDao medicineDao = new MedicineDao();
        List<Medicine> list = null;
        try {
            if (caseNumber == null || caseNumber.trim().equals("")) {
                response.getWriter().print("<script>alert('请输入需要发药的病历号！');window.location.href='giveMedicine.jsp'</script>");
            } else {
                list = medicineDao.selectByC(caseNumber, 0);
                if (list == null) {
                    response.getWriter().print("<script>alert('请输入正确的病历号！');window.location.href='giveMedicine.jsp'</script>");
                } else if (list.isEmpty()) {
                    response.getWriter().print("<script>alert('该病历号暂无可发药项！');window.location.href='giveMedicine.jsp'</script>");
                } else {
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("giveMedicine.jsp").forward(request, response);
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
        String selectedMedicineIds[] = request.getParameterValues("selectedMedicine");
        if (selectedMedicineIds != null) {
            try {
                MedicineDao medicineDao = new MedicineDao();
                for (String medicineId : selectedMedicineIds) {
                    medicineDao.updateById(Integer.parseInt(medicineId));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.getWriter().print("<script>alert('已完成发药！');window.location.href='giveMedicine.jsp'</script>");
        } else {
            response.getWriter().print("<script>alert('请选择发药项！');window.location.href='giveMedicine.jsp'</script>");
        }
    }
}
