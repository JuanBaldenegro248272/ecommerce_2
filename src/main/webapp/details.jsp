<%-- 
    Document   : details
    Created on : Dec 15, 2025, 11:55:28 AM
    Author     : jrasc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ECOstore - Detalle</title>
        <link rel="stylesheet" href="styles/albumes.css">
    </head>
    <body>
        <div class="container">
            <div class="main-grid">

                <div class="info-panel">
                    <a href="index.jsp" class="logo">
                        <img src="icons/logoeco.png" alt="logo ECOstore"/>
                        <span class="titulo">
                            <span class="resaltado">ECO</span>store
                        </span>
                    </a>
                    <div class="vinyl-icon">
                        <img id="img-detalle" src="" alt="Album cover"/>
                    </div>

                    <h1 id="nombre-detalle" class="album-title">Cargando...</h1>
                    <p id="artista-detalle" class="artist-name"></p>

                    <div class="options-list">
                        <a href="#" class="option active">Detalles</a>
                    </div>

                    <button id="btn-agregar-carrito" class="add-to-cart-btn">AGREGAR AL CARRITO</button>
                    <p id="precio-detalle" style="color: white; margin-top: 10px; font-size: 1.2em;"></p>
                </div>

                <div class="content-panel details-panel">

                    <div class="description">
                        <h3>Descripción</h3>
                        <p id="desc-detalle">Cargando información del producto...</p>
                    </div>

                    <div class="resenas-section">
                        <h3>Dejar una Reseña</h3>

                        <div class="form-group-resena">
                            <label for="sel-calificacion">Calificación:</label>
                            <select id="sel-calificacion" class="input-resena select-calificacion">
                                <option value="5">★★★★★ - Excelente</option>
                                <option value="4">★★★★☆ - Muy bueno</option>
                                <option value="3">★★★☆☆ - Bueno</option>
                                <option value="2">★★☆☆☆ - Regular</option>
                                <option value="1">★☆☆☆☆ - Malo</option>
                            </select>
                        </div>

                        <div class="form-group-resena">
                            <label for="txt-comentario">Comentario:</label>
                            <textarea id="txt-comentario" class="input-resena" rows="4" placeholder="Cuéntanos qué te pareció este álbum"></textarea>
                        </div>

                        <button onclick="enviarResena()" class="btn-enviar-resena">
                            Enviar Reseña
                        </button>
                    </div>

                </div>
            </div>
        </div>

        <script src="js/detalle-producto.js"></script>
    </body>
</html>