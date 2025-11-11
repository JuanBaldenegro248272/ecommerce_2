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
        <title>ECOstore - pagina generos</title>
    </head>
    <body>

        <%@ include file = "/WEB-INF/fragments/navbar.jspf" %>
        
        <section class="albumes">
            <div class="genero">
              <div class="cover">
                <img src="generoscover/new.png" alt="Generos nuevos">
              </div>
              <div class="info">
                <p>NEW</p>
              </div>
            </div>
            <div class="genero">    
                <div class="cover">
                    <img src="generoscover/alternative.png" alt="Genero alternative / rock">
                </div>
                <div class="info">
                    <p>ALTERNATIVE / ROCK</p>
                </div>   
            </div>
            <div class="genero">    
                <div class="cover">
                    <img src="generoscover/pop.png" alt="Genero pop">
                </div>
                <div class="info">
                    <p>POP</p>
                </div>   
            </div>
            <div class="genero">    
                <div class="cover">
                    <img src="generoscover/indie.png" alt="Genero indie">
                </div>
                <div class="info">
                    <p>INDIE</p>
                </div>   
            </div>
            <div class="genero">    
                <div class="cover">
                    <img src="generoscover/electronic.png" alt="Genero electronic">
                </div>
                <div class="info">
                    <p>ELECTRONIC</p>
                </div>   
            </div>
            <div class="genero">    
                <div class="cover">
                    <img src="generoscover/rb.png" alt="Genero R&B">
                </div>
                <div class="info">
                    <p>R&B</p>
                </div>   
            </div>
            <div class="genero">    
                <div class="cover">
                    <img src="generoscover/hiphop.png" alt="Genero hip hop">
                </div>
                <div class="info">
                    <p>HIP HOP</p>
                </div>   
            </div>
            <div class="genero">    
                <div class="cover">
                    <img src="generoscover/reggaeton.png" alt="Genero reggaeton">
                </div>
                <div class="info">
                    <p>REGGAETON</p>
                </div>   
            </div>
            <div class="genero">    
                <div class="cover">
                    <img src="generoscover/jazz.png" alt="Genero jazz">
                </div>
                <div class="info">
                    <p>JAZZ</p>
                </div>   
            </div>
            <div class="genero">    
                <div class="cover">
                    <img src="generoscover/classical.png" alt="Genero classical">
                </div>
                <div class="info">
                    <p>CLASSICAL</p>
                </div>   
            </div>
            <div class="genero">    
                <div class="cover">
                    <img src="generoscover/chillout.png" alt="Genero chill out">
                </div>
                <div class="info">
                    <p>CHILL OUT</p>
                </div>   
            </div>
            <div class="genero">    
                <div class="cover">
                    <img src="generoscover/classicrock.png" alt="Genero classicrock">
                </div>
                <div class="info">
                    <p>CLASSIC ROCK</p>
                </div>   
            </div>
        </section>
        
        <%@ include file = "/WEB-INF/fragments/footer.jspf" %>
        
    </body>
</html>

