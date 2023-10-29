<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
</head>
<style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    body {
        background-color: #f2f2f2;
        background-image: url(static/images/bg.jpg);
        background-repeat: no-repeat;
        background-size: 100%;
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
        height: 500px;
        text-align: center;
        position: relative;
        background-color: rgba(0, 0, 0, .5);
        margin: 6% auto 0;
        border-radius: 20px;
        padding-top: 20px;
    }

    .registerTitle {
        width: 300px;
        height: 50px;
        margin: 0 auto;
        line-height: 50px;
        font-size: 36px;
        color: whitesmoke;
    }

    .pwd,
    .npwd,
    .name,
    .btn {
        width: 450px;
        margin: 30px auto 0;
        box-sizing: border-box;
        color: whitesmoke;
        font-size: 28px;
        line-height: 50px;
    }

    #pwd,
    #npwd,
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
<body>
<form action="/register" class="" method="post">
    <div class="registerTitle">注册页面</div>
    <div class="name  clearfix">
        <p class="fl">用户名:</p><input type="text" class="fr" id="name" name="name" placeholder="请输入您要注册的账号">
    </div>
    <div class="pwd  clearfix">
        <p class="fl">密码:</p><input type="password" class="fr" id="pwd" name="pwd" placeholder="请输入您要注册的密码">
    </div>
    <div class="npwd  clearfix">
        <p class="fl">确认密码:</p><input type="password" class="fr" id="npwd" name="npwd" placeholder="请重新输入您的密码">
    </div>
    <div>
        <input type="checkbox" name="Y_or_N" checked>同意<a href="agreement.jsp" target="_blank">《注册账户协议》</a>
    </div>
    <div class="btn  clearfix">
        <button type="submit" class="fl" id="register">注册</button>
        <button type="reset" class="fr" id="reset">重置</button>
    </div>
    <div class="fr">已有帐号？<a href="login.jsp">立即登录</a></div>
</form>
</body>
</html>
