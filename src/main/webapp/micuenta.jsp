<%-- 
    Document   : micuenta
    Created on : Nov 7, 2025, 12:08:45 AM
    Author     : Dana Chavez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href ="styles/styles.css"/> 
        <link rel ="stylesheet" href="styles/formatocss.css"/>
    </head>
    <body>

        <%@ include file = "/WEB-INF/fragments/navbar.jspf" %>

        <main>
            <section class = "mi-cuenta">
                <div class ="cuenta-container">
                    <h1 class ="cuenta-titulo">MI CUENTA</h1>
                    <p class = "cuenta-bienvenida">Hola Fulanito de Tal</p>
                    <button class ="btn-editar">Editar datos</button>

                    <div class ="form-cuenta">
                        <form>
                            <h2>INFORMACIÓN PERSONAL</h2>
                            <input type="text" placeholder="Nombre completo" disabled>
                            <input type="email" placeholder="Correo electrónico" disabled>

                            <h2> DIRECCIÓN DE ENVÍO</h2>
                            <input type ="text" placeholder="Calle y número" disabled>
                            <div class="form-row">
                                <input type="text" placeholder="Ciudad" disabled>
                                <input type="text" placeholder="Estado" disabled>
                            </div>

                            <div class="form-row">
                                <input type="text" placeholder="Código postal" disabled>
                                <input type="tel" placeholder="Número de teléfono" disabled>
                            </div>

                            <div class="botones-form">
                                <button type="button" class="btn-cancelar">Cancelar</button>
                                <button type="submit" class="btn-guardar">Guardar cambios</button>
                            </div>

                        </form>
                    </div>

                </div>
            </section>
        </main>

        <%@ include file = "/WEB-INF/fragments/footer.jspf" %>

    </body>
</html>
