<%-- 
    Document   : about
    Created on : Nov 6, 2025, 10:43:53 PM
    Author     : Dana Chavez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/styles.css">
        <title>About Us - ECOstore</title>
    </head>
    <body>
        
        <%@ include file = "/WEB-INF/fragments/navbar.jspf" %>

        <main class="about-page">
             <nav class="sub-nav">
                 <div class="sub-nav-item">
                     <img src="icons/vinylicon.png" alt="">
                     <a href="#">VINYL</a>
                 </div>
                 <div class="sub-nav-item">
                     <img src="icons/cdicon.png" alt="">
                     <a href="#">CD</a>
                 </div>
                 <div class="sub-nav-item">
                     <img src="icons/casseteicon.png" alt="">
                     <a href="#">CASSETTE</a>
                 </div>
             </nav>

            <section class="hero">
                <img src="icons/musicpic.png" alt="Tocadiscos" class="hero-image">
            </section>

            <section class="history">
                <h2>Nuestra historia</h2>
                <p>ECOstore nacio por el amor a la música y el formato físico. Desde nuestros inicios hemos trabajado para ofrecer discos auténticos y de alta calidad que enciende la pasión por la música en cada uno de nuestros clientes.</p>
            </section>

            <section class="why-us">
                <h2>¿POR QUÉ ELEGIRNOS?</h2>
                <div class="features-grid">
                    <div class="feature-item">
                        <img src="icons/cdicon.png" alt="" class="feature-icon">
                        <p>
                            <span class="text-pink">DISCOS ORIGINALES</span><br>
                            <span class="text-pink">Y EDICIONES</span><br>
                            <span class="text-pink">LIMITADAS</span>
                        </p>
                    </div>
                    <div class="feature-item">
                        <img src="icons/leaficon.png" alt="" class="feature-icon">
                        <p>
                            <span class="text-pink">EMPAQUES</span><br>
                            <span class="text-pink">ECOLÓGICOS</span>
                        </p>
                    </div>
                    <div class="feature-item">
                        <img src="icons/micicon.png" alt="" class="feature-icon">
                        <p>
                            <span class="text-pink">PROMOVEMOS</span><br>
                            <span class="text-pink">ARTISTAS</span><br>
                            <span class="text-pink">INDEPENDIENTES</span>
                        </p>
                    </div>
                </div>
            </section>

            <section class="reviews">
                <h2>RESEÑAS</h2>
                <div class="reviews-grid">
                    <div class="review-item">
                        <img src="icons/accounticon.png" alt="Usuario" class="review-user-icon">
                        <div class="stars">★★★★☆</div>
                        <p>Me encantó el empaque y la calidad del sonido, excelente servicio.</p>
                    </div>
                    <div class="review-item">
                        <img src="icons/accounticon.png" alt="Usuario" class="review-user-icon">
                        <div class="stars">★★★★★</div>
                        <p>Excelente servicio, de muy buena calidad y llego muy rápido.</p>
                    </div>
                    <div class="review-item">
                        <img src="icons/accounticon.png" alt="Usuario" class="review-user-icon">
                        <div class="stars">★★★☆☆</div>
                        <p>Tardó algo en llegar pero la calidad es excelente.</p>
                    </div>
                </div>
            </section>

            <section class="community">
                <h3>UNETE A NUESTRA COMUNIDAD MELÓMANA</h3>
            </section>
        </main>

        <%@ include file = "/WEB-INF/fragments/footer.jspf" %>
        
    </body>
</html>
