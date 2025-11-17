<%-- 
    Document   : moderacion-resenas
    Created on : Nov 16, 2025, 8:30:06 PM
    Author     : victoria
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Moderación de Reseñas</title>

    <link href="${pageContext.request.contextPath}/styles/global.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/styles/admin.css" rel="stylesheet" type="text/css"/>
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap" rel="stylesheet">
</head>
<body>

    <div class="app">

        <jsp:include page="sidebarAdmin.jspf" />

        <main class="main">
            <div class="header-container">
                <div>
                    <h1 class="page-title">Moderar Reseñas</h1>
                    <p class="page-sub">Aprueba, rechaza o busca reseñas de clientes.</p>
                </div>
            </div>

            <div class="filter-bar" style="max-width: 1200px; width: 100%;">
                
                <form action="${pageContext.request.contextPath}/admin/moderacion-resenas" method="POST" class="filter-bar" style="width: 100%;">
                    
                    <input type="hidden" name="accion" value="buscar">

                    <div class="filter-search" style="flex: 2;">
                        <input type="search" 
                               name="terminoBusqueda" 
                               placeholder="Buscar por comentario..." 
                               value="${terminoBusquedaActual}">
                    </div>

                    <div class="filter-dropdown" style="flex: 1;">
                        <select name="filtroEstado">
                            <option value="">Todos los Estados</option>
                            <option value="PENDIENTE" ${filtroEstadoActual == 'PENDIENTE' ? 'selected' : ''}>
                                Pendientes
                            </option>
                            <option value="APROBADA" ${filtroEstadoActual == 'APROBADA' ? 'selected' : ''}>
                                Aprobadas
                            </option>
                        </select>
                    </div>

                    <button type="submit" class="btn primary" style="padding: 8px 12px; background-color: var(--color-primary); color: white; border: none; cursor: pointer;">Buscar</button>
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
                        
                        <c:forEach var="r" items="${listaResenas}">
                            <tr>
                                <td>${r.id}</td>
                                <td>${r.cliente.nombre}</td>
                                <td>${r.producto.album.titulo}</td>
                                <td>${r.comentario}</td>
                                <td>${r.calificacion} / 5</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${r.estado == 'PENDIENTE'}">
                                            <span class="status-badge status-pendiente">Pendiente</span>
                                        </c:when>
                                        <c:when test="${r.estado == 'APROBADA'}">
                                            <span class="status-badge status-entregado">Aprobada</span>
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td>
                                    <div style="display: flex; gap: 8px;">
                                        
                                        <c:if test="${r.estado == 'PENDIENTE'}">
                                            <form action="${pageContext.request.contextPath}/admin/moderacion-resenas" method="POST" style="margin:0;">
                                                <input type="hidden" name="accion" value="aprobar">
                                                <input type="hidden" name="idResena" value="${r.id}">
                                                <button type="submit" class="btn" style="background-color: #22c55e; color:white; border:none; padding: 5px 10px; cursor:pointer; border-radius: 4px;">
                                                    Aprobar
                                                </button>
                                            </form>
                                        </c:if>

                                        <form action="${pageContext.request.contextPath}/admin/moderacion-resenas" method="POST" style="margin:0;">
                                            <input type="hidden" name="accion" value="eliminar">
                                            <input type="hidden" name="idResena" value="${r.id}">
                                            <button type="submit" class="btn" style="background-color: #c53030; color:white; border:none; padding: 5px 10px; cursor:pointer; border-radius: 4px;">
                                                Eliminar
                                            </button>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

        </main>
    </div>

</body>
</html>