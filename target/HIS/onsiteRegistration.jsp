<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>窗口挂号</title>
    <style>
        body {
            background-color: #FFFFFF;
            margin: 0;
            padding: 0;
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

        #content {
            display: flex;
            flex-grow: 1;
        }

        #pane {
            background-color: #FFFFFF;
            flex-basis: 100%;
            padding: 10px;
        }

        form {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 10px;
            margin-top: 10px;
        }

        div.button-group input {
            width: 70px;
            height: 35px;
            font-size: 18px;
            font-family: "楷体", KaiTi, serif;
            padding: 7px 20px 20px 17px;
            margin-top: 10px;
            margin-right: 100px;
        }

        input,
        select {
            height: 33px;
            padding: 5px;
            box-sizing: border-box;
            border-radius: 5px;
            border: 1px solid #E0E0E0;
        }

        input {
            padding-left: 10px;
        }

        input[type="submit"] {
            margin-top: 10px;
            padding: 5px 10px;
            border-radius: 5px;
            background-color: #1890FF;
            color: white;
            border: 1px solid #1890FF;
            cursor: pointer;
        }

        input[type="reset"] {
            margin-top: 10px;
            padding: 5px 10px;
            border-radius: 5px;
            background-color: white;
            color: black;
            border: 1px solid;
            cursor: pointer;
        }

        .testswitch {
            position: relative;
            float: left;
            width: 60px;
            margin: 0;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
        }

        .testswitch-checkbox {
            display: none;
        }

        .testswitch-label {
            display: block;
            overflow: hidden;
            cursor: pointer;
            border: 2px solid #999999;
            border-radius: 20px;
        }

        .testswitch-inner {
            display: block;
            width: 200%;
            margin-left: -100%;
            transition: margin 0.2s ease-in 0s;
        }

        .testswitch-inner::before,
        .testswitch-inner::after {
            display: block;
            float: right;
            width: 50%;
            height: 24px;
            padding: 0;
            line-height: 24px;
            font-size: 14px;
            color: white;
            font-family: Trebuchet, Arial, sans-serif;
            font-weight: bold;
            box-sizing: border-box;
        }

        .testswitch-inner::after {
            content: attr(data-on);
            padding-left: 10px;
            background-color: #00e500;
            color: #FFFFFF;
        }

        .testswitch-inner::before {
            content: attr(data-off);
            padding-right: 10px;
            background-color: #EEEEEE;
            color: #999999;
            text-align: right;
        }

        .testswitch-switch {
            position: absolute;
            display: block;
            width: 19px;
            height: 19px;
            margin: 2.5px;
            background: #FFFFFF;
            top: 0;
            bottom: 0;
            right: 34px;
            border: 2px solid #999999;
            border-radius: 20px;
            transition: all 0.2s ease-in 0s;
        }

        .testswitch-checkbox:checked + .testswitch-label .testswitch-inner {
            margin-left: 0px;
        }

        .testswitch-checkbox:checked + .testswitch-label .testswitch-switch {
            right: 0px;
        }

        .form-row {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }

        .form-label {
            width: 120px;
            font-weight: normal;
            text-align: right;
            margin-right: 10px;
        }
    </style>
    <script>
        // 生成病历号的逻辑
        function generateCaseNumber() {
            var date = new Date();
            var hour = date.getHours().toString().padStart(2, "0");
            var minute = date.getMinutes().toString().padStart(2, "0");
            var second = date.getSeconds().toString().padStart(2, "0");
            return "N" + hour + minute + second;
        }

        // 设置病历号到输入框
        function setCaseNumber(caseNumber) {
            var caseNumberInput = document.getElementById("caseNumber");
            caseNumberInput.value = caseNumber;
            caseNumberInput.disabled = true;
        }

        // 设置当前日期到输入框
        function setVisitDate() {
            var date = new Date();
            var year = date.getFullYear();
            var month = (date.getMonth() + 1).toString().padStart(2, "0");
            var day = date.getDate().toString().padStart(2, "0");
            var visitDateInput = document.getElementById("visitDate");
            visitDateInput.value = year + "-" + month + "-" + day;
            visitDateInput.disabled = true;
        }

        // 设置午别为默认值
        function setDefaultNoon() {
            var currentTime = new Date().getHours();
            var noonInput = document.getElementById("noon");
            if (currentTime < 12) {
                noonInput.value = "上午";
            } else {
                noonInput.value = "下午";
            }
            noonInput.disabled = true;
        }

        // 页面加载时执行的操作
        window.onload = function () {
            var caseNumber = generateCaseNumber();
            setCaseNumber(caseNumber);
            setVisitDate();
            setDefaultNoon();
            document.getElementById("hiddeCaseNumber").value = document.getElementById("caseNumber").value;
            document.getElementById("hiddenVisitDate").value = document.getElementById("visitDate").value;
            document.getElementById("hiddenNoon").value = document.getElementById("noon").value;
            var registLevelSelect = document.getElementById("registLevel");
            var amountDueInput = document.getElementById("amountDue");
            var registLevelMap = {"1": "10", "2": "30", "3": "50", "4": "100"};
            registLevelSelect.onchange = function () {
                var selectedValue = registLevelSelect.value;
                var amountDue = registLevelMap[selectedValue];
                amountDueInput.value = amountDue;
            };
            amountDueInput.readOnly = true;
        };
    </script>
</head>

<body>
<h3>现场挂号</h3>
<div id="content">
    <div id="pane">
        <h4>基本信息</h4>
        <form action="/registration" method="post">
            <div class="form-row">
                <label class="form-label" for="caseNumber"><span style="color: red">*</span>病历号:</label>
                <input type="text" id="caseNumber" name="caseNumber" required>
                <input type="hidden" id="hiddeCaseNumber" name="caseNumber">
            </div>
            <div class="form-row">
                <label class="form-label" for="realName"><span style="color: red">*</span>姓名:</label>
                <input type="text" id="realName" name="realName" placeholder="请输入病人姓名" required>
            </div>
            <div class="form-row">
                <label class="form-label" for="gender"><span style="color: red">*</span>性别:</label>
                <select id="gender" name="gender" required>
                    <option value="none" selected disabled hidden>请选择</option>
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </div>
            <div class="form-row">
                <label class="form-label" for="age"><span style="color: red">*</span>年龄:</label>
                <input type="text" id="age" name="age" placeholder="请输入病人年龄" required>
            </div>
            <div class="form-row">
                <label class="form-label" for="birthdate">出生日期:</label>
                <input type="date" id="birthdate" name="birthdate">
            </div>
            <div class="form-row">
                <label class="form-label" for="address">家庭住址:</label>
                <input type="text" id="address" name="address" placeholder="请输入病人家庭住址">
            </div>
            <div class="form-row">
                <label class="form-label" for="idNumber"><span style="color: red">*</span>身份证号:</label>
                <input type="text" id="idNumber" name="idNumber" placeholder="请输入病人身份证号" required>
            </div>
            <div class="form-row">
                <label class="form-label" for="visitDate"><span style="color: red">*</span>看诊日期:</label>
                <input type="date" id="visitDate" name="visitDate" required>
                <input type="hidden" id="hiddenVisitDate" name="visitDate">
            </div>
            <div class="form-row">
                <label class="form-label" for="noon"><span style="color: red">*</span>午别:</label>
                <select id="noon" name="noon" required>
                    <option value="none" selected disabled hidden>请选择</option>
                    <option value="上午">上午</option>
                    <option value="下午">下午</option>
                </select>
                <input type="hidden" id="hiddenNoon" name="noon">
            </div>
            <div class="form-row">
                <label class="form-label" for="department"><span style="color: red">*</span>挂号科室:</label>
                <select id="department" name="department" required>
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
            </div>
            <div class="form-row">
                <label class="form-label" for="registLevel"><span style="color: red">*</span>挂号级别:</label>
                <select id="registLevel" name="registLevel" required>
                    <option value="none" selected disabled hidden>请选择</option>
                    <option value="1">普通就诊</option>
                    <option value="2">专病就诊</option>
                    <option value="3">专家就诊</option>
                    <option value="4">特需就诊</option>
                </select>
            </div>
            <div class="form-row">
                <label class="form-label" for="initialQuota">初始号额:</label>
                <input type="text" id="initialQuota" name="initialQuota">
            </div>
            <div class="form-row">
                <label class="form-label" for="usedQuota">已用号额:</label>
                <input type="text" id="usedQuota" name="usedQuota">
            </div>
            <div class="form-row">
                <label class="form-label">病历本:</label>
                <div class="testswitch">
                    <input class="testswitch-checkbox" id="onOffSwitch" name="onOffSwitch" type="checkbox">
                    <label class="testswitch-label" for="onOffSwitch">
                        <span class="testswitch-inner" data-on="是" data-off="否"></span>
                        <span class="testswitch-switch"></span>
                    </label>
                </div>
            </div>
            <div class="form-row">
                <label class="form-label" for="amountDue"><span style="color: red">*</span>应收金额:</label>
                <input type="text" id="amountDue" name="amountDue" placeholder="请输入应收金额" required>
            </div>
            <div class="form-row">
                <label class="form-label" for="paymentMethod"><span style="color: red">*</span>收费方式:</label>
                <select id="paymentMethod" name="paymentMethod" required>
                    <option value="none" selected disabled hidden>请选择</option>
                    <option value="1">现金支付</option>
                    <option value="2">微信支付</option>
                    <option value="3">支付宝支付</option>
                    <option value="4">医保卡支付</option>
                    <option value="5">银联支付</option>
                </select>
            </div>
            <div class="form-row  button-group">
                <input type="submit" value="挂号">
                <input type="reset" value="清空">
            </div>
        </form>
    </div>
</div>
</body>
</html>