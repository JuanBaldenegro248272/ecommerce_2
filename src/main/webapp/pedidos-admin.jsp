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
                                    <button class="acciones-btn">
                                        <img src="icons/3dot.png" class="icon">
                                    </button>
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
