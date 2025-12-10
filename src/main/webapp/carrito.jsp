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
                    <span class="summary-value">$0.00</span>
                </div>
                <div class="summary-line">
                    <span>SHIPPING</span>
                    <span class="summary-link">Calculate shipping 📋</span>
                </div>
                <div class="summary-total">
                    <span>TOTAL</span>
                    <span class="total-value">$0.00</span>
                </div>
                <div class="summary-actions">
                    <button class="btn-return" onclick="window.location.href='store.html'">RETURN TO SHOP</button>
                    <button class="btn-checkout">PROCEED TO CHECKOUT</button>
                </div>
            </aside>
        </section>
        
        <%@ include file="/WEB-INF/fragments/footer.jspf" %>
        
        <script src="js/cart-logic.js"></script>
        
    </body>
</html>