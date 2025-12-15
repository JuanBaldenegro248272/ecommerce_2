<%-- 
    Document   : details
    Created on : Dec 15, 2025, 11:55:28 AM
    Author     : jrasc
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Detalle de producto | ECOstore</title>
        <link rel="stylesheet" href="styles/global.css">
        <link rel="stylesheet" href="styles/styles.css">
    </head>
    <body>

        <%@ include file="/WEB-INF/fragments/navbar.jspf" %>

        <main class="store-content">
            <section class="option-layout">
                <div class="option-grid">
                    <div class="product-media">
                        <img id="img-detalle"
                             src="icons/cdicon.png"
                             alt="Album cover">
                    </div>
                    <div class="product-info">
                        <h4 id="nombre-detalle">Cargando...</h4>
                        <p id="artista-detalle"></p>
                        <p id="precio-detalle" class="album-format"></p>
                        <section class="formats">
                            <h2>Formato</h2>
                            <h3>Elige el formato</h3>
                            <ul id="lista-formatos" class="product-list">
                            </ul>
                        </section>

                        <div class="qty">
                            <button type="button" class="qty-btn" id="btn-cantidad-menor">−</button>
                            <input type="number" id="cantidad" class="qty-input" value="1" min="1">
                            <button type="button" class="qty-btn" id="btn-cantidad-mayor">+</button>
                        </div>

                        <button id="btn-agregar-carrito" class="add-cart">
                            Add to cart
                        </button>
                    </div>

                </div>

                <section class="product-desc">
                    <h3>Descripción</h3>
                    <p id="desc-detalle">
                        Cargando información del producto...
                    </p>
                </section>
            </section>

            <section class="resenas-section" style="margin-top: 40px;">
                <h3>Reseñas</h3>

                <form id="form-resena">
                    <div class="form-group-resena">
                        <label for="calificacion">Calificación:</label>
                        <select id="calificacion" class="input-resena select-calificacion">
                            <option value="5">★★★★★ - Excelente</option>
                            <option value="4">★★★★☆ - Muy bueno</option>
                            <option value="3">★★★☆☆ - Bueno</option>
                            <option value="2">★★☆☆☆ - Regular</option>
                            <option value="1">★☆☆☆☆ - Malo</option>
                        </select>
                    </div>

                    <div class="form-group-resena">
                        <label for="comentario">Comentario:</label>
                        <textarea id="comentario" class="input-resena"
                                  placeholder="Cuéntanos qué te pareció este álbum"></textarea>
                    </div>

                    <button type="submit" class="btn-enviar-resena">
                        Enviar Reseña
                    </button>
                </form>
            </section>

            <section class="category">
                <h2>Te podría gustar...</h2>
                <div id="contenedor-recomendados" class="grid-albums">
                    <!-- Aquí luego puedes reutilizar tarjetas de store.js -->
                </div>
            </section>

            <section class="category">
                <h2>Related products</h2>
                <div id="contenedor-relacionados" class="grid-albums">
                </div>
            </section>

        </main>

        <%@ include file="/WEB-INF/fragments/footer.jspf" %>

        <script src="js/store.js"></script>
        <script src="js/detalle-producto.js"></script>

        <script>
            document.addEventListener('DOMContentLoaded', () => {
                const input = document.getElementById('cantidad');
                const btnMenor = document.getElementById('btn-cantidad-menor');
                const btnMayor = document.getElementById('btn-cantidad-mayor');

                if (btnMenor && btnMayor && input) {
                    btnMenor.addEventListener('click', () => {
                        const val = Math.max(1, parseInt(input.value || '1', 10) - 1);
                        input.value = val;
                    });
                    btnMayor.addEventListener('click', () => {
                        const val = Math.max(1, parseInt(input.value || '1', 10) + 1);
                        input.value = val;
                    });
                }
            });
        </script>

    </body>
</html>