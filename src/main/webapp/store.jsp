
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>ECOstore - Catálogo</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="styles/formatocss.css">
        <link rel="stylesheet" href="styles/styles.css">
    </head>
    <body>

        <%@ include file="/WEB-INF/fragments/navbar.jspf" %>

        <h1 style="text-align: center; margin-top: 20px; font-family: var(--staatliches); font-size: 3rem;">STORE</h1>

        <section class="store-grid" id="contenedor-productos">
            <p style="text-align: center; width: 100%;">Cargando catálogo</p>
        </section>

        <%@ include file="/WEB-INF/fragments/footer.jspf" %>

        <script src="js/cart.js"></script>
        <script src="js/store.js"></script>

    </body>
</html>