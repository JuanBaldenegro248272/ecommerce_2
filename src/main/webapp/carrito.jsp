
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ECOstore - Tu Carrito</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styles.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/formatocss.css">
    </head>
    <body>
        
        <%@ include file="/WEB-INF/fragments/navbar.jspf" %>
        
        <section class="cart-layout" style="padding: 40px;">
            <div class="cart-content">
                <h2 style="margin-bottom: 20px;">Shopping Cart</h2>
                
                <table class="cart-table" style="width: 100%; border-collapse: collapse;">
                    <thead>
                        <tr style="border-bottom: 2px solid #000;">
                            <th style="text-align: left; padding: 10px;">PRODUCT</th>
                            <th style="padding: 10px;">PRICE</th>
                            <th style="padding: 10px;">QUANTITY</th>
                            <th style="padding: 10px;">SUBTOTAL</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${empty carritoReal or empty carritoReal.detalles}">
                                <tr>
                                    <td colspan="4" style="text-align:center; padding: 40px;">
                                        <p>Tu carrito está vacío.</p>
                                        <a href="${pageContext.request.contextPath}/index.jsp" class="btn-continue" style="color: blue; text-decoration: underline;">Volver a la tienda</a>
                                    </td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="detalle" items="${carritoReal.detalles}">
                                    <tr class="cart-row" style="border-bottom: 1px solid #ccc;">
                                        <td style="padding: 15px 0;">
                                            <div class="cart-item" style="display: flex; align-items: center; gap: 15px;">
                                                <a href="${pageContext.request.contextPath}/carrito/eliminar?id=${detalle.id}" 
                                                   class="remove-btn" 
                                                   style="color: red; font-weight: bold; text-decoration: none; font-size: 18px;"
                                                   onclick="return confirm('¿Eliminar este producto?');">✕</a>
                                                
                                                <img src="${pageContext.request.contextPath}/albumcovers/${detalle.imagenUrl}" 
                                                     class="cart-thumb" 
                                                     style="width: 80px; height: 80px; object-fit: cover;"
                                                     alt="cover">
                                                
                                                <div class="product-info">
                                                    <p class="product-name" style="font-weight: bold; margin: 0;">${detalle.nombre}</p>
                                                    <p class="product-artist" style="margin: 0; color: #666;">${detalle.artista}</p>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="price" style="text-align: center;">$${detalle.precio}</td>
                                        <td style="text-align: center;">
                                            <div class="qty">
                                                <span style="border: 1px solid #ccc; padding: 5px 15px;">${detalle.cantidad}</span>
                                            </div>
                                        </td>
                                        <td class="subtotal" style="text-align: center; font-weight: bold;">$${detalle.subtotal}</td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
                
                <div class="cart-actions" style="margin-top: 30px;">
                    <a href="${pageContext.request.contextPath}/index.jsp" class="btn-empty" style="text-decoration: none; background: #eee; padding: 10px 20px; color: black;">CONTINUE SHOPPING</a>
                </div>
            </div>
            
            <aside class="cart-summary" style="margin-top: 40px; border: 1px solid #eee; padding: 20px;">
                <h3>CART TOTAL</h3>
                <div class="summary-total" style="display: flex; justify-content: space-between; font-size: 1.2em; font-weight: bold; margin: 20px 0;">
                    <span>TOTAL</span>
                    <span class="total-value">$${carritoReal.total}</span>
                </div>
                <div class="summary-actions">
                    <button class="btn-checkout" style="width: 100%; padding: 15px; background: #fca5a5; border: none; font-weight: bold; cursor: pointer;">PROCEED TO CHECKOUT</button>
                </div>
            </aside>
        </section>
        
        <%@ include file="/WEB-INF/fragments/footer.jspf" %>
        
    </body>
</html>