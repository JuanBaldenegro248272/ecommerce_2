<%-- 
    Document   : mis-pedidos
    Created on : Dec 15, 2025, 9:12:56 AM
    Author     : jrasc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mis Pedidos - ECOstore</title>

        <link rel="stylesheet" href="styles/global.css">
        <link rel="stylesheet" href="styles/styles.css">
    </head>
    <body>
        <%@include file="WEB-INF/fragments/navbar.jspf" %>

        <div class="container-pedidos">
            <h2>Historial de Pedidos</h2>

            <div id="loading-msg">Cargando tus pedidos...</div>
            <p id="error-msg" style="color: red; display:none;"></p>
            <p id="empty-msg" style="display:none; text-align: center; margin-top: 20px;">
                No has realizado ningún pedido aún.
            </p>

            <table class="tabla-pedidos" id="tabla-pedidos" style="display:none;">
                <thead>
                    <tr>
                        <th>ID Pedido</th>
                        <th>Fecha</th>
                        <th>Estado</th>
                        <th>Total</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody id="lista-pedidos-body">
                </tbody>
            </table>
        </div>

        <%@include file="WEB-INF/fragments/footer.jspf" %>

        <script src="js/mis-pedidos.js"></script>
    </body>
</html>