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
<!-- Sign up form -->
<section class="signup" style="margin-top: 35px">
    <div class="container">
        <div class="signup-content">
            <div class="signup-form">
                <h2 class="form-title">Inscription</h2>
                <form method="POST" action="signup" class="register-form" id="register-form">
                    <div class="form-group">
                        <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                        <input type="text" name="prenomUser" id="name" placeholder="Prenom"/>
                    </div>
                    <div class="form-group">
                        <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                        <input type="text" name="nomUser" id="" placeholder="Nom"/>
                    </div>
                    <div class="form-group">
                        <label for="email"><i class="zmdi zmdi-email"></i></label>
                        <input type="email" name="emailUser" id="email" placeholder="Email"/>
                    </div>
                    <div class="form-group">
                        <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                        <input type="password" name="mdpUser" id="pass" placeholder="Mot de passe"/>
                    </div>
                    <div class="form-group form-button">
                        <input type="submit" name="signup" id="signup" class="form-submit" value="S'inscrire"/>
                    </div>
                </form>
            </div>
            <div class="signup-image">
                <figure><img src="./public/images/signup-image.jpg" alt="sing up image"></figure>
                <a href="login" class="signup-image-link">Se connecter</a>
            </div>
        </div>
    </div>
</section>
<script src="./public/vendor/jquery/jquery.min.js"></script>
<script src="./public/js/main.js"></script>
</body>
</html>
