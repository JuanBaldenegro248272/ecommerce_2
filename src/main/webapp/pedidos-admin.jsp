<%-- 
    Document   : pedidosAdmin
    Created on : Nov 15, 2025, 9:36:52 PM
    Author     : jrasc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                            <tr>
                                <td>ORD-001</td>
                                <td>
                                    <div class="cliente-info">
                                        <strong>Dana Chavez</strong>
                                        <span>@mail.com</span>
                                    </div>
                                </td>
                                <td>1</td>
                                <td>$45.00</td>
                                <td>
                                    <span class="status-badge status-pendiente">Pendiente</span>
                                </td>
                                <td>11/13/2025</td>
                                <td>
                                    <button class="acciones-btn"
                                            type="button"
                                            onclick="document.getElementById('dialog-pedido').showModal()">
                                        <img src="icons/3dot.png" class="icon">
                                    </button>
                                    <dialog id="dialog-pedido" class="pedido-dialog">
                                        <div class="pedido-dialog-header">
                                            <h2>Detalles del Pedido ORD-001</h2>
                                            <button type="button"
                                                    class="pedido-dialog-close"
                                                    onclick="document.getElementById('dialog-pedido').close()">
                                                ✕
                                            </button>
                                        </div>

                                        <div class="pedido-dialog-body">
                                            <h3 class="pedido-section-title">Información del Cliente</h3>
                                            <div class="pedido-card pedido-card-cliente">
                                                <p class="pedido-card-name">Dana Chavez</p>
                                                <p class="pedido-card-email">@mail.com</p>
                                            </div>

                                            <h3 class="pedido-section-title">Productos</h3>
                                            <div class="pedido-card pedido-producto">
                                                <div>
                                                    <p class="pedido-producto-titulo">Abbey Road - The Beattles</p>
                                                    <p class="pedido-producto-detalle">Cantidad: 1</p>
                                                </div>
                                                <span class="pedido-producto-precio">$45.00</span>
                                            </div>

                                            <hr class="pedido-divider">

                                            <div class="pedido-row">
                                                <span>Total</span>
                                                <strong>$45.00</strong>
                                            </div>

                                            <label class="pedido-label" for="estado-pedido">Actualizar Estado</label>
                                            <div class="pedido-select-wrapper">
                                                <select id="estado-pedido" name="estado">
                                                    <option>Pendiente</option>
                                                    <option>Procesando</option>
                                                    <option>Enviado</option>
                                                    <option>Entregado</option>
                                                </select>
                                            </div>

                                            <p class="pedido-fecha">
                                                Creado: 9/11/2025, 17:00:00 • Actualizado: 9/11/2025, 17:00:00
                                            </p>
                                        </div>
                                    </dialog>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div> 
            </div> 
        </main>
    </div>
</body>
</html>
