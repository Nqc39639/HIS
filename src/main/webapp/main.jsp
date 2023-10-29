<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>云医院HIS</title>
    <style>
        body {
            background-color: #FFFFFF;
            margin: 0;
            padding: 0;
        }

        #header {
            background-color: #20A0FF;
            padding: 10px;
            height: 50px;
            text-align: left;
            font-weight: bold;
        }

        .logoImg {
            height: 55px;
            width: 260px;
            margin-top: -5px;
            margin-left: 25px;
        }

        .img1 {
            height: 15px;
            width: 17px;
            margin-right: 10px;
            filter: brightness(0%) saturate(100%); /* invert(100%) */
        }

        .img2 {
            height: 15px;
            width: 17px;
            filter: brightness(0%) saturate(100%); /* invert(100%) */
            float: right;
            margin-top: 10px;
            margin-right: 5px;
        }

        .img2.active {
            transform: rotate(180deg);
        }

        #container {
            display: flex;
            height: calc(100vh - 40px);
        }

        #sidebar {
            background-color: #E0E0E0;
            flex-basis: 250px;
            padding: 10px;
            height: 150%;
            width: 256px;
            overflow-y: auto;
        }

        #content {
            display: flex;
            flex-grow: 1;
            height: 150%;
        }

        #pane {
            background-color: #FFFFFF;
            flex-basis: 100%;
            padding: 10px;
        }

        iframe {
            border: none;
            width: 100%;
            height: 100%;
        }

        .nav-heading {
            cursor: pointer;
            padding-left: 15px;
            margin-top: 10px;
            font-family: "楷体", KaiTi, serif;
            font-style: normal;
            font-size: 18px;
            line-height: 34px;
            font-weight: bold;
            text-align: left;
        }

        .nav-item {
            list-style-type: none;
            display: none;
            padding-left: 42px;
            font-family: "楷体", KaiTi, serif;
            font-style: normal;
            font-size: 18px;
            font-weight: bold;
            line-height: 34px;
            text-align: left;
        }

        .nav-item.active {
            display: block;
        }

        .nav-item a {
            text-decoration: none;
            outline: none;
            color: #000000;
        }

        form {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 10px;
            margin-top: 10px;
        }

        .userImg {
            width: 30px;
            height: 30px;
            border-radius: 50%;
            cursor: pointer;
            float: right;
            margin-top: 10px;
            margin-right: 10px;
        }

        .userName {
            float: right;
            top: 18px;
            right: -50px;
            position: absolute;
            font-weight: normal;
        }

        .dropdown {
            float: right;
            right: 70px;
            position: relative;
            display: inline-block;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            top: 45px;
            left: -10px;
            background-color: #f9f9f9;
            min-width: 120px;
            box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
            z-index: 1;
        }

        .dropdown-content a {
            color: black;
            padding: 15px;
            text-align: center;
            font-size: 14px;
            font-weight: normal;
            text-decoration: none;
            display: block;
        }

        .dropdown-content a:hover {
            background-color: #f1f1f1;
        }

        .dropdown:hover .dropdown-content {
            display: block;
        }

        .dropdown:hover .user-avatar {
            background-color: #999;
        }
    </style>
    <script>
        function toggleDropdown() {
            var dropdown = document.getElementById('dropdown-content');
        }

        function toggleNav(navId, imgId) {
            var navItem = document.getElementById(navId);
            var imgItem = document.getElementById(imgId);
            var isNavItemActive = navItem.classList.contains('active');
            var isImgItemActive = imgItem.classList.contains('active');
            if (isNavItemActive && isImgItemActive) {
                navItem.classList.remove('active');
                imgItem.classList.remove('active');
            } else {
                navItem.classList.add('active');
                imgItem.classList.add('active');
            }
        }
    </script>
</head>

<body>
<%
    String username = (String) session.getAttribute("username");
    if (username != null) {
%>
<div id="header">
    <img src="static/images/logo.png" class="logoImg">
    <div class="dropdown" onclick="toggleDropdown()">
        <div>
            <img src="static/images/QQ.jpg" alt="UserAvatar" class="userImg">
            <div class="userName"><%=session.getAttribute("username")%>
            </div>
        </div>
        <div class="dropdown-content">
            <a href="#">查看个人信息</a>
            <a href="login.jsp">退出登录</a>
        </div>
    </div>
</div>
<div id="container">
    <div id="sidebar">
        <div class="nav-heading" onclick="toggleNav('registration','1')">
            <img class="img1" src="static/images/u1.png"/>挂号收费<img id="1" class="img2" src="static/images/u2.png"/>
        </div>
        <ul id="registration" class="nav-item">
            <li><a href="onsiteRegistration.jsp" target="iframe_content">窗口挂号</a></li>
            <li><a href="/registration" target="iframe_content">窗口退号</a></li>
            <li><a href="expenseCharge.jsp" target="iframe_content">收费</a></li>
            <li><a href="expenseRefund.jsp" target="iframe_content">退费</a></li>
            <li><a href="expenseManage.jsp" target="iframe_content">费用记录查询</a></li>
        </ul>
        <div class="nav-heading" onclick="toggleNav('physician','2')">
            <img class="img1" src="static/images/u1.png"/>医生工作<img id="2" class="img2" src="static/images/u2.png"/>
        </div>
        <ul id="physician" class="nav-item">
            <li><a href="#" target="iframe_content">患者查看</a></li>
            <li><a href="#" target="iframe_content">医生诊疗</a></li>
            <li><a href="#" target="iframe_content">看诊记录</a></li>
        </ul>
        <div class="nav-heading" onclick="toggleNav('examination','3')">
            <img class="img1" src="static/images/u1.png"/>检查管理<img id="3" class="img2" src="static/images/u2.png"/>
        </div>
        <ul id="examination" class="nav-item">
            <li><a href="#" target="iframe_content">检查申请</a></li>
            <li><a href="#" target="iframe_content">患者录入</a></li>
            <li><a href="#" target="iframe_content">检查录入</a></li>
            <li><a href="#" target="iframe_content">检查管理</a></li>
        </ul>
        <div class="nav-heading" onclick="toggleNav('lab','4')">
            <img class="img1" src="static/images/u1.png"/>检验管理<img id="4" class="img2" src="static/images/u2.png"/>
        </div>
        <ul id="lab" class="nav-item">
            <li><a href="#" target="iframe_content">检验申请</a></li>
            <li><a href="#" target="iframe_content">患者录入</a></li>
            <li><a href="#" target="iframe_content">检验录入</a></li>
            <li><a href="#" target="iframe_content">检验管理</a></li>
        </ul>
        <div class="nav-heading" onclick="toggleNav('pharmacy','5')">
            <img class="img1" src="static/images/u1.png"/>药房管理<img id="5" class="img2" src="static/images/u2.png"/>
        </div>
        <ul id="pharmacy" class="nav-item">
            <li><a href="giveMedicine.jsp" target="iframe_content">药房发药</a></li>
            <li><a href="drugMedicine.jsp" target="iframe_content">药房退药</a></li>
            <li><a href="/drugStorage" target="iframe_content">药库管理</a></li>
            <li><a href="tranHistory.jsp" target="iframe_content">发退药记录管理</a></li>
        </ul>
        <div class="nav-heading" onclick="toggleNav('procedure','6')">
            <img class="img1" src="static/images/u1.png"/>处置管理<img id="6" class="img2" src="static/images/u2.png"/>
        </div>
        <ul id="procedure" class="nav-item">
            <li><a href="#" target="iframe_content">处置申请</a></li>
            <li><a href="#" target="iframe_content">患者录入</a></li>
            <li><a href="#" target="iframe_content">处置录入</a></li>
            <li><a href="#" target="iframe_content">处置管理</a></li>
        </ul>
    </div>
    <div id="content">
        <div id="pane">
            <iframe src="home.jsp" id="iframe_content" name="iframe_content"></iframe>
        </div>
    </div>
</div>
<%
    } else {
        response.sendRedirect("login.jsp");
    }
%>
</body>
</html>