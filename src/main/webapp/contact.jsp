<%-- 
    Document   : contact
    Created on : Nov 6, 2025, 11:56:14 PM
    Author     : Dana Chavez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/styles.css">
        <title>Contacto - ECOstore</title>
    </head>
    <body>
        
        <%@ include file = "/WEB-INF/fragments/navbar.jspf" %>

        <main class="contact-page">
            <div class="contact-container">
                <h1>CONTACTO</h1>

                <div class="form-wrapper">
                    <form class="contact-form">
                        <label for="nombre">Nombre</label>
                        <input type="text" id="nombre">

                        <label for="email">Correo electronico</label>
                        <input type="email" id="email">

                        <label for="mensaje">Mensaje</label>
                        <textarea id="mensaje"></textarea>
                    </form>

                    <div class="icon-column">
                        <img src="icons/callicon.png" alt="Teléfono" class="contact-icon-large phone-icon">
                        <img src="icons/sendicon.png" alt="Enviar" class="contact-icon-large cursor-icon">
                    </div>
                </div>

                <div class="other-contact">
                    <h3>Otras formas de contacto</h3>
                    <div class="contact-details">
                        <div class="contact-item">
                            <img src="icons/locationicon.png" alt="Ubicación" class="icon-location">
                            <p>Calle Kino #115 Entre Otimuri y Antonio Caso</p>
                        </div>
                        <div class="contact-item">
                            <img src="icons/mailicon.png" alt="Email" class="icon-mail">
                            <p>holaECOstore@gmail.com</p>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        
        <%@ include file = "/WEB-INF/fragments/footer.jspf" %>
        
    </body>
</html>
