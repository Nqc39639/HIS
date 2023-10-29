<%@ page import="java.util.List" %>
<%@ page import="com.neuedu.his.entity.Medicine" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>药库管理</title>
    <style>
        a {
            text-decoration: none;
        }

        h3 {
            height: 45px;
            width: 100%;
            font-size: 20px;
            padding: 15px 0 0 25px;
            margin: 35px 0 30px -10px;
            border-right: #FFFFFF;
            border: 1px solid #E0E0E0;
        }

        .search-form input, select {
            height: 33px;
            padding: 5px;
            margin: 10px 30px 10px -5px;
            box-sizing: border-box;
            border-radius: 5px;
            border: 1px solid #E0E0E0;
        }

        .search-form input {
            padding-left: 10px;
        }

        .window-backout {
            padding: 0 10px;
        }

        .search-form {
            display: inline-block;
            margin: 10px 0 0 0;
        }

        .search-form h4 {
            margin: 0;
            font-size: 16px;
            font-weight: bold;
        }

        .search-form label {
            margin-left: 25px;
            margin-right: 5px;
        }

        .search-form button[type="submit"] {
            height: 32px;
            width: 60px;
            font-size: 16px;
            margin: 10px 10px 10px 0px;
            padding: 0 10px;
            border-radius: 5px;
            background-color: #1890FF;
            color: white;
            border: 1px solid #1890FF;
            cursor: pointer;
        }

        .search-form button[type="reset"] {
            height: 32px;
            width: 60px;
            font-size: 16px;
            margin: 10px 25px 10px 0px;
            padding: 0 10px;
            border-radius: 5px;
            background-color: white;
            color: black;
            border: 1px solid;
            cursor: pointer;
        }

        .search-form button a {
            text-decoration: none;
            color: black;
        }

        .table-container table {
            width: 100%;
            border-collapse: collapse;
        }

        .table-container th,
        .table-container td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        .table-container th {
            background-color: #f2f2f2;
        }

        .table-container td.actions {
            white-space: nowrap;
        }

        .pagination-container {
            display: flex;
            align-items: center;
            margin-top: 20px;
        }

        .pagination-container .pagination-label {
            margin-right: 10px;
        }

        .pagination-container select {
            margin-right: 10px;
        }
    </style>
    <script>
        function toggleAllCheckboxes() {
            var checkboxes = document.querySelectorAll('.table-container input[type="checkbox"]');
            var selectAllCheckbox = document.getElementById('select-all');
            for (var i = 0; i < checkboxes.length; i++) {
                checkboxes[i].checked = selectAllCheckbox.checked;
            }
        }
    </script>
</head>
<body>
<h3>药库管理</h3>
<div class="window-backout">
    <div class="search-form">
        <form action="/drugStorage" method="get" id="form">
            <label for="caseNumber">药品编号：</label>
            <input type="text" id="caseNumber" name="caseNumber" placeholder="请输入药品编号">
            <label for="drugType">分类：</label>
            <select id="drugType" name="drugType">
                <option value="none" selected disabled hidden>请选择</option>
                <option value="处方药">处方药</option>
                <option value="非处方药">非处方药</option>
                <option value="西药">西药</option>
            </select>
            <button type="submit" id="submit">查询</button>
            <button type="reset"><a href="/drugStorage">重置</a></button>
        </form>
    </div>
</div>
<%--<% List<Medicine> list = (List<Medicine>) request.getAttribute("list");%>--%>
<div class="table-container">
    <table>
        <tr>
            <th><input type="checkbox" id="select-all" onclick="toggleAllCheckboxes()"></th>
            <th>药品编号</th>
            <th>药品名称</th>
            <th>药品类别</th>
            <th>生产日期</th>
            <th>保质期</th>
            <th>库存数量</th>
            <th>规格</th>
            <th>操作</th>
        </tr>
        <%--        <%--%>
        <%--            if (list != null) {--%>
        <%--                for (Patient patient : list) {--%>
        <%--        %>--%>
        <tr>
            <td><input type="checkbox"></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td class="actions">
                <a href="#">详情</a>
                <a href="#">编辑</a>
                <a href="#">删除</a>
            </td>
        </tr>
        <%--        <%--%>
        <%--            }--%>
        <%--            }--%>
        <%--        %>--%>
    </table>
</div>
<div class="pagination-container">
    <div class="pagination-label">跳至：
        <select id="page-select">
            <option value="1">1</option>
        </select>页
    </div>
</div>
</body>
</html>
