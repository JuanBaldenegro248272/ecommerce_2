<%-- 
    Document   : generoAdmin
    Created on : Nov 15, 2025, 9:05:45 PM
    Author     : jrasc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ECOstore - Administrador</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/admin.css">
    </head>
    <body>
        <div class="admin-dashboard">
            <%@ include file="/WEB-INF/fragments/sidebarAdmin.jspf" %>

            <div class="admin-main">
                <div class="categorias-container">
                    <div class="categorias-header" style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
                        <div>
                            <h2 style="margin: 0 0 8px 0; font-size: 28px;">Gestión de Categorías</h2>
                            <p style="margin: 0; color: #666;">Organiza los productos por categorías</p>
                        </div>
<!--                        <button class="btn-nueva" onclick="location.href = '${pageContext.request.contextPath}/admin/categorias/nuevo'">
                            + Nueva Categoría
                        </button>-->
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

                    <div class="grid-categorias">
                        <c:choose>
                            <c:when test="${empty listaGeneros}">
                                <div class="empty-message" style="grid-column: 1 / -1;">
                                    No se encontraron categorías
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="genero" items="${listaGeneros}">
                                    <div class="categoria-card">
                                        <div class="categoria-icono">
                                            <img src="${pageContext.request.contextPath}/icons/tagAdmin.png" alt="Categoría">
                                        </div>
                                        <h3>${genero.nombre}</h3>
                                        <span class="descripcion">
                                            ${genero.cantidadAlbums} álbum${genero.cantidadAlbums != 1 ? 's' : ''}
                                        </span>
                                        <div class="categoria-acciones">
                                            <button class="btn-editar" 
                                                    onclick="location.href = '${pageContext.request.contextPath}/admin/categorias/editar?id=${genero.id}'">
                                                <img src="${pageContext.request.contextPath}/icons/editAdmin.png" alt="Editar">
                                                Editar
                                            </button>
                                            <button class="btn-eliminar" 
                                                    onclick="confirmarEliminar(${genero.id}, '${genero.nombre}')">
                                                <img src="${pageContext.request.contextPath}/icons/deleteAdmin.png" alt="Eliminar">
                                            </button>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>

        <div id="modalEliminar" class="modal-overlay">
            <div class="modal-content">
                <h3>¿Eliminar categoría?</h3>
                <p id="modalMensaje"></p>
                <form id="formEliminar" action="${pageContext.request.contextPath}/admin/categorias/eliminar" method="POST">
                    <input type="hidden" name="id" id="idGeneroEliminar">
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

            function confirmarEliminar(id, nombre) {
                document.getElementById('idGeneroEliminar').value = id;
                document.getElementById('modalMensaje').textContent =
                        'Estás a punto de eliminar la categoría "' + nombre + '". Esta acción no se puede deshacer.';
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
