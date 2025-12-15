<%-- 
    Document   : estilos
    Created on : Nov 6, 2025, 11:58:41 PM
    Author     : Dana Chavez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/styles.css">
        <title>ECOstore - Géneros</title>
    </head>
    <body>

        <%@ include file = "/WEB-INF/fragments/navbar.jspf" %>

        <section class="albumes">
            <div class="genero">
                <div class="cover"><img src="generoscover/new.png" alt="Generos nuevos"></div>
                <div class="info"><p>NEW</p></div>
            </div>

            <div class="genero">    
                <div class="cover"><img src="generoscover/alternative.png" alt="Genero rock"></div>
                <div class="info"><p>ALTERNATIVE / ROCK</p></div>   
            </div>

            <div class="genero">    
                <div class="cover"><img src="generoscover/pop.png" alt="Genero pop"></div>
                <div class="info"><p>POP</p></div>   
            </div>
        </section>

        <%@ include file = "/WEB-INF/fragments/footer.jspf" %>

        <script src="js/estilos.js"></script>
    </body>
</html>