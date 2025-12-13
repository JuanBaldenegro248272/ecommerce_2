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
        <title>ECOstore - pagina principal</title>
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
                <div class="album-grid">
                    <article class="album">
                        <a href="rumours-detalle.jsp" class="album-link">
                            <img src="${pageContext.request.contextPath}/albumcovers/rumours.png" alt="Rumours - Fleetwood Mac">
                            <h3>Rumours</h3>
                            <p>Fleetwood</p>
                        </a>
                        <button onclick="agregarAlCarrito(1)">ADD TO CART</button>
                    </article>
                    <article class="album">
                        <a href="nevermind-detalle.jsp" class="album-link">
                            <img src="${pageContext.request.contextPath}/albumcovers/nevermind.png" alt="Nevermind - Nirvana">
                            <h3>Nevermind</h3>
                            <p>Nirvana</p>
                        </a>
                        <button onclick="agregarAlCarrito(2)">ADD TO CART</button>
                    </article>
                    <article class="album">
                        <a href="b2b-detalle.jsp" class="album-link">
                            <img src="${pageContext.request.contextPath}/albumcovers/back2black.png" alt="Back To Black - Amy Winehouse">
                            <h3>Back To Black</h3>
                            <p>Amy Winehouse</p>
                        </a>
                        <button onclick="agregarAlCarrito(3)">ADD TO CART</button>
                    </article>
                    <article class="album">
                        <a href="shortnsweet-detalle.jsp" class="album-link">
                            <img src="${pageContext.request.contextPath}/albumcovers/shortnsweet.png" alt="Short n' Sweet - Sabrina Carpenter">
                            <h3>Short n' Sweet</h3>
                            <p>Sabrina Carpenter</p>
                        </a>
                        <button onclick="agregarAlCarrito(4)">ADD TO CART</button>
                    </article>
                    <article class="album">
                        <a href="folklore-detalle.html" class="album-link">
                            <img src="${pageContext.request.contextPath}/albumcovers/folklore.png" alt="folklore - Taylor Swift">
                            <h3>folklore</h3>
                            <p>Taylor Swift</p>
                        </a>
                        <button onclick="agregarAlCarrito(5)">ADD TO CART</button>
                    </article>
                </div>
            </section>
            
            <section class="seccion-generica">
                <div class="header-seccion">
                    <h2 class="titulo-seccion">Rock / Alternativo</h2>
                    <a href="#" class="enlace-ver-todo">| see all</a>
                </div>
                <div class="album-grid">
                    <article class="album">
                        <a href="am-detalle.html" class="album-link">
                            <img src="${pageContext.request.contextPath}/albumcovers/am.png" alt="AM - Arctic Monkeys">
                            <h3>AM</h3>
                            <p>Arctic Monkeys</p>
                        </a>
                        <button onclick="agregarAlCarrito(6)">ADD TO CART</button>
                    </article>
                    <article class="album">
                        <a href="turnbright-detalle.html" class="album-link">
                            <img src="${pageContext.request.contextPath}/albumcovers/turnbright.png" alt="Turn on the Bright Lights - Interpol">
                            <h3>Turn on the Bright Lights</h3>
                            <p>Interpol</p>
                        </a>
                        <button onclick="agregarAlCarrito(7)">ADD TO CART</button>
                    </article>
                    <article class="album">
                        <a href="wipedout-detalle.html" class="album-link">
                            <img src="${pageContext.request.contextPath}/albumcovers/wipedout.png" alt="Wiped Out! - The Neighbourhood">
                            <h3>Wiped Out!</h3>
                            <p>The Neighbourhood</p>
                        </a>
                        <button onclick="agregarAlCarrito(8)">ADD TO CART</button>
                    </article>
                    <article class="album">
                        <a href="thebends-detalle.html" class="album-link">
                            <img src="${pageContext.request.contextPath}/albumcovers/thebends.png" alt="the bends - Radiohead">
                            <h3>the bends</h3>
                            <p>Radiohead</p>
                        </a>
                        <button onclick="agregarAlCarrito(9)">ADD TO CART</button>
                    </article>
                    <article class="album">
                        <a href="souvlaki-detalle.html" class="album-link">
                            <img src="${pageContext.request.contextPath}/albumcovers/souvlaki.png" alt="Souvlaki - Slowdive">
                            <h3>Souvlaki</h3>
                            <p>Slowdive</p>
                        </a>
                        <button onclick="agregarAlCarrito(10)">ADD TO CART</button>
                    </article>                    
                </div>
            </section>

            <section class="seccion-generica">
                <div class="header-seccion">
                    <h2 class="titulo-seccion">Pop</h2>
                    <a href="#" class="enlace-ver-todo">| see all</a>
                </div>
                <div class="album-grid">
                    <article class="album">
                        <a href="guts-detalle.html" class="album-link">
                            <img src="${pageContext.request.contextPath}/albumcovers/guts.png" alt="Guts - Olivia Rodrigo">
                            <h3>Guts</h3>
                            <p>Olivia Rodrigo</p>
                        </a>
                        <button onclick="agregarAlCarrito(11)">ADD TO CART</button>
                    </article>
                    <article class="album">
                        <a href="soclosetowhat-detalle.html" class="album-link">
                            <img src="${pageContext.request.contextPath}/albumcovers/soclose.png" alt="So Close to What - Tate McRae">
                            <h3>So Close to What</h3>
                            <p>Tate McRae</p>
                        </a>
                        <button onclick="agregarAlCarrito(12)">ADD TO CART</button>
                    </article>
                    <article class="album">
                        <a href="mansbf-detalle.html" class="album-link">
                            <img src="${pageContext.request.contextPath}/albumcovers/mansbf.png" alt="Man’s Best Friend - Sabrina Carpenter">
                            <h3>Man’s Best Friend</h3>
                            <p>Sabrina Carpenter</p>
                        </a>
                        <button onclick="agregarAlCarrito(13)">ADD TO CART</button>
                    </article>
                    <article class="album">
                        <a href="brat-detalle.html" class="album-link">
                            <img src="${pageContext.request.contextPath}/albumcovers/brat.png" alt="brat - Charli xcx">
                            <h3>brat</h3>
                            <p>Charli xcx</p>
                        </a>
                        <button onclick="agregarAlCarrito(14)">ADD TO CART</button>
                    </article>
                    <article class="album">
                        <a href="thefame-detalle.html" class="album-link">
                            <img src="${pageContext.request.contextPath}/albumcovers/thefame.png" alt="The Fame - Lady Gaga">
                            <h3>The Fame</h3>
                            <p>Lady Gaga</p>
                        </a>
                        <button onclick="agregarAlCarrito(15)">ADD TO CART</button>
                    </article>    
                </div>
            </section>

            <section class='categorias'>
                <div class='cat-grid'>
                    <a href="#" class='cat'>
                        <img src="${pageContext.request.contextPath}/albumcovers/fondokpop.png" alt='Fondo kpop'>
                        <span class="genero">K-Pop</span>
                        <span class="cta">SHOP THIS</span>
                    </a>
                    <a href='#' class="cat">
                        <img src="${pageContext.request.contextPath}/albumcovers/fondorb.png" alt='Fondo R&B'>
                        <span class="genero">R&B</span>
                        <span class="cta">SHOP THIS</span>
                    </a>
                    <a href="#" class='cat'>
                        <img src="${pageContext.request.contextPath}/albumcovers/fondoreg.png" alt='Fondo Reggaeton'>
                        <span class="genero">Reggaeton</span>
                        <span class="cta">SHOP THIS</span>                        
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
                        <a href="${pageContext.request.contextPath}/carrito" class="btn-modal btn-view-cart">VER CARRITO</a>
                    </div>
                </div>
            </div>
        </div>

        <style>
            .modal-overlay { display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0,0,0,0.6); z-index: 9999; justify-content: center; align-items: center; }
            .modal-content { background: white; width: 400px; font-family: sans-serif; box-shadow: 0 10px 25px rgba(0,0,0,0.5); border-radius: 4px; overflow: hidden; }
            .modal-header { background-color: #5b9a8b; color: white; padding: 15px; text-align: center; font-weight: bold; }
            .modal-body { padding: 20px; text-align: center; }
            .modal-product-info { display: flex; align-items: center; gap: 15px; justify-content: center; margin-bottom: 20px; }
            .success-icon { color: #5b9a8b; font-size: 24px; border: 2px solid #5b9a8b; border-radius: 50%; padding: 5px; }
            .modal-actions { display: flex; justify-content: space-around; gap: 10px; }
            .btn-modal { padding: 10px 15px; border: none; cursor: pointer; font-weight: bold; text-decoration: none; color: white; }
            .btn-view-cart { background-color: #333; }
            .btn-continue { background-color: #eee; color: #333; }
        </style>

        <script>
            function agregarAlCarrito(idProducto) {
                // Mensaje en consola para verificar
                console.log("Enviando ID al servidor:", idProducto);
                
                // NOTA IMPORTANTE: Usamos la ruta base '/carrito' con método POST.
                // Esto es más seguro que '/carrito/agregar' para evitar errores de ruta en el Servlet.
                const url = '${pageContext.request.contextPath}/carrito';

                fetch(url, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                    body: 'idProducto=' + idProducto
                })
                .then(response => {
                    if (response.ok) {
                        document.getElementById('cartModal').style.display = 'flex';
                    } else {
                        // Si falla, intentamos leer el error
                        return response.text().then(text => { throw new Error(text) });
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert("Error de conexión: " + error.message);
                });
            }

            function closeModal() {
                document.getElementById('cartModal').style.display = 'none';
            }
            
            // Cerrar al dar clic fuera del modal
            window.onclick = function(event) {
                if (event.target == document.getElementById('cartModal')) {
                    closeModal();
                }
            }
        </script>
        
    </body>
</html>