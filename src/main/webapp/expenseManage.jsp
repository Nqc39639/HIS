<%@ page import="java.util.List" %>
<%@ page import="com.neuedu.his.entity.DisposalRequest" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>费用记录查询</title>
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
            text-align: center;
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
<h3>患者费用信息</h3>
<div class="window-backout">
    <div class="search-form">
        <h4>精准搜索</h4>
        <form action="/expenseManageServlet" method="get" id="form">
            <label for="caseNumber">病历号：</label>
            <input type="text" id="caseNumber" name="caseNumber" placeholder="请输入病历号">
            <label for="realName">姓名：</label>
            <input type="text" id="realName" name="realName" placeholder="请输入病人姓名">
            <button type="submit" id="submit1">搜索</button>
            <button type="reset"><a href="expenseManage.jsp">重置</a></button>
        </form>
    </div>
    <div class="search-form">
        <h4>按条件筛选</h4>
        <form action="/expenseManageServlet" method="get">
            <label for="medicalTechnologyId">消费项目：</label>
            <select id="medicalTechnologyId" name="medicalTechnologyId">
                <option value="none" selected disabled hidden>请选择</option>
                <option value="1">CT</option>
                <option value="2">心电图</option>
                <option value="3">核磁共振成像</option>
                <option value="4">超声波检查</option>
                <option value="5">放射性同位素扫描</option>
                <option value="6">血检</option>
                <option value="7">尿检</option>
                <option value="8">麻醉</option>
                <option value="9">外科手术</option>
            </select>
            <label for="expenseState">费用状态：</label>
            <select id="expenseState" name="expenseState">
                <option value="none" selected disabled hidden>请选择</option>
                <option value="0">未缴费</option>
                <option value="1">已缴费</option>
                <option value="2">已退费</option>
            </select>
            <button type="submit" id="submit2">查询</button>
            <button type="reset"><a href="expenseManage.jsp">重置</a></button>
        </form>
    </div>
</div>
<% List<DisposalRequest> list = (List<DisposalRequest>) request.getAttribute("list");%>
<div class="table-container">
    <table>
        <tr>
            <th>病历号</th>
            <th>姓名</th>
            <th>消费名称</th>
            <th>单价</th>
            <th>数量</th>
            <th>费用状态</th>
            <th>结算方式</th>
            <th>操作时间</th>
        </tr>
        <%
            if (list != null) {
                for (DisposalRequest disposalRequest1 : list) {
        %>
        <tr>
            <td><%=disposalRequest1.getCaseNumber()%>
            </td>
            <td><%=disposalRequest1.getRealName()%>
            </td>
            <td><%=disposalRequest1.getTechName()%>
            </td>
            <td><%=disposalRequest1.getTechPrice()%>
            </td>
            <td>1</td>
            <%if (disposalRequest1.getExpenseState() == 0) {%>
            <td>未缴费</td>
            <%}%>
            <%if (disposalRequest1.getExpenseState() == 1) {%>
            <td>已缴费</td>
            <%}%>
            <%if (disposalRequest1.getExpenseState() == 2) {%>
            <td>已退费</td>
            <%}%>
            <td>
                <%--            <%=disposalRequest1.getRegistMethodName()%>--%>
            </td>
            <td><%=disposalRequest1.getCreationTime()%>
            </td>
        </tr>
        <%}%>
    </table>
</div>
<div class="pagination-container">
    <div class="pagination-label">跳至：
        <select id="page-select">
            <option value="1">1</option>
        </select>页
    </div>
</div>
<%}%>
</body>
</html>
