<%-- 
    Document   : albumes-admin
    Created on : Nov 16, 2025, 9:48:05 PM
    Author     : victoria
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Administrar Álbumes</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/admin.css">
    <style>
        .pedidos-table { width: 100%; border-collapse: collapse; font-size: 14px; }
        .pedidos-table thead { background-color: #cfe7e2; }
        .pedidos-table th { text-align: left; padding: 12px 16px; font-weight: 600; }
        .pedidos-table td { padding: 14px 16px; border-top: 1px solid #e5e7eb; vertical-align: middle; }
        .album-cover-thumb { width: 60px; height: 60px; object-fit: cover; border-radius: 4px; }
        .actions { display: flex; gap: 10px; }
        .actions button { background: none; border: none; cursor: pointer; padding: 5px; }
        .actions button img.icon { width: 22px; height: 22px; }
        .actions button.edit:hover img { filter: brightness(0.6); }
        .actions button.delete:hover img { filter: brightness(0.6); }
    </style>
</head>
<body>
    <div class="admin-dashboard">
        <%@include file="/WEB-INF/fragments/sidebarAdmin.jspf" %>

        <main class="admin-main">
            <div class="header-row">
                <div>
                    <h1>Álbumes</h1>
                    <p>Administra los metadatos de los álbumes</p>
                </div>
                <a href="${pageContext.request.contextPath}/admin/albumes/nuevo" class="btn-primary">
                    + Nuevo Álbum
                </a>
            </div>
            <c:if test="${not empty mensaje}">
                <div id="alert" class="alerta success">${mensaje}</div>
            </c:if>
            <c:if test="${not empty error}">
                <div id="alerta" class="alerta error">${error}</div>
            </c:if>
            <form method="get" action="${pageContext.request.contextPath}/admin/albumes" class="search-box">
                <input type="text" name="buscar" placeholder="Buscar por álbum o artista..." value="${terminoBusqueda}">
                <button type="submit">Buscar</button>
            </form>

            <%-- Tabla de Maquetado --%>
            <div class="pedidos-table-container" style="background-color: white;">
                <table class="pedidos-table">
                    <thead>
                        <tr>
                            <th></th>
                            <th>Álbum</th>
                            <th>Artista</th>
                            <th>Lanzamiento</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${empty listaAlbumes}">
                                <tr>
                                    <td colspan="5" style="text-align: center; padding: 20px;">No se encontraron álbumes</td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="album" items="${listaAlbumes}">
                                    <tr>
                                        <td>
                                            <img src="${album.imagenUrl}" alt="${album.nombre}" class="album-cover-thumb"
                                                 onerror="this.src='${pageContext.request.contextPath}/images/no-image.png'">
                                        </td>
                                        <td><strong>${album.nombre}</strong></td>
                                        <td>${album.nombreArtista}</td>
                                        <td>
                                            <fmt:formatDate value="${album.fechaLanzamiento.time}" pattern="dd/MM/yyyy" />
                                        </td>
                                        <td>
                                            <div class="actions">
                                                <button class="edit" 
                                                        title="Editar"
                                                        onclick="location.href='${pageContext.request.contextPath}/admin/albumes/editar?id=${album.id}'">
                                                    <img src="${pageContext.request.contextPath}/icons/editAdmin.png" class="icon" alt="Editar">
                                                </button>
                                                <button class="delete" 
                                                        title="Eliminar"
                                                        onclick="confirmarEliminar(${album.id}, '${album.nombre}')">
                                                    <img src="${pageContext.request.contextPath}/icons/deleteAdmin.png" class="icon" alt="Eliminar">
                                                </button>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
        </main>
    </div>
    <div id="modalEliminar" class="modal-overlay">
        <div class="modal-content">
            <h3>¿Eliminar Álbum?</h3>
            <p id="modalMensaje"></p>
            <div class="modal-buttons">
                <button class="btn-cancelar" onclick="cerrarModal()">Cancelar</button>
                <button class="btn-confirmar" onclick="eliminarAlbum()">Eliminar</button>
            </div>
        </div>
    </div>
    <script>
        window.addEventListener('DOMContentLoaded', function () {
            const alertas = document.querySelectorAll('.alerta');
            alertas.forEach(function (alerta) {
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

        let albumIdEliminar = null;

        function confirmarEliminar(id, nombreAlbum) {
            albumIdEliminar = id;
            document.getElementById('modalMensaje').textContent =
                'Estás a punto de eliminar el álbum "' + nombreAlbum + '". Esta acción no se puede deshacer.';
            document.getElementById('modalEliminar').classList.add('active');
        }

        function cerrarModal() {
            const modal = document.getElementById('modalEliminar');
            if (modal)
                modal.classList.remove('active');
            albumIdEliminar = null;
        }

        function eliminarAlbum() {
            if (albumIdEliminar) {
                window.location.href = '${pageContext.request.contextPath}/admin/albumes/eliminar?id=' + albumIdEliminar;
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