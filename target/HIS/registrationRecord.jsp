<%@ page import="java.util.List" %>
<%@ page import="com.neuedu.his.entity.Patient" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>窗口退号</title>
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
<h3>窗口退号</h3>
<div>
    <div class="window-backout">
        <div class="search-form">
            <h4>精准搜索</h4>
            <form action="/registration" method="get" id="form">
                <label for="caseNumber">病历号：</label>
                <input type="text" id="caseNumber" name="caseNumber" placeholder="请输入病历号">
                <label for="realName">姓名：</label>
                <input type="text" id="realName" name="realName" placeholder="请输入病人姓名">
                <button type="submit" id="submit1">搜索</button>
                <button type="reset"><a href="/registration">重置</a></button>
            </form>
        </div>
        <br>
        <div class="search-form">
            <h4>按条件筛选</h4>
            <form action="/registration" method="get">
                <label for="visitState">看诊状态：</label>
                <select id="visitState" name="visitState">
                    <option value="none" selected disabled hidden>请选择</option>
                    <option value="1">已挂号</option>
                    <option value="2">医生接诊</option>
                    <option value="3">看诊结束</option>
                    <option value="4">已退号</option>
                </select>
                <%--            <label for="startVisitDate">挂号日期：</label>--%>
                <%--            <input type="date" id="startVisitDate" name="startVisitDate">--%>
                <%--            <label for="endVisitDate">至</label>--%>
                <%--            <input type="date" id="endVisitDate" name="endVisitDate">--%>
                <label for="department">看诊科室：</label>
                <select id="department" name="department">
                    <option value="none" selected disabled hidden>请选择</option>
                    <option value="1">内科</option>
                    <option value="2">外科</option>
                    <option value="3">儿科</option>
                    <option value="4">肿瘤科</option>
                    <option value="5">中医科</option>
                    <option value="6">妇产科</option>
                    <option value="7">麻醉科</option>
                    <option value="8">精神科</option>
                    <option value="9">影像科</option>
                    <option value="10">其他科</option>
                </select>
                <button type="submit" id="submit2">查询</button>
                <button type="reset"><a href="/registration">重置</a></button>
            </form>
        </div>
    </div>
    <% List<Patient> list = (List<Patient>) request.getAttribute("list");%>
    <div class="table-container">
        <table>
            <tr>
                <th><input type="checkbox" id="select-all" onclick="toggleAllCheckboxes()"></th>
                <th>病历号</th>
                <th>姓名</th>
                <th>看诊医生</th>
                <th>看诊状态</th>
                <th>挂号日期</th>
                <th>看诊科室</th>
                <th>操作</th>
            </tr>
            <%
                if (list != null) {
                    for (Patient patient : list) {
            %>
            <tr>
                <td><input type="checkbox"></td>
                <td><%=patient.getCaseNumber()%>
                </td>
                <td><%=patient.getRealName()%>
                </td>
                <td><%=patient.getDoctorName()%>
                </td>

                <% if (patient.getVisitState() == 1) {%>
                <td>已挂号</td>
                <% } else if (patient.getVisitState() == 2) {%>
                <td>医生接诊</td>
                <% } else if (patient.getVisitState() == 3) {%>
                <td>看诊结束</td>
                <% } else if (patient.getVisitState() == 4) {%>
                <td>已退号</td>
                <% }%>
                <td><%=patient.getVisitDate()%>
                </td>
                <td><%=patient.getDeptmentName()%>
                </td>
                <td class="actions">
                    <a href="/quitNum?id=<%=patient.getId()%>">退号</a>
                    <a href="">查看</a>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </div>
    <div class="pagination-container">
        <div class="pagination-label">跳至：
            <select id="page-select">
                <option value="1">1</option>
            </select>页
        </div>
    </div>
</div>
</body>
</html>
