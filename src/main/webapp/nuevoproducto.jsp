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
                    <form action="${pageContext.request.contextPath}/admin/productos/nuevo" method="post">
                        <div class="field">
                            <label>Album *</label>
                            <button type="button" class="select-album-btn">
                                <img src="icons/search.png" alt="buscar" style="width:16px;height:16px;" />
                                Seleccionar Album
                            </button>
                            <input type="hidden" name="albumId" required />
                        </div>

                        <div class="grid-2">
                            <div class="field">
                                <label>Formato *</label>
                                <select name="formato" required>
                                    <option value="VINILO">Vinilo</option>
                                    <option value="CD">CD</option>
                                    <option value="CASSETTE">Cassette</option>
                                </select>
                            </div>
                            <div class="field">
                                <label>Stock *</label>
                                <input type="number" name="stock" value="0" min="0" required />
                            </div>
                        </div>

                        <div class="grid-2">
                            <div class="field">
                                <label>Precio ($) *</label>
                                <input type="number" name="precio" value="0" step="0.01" min="0" required />
                            </div>
                            <div class="field">
                                <label>Estado *</label>
                                <select name="esDisponible" required>
                                    <option value="true">Activo</option>
                                    <option value="false">Inactivo</option>
                                </select>
                            </div>
                        </div>

                        <div class="field">
                            <label>Descripción *</label>
                            <textarea name="descripcion" placeholder="Describe el producto..." required></textarea>
                        </div>

                        <div class="actions">
                            <button type="submit" class="btn primary">Crear Producto</button>
                            <a href="${pageContext.request.contextPath}/admin/productos" class="btn">Cancelar</a>
                        </div>
                    </form>
                </section>

            </main>
        </div>
    </body>
</html>