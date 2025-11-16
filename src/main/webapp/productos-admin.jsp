<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ECOstore - Administrador</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/admin.css">
    </head>
    <body>
        <div class="admin-dashboard">
            <%@include file="/WEB-INF/fragments/sidebarAdmin.jspf" %>

            <main class="admin-main">
                <div class="header-row">
                    <div>
                        <h1>Productos</h1>
                        <p>Administra el catálogo de productos</p>
                    </div>
                    <a href="${pageContext.request.contextPath}/nuevo-producto.jsp" class="btn-primary">
                        + Nuevo Producto
                    </a>
                </div>
                <c:if test="${not empty mensaje}">
                    <div class="alert success">${mensaje}</div>
                </c:if>
                <c:if test="${not empty error}">
                    <div class="alert error">${error}</div>
                </c:if>
                
                <form method="get" action="${pageContext.request.contextPath}/admin/productos" class="search-box">
                    <input type="text" 
                           name="buscar" 
                           placeholder="Buscar por álbum o artista..." 
                           value="${terminoBusqueda}">
                    <button type="submit">Buscar</button>
                </form>

                <div class="product-grid">
                    <c:choose>
                        <c:when test="${empty listaProductos}">
                            <p class="empty-message">No se encontraron productos</p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="producto" items="${listaProductos}">
                                <div class="product-card">
                                    <img src="${producto.albumImagenUrl}" 
                                         alt="${producto.albumNombre}"
                                         onerror="this.src='${pageContext.request.contextPath}/images/no-image.png'">

                                    <h3>${producto.albumNombre}</h3>
                                    <p>${producto.artistaNombre}</p>

                                    <div class="badges">
                                        <c:if test="${not empty producto.generos}">
                                            <span class="tag">${producto.generos[0]}</span>
                                        </c:if>
                                        <span class="tag">${producto.formato}</span>
                                    </div>

                                    <div class="product-info">
                                        <span class="stock">Stock: ${producto.stock}</span>
                                        <span class="status ${producto.esDisponible ? 'active' : 'inactive'}">
                                            ${producto.esDisponible ? 'Activo' : 'Inactivo'}
                                        </span>
                                    </div>

                                    <div class="price-row">
                                        <span class="price">
                                            $<fmt:formatNumber value="${producto.precio}" minFractionDigits="2" maxFractionDigits="2"/>
                                        </span>
                                        <div class="actions">
                                            <button class="edit" 
                                                    onclick="location.href = '${pageContext.request.contextPath}/admin/productos/editar?id=${producto.idProducto}'">
                                                <img src="${pageContext.request.contextPath}/icons/editAdmin.png" class="icon" alt="Editar">
                                            </button>
                                            <button class="delete" 
                                                    onclick="confirmarEliminar(${producto.idProducto})">
                                                <img src="${pageContext.request.contextPath}/icons/deleteAdmin.png" class="icon" alt="Eliminar">
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
            </main>
        </div>

        <script>
            function confirmarEliminar(id) {
                if (confirm('¿Estás seguro de eliminar este producto?')) {
                    location.href = '${pageContext.request.contextPath}/admin/productos/eliminar?id=' + id;
                }
            }
        </script>
    </body>
</html>