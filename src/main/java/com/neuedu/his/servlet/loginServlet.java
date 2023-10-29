package com.neuedu.his.servlet;

import com.neuedu.his.common.MySQLdbUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/login")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            response.sendRedirect("main.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        String sql = "select username,password from users where username=? and password=?";
        try {
            PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
            ps.setString(1, name);
            ps.setString(2, pwd);
            ResultSet rs = ps.executeQuery();
            if (name.isEmpty() || pwd.isEmpty()) {
                response.getWriter().print("<script>alert('用户名或密码不能为空！');window.location.href='login.jsp'</script>");
            } else if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("username", name);
                response.getWriter().print("<script>alert('登录成功!');window.location.href='main.jsp'</script>");
            } else {
                response.getWriter().print("<script>alert('用户名或密码不正确!');window.location.href='login.jsp'</script>");
            }
            MySQLdbUtils.close(MySQLdbUtils.conn, ps, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}