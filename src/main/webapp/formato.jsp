<%-- 
    Document   : formato
    Created on : Nov 7, 2025, 12:01:19 AM
    Author     : Dana Chavez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>ECOstore</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="styles/formatocss.css">
        <link rel="stylesheet" href="styles/styles.css">
    </head>

        <%@ include file = "/WEB-INF/fragments/navbar.jspf" %>

        <main class="store-layout">
            <aside class="sidebar">
                <div class="search-box">
                    <input type="text" placeholder="Buscar" />
                    <button><img src="icons/searchicon.png"></button>
                </div>

                <h3>Organizar por:</h3>
                <h3>Formato</h3>

                <ul class="formats">
                    <li>
                        <div class="format">
                            <img src="icons/vinylicon.png"> Vinyl
                        </div>
                        <ul class="subformats">
                            <li>LP</li>
                        </ul>
                    </li>
                    <li>
                        <div class="format">
                            <img src="icons/cdicon.png"> Disk
                        </div>
                    </li>
                    <li>
                        <div class="format">
                            <img src="icons/casseteicon.png"> Cassette
                        </div>
                    </li>
                </ul>

                <div class="info-card dark">
                    <h4>Vinilo de color</h4>
                    <p>Se usa para crear decoraciones y diseños llamativos en distintas superficies.</p>
                </div>

                <div class="info-card teal">
                    <h4>Envío gratis!</h4>
                    <p>Para compras mayores a<br>$800.00 MXN</p>
                    <img src="icons/truck.png" />
                </div>
            </aside>

            <section class="store-content">
                <div class="grid-albums">
                    <article class="album-card">
                        <a class="album-media">
                            <img src="albumcovers/rumours.png">
                        </a>
                        <div class="album-meta">
                            <h4 class="album-title">Rumours</h4>
                            <p>Fleetwood Mac</p>
                            <p class="album-format">
                                <img src="icons/vinylicon.png" class="icon-solid"> LP
                            </p>
                        </div>
                        <div class="album-actions">
                            <button class="btn btn-outline">AÑADIR AL CARRITO</button>
                        </div>
                    </article>

                    <article class="album-card">
                        <a class="album-media">
                            <img src="albumcovers/nevermind.png">
                        </a>
                        <div class="album-meta">
                            <h4 class="album-title">Nevermind</h4>
                            <p>Nirvana</p>
                            <p class="album-format">
                                <img src="icons/vinylicon.png" class="icon-solid"> LP
                            </p>
                            <p class="album-format">
                                <img src="icons/vinylicon.png" class="icon-solid"> Colour
                            </p>
                        </div>
                        <div class="album-actions">
                            <a class="btn btn-solid">SELECCIONAR OPCION</a>
                        </div>
                    </article>

                    <article class="album-card">
                        <a class="album-media">
                            <img src="albumcovers/back2black.png">
                        </a>
                        <div class="album-meta">
                            <h4 class="album-title">Back to Black</h4>
                            <p>Amy Winehouse</p>
                            <p class="album-format">
                                <img src="icons/vinylicon.png" class="icon-solid"> LP
                            </p>
                        </div>
                        <div class="album-actions">
                            <button class="btn btn-outline">AÑADIR AL CARRITO</button>
                        </div>
                    </article>

                    <article class="album-card">
                        <a href="product-short-n-sweet.html" class="album-media">
                            <img src="albumcovers/shortnsweet.png">
                        </a>
                        <div class="album-meta">
                            <h4 class="album-title">Short n’ Sweet</h4>
                            <p>Sabrina Carpenter</p>
                            <p class="album-format">
                                <img src="icons/vinylicon.png" class="icon-solid"> LP
                            </p>
                            <p class="album-format">
                                <img src="icons/vinylicon.png" class="icon-solid"> Colour
                            </p>
                        </div>
                        <div class="album-actions">
                            <a href="product-short-n-sweet.html" class="btn btn-solid">SELECCIONAR OPCION</a>
                        </div>
                    </article>

                    <article class="album-card">
                        <a class="album-media">
                            <img src="albumcovers/am.png">
                        </a>
                        <div class="album-meta">
                            <h4 class="album-title">AM</h4>
                            <p>Arctic Monkeys</p>
                            <p class="album-format">
                                <img src="icons/vinylicon.png" class="icon-solid"> LP
                            </p>
                        </div>
                        <div class="album-actions">
                            <button class="btn btn-outline">AÑADIR AL CARRITO</button>
                        </div>
                    </article>

                    <article class="album-card">
                        <a class="album-media">
                            <img src="albumcovers/turnbright.png">
                        </a>
                        <div class="album-meta">
                            <h4 class="album-title">Turn on the Bright Lights</h4>
                            <p>Interpool</p>
                            <p class="album-format">
                                <img src="icons/vinylicon.png" class="icon-solid"> LP
                            </p>
                        </div>
                        <div class="album-actions">
                            <a class="btn btn-solid">SELECCIONAR OPCION</a>
                        </div>
                    </article>

                    <article class="album-card">
                        <a class="album-media">
                            <img src="albumcovers/wipedout.png">
                        </a>
                        <div class="album-meta">
                            <h4 class="album-title">Wiped Out!</h4>
                            <p>The Neightbourhood</p>
                            <p class="album-format">
                                <img src="icons/vinylicon.png" class="icon-solid"> LP
                            </p>
                        </div>
                        <div class="album-actions">
                            <button class="btn btn-outline">AÑADIR AL CARRITO</button>
                        </div>
                    </article>

                    <article class="album-card">
                        <a class="album-media">
                            <img src="albumcovers/thebends.png">
                        </a>
                        <div class="album-meta">
                            <h4 class="album-title">The Bends</h4>
                            <p>Radiohead</p>
                            <p class="album-format">
                                <img src="icons/vinylicon.png" class="icon-solid"> LP
                            </p>
                            <p class="album-format">
                                <img src="icons/vinylicon.png" class="icon-solid"> Colour
                            </p>
                        </div>
                        <div class="album-actions">
                            <a class="btn btn-solid">SELECCIONAR OPCION</a>
                        </div>
                    </article>

                    <article class="album-card">
                        <a class="album-media">
                            <img src="albumcovers/guts.png">
                        </a>
                        <div class="album-meta">
                            <h4 class="album-title">Guts</h4>
                            <p>Olivia Rodrigo</p>
                            <p class="album-format">
                                <img src="icons/vinylicon.png" class="icon-solid"> LP
                            </p>
                            <p class="album-format">
                                <img src="icons/vinylicon.png" class="icon-solid"> Colour
                            </p>
                        </div>
                        <div class="album-actions">
                            <button class="btn btn-outline">AÑADIR AL CARRITO</button>
                        </div>
                    </article>

                    <article class="album-card">
                        <a class="album-media">
                            <img src="albumcovers/soclose.png">
                        </a>
                        <div class="album-meta">
                            <h4 class="album-title">So Close to What</h4>
                            <p>Taylor Hale</p>
                            <p class="album-format">
                                <img src="icons/vinylicon.png" class="icon-solid"> LP
                            </p>
                            <p class="album-format">
                                <img src="icons/vinylicon.png" class="icon-solid"> Colour
                            </p>
                        </div>
                        <div class="album-actions">
                            <a class="btn btn-solid">SELECCIONAR OPCION</a>
                        </div>
                    </article>

                    <article class="album-card">
                        <a class="album-media">
                            <img src="albumcovers/mansbf.png">
                        </a>
                        <div class="album-meta">
                            <h4 class="album-title">Man's Best Friend</h4>
                            <p>Sabrina Carpenter</p>
                            <p class="album-format">
                                <img src="icons/vinylicon.png" class="icon-solid"> LP
                            </p>
                        </div>
                        <div class="album-actions">
                            <button class="btn btn-outline">AÑADIR AL CARRITO</button>
                        </div>
                    </article>

                    <article class="album-card">
                        <a class="album-media">
                            <img src="albumcovers/brat.png">
                        </a>
                        <div class="album-meta">
                            <h4 class="album-title">Brat</h4>
                            <p>Charli XCX</p>
                            <p class="album-format">
                                <img src="icons/vinylicon.png" class="icon-solid"> LP
                            </p>
                            <p class="album-format">
                                <img src="icons/vinylicon.png" class="icon-solid"> Colour
                            </p>
                        </div>
                        <div class="album-actions">
                            <a class="btn btn-solid">SELECCIONAR OPCION</a>
                        </div>
                    </article>
                </div>
            </section>
        </main>
        
        <%@ include file = "/WEB-INF/fragments/footer.jspf" %>
        
    </body>

</html>
