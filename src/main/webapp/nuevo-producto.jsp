<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/admin.css">
        <title>Nuevo Producto</title>
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
                        <h2 class="page-title">Nuevo producto</h2>
                        <div class="page-sub">Agrega un nuevo producto al catálogo</div>
                    </div>
                </div>

                <c:if test="${not empty error}">
                    <div class="alert error">${error}</div>
                </c:if>

                <section class="panel">
                    <form action="${pageContext.request.contextPath}/admin/productos/nuevo" method="post">
                        <div class="field">
                            <label>Album *</label>
                            <button type="button" class="select-album-btn">
                                <img src="${pageContext.request.contextPath}/icons/search.png" 
                                     alt="buscar" 
                                     style="width:16px;height:16px;" />
                                Seleccionar Album
                            </button>
                            <input type="hidden" name="albumId" value="${dto.albumId}" required />
                        </div>

                        <div class="grid-2">
                            <div class="field">
                                <label>Formato *</label>
                                <select name="formato" required>
                                    <option value="VINYL" ${dto.formato == 'VINYL' ? 'selected' : ''}>Vinilo</option>
                                    <option value="VINYL_COLOR" ${dto.formato == 'VINYL_COLOR' ? 'selected' : ''}>Vinilo Color</option>
                                    <option value="CD" ${dto.formato == 'CD' ? 'selected' : ''}>CD</option>
                                    <option value="CASSETTE" ${dto.formato == 'CASSETTE' ? 'selected' : ''}>Cassette</option>
                                </select>
                            </div>

                            <div class="field">
                                <label>Stock *</label>
                                <input type="number" 
                                       name="stock" 
                                       value="${dto.stock != null ? dto.stock : 0}" 
                                       min="0" 
                                       required />
                            </div>
                        </div>

                        <div class="grid-2">
                            <div class="field">
                                <label>Precio ($) *</label>
                                <input type="number" 
                                       name="precio" 
                                       value="${dto.precio != null ? dto.precio : 0}" 
                                       step="0.01" 
                                       min="0" 
                                       required />
                            </div>

                            <div class="field">
                                <label>Estado *</label>
                                <select name="esDisponible" required>
                                    <option value="true" ${dto.esDisponible == true ? 'selected' : ''}>Activo</option>
                                    <option value="false" ${dto.esDisponible == false ? 'selected' : ''}>Inactivo</option>
                                </select>
                            </div>
                        </div>

                        <div class="field">
                            <label>Descripción *</label>
                            <textarea name="descripcion" 
                                      placeholder="Describe el producto..." 
                                      required>${dto.descripcion}</textarea>
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