<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ECOstore - Tu Carrito</title>
        <link rel="stylesheet" href="styles/styles.css">
        <link rel="stylesheet" href="styles/formatocss.css">
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
                    <tbody id="lista-productos">
                        <tr><td colspan="4" style="text-align:center; padding: 20px;">Cargando carrito...</td></tr>
                    </tbody>
                </table>

                <div class="cart-actions" style="margin-top: 30px;">
                    <a href="store.jsp" class="btn-empty" style="text-decoration: none; background: #eee; padding: 10px 20px; color: black;">CONTINUE SHOPPING</a>
                </div>
            </div>

            <aside class="cart-summary" style="margin-top: 40px; border: 1px solid #eee; padding: 20px;">
                <h3>CART TOTAL</h3>
                <div class="summary-total" style="display: flex; justify-content: space-between; font-size: 1.2em; font-weight: bold; margin: 20px 0;">
                    <span>TOTAL</span>
                    <span class="total-value" id="total-carrito">$0.00</span>
                </div>
                <div class="summary-actions">
                    <a href="checkout.jsp" class="btn-checkout" style="display:block; text-align:center; text-decoration:none; width: 100%; padding: 15px; background: #fca5a5; border: none; font-weight: bold; color: black; cursor: pointer;">
                        PROCEED TO CHECKOUT
                    </a>
                </div>
            </aside>
        </section>

        <%@ include file="/WEB-INF/fragments/footer.jspf" %>

        <script src="js/cart.js"></script>
    </body>
</html>