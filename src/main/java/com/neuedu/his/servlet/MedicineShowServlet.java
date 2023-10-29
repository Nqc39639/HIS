package com.neuedu.his.servlet;

import com.neuedu.his.common.MySQLdbUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/log")
public class MedicineShowServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = MySQLdbUtils.getConnection();
            String sql = "SELECT registerId, drugId, drugNumber, creationTime FROM log";
            if (keyword != null && !keyword.isEmpty()) {
                sql += " WHERE registerId LIKE ? OR drugId LIKE ? OR creationTime LIKE ?";
            }
            ps = MySQLdbUtils.getPreparedStatement(sql);
            if (keyword != null && !keyword.isEmpty()) {
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
                ps.setString(3, "%" + keyword + "%");
            }
            rs = ps.executeQuery();
            request.setAttribute("logList", rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLdbUtils.close(conn, ps, rs);
        }
        request.getRequestDispatcher("/medicineShow.jsp").forward(request, response);
    }
}