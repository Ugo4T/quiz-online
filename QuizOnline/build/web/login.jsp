<%-- 
    Document   : login
    Created on : FED 8, 2021, 11:51:50 AM
    Author     : AVITA
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>
        <meta charset="UTF-8">

        <script src='https://www.google.com/recaptcha/api.js'></script>

        <style>
            body{
                background-color: #2CC185;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5 px-3 py-3 border rounded text-center bg-white" style="width: 350px">
            <h2>Login Page</h2>
            <p> ${requestScope.ERRORLOGIN} </p>
            <form class="mb-2" action="login" method="POST" onsubmit="return submitForm()">
                <input type="email" cssClass="form-control mb-0" name="txtUsername" placeholder="Email"/><br/>
                <input type="password" cssClass="form-control mb-1" name="txtPassword" placeholder="Password"/><br/>
                <input class="btn btn-primary px-4" type="submit" value="Login" />
                <input class="btn btn-secondary px-4" type="reset" value="Reset" />

                <jsp:useBean id="recaptcha" class="vanlt.util.Recaptcha"></jsp:useBean>
                    <div 
                        class="g-recaptcha" data-sitekey="${recaptcha.siteKey}"data-callback='onSubmit' data-action='submit'
                    ></div>

                <div id="errMsg" style="display: none" class="my-1 text-danger">Invalid recaptcha, try again!</div>
                <a href="register">Click here to Sign Up</a>
        </div>
    </form>
    <script>
        let captchaResponse = "";
        function onSubmit(token) {
            if (token.length > 0) {
                document.getElementById("errMsg").style.display = "none";
                captchaResponse = token;
            }
        }
        function submitForm() {
            if (captchaResponse == "") {
                document.getElementById("errMsg").style.display = "block";
                return false;
            }
            return true;
        }

    </script>

</body>
</html>
