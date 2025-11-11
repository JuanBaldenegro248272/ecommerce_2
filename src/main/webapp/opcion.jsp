<%-- 
    Document   : opcion
    Created on : Nov 7, 2025, 12:11:40 AM
    Author     : Dana Chavez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>ECOstore</title>
        <meta charset="UTF-8">
        <link href="https://fonts.googleapis.com/css2?family=Staatliches&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="styles/formatocss.css">
        <link rel="stylesheet" href="styles/styles.css">
    </head>

    <body>
        
        <%@ include file = "/WEB-INF/fragments/navbar.jspf" %>

        <main class="option-layout">
            <div class="option-grid">
                <div class="product-media">
                    <img src="albumcovers/shortnsweet.png">
                </div>
                <div class="product-info">
                    <h4>Short n’ Sweet</h4>
                    <p>Sabrina Carpenter</p>
                    <div class="formats">
                        <h2>Formato</h2>
                        <h3>Elige el formato</h3>
                        <ul class="product-list">
                            <li>
                                <label class="product-row">
                                    <input type="radio" name="product">
                                    <img src="icons/vinylicon.png" class="product-icon">
                                    <span class="product-name">Translucent Red</span>
                                    <span class="product-type">LP</span>
                                    <span class="product-price">$700.00</span>
                                    <span class="product-cur">MXN</span>
                                </label>
                            </li>
                            <li>
                                <label class="product-row">
                                    <input type="radio" name="product">
                                    <img src="icons/vinylicon.png" class="product-icon">
                                    <span class="product-name">Picture Disc</span>
                                    <span class="product-type">LP</span>
                                    <span class="product-price">$1200.00</span>
                                    <span class="product-cur">MXN</span>
                                </label>
                            </li>
                            <li>
                                <label class="product-row">
                                    <input type="radio" name="product">
                                    <img src="icons/vinylicon.png" class="product-icon">
                                    <span class="product-name">Crystal Clear</span>
                                    <span class="product-type">LP</span>
                                    <span class="product-price">$750.00</span>
                                    <span class="product-cur">MXN</span>
                                </label>
                            </li>
                        </ul>
                        <div class="qty">
                            <button type="button" class="qty-btn">−</button>
                            <input type="number" class="qty-input" value="1" min="1">
                            <button type="button" class="qty-btn">+</button>
                        </div>
                        <button class="add-cart">Anadir al carrito</button>
                    </div>
                </div>
                <div class="product-share">
                    <h3 class="share-title">Compartir</h3>
                    <div class="share-icons">
                        <a href="#"><img src="icons/facebookBlack.png"></a>
                        <a href="#"><img src="icons/mailicon.png"></a>
                        <a href="#"><img src="icons/chainLink.png"></a>
                    </div>
                </div>
                <div class="product-desc">
                    <h3 class="share-title">Descripcion</h3>
                    <p>
                        Un álbum pop vibrante y directo que captura la visión de Sabrina Carpenter sobre el amor moderno.
                        Con
                        producción de alto nivel y letras que combinan diversión y sinceridad, “Short n’ Sweet” es una
                        celebración de momentos intensos, relaciones breves y una nueva etapa creativa en la carrera de la
                        artista
                    </p>
                </div>
            </div>

            <section class="recommendations">
                <h3 class="section-heading">Te podria gustar…</h3>
                <div class="store-content">
                    <div class="grid-albums">
                        <article class="album-card">
                            <a class="album-media"><img src="albumcovers/guts.png"></a>
                            <div class="album-meta">
                                <h4 class="album-title">Guts</h4>
                                <p>Olivia Rodrigo</p>
                                <p class="album-format"><img src="icons/vinylicon.png" class="icon-solid"> LP</p>
                            </div>
                            <div class="album-actions">
                                <button class="btn btn-outline">ADD TO CART</button>
                            </div>
                        </article>

                        <article class="album-card">
                            <a class="album-media"><img src="albumcovers/soclose.png"></a>
                            <div class="album-meta">
                                <h4 class="album-title">So Close to What</h4>
                                <p>Tate McRae</p>
                                <p class="album-format"><img src="icons/vinylicon.png" class="icon-solid"> LP</p>
                                <p class="album-format"><img src="icons/vinylicon.png" class="icon-solid"> LP Colour</p>
                            </div>
                            <div class="album-actions">
                                <a class="btn btn-solid">SELECCIONAR OPCIÓN</a>
                            </div>
                        </article>

                        <article class="album-card">
                            <a class="album-media"><img src="albumcovers/mansbf.png"></a>
                            <div class="album-meta">
                                <h4 class="album-title">Man's Best Friend</h4>
                                <p>Sabrina Carpenter</p>
                                <p class="album-format"><img src="icons/vinylicon.png" class="icon-solid"> LP</p>
                            </div>
                            <div class="album-actions">
                                <button class="btn btn-outline">ADD TO CART</button>
                            </div>
                        </article>

                        <article class="album-card">
                            <a class="album-media"><img src="albumcovers/brat.png"></a>
                            <div class="album-meta">
                                <h4 class="album-title">Brat</h4>
                                <p>Charli XCX</p>
                                <p class="album-format"><img src="icons/vinylicon.png" class="icon-solid"> LP Colour</p>
                            </div>
                            <div class="album-actions">
                                <a class="btn btn-solid">SELECCIONAR OPCIÓN</a>
                            </div>
                        </article>
                    </div>
                </div>
            </section>

            <section class="related">
                <h3 class="section-heading">Related products</h3>
                <div class="store-content">
                    <div class="grid-albums">
                        <article class="album-card">
                            <a class="album-media"><img src="albumcovers/folklore.png"></a>
                            <div class="album-meta">
                                <h4 class="album-title">folklore</h4>
                                <p>Taylor Swift</p>
                                <p class="album-format"><img src="icons/vinylicon.png" class="icon-solid"> LP</p>
                            </div>
                            <div class="album-actions">
                                <button class="btn btn-outline">ADD TO CART</button>
                            </div>
                        </article>

                        <article class="album-card">
                            <a class="album-media"><img src="albumcovers/mansbf.png"></a>
                            <div class="album-meta">
                                <h4 class="album-title">Man's Best Friend</h4>
                                <p>Sabrina Carpenter</p>
                                <p class="album-format"><img src="icons/vinylicon.png" class="icon-solid"> LP</p>
                            </div>
                            <div class="album-actions">
                                <button class="btn btn-outline">ADD TO CART</button>
                            </div>
                        </article>

                        <article class="album-card">
                            <a class="album-media"><img src="albumcovers/taylor.png"></a>
                            <div class="album-meta">
                                <h4 class="album-title">1989 (Taylor’s Version)</h4>
                                <p>Taylor Swift</p>
                                <p class="album-format"><img src="icons/vinylicon.png" class="icon-solid"> LP</p>
                            </div>
                            <div class="album-actions">
                                <button class="btn btn-outline">ADD TO CART</button>
                            </div>
                        </article>

                        <article class="album-card">
                            <a class="album-media"><img src="albumcovers/weeknd.png"></a>
                            <div class="album-meta">
                                <h4 class="album-title">Starboy</h4>
                                <p>The Weeknd</p>
                                <p class="album-format"><img src="icons/vinylicon.png" class="icon-solid"> LP</p>
                            </div>
                            <div class="album-actions">
                                <button class="btn btn-outline">ADD TO CART</button>
                            </div>
                        </article>
                    </div>
                </div>
            </section>
        </main>

        <%@ include file = "/WEB-INF/fragments/footer.jspf" %>
        
    </body>

</html>