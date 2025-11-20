<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Moderación de Reseñas - ECOstore</title>
        <link href="${pageContext.request.contextPath}/styles/global.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/styles/admin.css" rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap" rel="stylesheet">
    </head>
    <body>

        <div class="app">

            <%@ include file="/WEB-INF/fragments/sidebarAdmin.jspf" %>

            <main class="main">
                <div class="header-container">
                    <div>
                        <h1 class="page-title">Moderar Reseñas</h1>
                        <p class="page-sub">Aprueba o elimina reseñas de clientes</p>
                    </div>
                </div>

                <c:if test="${not empty mensaje}">
                    <div id="alerta" class="alert success">
                        ${mensaje}
                    </div>
                </c:if>
                <c:if test="${not empty error}">
                    <div id="alerta" class="alert error">
                        ${error}
                    </div>
                </c:if>

                <div class="filter-bar" style="max-width: 1200px; width: 100%; margin-bottom: 20px;">
                    <form action="${pageContext.request.contextPath}/admin/moderacion-resenas" 
                          method="POST" 
                          style="display: flex; gap: 12px; width: 100%;">

                        <input type="hidden" name="accion" value="buscar">

                        <div style="flex: 2;">
                            <input type="search" 
                                   name="terminoBusqueda" 
                                   placeholder="Buscar por comentario..." 
                                   value="${terminoBusquedaActual}"
                                   style="width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 6px;">
                        </div>

                        <div style="flex: 1;">
                            <select name="filtroEstado" style="width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 6px;">
                                <option value="">Todos los Estados</option>
                                <option value="PENDIENTE" ${filtroEstadoActual == 'PENDIENTE' ? 'selected' : ''}>
                                    Pendientes
                                </option>
                                <option value="APROBADA" ${filtroEstadoActual == 'APROBADA' ? 'selected' : ''}>
                                    Aprobadas
                                </option>
                            </select>
                        </div>

                        <button type="submit" class="btn primary" style="padding: 10px 20px;">
                            Buscar
                        </button>
                    </form>
                </div>

                <div class="pedidos-table-container" style="max-width: 1200px; width: 100%;">
                    <table class="pedidos-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Cliente</th>
                                <th>Producto</th>
                                <th style="width: 30%;">Comentario</th>
                                <th>Calif.</th>
                                <th>Estado</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${empty listaResenas}">
                                    <tr>
                                        <td colspan="7" style="text-align: center; padding: 40px; color: #999;">
                                            No se encontraron reseñas
                                        </td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="resena" items="${listaResenas}">
                                        <tr>
                                            <td>${resena.id}</td>
                                            <td>${resena.clienteNombre}</td>
                                            <td>
                                                <div>
                                                    <strong>${resena.albumNombre}</strong>
                                                    <br>
                                                    <small style="color: #666;">${resena.artistaNombre}</small>
                                                </div>
                                            </td>
                                            <td>${resena.comentario}</td>
                                            <td>
                                                <strong>${resena.calificacion}</strong> / 5
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${resena.estado == 'PENDIENTE'}">
                                                        <span class="status-badge" style="background: #ff9800; color: white; padding: 4px 12px; border-radius: 12px; font-size: 12px;">
                                                            Pendiente
                                                        </span>
                                                    </c:when>
                                                    <c:when test="${resena.estado == 'APROBADA'}">
                                                        <span class="status-badge" style="background: #4CAF50; color: white; padding: 4px 12px; border-radius: 12px; font-size: 12px;">
                                                            Aprobada
                                                        </span>
                                                    </c:when>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <div style="display: flex; gap: 8px; justify-content: center;">

                                                    <c:if test="${resena.estado == 'PENDIENTE'}">
                                                        <form action="${pageContext.request.contextPath}/admin/moderacion-resenas" 
                                                              method="POST" 
                                                              style="margin: 0;">
                                                            <input type="hidden" name="accion" value="aprobar">
                                                            <input type="hidden" name="idResena" value="${resena.id}">
                                                            <button type="submit" 
                                                                    style="background-color: #22c55e; color: white; border: none; padding: 6px 12px; cursor: pointer; border-radius: 4px; font-size: 13px;">
                                                                Aprobar
                                                            </button>
                                                        </form>
                                                    </c:if>

                                                    <button type="button"
                                                            onclick="confirmarEliminar(${resena.id}, '${resena.clienteNombre}')"
                                                            style="background-color: #ef4444; color: white; border: none; padding: 6px 12px; cursor: pointer; border-radius: 4px; font-size: 13px;">
                                                        Eliminar
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
                <h3>🗑️ ¿Eliminar reseña?</h3>
                <p id="modalMensaje"></p>
                <form id="formEliminar" action="${pageContext.request.contextPath}/admin/moderacion-resenas" method="POST">
                    <input type="hidden" name="accion" value="eliminar">
                    <input type="hidden" name="idResena" id="idResenaEliminar">
                    <div class="modal-buttons">
                        <button type="button" class="btn-cancelar" onclick="cerrarModal()">Cancelar</button>
                        <button type="submit" class="btn-confirmar">Eliminar</button>
                    </div>
                </form>
            </div>
        </div>

        <script>
            document.addEventListener('DOMContentLoaded', function () {
                const alerta = document.getElementById('alerta');
                if (alerta) {
                    setTimeout(function () {
                        alerta.classList.add('fade-out');
                        setTimeout(function () {
                            alerta.remove();
                        }, 500);
                    }, 3000);
                }
            });
            
            function confirmarEliminar(id, nombreCliente) {
                document.getElementById('idResenaEliminar').value = id;
                document.getElementById('modalMensaje').textContent =
                        'Estás a punto de eliminar la reseña de "' + nombreCliente + '". Esta acción no se puede deshacer.';
                document.getElementById('modalEliminar').classList.add('active');
            }

            function cerrarModal() {
                document.getElementById('modalEliminar').classList.remove('active');
            }

            document.getElementById('modalEliminar').addEventListener('click', function (e) {
                if (e.target === this) {
                    cerrarModal();
                }
            });

            document.addEventListener('keydown', function (e) {
                if (e.key === 'Escape') {
                    cerrarModal();
                }
            });
        </script>
    </body>
</html>