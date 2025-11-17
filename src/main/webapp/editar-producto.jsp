<%-- 
    Document   : editar-producto
    Created on : Nov 16, 2025, 6:16:32 PM
    Author     : Dana Chavez
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/admin.css">
        <title>Editar Producto - ECOstore</title>
    </head>
    <body>
        <div class="app">
            <%@ include file="/WEB-INF/fragments/sidebarAdmin.jspf" %>

            <main class="main">
                <div class="header-container">
                    <div class="back-btn">
                        <img src="${pageContext.request.contextPath}/icons/iconvolver.png" 
                             alt="Volver" 
                             style="width:30px;height:30px;object-fit:contain;cursor:pointer;" 
                             onclick="location.href = '${pageContext.request.contextPath}/admin/productos'" />
                    </div>
                    <div>
                        <h2 class="page-title">Editar producto</h2>
                        <div class="page-sub">Modifica la información del producto</div>
                    </div>
                </div>

                <c:if test="${not empty error}">
                    <div id="alerta" class="alert error">
                        ${error}
                    </div>
                </c:if>

                <section class="panel">
                    <form action="${pageContext.request.contextPath}/admin/productos/editar" method="post">
                        <input type="hidden" name="id" value="${producto.id}" />
                        
                        <div class="field">
                            <label>Álbum <span class="album-badge">No editable</span></label>
                            <div class="album-info">
                                <img src="${producto.albumImagenUrl}" 
                                     alt="${producto.albumNombre}"
                                     onerror="this.src='${pageContext.request.contextPath}/images/no-image.png'">
                                <div class="album-details">
                                    <h4>${producto.albumNombre}</h4>
                                    <p>por ${producto.artistaNombre}</p>
                                </div>
                            </div>
                        </div>

                        <div class="grid-2">
                            <div class="field">
                                <label>Formato *</label>
                                <select name="formato" required>
                                    <option value="VINYL" ${producto.formato == 'VINYL' ? 'selected' : ''}>Vinilo</option>
                                    <option value="VINYL_COLOR" ${producto.formato == 'VINYL_COLOR' ? 'selected' : ''}>Vinilo Color</option>
                                    <option value="CD" ${producto.formato == 'CD' ? 'selected' : ''}>CD</option>
                                    <option value="CASSETTE" ${producto.formato == 'CASSETTE' ? 'selected' : ''}>Cassette</option>
                                </select>
                            </div>

                            <div class="field">
                                <label>Stock *</label>
                                <input type="number" 
                                       name="stock" 
                                       value="${producto.stock}" 
                                       min="0" 
                                       required />
                            </div>
                        </div>

                        <div class="grid-2">
                            <div class="field">
                                <label>Precio ($) *</label>
                                <input type="number" 
                                       name="precio" 
                                       value="${producto.precio}" 
                                       step="0.01" 
                                       min="0" 
                                       required />
                            </div>

                            <div class="field">
                                <label>Estado *</label>
                                <select name="esDisponible" required>
                                    <option value="true" ${producto.esDisponible == true ? 'selected' : ''}>Activo</option>
                                    <option value="false" ${producto.esDisponible == false ? 'selected' : ''}>Inactivo</option>
                                </select>
                            </div>
                        </div>

                        <div class="field">
                            <label>Descripción *</label>
                            <textarea name="descripcion" 
                                      placeholder="Describe el producto..." 
                                      required>${producto.descripcion}</textarea>
                        </div>

                        <div class="actions">
                            <button type="submit" class="btn primary">Guardar Cambios</button>
                            <a href="${pageContext.request.contextPath}/admin/productos" class="btn">Cancelar</a>
                        </div>
                    </form>
                </section>
            </main>
        </div>

        <script>
            document.addEventListener('DOMContentLoaded', function() {
                requestAnimationFrame(function() {
                    const alerta = document.getElementById('alerta');
                    if (alerta) {
                        setTimeout(function() {
                            alerta.classList.add('fade-out');
                            setTimeout(function() {
                                alerta.remove();
                            }, 500);
                        }, 3000);
                    }
                });
            });
        </script>
    </body>
</html>