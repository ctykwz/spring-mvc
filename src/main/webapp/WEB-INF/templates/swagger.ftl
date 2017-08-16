<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="service monitor">
    <meta name="author" content="ctyk">
    <link rel="shortcut icon" href="${baseUrl}/resources/favicon.ico">
    <title>API Document</title>
    <!-- Bootstrap Core CSS -->
    <link href="${baseUrl}/resources/bootstrap/bootstrap.min.css" rel="stylesheet">
    <!-- MetisMenu CSS -->
    <link href="${baseUrl}/resources/metisMenu/metisMenu.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="${baseUrl}/resources/dist/sb-admin-2.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="${baseUrl}/resources/font-awesome/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="${baseUrl}/resources/ie/html5shiv.js"></script>
    <script src="${baseUrl}/resources/ie/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Please Sign In</h3>
                </div>
                <div class="panel-body">
                    <form role="form">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="Username" name="username" id="username"
                                       autofocus>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Password" name="password" type="password"
                                       id="password" value="">
                            </div>
                            <div class="form-group" id="verifyCodeContain">
                                <input class="form-control" placeholder="Verify Code" maxlength="4" name="verify code"
                                       id="verifyCode" style="float: left;width: 61%">
                                <img src="${baseUrl}/swagger/verify/code" id="verifyCodeImage" style="float: right"
                                     onclick='freshVerifyCode()'>
                            </div>
                            <div class="form-group" style="height: 37px">
                            </div>
                            <!-- Change this to a button or input when using this as a form -->
                            <a class="btn btn-lg btn-success btn-block" onclick="login()">Login</a>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- jQuery -->
<script src="${baseUrl}/resources/jquery/jquery.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="${baseUrl}/resources/bootstrap/bootstrap.min.js"></script>
<!-- Metis Menu Plugin JavaScript -->
<script src="${baseUrl}/resources/metisMenu/metisMenu.min.js"></script>
<!-- Custom Theme JavaScript -->
<script src="${baseUrl}/resources/dist/sb-admin-2.js"></script>
<script>
    order = 1;
    lastFreshTime = new Date();

    function login() {
        var username = $("#username").val();
        if (username.length < 1) {
            $("#username").focus();
            return;
        }
        var password = $("#password").val();
        if (password.length < 1) {
            $("#password").focus();
            return;
        }
        try {
            window.btoa(password);
        } catch (error) {
            $("#password").val("");
            return;
        }
        var verifyCode = $("#verifyCode").val();
        if (verifyCode.length < 1) {
            $("#verifyCode").focus();
            return;
        }
        $.ajax({
            type: "POST",
            url: "${baseUrl}/swagger/login/check",
            dataType: "json",
            data: {
                "username": username,
                "password": password,
                "verifyCode": verifyCode
            }, success: function (data) {
                console.log(data);
                if (data.status === 0) {
                    window.location.href = "${baseUrl}/swagger-ui.html";
                } else {
                    $("#password").val("");
                    $("#verifyCode").val("");
                    freshVerifyCode(true);
                }
            }
        });
    }

    function freshVerifyCode() {
        $("#verifyCodeImage").remove();
        var html = "<img src='${baseUrl}/swagger/verify/code?#seed#' id='verifyCodeImage' style='float: right' onclick='freshVerifyCode()'>";
        html = html.replace(/#seed#/g, order++);
        $("#verifyCodeContain").append(html);
    }
</script>
</body>
</html>