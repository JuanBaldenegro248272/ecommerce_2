<%-- 
    Document   : register
    Created on : Nov 7, 2025, 12:13:41 AM
    Author     : Dana Chavez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/styles.css">
        <title>Registrarse - ECOstore</title>
    </head>
    <body>

        <%@ include file = "/WEB-INF/fragments/navbar.jspf" %>

        <main class="auth-page">
            <div class="auth-container">
                <h2>REGISTRARSE</h2>
                <p>Crea tu cuenta para empezar.</p>
                <form class="login-form" id="registerForm">
                    <label for="name-register">NOMBRE COMPLETO</label>
                    <input type="text" id="name-register" class="login-input" required>

                    <label for="email-register">CORREO ELECTRONICO</label>
                    <input type="email" id="email-register" class="login-input" required>

                    <label for="password-register">CONTRASEÑA</label>
                    <input type="password" id="password-register" class="login-input" required>

                    <label for="phone-register">TELÉFONO</label>
                    <input type="tel" id="phone-register" class="login-input" required>

                    <label for="address-register">DIRECCIÓN</label>
                    <textarea id="address-register" class="login-input" rows="3" required></textarea>

                    <button type="submit" class="login-button">CREAR CUENTA</button>
                </form>
                <p class="switch-form-link">¿Ya tienes cuenta? <a href="login.jsp">Inicia sesión</a></p>
            </div>
        </main>

        <%@ include file = "/WEB-INF/fragments/footer.jspf" %>

    </body>
</html>