<%@ page import="java.util.List" %>
<%@ page import="com.neuedu.his.entity.DisposalRequest" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>收费</title>
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

        .get-form h4 {
            margin: 0;
        }

        .get-form label {
            margin-left: 25px;
            margin-right: 150px;
        }

        .get-form input, select {
            height: 33px;
            padding: 5px 5px 5px 10px;
            margin: 10px 30px 10px 18px;
            box-sizing: border-box;
            border-radius: 5px;
            border: 1px solid #E0E0E0;
        }

        .get-form select {
            margin-left: 40px;
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

        .countFee {
            height: 32px;
            width: 90px;
            font-size: 16px;
            margin: 10px 10px 10px 0px;
            padding: 0 10px;
            border-radius: 5px;
            background-color: #1890FF;
            color: white;
            border: 1px solid #1890FF;
            cursor: pointer;
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
<% List<DisposalRequest> list = (List<DisposalRequest>) request.getAttribute("list");%>
<h3>窗口收费</h3>
<div class="window-backout">
    <div class="search-form">
        <h4>患者搜索</h4>
        <form action="/expenseServlet" method="get" id="form">
            <label for="caseNumber">病历号：</label>
            <input type="text" id="caseNumber" name="caseNumber" placeholder="请输入病历号">
            <button type="submit" id="submit1">搜索</button>
            <button type="reset"><a href="expenseCharge.jsp">重置</a></button>
        </form>
    </div>
    <br>
    <div class="get-form">
        <%
            if (list != null) {
                DisposalRequest patient = list.get(0);
        %>
        <h4>患者信息</h4>
        <label for="realName">姓名：</label>
        <label for="age">年龄：</label>
        <label for="idNumber">身份证号：</label>
        <label for="gender">性别：</label>
        <br>
        <input type="text" id="realName" name="realName" value="<%=patient.getRealName()%>" readonly>
        <input type="text" id="age" name="age" value="<%=patient.getAge()%>" readonly>
        <input type="text" id="idNumber" name="idNumber" value="<%=patient.getCardNumber()%>" readonly>
        <select id="gender" name="gender" style="pointer-events: none;">
            <option value="none" disabled hidden>未获取</option>
            <option value="男" <%=(patient.getGender().equals("男")) ? "selected" : ""%>>男</option>
            <option value="女" <%=(patient.getGender().equals("女")) ? "selected" : ""%>>女</option>
        </select>
        <%}%>
    </div>
</div>

<div class="table-container">
    <form action="/expenseServlet" method="post">
        <table>
            <tr>
                <th><input type="checkbox" id="select-all" onclick="toggleAllCheckboxes()"></th>
                <th>项目名称</th>
                <th>单价</th>
                <th>数量</th>
                <th>开立时间</th>
            </tr>
            <%
                double Sum = 0.0;
                if (list != null) {
                    for (DisposalRequest disposalRequest : list) {
            %>
            <tr>
                <td><input type="checkbox"></td>
                <td><%=disposalRequest.getTechName()%>
                </td>
                <td><%=disposalRequest.getTechPrice()%>
                </td>
                <td>1</td>
                <td><%=disposalRequest.getCreationTime()%>
                </td>
            </tr>
            <%
                    Sum += disposalRequest.getTechPrice();
                }
            %>
        </table>
        <button type="submit" id="countFee" class="countFee" name="countFee">
            <a href="/SumNum?Sum=<%=Sum%>">收费结算</a></button>
        <%}%>
    </form>
</div>
</body>
</html>
