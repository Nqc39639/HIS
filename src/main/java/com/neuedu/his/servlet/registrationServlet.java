package com.neuedu.his.servlet;

import com.neuedu.his.dao.EmployeeDao;
import com.neuedu.his.dao.PatientDao;
import com.neuedu.his.entity.Patient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/registration")
public class registrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");
        String id = request.getParameter("id");
        String caseNumber = request.getParameter("caseNumber");
        String realName = request.getParameter("realName");
        String visitState = request.getParameter("visitState");
        String department = request.getParameter("department");
        PatientDao patientDao = new PatientDao();
        List<Patient> list = null;
        try {
            if ((operation == null || operation.trim().equals("")) && (caseNumber == null || caseNumber.trim().equals("")) && (realName == null || realName.trim().equals("")) && (visitState == null || visitState.trim().equals("")) && (department == null || department.trim().equals(""))) {
                list = patientDao.selectAll();
                request.setAttribute("list", list);
                request.getRequestDispatcher("registrationRecord.jsp").forward(request, response);
            } else if ((operation == null || operation.trim().equals("")) && (visitState == null || visitState.trim().equals("")) && (department == null || department.trim().equals(""))) {
                byte[] bytes = realName.getBytes(StandardCharsets.ISO_8859_1);
                realName = new String(bytes, StandardCharsets.UTF_8);
                list = patientDao.selectByCR(caseNumber, realName);
                request.setAttribute("list", list);
                request.getRequestDispatcher("registrationRecord.jsp").forward(request, response);
            } else if ((operation == null || operation.trim().equals(""))) {
                list = patientDao.selectByVD(visitState, department);
                request.setAttribute("list", list);
                request.getRequestDispatcher("registrationRecord.jsp").forward(request, response);
            } else if (operation.equals("delete")) {
                if (patientDao.update(Integer.parseInt(id))) {
                    response.sendRedirect("/registration");
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
        String caseNumber = request.getParameter("caseNumber");
        String realName = request.getParameter("realName");
        String gender = request.getParameter("gender");
        String age = request.getParameter("age");
        String birthdate = request.getParameter("birthdate");
        String address = request.getParameter("address");
        String idNumber = request.getParameter("idNumber");
        String noon = request.getParameter("noon");
        String registLevel = request.getParameter("registLevel");
        String department = request.getParameter("department");
        String initialQuota = request.getParameter("initialQuota");
        String usedQuota = request.getParameter("usedQuota");
        String onOffSwitch = request.getParameter("onOffSwitch");
        String amountDue = request.getParameter("amountDue");
        String paymentMethod = request.getParameter("paymentMethod");
        try {
            Patient patient = new Patient();
            patient.setCaseNumber(caseNumber);
            patient.setRealName(realName);
            patient.setGender(gender);
            patient.setAge(Integer.valueOf(age));
            patient.setBirthdate(birthdate);
            patient.setAddress(address);
            patient.setIdNumber(idNumber);
            String visitDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
            patient.setVisitDate(visitDate);
            patient.setNoon(noon);
            patient.setRegistLevel(Integer.valueOf(registLevel));
            patient.setDepartmentId(Integer.valueOf(department));
            EmployeeDao employeeDao = new EmployeeDao();
            Integer doctorId = employeeDao.selectByDRN(Integer.valueOf(department), Integer.valueOf(registLevel), noon);
            patient.setDoctorId(doctorId);
            patient.setInitialQuota(Integer.valueOf(initialQuota));
            patient.setUsedQuota(Integer.valueOf(usedQuota));
            onOffSwitch = onOffSwitch.equals("on") ? "是" : "否";
            patient.setOnOffSwitch(onOffSwitch);
            patient.setAmountDue(Integer.valueOf(amountDue));
            patient.setPaymentMethodId(Integer.valueOf(paymentMethod));
            PatientDao PatientDao = new PatientDao();
            if (PatientDao.add(patient)) {
                response.getWriter().print("<script>alert('挂号成功！');window.location.href='onsiteRegistration.jsp'</script>");
            } else {
                response.getWriter().print("<script>alert('挂号失败！');window.location.href='onsiteRegistration.jsp'</script>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}