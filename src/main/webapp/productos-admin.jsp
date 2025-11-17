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
                    <a href="${pageContext.request.contextPath}/admin/productos/nuevo" class="btn-primary">
                        + Nuevo Producto
                    </a>
                </div>

                <c:if test="${not empty mensaje}">
                    <div id="alert" class="alerta success">
                        ${mensaje}
                    </div>
                </c:if>
                <c:if test="${not empty error}">
                    <div id="alerta" class="alerta error">
                        ${error}
                    </div>
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
                                                    onclick="confirmarEliminar(${producto.idProducto}, '${producto.albumNombre}', '${producto.formato}')">
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

        <div id="modalEliminar" class="modal-overlay">
            <div class="modal-content">
                <h3> ¿Eliminar producto?</h3>
                <p id="modalMensaje"></p>
                <div class="modal-buttons">
                    <button class="btn-cancelar" onclick="cerrarModal()">Cancelar</button>
                    <button class="btn-confirmar" onclick="eliminarProducto()">Eliminar</button>
                </div>
            </div>
        </div>

        <script>
            window.addEventListener('DOMContentLoaded', function () {
                const alertas = document.querySelectorAll('.alerta');
                alertas.forEach(function (alerta) {
                    alerta.setAttribute('role', 'status');
                    alerta.setAttribute('aria-live', 'polite');

                    setTimeout(function () {
                        alerta.classList.add('fade-out');
                        setTimeout(function () {
                            if (alerta && alerta.parentNode) {
                                alerta.parentNode.removeChild(alerta);
                            }
                        }, 420); 
                    }, 3000);
                });
            });

            let productoIdEliminar = null;

            function confirmarEliminar(id, nombreAlbum, formato) {
                productoIdEliminar = id;
                document.getElementById('modalMensaje').textContent =
                        'Estás a punto de eliminar el producto "' + nombreAlbum + ' - ' + formato + '". Esta acción no se puede deshacer.';
                document.getElementById('modalEliminar').classList.add('active');
            }

            function cerrarModal() {
                const modal = document.getElementById('modalEliminar');
                if (modal)
                    modal.classList.remove('active');
                productoIdEliminar = null;
            }

            function eliminarProducto() {
                if (productoIdEliminar) {
                    window.location.href = '${pageContext.request.contextPath}/admin/productos/eliminar?id=' + productoIdEliminar;
                }
            }

            const modalEl = document.getElementById('modalEliminar');
            if (modalEl) {
                modalEl.addEventListener('click', function (e) {
                    if (e.target === this) {
                        cerrarModal();
                    }
                });
            }

            document.addEventListener('keydown', function (e) {
                if (e.key === 'Escape') {
                    cerrarModal();
                }
            });
        </script>

    </body>
</html>