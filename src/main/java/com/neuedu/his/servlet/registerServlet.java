package com.neuedu.his.servlet;

import com.neuedu.his.common.MySQLdbUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/register")
public class registerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        String npwd = request.getParameter("npwd");
        String sql = "select username,password from users where username=? and password=?";
        if (name.isEmpty() || pwd.isEmpty()) {
            response.getWriter().print("<script>alert('用户名或密码不能为空！');window.location.href='register.jsp'</script>");
        } else if (!pwd.equals(npwd)) {
            response.getWriter().print("<script>alert('两次密码不一致!');window.location.href='register.jsp'</script>");
        } else {
            try {
                PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
                ps.setString(1, name);
                ps.setString(2, pwd);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    response.getWriter().print("<script>alert('用户名已注册！');window.location.href='register.jsp'</script>");
                } else {
                    String sql1 = "insert into users(username,password) values(?,?)";
                    PreparedStatement insertPs = MySQLdbUtils.getPreparedStatement(sql1);
                    insertPs.setString(1, name);
                    insertPs.setString(2, pwd);
                    insertPs.executeUpdate();
                    insertPs.close();
                    response.getWriter().print("<script>alert('注册成功!');window.location.href='login.jsp'</script>");
                }
                MySQLdbUtils.close(MySQLdbUtils.conn, ps, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}