<%@ page import="java.util.List" %>
<%@ page import="com.neuedu.his.entity.DrugInfoLog" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发退药记录管理</title>
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

        .search-form input {
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

        .operationType .give, .back {
            height: 36px;
            width: 200px;
            font-size: 16px;
            margin: 0 10px 10px 0;
            padding: 0 0 10px -5px;
            border-radius: 5px;
            border: 1px solid #E0E0E0;
            cursor: pointer;
        }

        .operationType button a:hover {
            color: #1890FF;
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
            margin: 10px 5px 0 25px;
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

        #editModal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.4);
        }

        #editModal > div {
            position: relative;
            top: 5%;
            left: 18%;
            width: 60%;
            background-color: white;
            padding: 15px 20px 20px 15px;
            border-radius: 5px;
        }

        .row {
            display: flex;
            align-items: center;
            margin: 15px 0 0 -16px;
        }

        .row label {
            flex-basis: 120px;
            display: flex;
            justify-content: flex-end;
        }

        .row input, select {
            flex: 1;
            height: 33px;
            padding: 5px 5px 5px 10px;
            margin-left: 5px;
            margin-right: 20px;
            box-sizing: border-box;
            border-radius: 5px;
            border: 1px solid #E0E0E0;
        }

        .row button {
            height: 32px;
            width: 60px;
            font-size: 16px;
            margin: 15px 0 5px 250px;
            padding: 0 10px;
            border-radius: 5px;
            background-color: white;
            color: black;
            border: 1px solid;
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

        function openEditModal(caseNumber, realName, gender, age, birthdate, idNumber, address, visitDate, department, employee, drugCode, drugName, drugFormat, manufacturer, drugType, drugPrice, Time, drugNumber) {
            event.preventDefault();
            document.getElementById('editCaseNumber').value = caseNumber;
            document.getElementById('editRealName').value = realName;
            document.getElementById('editGender').value = gender;
            document.getElementById('editAge').value = age;
            document.getElementById('editBirthdate').value = birthdate;
            document.getElementById('editIdNumber').value = idNumber;
            document.getElementById('editAddress').value = address;
            document.getElementById('editVisitDate').value = visitDate;
            document.getElementById('editDepartment').value = department;
            document.getElementById('editEmployee').value = employee;
            document.getElementById('editDrugCode').value = drugCode;
            document.getElementById('editDrugName').value = drugName;
            document.getElementById('editDrugFormat').value = drugFormat;
            document.getElementById('editManufacturer').value = manufacturer;
            document.getElementById('editDrugType').value = drugType;
            document.getElementById('editDrugPrice').value = drugPrice;
            document.getElementById('editTime').value = Time;
            document.getElementById('editDrugNumber').value = drugNumber;
            document.getElementById('editModal').style.display = 'block';
        }

        function closeEditModal() {
            document.getElementById('editModal').style.display = 'none';
        }


    </script>
</head>
<body>
<h3>发退药记录管理</h3>
<div class="window-backout">
    <div class="search-form">
        <form action="/tranHistory" method="get" id="form">
            <label for="drugCode">药品编号：</label>
            <input type="text" id="drugCode" name="drugCode" placeholder="请输入药品编号">
            <label for="Time">选择日期：</label>
            <input type="date" id="Time" name="Time">
            <button type="submit" id="submit">查询</button>
            <button type="reset"><a href="tranHistory.jsp">重置</a></button>
            <%--            <div class="operationType">--%>
            <h4 style="margin-left: 25px;font-weight: normal">操作类型：</h4>
            <input type="button" id="give" name="operationType" value="发药">
            <input type="button" id="back" name="operationType" value="退药">
            <%--            </div>--%>
        </form>
    </div>
</div>
<% List<DrugInfoLog> list = (List<DrugInfoLog>) request.getAttribute("list");%>
<div class="table-container">
    <table>
        <tr>
            <th><input type="checkbox" id="select-all" onclick="toggleAllCheckboxes()"></th>
            <th>药品编号</th>
            <th>姓名</th>
            <th>药品名称</th>
            <th>日期</th>
            <th>主诊医生</th>
            <th>数量</th>
            <th>费用</th>
            <th>操作</th>
        </tr>
        <%
            if (list != null) {
                for (DrugInfoLog drugInfoLog : list) {
        %>
        <tr>
            <td><input type="checkbox"></td>
            <td><%=drugInfoLog.getDrugCode()%>
            </td>
            <td><%=drugInfoLog.getRealName()%>
            </td>
            <td><%=drugInfoLog.getDrugName()%>
            </td>
            <td><%=drugInfoLog.getTime()%>
            </td>
            <td><%=drugInfoLog.getEmployeeName()%>
            </td>
            <td><%=drugInfoLog.getDrugNumber()%>
            </td>
            <td>￥<%=drugInfoLog.getDrugNumber() * drugInfoLog.getDrugPrice()%>
            </td>
            <td class="actions">
                <a href="#">详情</a>
                <a href="#"
                   onclick="openEditModal('<%=drugInfoLog.getCaseNumber()%>','<%=drugInfoLog.getRealName()%>','<%=drugInfoLog.getGender()%>','<%=drugInfoLog.getAge()%>','<%=drugInfoLog.getBirthdate()%>','<%=drugInfoLog.getCardNumber()%>','<%=drugInfoLog.getHomeAddress()%>','<%=drugInfoLog.getVisitDate()%>','<%=drugInfoLog.getDepartmentName()%>','<%=drugInfoLog.getEmployeeName()%>','<%=drugInfoLog.getDrugCode()%>','<%=drugInfoLog.getDrugName()%>','<%=drugInfoLog.getDrugFormat()%>','<%=drugInfoLog.getManufacturer()%>','<%=drugInfoLog.getDrugType()%>','<%=drugInfoLog.getDrugPrice()%>','<%=drugInfoLog.getTime()%>','<%=drugInfoLog.getDrugNumber()%>')">编辑</a>
                <a href="/tranHistory?operation=delete&id=<%=drugInfoLog.getId()%>">删除</a>
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
<div id="editModal" style="display: none;">
    <div>
        <h4 style="text-align: center;font-size: 25px;margin: 10px 0 0 0">修改发退药记录</h4>
        <form id="editForm" action="/tranHistory?operation=update" method="post">
            <h4 style="margin: 7px">病人信息</h4>
            <div class="row">
                <label for="editCaseNumber">病历号:</label>
                <input type="text" id="editCaseNumber" name="caseNumber" readonly>
                <label for="editRealName">姓名:</label>
                <input type="text" id="editRealName" name="realName" readonly>
                <label for="editGender">性别:</label>
                <select id="editGender" name="gender" disabled>
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </div>
            <div class="row">
                <label for="editAge">年龄:</label>
                <input type="text" id="editAge" name="age" readonly>
                <label for="editBirthdate">出生日期:</label>
                <input type="date" id="editBirthdate" name="birthdate" readonly>
            </div>
            <div class="row">
                <label for="editIdNumber">身份证号:</label>
                <input type="text" id="editIdNumber" name="idNumber" readonly>
                <label for="editAddress">家庭住址:</label>
                <input type="text" id="editAddress" name="address" readonly>
            </div>
            <div class="row">
                <label for="editVisitDate">看诊日期:</label>
                <input type="date" id="editVisitDate" name="visitDate" readonly>
                <label for="editDepartment">挂号科室:</label>
                <select id="editDepartment" name="department" disabled>
                    <option value="内科">内科</option>
                    <option value="外科">外科</option>
                    <option value="儿科">儿科</option>
                    <option value="肿瘤科">肿瘤科</option>
                    <option value="中医科">中医科</option>
                    <option value="妇产科">妇产科</option>
                    <option value="麻醉科">麻醉科</option>
                    <option value="精神科">精神科</option>
                    <option value="影像科">影像科</option>
                    <option value="其他科">其他科</option>
                </select>
                <label for="editEmployee">主诊医生:</label>
                <input type="text" id="editEmployee" name="employee" readonly>
            </div>
            <h4 style="margin: 7px">药品信息</h4>
            <div class="row">
                <label for="editDrugCode">药品编号：</label>
                <input type="text" id="editDrugCode" name="drugCode" readonly>
                <label for="editDrugName">药品名称：</label>
                <input type="text" id="editDrugName" name="drugName" readonly>
            </div>
            <div class="row">
                <label for="editDrugFormat">规格：</label>
                <input type="text" id="editDrugFormat" name="drugFormat" readonly>
                <label for="editManufacturer">生产厂家：</label>
                <input type="text" id="editManufacturer" name="manufacturer" readonly>
            </div>
            <div class="row">
                <label for="editDrugType">药品类别：</label>
                <select id="editDrugType" name="drugType" disabled>
                    <option value="处方药">处方药</option>
                    <option value="非处方药">非处方药</option>
                    <option value="西药">西药</option>
                </select>
                <label for="editDrugPrice">药品单价：</label>
                <input type="text" id="editDrugPrice" name="drugPrice" readonly>
            </div>
            <div class="row">
                <label for="editTime">发退药日：</label>
                <input type="date" id="editTime" name="Time" readonly>
                <label for="editDrugNumber">发退药量：</label>
                <input type="text" id="editDrugNumber" name="drugNumber">
            </div>
            <div class="row">
                <button type="submit">保存</button>
                <button type="button" onclick="closeEditModal()">取消</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>