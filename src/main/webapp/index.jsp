<%-- 
    Document   : index
    Created on : Nov 6, 2025, 10:32:20 PM
    Author     : Dana Chavez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styles.css">
        <title>ECOstore - Pagina Principal</title>
    </head>
    <body>

        <%@ include file="/WEB-INF/fragments/navbar.jspf" %>

        <main>
            <section class="highlight">
                <img src="${pageContext.request.contextPath}/icons/highlight.png" alt="Banner Tercer Sol">
                <div class="highlight-text">
                    <h2>TERCER SOL</h2>
                    <h4>"La ciudad reflejada" LP</h4>
                    <p>Edición limitada</p>
                </div>
            </section>

            <section class="seccion-generica">
                <div class="header-seccion">
                    <h2 class='titulo-seccion'>Recomendados</h2>
                    <div class="linea-divisora"></div>
                </div>
                <div class="album-grid" id="container-recommended">
                    <p>Cargando recomendados...</p>
                </div>
            </section>

            <section class="seccion-generica">
                <div class="header-seccion">
                    <h2 class="titulo-seccion">Rock / Alternativo</h2>
                    <a href="store.jsp" class="enlace-ver-todo">| see all</a>
                </div>
                <div class="album-grid" id="container-rock">
                    <p>Cargando...</p>
                </div>
            </section>

            <section class="seccion-generica">
                <div class="header-seccion">
                    <h2 class="titulo-seccion">Pop</h2>
                    <a href="store.jsp" class="enlace-ver-todo">| see all</a>
                </div>
                <div class="album-grid" id="container-pop">
                    <p>Cargando...</p>
                </div>
            </section>

            <section class='categorias'>
                <div class='cat-grid'>
                    <a href="store.jsp" class='cat'>
                        <img src="${pageContext.request.contextPath}/albumcovers/fondokpop.png" alt='Fondo kpop'>
                        <span class="genero">K-Pop</span><span class="cta">SHOP THIS</span>
                    </a>
                    <a href='store.jsp' class="cat">
                        <img src="${pageContext.request.contextPath}/albumcovers/fondorb.png" alt='Fondo R&B'>
                        <span class="genero">R&B</span><span class="cta">SHOP THIS</span>
                    </a>
                    <a href="store.jsp" class='cat'>
                        <img src="${pageContext.request.contextPath}/albumcovers/fondoreg.png" alt='Fondo Reggaeton'>
                        <span class="genero">Reggaeton</span><span class="cta">SHOP THIS</span>                        
                    </a>
                </div>
            </section>

            <section class="shipping-offer">
                <div class="shipping-offer-container">
                    <div class="shipping-info">
                        <div class="shipping-main">
                            <h3>ENVIO GRATUITO</h3>
                            <p>POR COMPRAS SUPERIORES A $60</p>
                        </div>
                        <div class="shipping-details">
                            <p>EN PRODUCTOS ECO DISC</p>
                        </div>
                        <div class="special-offer">
                            <h3>OFERTA ESPECIAL</h3>
                        </div>
                    </div>
                </div>
            </section>

            <section class="additional-info">
                <div class="add-grid">
                    <a href="about.jsp" class='add'>
                        <img src="${pageContext.request.contextPath}/icons/iconaboutus.png" alt='Fondo about us'>
                        <span>ABOUT US</span>
                    </a>
                    <a href='contact.jsp' class="add">
                        <img src="${pageContext.request.contextPath}/icons/iconcontact.png" alt='Fondo contact us'>
                        <span>CONTACT US</span>
                    </a>
                </div>
            </section>
        </main>

        <%@ include file = "/WEB-INF/fragments/footer.jspf" %>

        <div id="cartModal" class="modal-overlay">
            <div class="modal-content">
                <div class="modal-header">Producto Agregado</div>
                <div class="modal-body">
                    <div class="modal-product-info">
                        <span class="success-icon">✔</span>
                        <div>
                            <strong>¡Éxito!</strong><br>
                            <small>El producto se añadió al carrito.</small>
                        </div>
                    </div>
                    <div class="modal-actions">
                        <button class="btn-modal btn-continue" onclick="closeModal()">SEGUIR COMPRANDO</button>
                        <a href="${pageContext.request.contextPath}/carrito.jsp" class="btn-modal btn-view-cart">VER CARRITO</a>
                    </div>
                </div>
            </div>
        </div>

        <script src="js/store.js"></script>

    </body>
</html>