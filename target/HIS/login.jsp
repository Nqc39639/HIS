<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <title>用户登录</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            background-image: url(static/images/bg.jpg);
            background-repeat: no-repeat;
            background-size: 100%;
            background-color: #f2f2f2;
        }

        button {
            width: 160px;
            height: 50px;
            background: none;
            outline: none;
            border: 2px solid gray;
            border-radius: 15px;
            font-size: 24px;
            transition: all 1s;
            color: whitesmoke;
        }

        button:hover {
            background: rgb(175, 170, 175);
            color: whitesmoke;
        }

        div,
        input {
            color: whitesmoke;
        }

        a {
            text-decoration: none;
            color: whitesmoke;
        }

        .fl {
            float: left;
        }

        .fr {
            float: right;
        }

        .clearfix::after {
            content: '';
            height: 0px;
            display: block;
            clear: both;
        }

        /* 小眼睛颜色 */
        ::-ms-reveal {
            filter: invert(100%);
        }

        form {
            width: 600px;
            height: 400px;
            text-align: center;
            position: relative;
            margin: 0 auto;
            background-color: rgba(0, 0, 0, .5);
            margin-top: 10%;
            border-radius: 20px;
            padding-top: 40px;
        }

        .loginTitle {
            width: 200px;
            height: 50px;
            margin: 0% auto;
            line-height: 50px;
            font-size: 36px;
            color: whitesmoke;
        }

        .pwd,
        .name,
        .btn {
            width: 420px;
            margin: 0 auto;
            margin-top: 30px;
            box-sizing: border-box;
        }

        .pwd p,
        .name p {
            color: whitesmoke;
            font-size: 28px;
            line-height: 50px;
        }

        #pwd,
        #name {
            height: 50px;
            width: 320px;
            font-size: 24px;
            outline: none;
            padding-left: 20px;
            background: none;
            border-radius: 15px;
            border: 2px solid gray;
        }
    </style>
</head>

<body>
<form action="/login" method="post">
    <div class="loginTitle">登录页面</div>
    <div class="name  clearfix">
        <p class="fl">用户名:</p><input type="text" class="fr" id="name" name="name" placeholder="请输入用户名">
    </div>
    <div class="pwd  clearfix">
        <p class="fl">密码:</p><input type="password" class="fr" id="pwd" name="pwd" placeholder="请输入密码">
    </div>
    <div class="btn  clearfix">
        <button type="submit" class="fl" id="login">登录</button>
        <button type="submit" class="fr" id="register" formaction="register.jsp">注册</button>
    </div>
</form>
</body>
</html>
