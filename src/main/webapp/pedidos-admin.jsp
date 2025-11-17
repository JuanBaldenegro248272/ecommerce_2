<%-- 
    Document   : pedidosAdmin
    Created on : Nov 15, 2025, 9:36:52 PM
    Author     : jrasc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ECOstore - Administrador</title>
        <link rel="stylesheet" href="styles/admin.css">
    </head>
    <div class="admin-dashboard">
        <%@include file="/WEB-INF/fragments/sidebarAdmin.jspf" %>
        <main class="admin-main">
            <div class="pedidos-container">

                <div class="pedidos-header">
                    <div>
                        <h2>Gestión de Pedidos</h2>
                        <p>Administra los pedidos de los clientes</p>
                    </div>
                </div>

                <div class="filter-bar">
                    <div class="filter-search">
                        <img src="icons/searchicon.png" class="icon">
                        <input type="text" placeholder="Buscar por nombre o por email...">
                    </div>
                    <div class="filter-dropdown">
                        <select>
                            <option>Todos los estados</option>
                            <option>Pendiente</option>
                            <option>Procesando</option>
                            <option>Enviado</option>
                            <option>Entregado</option>
                        </select>
                    </div>
                </div>

                <div class="pedidos-table-container">
                    <table class="pedidos-table">
                        <thead>
                            <tr>
                                <th>ID Pedido</th>
                                <th>Cliente</th>
                                <th>Productos</th>
                                <th>Total</th>
                                <th>Estado</th>
                                <th>Fecha</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="p" items="${listaPedidos}">
                                <tr>
                                    <td>ORD-${p.id}</td>
                                    <td>
                                        <div class="cliente-info">
                                            <strong>${p.nombreCliente}</strong>
                                            <span>${p.correoCliente}</span>
                                        </div>
                                    </td>
                                    <td>
                                        <c:out value="1"/>
                                    </td>
                                    <td>${p.total}</td>
                                    <td>
                                        <span class="status-badge status-pendiente">${p.estado}</span>
                                    </td>
                                    <td>
                                        <fmt:formatDate value="${p.fechaCompra.time}" pattern="MM/dd/yyyy"/>
                                    </td>
                                    <td>
                                        <button class="acciones-btn"
                                                type="button"
                                                onclick="document.getElementById('dialog-pedido-${p.id}').showModal()">
                                            <img src="icons/3dot.png" class="icon">
                                        </button>
                                        <dialog id="dialog-pedido-${p.id}" class="pedido-dialog">
                                            <div class="pedido-dialog-header">
                                                <h2>Detalles del Pedido ORD-${p.id}</h2>
                                                <button type="button"
                                                        class="pedido-dialog-close"
                                                        onclick="document.getElementById('dialog-pedido-${p.id}').close()">
                                                    ✕
                                                </button>
                                            </div>

                                            <div class="pedido-dialog-body">
                                                <h3 class="pedido-section-title">Información del Cliente</h3>
                                                <div class="pedido-card pedido-card-cliente">
                                                    <p class="pedido-card-name">${p.nombreCliente}</p>
                                                    <p class="pedido-card-email">${p.correoCliente}</p>
                                                </div>

                                                <h3 class="pedido-section-title">Productos</h3>
                                                <div class="pedido-card pedido-producto">
                                                    <div>
                                                        <p class="pedido-producto-titulo">Abbey Road - The Beattles</p>
                                                        <p class="pedido-producto-detalle">Cantidad: 1</p>
                                                    </div>
                                                    <span class="pedido-producto-precio">${p.total}</span>
                                                </div>

                                                <hr class="pedido-divider">

                                                <div class="pedido-row">
                                                    <span>Total</span>
                                                    <strong>${p.total}</strong>
                                                </div>

                                                <form method="post" action="${pageContext.request.contextPath}/PedidoServlet" class="pedido-form-estado">
                                                    <input type="hidden" name="accion" value="actualizarEstado">
                                                    <input type="hidden" name="idPedido" value="${p.id}">

                                                    <label class="pedido-label" for="estado-pedido-${p.id}">Actualizar Estado</label>
                                                    <div class="pedido-select-wrapper">
                                                        <select id="estado-pedido-${p.id}" name="estado" onchange="this.form.submit()">
                                                            <option value="PENDIENTE"
                                                                    <c:if test="${p.estado == 'PENDIENTE'}">selected</c:if>>
                                                                        Pendiente
                                                                    </option>
                                                                    <option value="PROCESANDO"
                                                                    <c:if test="${p.estado == 'PROCESANDO'}">selected</c:if>>
                                                                        Procesando
                                                                    </option>
                                                                    <option value="ENVIADO"
                                                                    <c:if test="${p.estado == 'ENVIADO'}">selected</c:if>>
                                                                        Enviado
                                                                    </option>
                                                                    <option value="ENTREGADO"
                                                                    <c:if test="${p.estado == 'ENTREGADO'}">selected</c:if>>
                                                                        Entregado
                                                                    </option>
                                                            </select>
                                                        </div>
                                                    </form>
                                                    <p class="pedido-fecha">
                                                        Creado: 9/11/2025, 17:00:00 • Actualizado: 9/11/2025, 17:00:00
                                                    </p>
                                                </div>
                                            </dialog>
                                        </td>
                                    </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div> 
            </div> 
        </main>
    </div>
</body>
</html>
