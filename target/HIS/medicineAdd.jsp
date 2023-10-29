<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>药品入库</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        h1 {
            margin-bottom: 20px;
        }

        form {
            margin-bottom: 20px;
        }

        label {
            display: inline-block;
            width: 100px;
        }

        input[type="text"],
        input[type="number"],
        input[type="submit"] {
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 3px;
            margin-bottom: 10px;
        }

        input[type="submit"] {
            padding: 5px 10px;
            background-color: #428bca;
            color: white;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #3071a9;
        }
    </style>
</head>
<body>
    <h1>药品入库</h1>

    <form id="drugInventoryForm" method="POST" action="MedicineServlet">
        <label for="drugCode">药品编码:</label>
        <input type="text" id="drugCode" name="drugCode" required><br><br>

        <label for="drugName">药品名称:</label>
        <input type="text" id="drugName" name="drugName" required><br><br>

        <label for="drugFormat">药品规格:</label>
        <input type="text" id="drugFormat" name="drugFormat" required><br><br>

        <label for="drugUnit">药品单位:</label>
        <input type="text" id="drugUnit" name="drugUnit" required><br><br>

        <label for="manufacturer">生产厂家:</label>
        <input type="text" id="manufacturer" name="manufacturer" required><br><br>

        <label for="drugDosage">药品剂型:</label>
        <input type="text" id="drugDosage" name="drugDosage" required><br><br>

        <label for="drugType">药品类型:</label>
        <input type="text" id="drugType" name="drugType" required><br><br>

        <label for="drugPrice">药品价格:</label>
        <input type="number" id="drugPrice" name="drugPrice" step="0.01" required><br><br>

        <label for="mnemonicCode">助记码:</label>
        <input type="text" id="mnemonicCode" name="mnemonicCode" required><br><br>

        <label for="creationDate">创建日期:</label>
        <input type="date" id="creationDate" name="creationDate" required><br><br>

        <input type="submit" value="提交">
    </form>

    <script>
        const form = document.getElementById("drugInventoryForm");

        form.addEventListener("submit", function (event) {
            event.preventDefault(); // 阻止表单提交

            // 发送异步请求到Servlet
            const xhr = new XMLHttpRequest();
            xhr.open("POST", form.action, true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                    // 请求成功时显示成功消息
                    alert(xhr.responseText);
                    form.reset();
                } else if (xhr.readyState === XMLHttpRequest.DONE && xhr.status !== 200) {
                    // 请求失败时显示错误消息
                    alert("发生错误：" + xhr.responseText);
                }
            };

            // 将表单数据编码并发送
            const formData = new FormData(form);
            const encodedData = new URLSearchParams(formData).toString();
            xhr.send(encodedData);
        });
    </script>
</body>
</html>