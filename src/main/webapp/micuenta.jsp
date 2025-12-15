<%-- 
    Document   : micuenta
    Created on : Nov 7, 2025, 12:08:45 AM
    Author     : Dana Chavez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Mi Cuenta</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href ="styles/styles.css"/> 
        <link rel ="stylesheet" href="styles/formatocss.css"/>
        <script src="js/micuenta.js"></script>
    </head>
    <body>

        <%@ include file = "/WEB-INF/fragments/navbar.jspf" %>

        <main>
            <section class = "mi-cuenta">
                <div class ="cuenta-container">
                    <h1 class ="cuenta-titulo">MI CUENTA</h1>
                    <p class = "cuenta-bienvenida">Hola ${cliente.nombre}</p>
                    <button type="button" class="btn-editar" id="btn-editar">Editar datos</button>
                    <a href="mis-pedidos.jsp" class="btn-editar">Mis Pedidos</a>
                    <div class ="form-cuenta">
                        <form action="PerfilServlet" method="POST" id="form-perfil">
                            <h2>INFORMACIÓN PERSONAL</h2>
                            <input type="text" name="nombre" placeholder="Nombre completo" value="${cliente.nombre}" disabled>
                            <input type="email" name="email" placeholder="Correo electrónico" value="${cliente.correoElectronico}" disabled>

                            <h2> DIRECCIÓN DE ENVÍO</h2>
                            <input type ="text" name="calle" placeholder="Calle y número" value="${cliente.calle}" disabled>
                            <div class="form-row">
                                <input type="text" name="ciudad" placeholder="Ciudad" value="${cliente.ciudad}" disabled>
                                <input type="text" name="estado" placeholder="Estado" disabled>
                            </div>

                            <div class="form-row">
                                <input type="text" name="codigoPostal" placeholder="Código postal" disabled>
                                <input type="tel" name="telefono" placeholder="Número de teléfono" value="${cliente.telefono}" disabled>
                            </div>

                            <div class="botones-form">
                                <button type="button" class="btn-cancelar" id="btn-cancelar">Cancelar</button>
                                <button type="submit" class="btn-guardar" id="btn-guardar" style="display: none;">Guardar cambios</button>
                            </div>

                        </form>
                    </div>

                </div>
            </section>
        </main>

        <%@ include file = "/WEB-INF/fragments/footer.jspf" %>
        <script src="js/micuenta.js"></script>

    </body>
</html>
