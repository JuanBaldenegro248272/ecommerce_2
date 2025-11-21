<%-- 
    Document   : dashboard-admin
    Created on : 19 nov 2025, 11:44:07
    Author     : Gael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard - ECOstore</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/admin.css">
    </head>
    <body>
        <div class="admin-dashboard">
            <c:if test="${usuarioLogueado == null || usuarioLogueado.rol != 'ADMIN'}">
                <c:redirect url="/login.jsp"/>
            </c:if>
            <%@include file="/WEB-INF/fragments/sidebarAdmin.jspf" %>

            <main class="admin-main">
                <div class="header-container">
                    <div>
                        <h1 class="page-title">Dashboard</h1>
                        <p class="page-sub">Resumen general de la tienda</p>
                    </div>
                </div>

                
                <div class="dashboard-grid">
                    <div class="stat-card">
                        <div class="stat-icon-container icon-blue">
                            <img src="${pageContext.request.contextPath}/icons/usuariosicon.png" alt="Users" style="filter: brightness(100);">
                        </div>
                        <div>
                            <div class="stat-number">${totalUsuarios}</div>
                            <div class="stat-label">Usuarios activos</div>
                        </div>
                    </div>

                    <div class="stat-card">
                        <div class="stat-icon-container icon-pink">
                            <img src="${pageContext.request.contextPath}/icons/productosicon.png" alt="Products">
                        </div>
                        <div>
                            <div class="stat-number">${totalProductos}</div>
                            <div class="stat-label">Productos</div>
                        </div>
                    </div>

                    <div class="stat-card">
                        <div class="stat-icon-container icon-red">
                            <img src="${pageContext.request.contextPath}/icons/carticon.png" alt="Orders" style="filter: brightness(0) invert(1);">
                        </div>
                        <div>
                            <div class="stat-number">${totalPedidosPendientes}</div>
                            <div class="stat-label">Pedidos pendientes</div>
                        </div>
                    </div>

                    <div class="stat-card">
                        <div class="stat-icon-container icon-dark">
                            <img src="${pageContext.request.contextPath}/icons/resenasicon.png" alt="Reviews" style="filter: brightness(0) invert(1);">
                        </div>
                        <div>
                            <div class="stat-number">${totalResenasNuevas}</div>
                            <div class="stat-label">Reseñas nuevas</div>
                        </div>
                    </div>
                </div>

                
                <div class="dashboard-section">
                    <div class="section-header">
                        <h3>Pedidos recientes</h3>
                        <a href="${pageContext.request.contextPath}/PedidoServlet" class="btn-small">Ver Todos</a>
                    </div>

                    <c:forEach var="p" items="${pedidosRecientes}">
                        <div class="list-item">
                            <div class="item-info">
                                <h4>
                                    #ORD-${p.id} 
                                    <span class="status-tag ${p.estado == 'PENDIENTE' ? 'bg-red' : 'bg-green'}">
                                        ${p.estado}
                                    </span>
                                </h4>
                                <p>${p.nombreCliente} - ${p.correoCliente}</p>
                                <p style="font-size: 11px; color: #888;">
                                    <fmt:formatDate value="${p.fechaCompra.time}" pattern="dd MMM yyyy"/>
                                </p>
                            </div>
                            <div class="price-tag">
                                $<fmt:formatNumber value="${p.total}" minFractionDigits="2"/>
                            </div>
                        </div>
                    </c:forEach>
                    
                    <c:if test="${empty pedidosRecientes}">
                        <p style="color:#666; text-align: center; padding: 10px;">No hay pedidos recientes.</p>
                    </c:if>
                </div>

                
                <div class="dashboard-section">
                    <div class="section-header">
                        <h3>Reseñas pendientes</h3>
                        <a href="${pageContext.request.contextPath}/admin/moderacion-resenas" class="btn-small">Moderar</a>
                    </div>

                    <c:forEach var="r" items="${resenasPendientes}">
                        <div class="list-item">
                            <div class="item-info">
                                <h4>${r.albumNombre} - ${r.artistaNombre}</h4>
                                <p>${r.clienteNombre}</p>
                                <p style="font-style: italic; font-size: 12px;">"${r.comentario}"</p>
                            </div>
                            <div class="stars">
                                <c:forEach begin="1" end="${r.calificacion}">★</c:forEach>
                                <c:forEach begin="${r.calificacion + 1}" end="5">☆</c:forEach>
                            </div>
                        </div>
                    </c:forEach>
                    
                    <c:if test="${empty resenasPendientes}">
                        <p style="color:#666; text-align: center; padding: 10px;">No hay reseñas pendientes de moderación.</p>
                    </c:if>
                </div>

            </main>
        </div>
    </body>
</html>
