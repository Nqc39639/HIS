<%@ page import="java.util.List" %>
<%@ page import="com.neuedu.his.entity.DrugInfo" %>
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

        .window-backout button {
            height: 36px;
            width: 200px;
            font-size: 16px;
            margin: 0 10px 10px 0;
            padding: 0 0 10px -5px;
            border-radius: 5px;
            background-color: #1890FF;
            border: 1px solid #1890FF;
            cursor: pointer;
        }

        .window-backout button a {
            color: white;
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

        #editModal, #addModal {
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

        #editModal > div, #addModal > div {
            position: relative;
            top: 15%;
            left: 23%;
            width: 50%;
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
            margin-right: 20px;
            box-sizing: border-box;
            border-radius: 5px;
            border: 1px solid #E0E0E0;
        }

        .row button {
            height: 32px;
            width: 60px;
            font-size: 16px;
            margin: 15px 0 10px 200px;
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

        function openEditModal(drugCode, drugName, drugFormat, manufacturer, drugType, drugPrice, creationDate, quantity) {
            event.preventDefault();
            document.getElementById('editDrugCode').value = drugCode;
            document.getElementById('editDrugName').value = drugName;
            document.getElementById('editDrugFormat').value = drugFormat;
            document.getElementById('editManufacturer').value = manufacturer;
            document.getElementById('editDrugType').value = drugType;
            document.getElementById('editDrugPrice').value = drugPrice;
            document.getElementById('editCreationDate').value = creationDate;
            document.getElementById('editQuantity').value = quantity;
            document.getElementById('editModal').style.display = 'block';
        }

        function closeEditModal() {
            document.getElementById('editModal').style.display = 'none';
        }

        function openAddModal() {
            event.preventDefault();
            document.getElementById('addModal').style.display = 'block';
        }

        function closeAddModal() {
            document.getElementById('addModal').style.display = 'none';
        }
    </script>
</head>
<body>
<h3>药库管理</h3>
<div class="window-backout">
    <div class="search-form">
        <form action="/drugStorage" method="get" id="form">
            <label for="drugCode">药品编号：</label>
            <input type="text" id="drugCode" name="drugCode" placeholder="请输入药品编号">
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
    <div>
        <button><a href="#" onclick="openAddModal()">+ 添加新药物</a></button>
    </div>
</div>
<% List<DrugInfo> list = (List<DrugInfo>) request.getAttribute("list");%>
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
        <%
            if (list != null) {
                for (DrugInfo drugInfo : list) {
        %>
        <tr>
            <td><input type="checkbox"></td>
            <td><%=drugInfo.getDrugCode()%>
            </td>
            <td><%=drugInfo.getDrugName()%>
            </td>
            <td><%=drugInfo.getDrugType()%>
            </td>
            <td><%=drugInfo.getCreationDate()%>
            </td>
            <td>2年</td>
            <td><%=drugInfo.getQuantity()%>
            </td>
            <td><%=drugInfo.getDrugFormat()%>
            </td>
            <td class="actions">
                <a href="#">详情</a>
                <a href="#"
                   onclick="openEditModal('<%=drugInfo.getDrugCode()%>','<%=drugInfo.getDrugName()%>','<%=drugInfo.getDrugFormat()%>','<%=drugInfo.getManufacturer()%>','<%=drugInfo.getDrugType()%>','<%=drugInfo.getDrugPrice()%>','<%=drugInfo.getCreationDate()%>','<%=drugInfo.getQuantity()%>')">编辑</a>
                <a href="/drugStorage?operation=delete&id=<%=drugInfo.getId()%>">删除</a>
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
        <h4 style="text-align: center;font-size: 20px;">修改药品信息</h4>
        <form id="editForm" action="/drugStorage?operation=update" method="post">
            <div class="row">
                <label for="editDrugCode">药品编号：</label>
                <input type="text" id="editDrugCode" name="drugCode" readonly>
                <label for="editDrugName">药品名称：</label>
                <input type="text" id="editDrugName" name="drugName">
            </div>
            <div class="row">
                <label for="editDrugFormat">规格：</label>
                <input type="text" id="editDrugFormat" name="drugFormat">
                <label for="editManufacturer">生产厂家：</label>
                <input type="text" id="editManufacturer" name="manufacturer">
            </div>
            <div class="row">
                <label for="editDrugType">药品类别：</label>
                <select id="editDrugType" name="drugType">
                    <option value="none" selected disabled hidden>请选择</option>
                    <option value="处方药">处方药</option>
                    <option value="非处方药">非处方药</option>
                    <option value="西药">西药</option>
                </select>
                <label for="editDrugPrice">药品单价：</label>
                <input type="text" id="editDrugPrice" name="drugPrice">
            </div>
            <div class="row">
                <label for="editCreationDate">生产日期：</label>
                <input type="date" id="editCreationDate" name="creationDate">
                <label for="editQuantity">库存数量：</label>
                <input type="text" id="editQuantity" name="quantity">
            </div>
            <div class="row">
                <button type="submit">保存</button>
                <button type="button" onclick="closeEditModal()">取消</button>
            </div>
        </form>
    </div>
</div>
<div id="addModal" style="display: none;">
    <div>
        <h4 style="text-align: center;font-size: 20px;">添加新药品</h4>
        <form id="addForm" action="/drugStorage?operation=add" method="post">
            <div class="row">
                <label for="addDrugCode"><span style="color: red">* </span>药品编号：</label>
                <input type="text" id="addDrugCode" name="drugCode" placeholder="请输入药品编号" required>
                <label for="addDrugName"><span style="color: red">* </span>药品名称：</label>
                <input type="text" id="addDrugName" name="drugName" placeholder="请输入药品名称" required>
            </div>
            <div class="row">
                <label for="addDrugFormat"><span style="color: red">* </span>规格：</label>
                <input type="text" id="addDrugFormat" name="drugFormat" placeholder="请输入药品规格" required>
                <label for="addManufacturer">生产厂家：</label>
                <input type="text" id="addManufacturer" name="manufacturer" placeholder="请输入生产厂家">
            </div>
            <div class="row">
                <label for="addDrugType"><span style="color: red">* </span>药品类别：</label>
                <select id="addDrugType" name="drugType" required>
                    <option value="none" selected disabled hidden>请选择</option>
                    <option value="处方药">处方药</option>
                    <option value="非处方药">非处方药</option>
                    <option value="西药">西药</option>
                </select>
                <label for="addDrugPrice"><span style="color: red">* </span>药品单价：</label>
                <input type="text" id="addDrugPrice" name="drugPrice" placeholder="请输入药品单价" required>
            </div>
            <div class="row">
                <label for="addCreationDate"><span style="color: red">* </span>生产日期：</label>
                <input type="date" id="addCreationDate" name="creationDate" required>
                <label for="addQuantity"><span style="color: red">* </span>添加数量：</label>
                <input type="text" id="addQuantity" name="quantity" placeholder="请输入添加数量" required>
            </div>
            <div class="row">
                <button type="submit">保存</button>
                <button type="button" onclick="closeAddModal()">取消</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>