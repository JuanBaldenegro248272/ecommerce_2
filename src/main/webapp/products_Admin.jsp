<%-- 
    Document   : products_Admin
    Created on : Nov 15, 2025, 5:30:00 PM
    Author     : jrasc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ECOstore - Administrador</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styles.css">
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
                    <button class="btn-primary">+ Nuevo Producto</button>
                </div>

                <div class="search-box">
                    <input type="text" placeholder="Buscar productos...">
                </div>

                <div class="product-grid">

                    <c:forEach var="product" items="${listaProductos}">

                        <div class="product-card">

                            <img src="${product.album.imagenUrl}" alt="${prod.album.nombre}">

                            <h3>${product.album.nombre}</h3>

                            <p>${product.album.artista.nombreArtistico}</p>

                            <div class="badges">
                                <c:forEach var="genre" items="${product.album.generos}" varStatus="st">
                                    <span class="tag">${genre.nombre}</span>
                                    <c:if test="${st.index == 0}">
                                        <c:break/>
                                    </c:if>
                                </c:forEach>

                                <span class="tag">${product.formato}</span>
                            </div>

                            <div class="price-row">
                                <span class="price">$${product.precio}</span>

                                <div class="actions">
                                    <button class="edit"
                                            onclick="location.href = 'editarProducto?id=${product.idProducto}'">️<image src="icons/editAdmin.png" class="icon"></button>

                                    <button class="delete"
                                            onclick="location.href = 'eliminarProducto?id=${product.idProducto}'">️<image src="icons/deleteAdmin.png" class="icon"></button>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </main>
        </div>
    </body>
</html>
