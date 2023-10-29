<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.neuedu.his.entity.Drug" %>
<%@ page import="java.util.List" %>
<%!
    private List<Drug> drugList;
%>
<!DOCTYPE html>
<html>
<head>
    <title>药品管理</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        h1 {
            margin-bottom: 20px;
        }

        form {
            margin-bottom: 10px;
        }

        #searchInput {
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        #drugList {
            margin-bottom: 20px;
        }

        p {
            margin-bottom: 5px;
        }

        span {
            font-weight: bold;
        }

        em {
            font-style: italic;
        }

        #addDrug {
            margin-top: 20px;
        }

        button {
            padding: 5px 10px;
            background-color: #428bca;
            color: white;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        button:hover {
            background-color: #3071a9;
        }
    </style>
</head>
<body>
    <h1>药品管理系统</h1>
    <form>
        <label for="searchInput">药品搜索：</label>
        <input type="text" id="searchInput">
        <button type="button" onclick="searchDrugs()">搜索</button>
    </form>

    <div id="drugList">
        <% if (drugList != null && !drugList.isEmpty()) {
            for (Drug drug : drugList) { %>
            <p>药品名：<span><%= drug.getName() %></span>，价格：<em><%= drug.getPrice() %></em></p>
        <% }
        } else { %>
            <p>没有找到符合条件的药品。</p>
        <% } %>
    </div>

    <div id="addDrug">
        <button type="button" onclick="goToAddDrugPage()">添加药品</button>
    </div>

    <script>
        // 药品搜索函数
        function searchDrugs() {
            var searchInput = document.getElementById("searchInput").value;
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "/drugManagement?search=" + searchInput);
            xhr.onreadystatechange = function() {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        document.getElementById("drugList").innerHTML = xhr.responseText;
                    } else {
                        console.log("Error: " + xhr.status);
                    }
                }
            };
            xhr.send();
        }

        // 跳转到药品添加页面
        function goToAddDrugPage() {
            window.location.href = "medicineAdd.jsp";
        }
    </script>
</body>
</html>