<%@ page import="java.util.List" %>
<%@ page import="com.neuedu.his.entity.Medicine" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>药房退药</title>
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
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        .table-container th {
            background-color: #f2f2f2;
        }

        .table-container td.actions {
            white-space: nowrap;
        }

        .drugMedicine {
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
</head>
<body>
<% List<Medicine> list = (List<Medicine>) request.getAttribute("list");%>
<h3>药房退药</h3>
<div class="window-backout">
    <div class="search-form">
        <h4>患者信息查询：</h4>
        <form action="/drugMedicine" method="get" id="form">
            <label for="caseNumber">病历号：</label>
            <input type="text" id="caseNumber" name="caseNumber" placeholder="请输入病历号">
            <button type="submit" id="submit">搜索</button>
            <button type="reset"><a href="drugMedicine.jsp">重置</a></button>
        </form>
    </div>
    <br>
    <div class="get-form">
        <%
            if (list != null) {
                Medicine patient = list.get(0);
        %>
        <h4>患者信息</h4>
        <label for="realName">姓名：</label>
        <label for="idNumber">身份证号：</label>
        <label for="address">家庭住址：</label>
        <label for="employee">主诊医生：</label>
        <br>
        <input type="text" id="realName" name="realName" value="<%=patient.getRealName()%>" readonly>
        <input type="text" id="idNumber" name="idNumber" value="<%=patient.getCardNumber()%>" readonly>
        <input type="text" id="address" name="address" value="<%=patient.getHomeAddress()%>" readonly>
        <input type="text" id="employee" name="employee" value="<%=patient.getEmployee()%>" readonly>
    </div>
</div>

<div class="table-container">
    <form action="/drugMedicine" method="post">
        <table>
            <th colspan="6">
                <button type="submit">
                    <a href="/drugMedicine?operation=all&caseNumber=<%=patient.getCaseNumber()%>">全部退药</a>
                </button>
            </th>
            <%
                for (Medicine medicine : list) {
            %>
            <tr>
                <td>药品编号<br><%=medicine.getDrugCode()%>
                </td>
                <td>药品名称<br><%=medicine.getDrugName()%>
                </td>
                <td>购买数量<br><%=medicine.getDrugNumber()%>
                </td>
                <td>单价<br>￥<%=medicine.getDrugPrice()%>
                </td>
                <td>退药数量<br>
                    <input type="number" name="quantity_<%=medicine.getId()%>" min="0"
                           max="<%=medicine.getDrugNumber()%>" step="1" value="0">
                </td>
                <td><a href="/drugMedicine?operation=selectedAll&id=<%=medicine.getId()%>">全退</a></td>
            </tr>
            <%}%>
        </table>
        <button type="submit" id="drugMedicine" class="drugMedicine" name="drugMedicine">退药</button>
        <%}%>
    </form>
</div>
</body>
</html>
