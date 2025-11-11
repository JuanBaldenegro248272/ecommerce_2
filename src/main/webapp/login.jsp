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
        
        <%@ include file = "/WEB-INF/fragments/navbar.jspf" %>

        <main class="auth-page">
            <div class="auth-container">
                <h2>INICIAR SESION</h2>
                <p>Bienvenido de vuelta!</p>
                <form class="login-form" id="loginForm">
                    <label for="email-login">CORREO ELECTRONICO</label>
                    <input type="email" id="email-login" class="login-input" required>

                    <label for="password-login">CONTRASEÑA</label>
                    <input type="password" id="password-login" class="login-input" required>

                    <button type="submit" class="login-button">ACCEDER</button>
                </form>
                <p class="switch-form-link">¿No tienes cuenta? <a href="register.jsp">Regístrate aquí</a></p>
            </div>
        </main>

        <%@ include file = "/WEB-INF/fragments/footer.jspf" %>
        
    </body>
</html>
