<%-- 
    Document   : formato
    Created on : Nov 7, 2025, 12:01:19 AM
    Author     : Dana Chavez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>ECOstore | Formatos</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="styles/formatocss.css">
        <link rel="stylesheet" href="styles/styles.css">
        <style>
            .formats li {
                cursor: pointer;
            }
            .formats li:hover {
                color: #555;
                font-weight: bold;
            }
        </style>
    </head>

    <body>

        <%@ include file = "/WEB-INF/fragments/navbar.jspf" %>

        <main class="store-layout">
            <aside class="sidebar">
                <div class="search-box">
                    <input type="text" id="inputBusqueda" placeholder="Buscar álbum o artista..." onkeyup="buscarProductos()" />
                    <button onclick="buscarProductos()"><img src="icons/searchicon.png"></button>
                </div>

                <h3>Organizar por:</h3>

                <div style="margin-bottom: 10px; cursor: pointer;" onclick="filtrarProductos('todos')">
                    <strong>Ver Todo</strong>
                </div>

                <h3>Formato</h3>

                <ul class="formats">
                    <li onclick="filtrarProductos('Vinilo')">
                        <div class="format">
                            <img src="icons/vinylicon.png"> Vinyl
                        </div>
                        <ul class="subformats">
                            <li>LP</li>
                        </ul>
                    </li>

                    <li onclick="filtrarProductos('CD')">
                        <div class="format">
                            <img src="icons/cdicon.png"> Disk (CD)
                        </div>
                    </li>

                    <li onclick="filtrarProductos('Cassette')">
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
                <div id="contenedorProductos" class="grid-albums">
                    <p style="text-align: center; width: 100%;">Cargando productos...</p>
                </div>
            </section>
        </main>

        <%@ include file = "/WEB-INF/fragments/footer.jspf" %>

        <div id="cartModal" class="modal" style="display:none; position:fixed; z-index: 1000; top:0; left:0; width:100%; height:100%; background:rgba(0,0,0,0.5); justify-content:center; align-items:center;">
            <div style="background:white; padding:20px; border-radius:5px; text-align:center; min-width: 300px;">
                <h3 style="margin-top:0;">¡Producto agregado!</h3>
                <p>El artículo se añadió a tu carrito.</p>
                <div style="margin-top:15px;">
                    <button onclick="closeModal()" style="padding: 8px 15px; margin-right: 10px; cursor: pointer;">Seguir comprando</button>
                    <a href="carrito.jsp"><button style="padding: 8px 15px; background: #000; color: white; border: none; cursor: pointer;">Ir al carrito</button></a>
                </div>
            </div>
        </div>

        <script src="js/store.js"></script>

        <script src="js/formato.js"></script>

    </body>

</html>