<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Connexion</title>

    <!-- Font Icon -->
    <link rel="stylesheet" href="./public/fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="./public/css/style.css">
</head>
<body>
<!-- Sing in  Form -->
<section class="sign-in" style="margin-top: 35px">
    <div class="container">
        <div class="signin-content">
            <div class="signin-image">
                <figure><img src="./public/images/signin-image.jpg" alt="sing up image"></figure>
                <a href="#" class="signup-image-link">Creer un compte</a>
            </div>

            <div class="signin-form">
                <h2 class="form-title">Connexion</h2>
                <form method="POST" action="login" class="register-form" id="login-form">
                    <div class="form-group">
                        <label for="email"><i class="zmdi zmdi-account material-icons-name"></i></label>
                        <input type="email" name="email" id="email" placeholder="Votre email"/>
                    </div>
                    <div class="form-group">
                        <label for="your_pass"><i class="zmdi zmdi-lock"></i></label>
                        <input type="password" name="mdpUser" id="your_pass" placeholder="Mot de passe"/>
                    </div>
                    <div class="form-group">
                        <input type="checkbox" name="remember-me" id="remember-me" class="agree-term" />
                        <label for="remember-me" class="label-agree-term"><span><span></span></span>Se souvenir de moi</label>
                    </div>
                    <div class="form-group form-button">
                        <input type="submit" name="signin" id="signin" class="form-submit" value="Se connecter"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
<!-- JS -->
<script src="./public/vendor/jquery/jquery.min.js"></script>
<script src="./public/js/main.js"></script>
</body>
</html>