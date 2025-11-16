<%-- 
    Document   : nuevoproducto
    Created on : Nov 15, 2025, 6:57:15 PM
    Author     : Dana Chavez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles/admin.css">
        <title>Nuevo Producto</title>
    </head>
    <body>
        <div class="app">
            <%@ include file = "/WEB-INF/fragments/sidebarAdmin.jspf" %>
            
            <main class="main">
                <div class="header-container">
                    <div class="back-btn">
                        <img src="icons/iconvolver.png" alt="icon" style="width:30px;height:30px;object-fit:contain;" />
                    </div>
                    <div>
                        <h2 class="page-title">Nuevo producto</h2>
                        <div class="page-sub">Agrega un nuevo producto al catálogo</div>
                    </div>
                </div>

                <section class="panel">
                    <form>
                        <div class="grid-2">
                            <div class="field">
                                <label>Nombre del Album *</label>
                                <input type="text" placeholder="Ej: Abbey Road" />
                            </div>
                            <div class="field">
                                <label>Artista *</label>
                                <input type="text" placeholder="Ej: The Beatles" />
                            </div>
                        </div>

                        <div class="grid-2">
                            <div class="field">
                                <label>Categoria *</label>
                                <select>
                                    <option>Seleccione categoría</option>
                                    <option>R&B</option>
                                    <option>Pop</option
                                    <option>Hip Hop</option>
                                    <option>Reggaeton</option>
                                    <option>Jazz</option>
                                </select>
                            </div>
                            <div class="field">
                                <label>Formato *</label>
                                <select>
                                    <option>Vinilo</option>
                                    <option>CD</option>
                                    <option>Cassette</option>
                                </select>
                            </div>
                        </div>

                        <div class="grid-2">
                            <div class="field">
                                <label>Precio ($) *</label>
                                <input type="number" value="0" />
                            </div>
                            <div class="field">
                                <label>Stock *</label>
                                <input type="number" value="0" />
                            </div>
                        </div>

                        <div class="grid-2">
                            <div class="field">
                                <label>Año de Lanzamiento *</label>
                                <input type="number" value="2025" />
                            </div>
                            <div class="field">
                                <label>Estado *</label>
                                <select>
                                    <option>Activo</option>
                                    <option>Inactivo</option>
                                </select>
                            </div>
                        </div>

                        <div class="field">
                            <label>URL de la Imagen *</label>
                            <input type="text" placeholder="https://example.com/image.jpg" />
                        </div>

                        <div class="field">
                            <label>Descripción *</label>
                            <textarea placeholder="Describe el producto..."></textarea>
                        </div>

                        <div class="actions">
                            <button type="button" class="btn primary">Crear Producto</button>
                            <button type="button" class="btn">Cancelar</button>
                        </div>
                    </form>
                </section>
            </main>
        </div>
    </body>
</html>