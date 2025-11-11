<%-- 
    Document   : carrito
    Created on : Nov 6, 2025, 10:46:14 PM
    Author     : Dana Chavez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>ECOstore - Carrito</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="styles/formatocss.css">
        <link rel="stylesheet" href="styles/styles.css">
    </head>
    <body>
        
        <%@ include file="/WEB-INF/fragments/navbar.jspf" %>
        
        <section class="cart-layout">
            <div class="cart-content">
                <table class="cart-table">
                    <thead>
                        <tr>
                            <th>PRODUCT</th>
                            <th>PRICE</th>
                            <th>QUANTITY</th>
                            <th>SUBTOTAL</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="cart-row">
                            <td>
                                <div class="cart-item">
                                    <button class="remove-btn">✕</button>
                                    <img src="albumcovers/folklore.png" alt="folklore" class="cart-thumb">
                                    <div class="product-info">
                                        <p class="product-name">folklore</p>
                                        <p class="product-artist">Taylor Swift</p>
                                        <span class="format-icon"> vinyl </span>
                                    </div>
                                </div>
                            </td>
                            <td class="price">$40.00</td>
                            <td>
                                <div class="qty">
                                    <button class="qty-btn">−</button>
                                    <input class="qty-input" type="text" value="1" readonly>
                                    <button class="qty-btn">+</button>
                                </div>
                            </td>
                            <td class="subtotal">$40.00</td>
                        </tr>
                        <tr class="cart-row">
                            <td>
                                <div class="cart-item">
                                    <button class="remove-btn">✕</button>
                                    <img src="albumcovers/nevermind.png" alt="Nevermind" class="cart-thumb">
                                    <div class="product-info">
                                        <p class="product-name">NEVERMIND</p>
                                        <p class="product-artist">NIRVANA</p>
                                        <span class="format-icon">vinyl</span>
                                    </div>
                                </div>
                            </td>
                            <td class="price">$20.00</td>
                            <td>
                                <div class="qty">
                                    <button class="qty-btn">−</button>
                                    <input class="qty-input" type="text" value="2" readonly>
                                    <button class="qty-btn">+</button>
                                </div>
                            </td>
                            <td class="subtotal">$40.00</td>
                        </tr>
                    </tbody>
                </table>
                
                <div class="cart-actions">
                    <button class="btn-empty">EMPTY CART</button>
                </div>
                
                <div class="cart-info">
                    <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet.</p>
                </div>
            </div>
            
            <aside class="cart-summary">
                <h3>CART TOTAL</h3>
                <div class="summary-line">
                    <span>SUBTOTAL</span>
                    <span class="summary-value">$80.00</span>
                </div>
                <div class="summary-line">
                    <span>SHIPPING</span>
                    <span class="summary-link">Calculate shipping 📋</span>
                </div>
                <div class="summary-total">
                    <span>TOTAL</span>
                    <span class="total-value">$80.00</span>
                </div>
                <div class="summary-actions">
                    <button class="btn-return">RETURN TO SHOP</button>
                    <button class="btn-checkout">PROCEED TO CHECKOUT</button>
                </div>
            </aside>
        </section>
        
        <%@ include file="/WEB-INF/fragments/footer.jspf" %>
        
    </body>
</html>