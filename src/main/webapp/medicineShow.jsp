<%@ page import="java.sql.*" %>
<%@ page import="com.neuedu.his.data.MYSQLDBUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>日志表展示</title>
    <meta charset="UTF-8">
    <script>
        function searchLog() {
            var keyword = document.getElementById("searchInput").value;
            window.location.href = "log?keyword=" + encodeURIComponent(keyword);
        }
    </script>
</head>
<body>
    <h2>日志表展示</h2>
    <div>
        <input type="text" id="searchInput" placeholder="输入关键字">
        <button onclick="searchLog()">搜索</button>
    </div>
    <br>
    <table>
        <tr>
            <th>Register ID</th>
            <th>Drug ID</th>
            <th>Drug Number</th>
            <th>Creation Time</th>
        </tr>
        <%
            ResultSet rs = (ResultSet) request.getAttribute("log");
            try {
                if (rs != null) {
                    while (rs.next()) {
        %>
        <tr>
            <td><%= rs.getString("registerId") %></td>
            <td><%= rs.getString("drugId") %></td>
            <td><%= rs.getInt("drugNumber") %></td>
            <td><%= rs.getString("creationTime") %></td>
        </tr>
        <%
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        %>
    </table>
</body>
</html>