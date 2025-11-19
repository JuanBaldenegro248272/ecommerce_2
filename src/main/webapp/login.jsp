<%-- 
    Document   : login
    Created on : Nov 7, 2025, 12:07:05 AM
    Author     : Dana Chavez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/styles.css">
        <title>Iniciar Sesión - ECOstore</title>
    </head>
    <body>

        <main class="auth-page">
            <div class="auth-container">
                <h2>INICIAR SESIÓN</h2>
                <p>Bienvenido de vuelta!</p>

                
                <form class="login-form" action="LoginServlet" method="post">
                    <label for="email-login">CORREO ELECTRÓNICO</label>
                    <input 
                        type="email" 
                        id="email-login" 
                        name="email-login"
                        class="login-input" 
                        required
                    >

                    <label for="password-login">CONTRASEÑA</label>
                    <input 
                        type="password" 
                        id="password-login" 
                        name="password-login"
                        class="login-input" 
                        required
                    >

                    <button type="submit" class="login-button">ACCEDER</button>
                </form>

                <p class="switch-form-link">
                    ¿No tienes cuenta? 
                    <a href="register.jsp">Regístrate aquí</a>
                </p>
            </div>
        </main>

    </body>
</html>

