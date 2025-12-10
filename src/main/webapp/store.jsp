
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>ECOstore - Catálogo</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <link rel="stylesheet" href="styles/formatocss.css">
        <link rel="stylesheet" href="styles/styles.css">
        
        <style>
            .store-grid {
                display: flex;
                flex-wrap: wrap;
                gap: 30px;
                padding: 40px;
                justify-content: center;
            }
            .product-card {
                border: 1px solid #ddd;
                padding: 15px;
                border-radius: 8px;
                width: 250px;
                text-align: center;
                box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            }
            .product-card img {
                width: 100%;
                height: auto;
                border-radius: 4px;
            }
            .btn-add {
                background-color: #000;
                color: white;
                border: none;
                padding: 10px 20px;
                cursor: pointer;
                width: 100%;
                margin-top: 10px;
                font-weight: bold;
            }
            .btn-add:hover {
                background-color: #333;
            }
        </style>
    </head>
    <body>
        
        <%@ include file="/WEB-INF/fragments/navbar.jspf" %>
        
        <h1 style="text-align: center; margin-top: 20px;">STORE</h1>
        
        <section class="store-grid">
            
            <div class="product-card">
                <img src="albumcovers/folklore.png" alt="Folklore">
                <h3>folklore</h3>
                <p>Taylor Swift</p>
                <p>$40.00</p>
                
                <button class="btn-add" onclick="addToCart({
                    id: 'alb001', 
                    name: 'folklore', 
                    artist: 'Taylor Swift', 
                    format: 'vinyl', 
                    price: 40.00, 
                    image: 'albumcovers/folklore.png'
                })">ADD TO CART</button>
            </div>

            <div class="product-card">
                <img src="albumcovers/nevermind.png" alt="Nevermind">
                <h3>NEVERMIND</h3>
                <p>NIRVANA</p>
                <p>$20.00</p>
                
                <button class="btn-add" onclick="addToCart({
                    id: 'alb002', 
                    name: 'NEVERMIND', 
                    artist: 'NIRVANA', 
                    format: 'vinyl', 
                    price: 20.00, 
                    image: 'albumcovers/nevermind.png'
                })">ADD TO CART</button>
            </div>
            
            <div class="product-card">
                <img src="albumcovers/abbeyroad.png" alt="Abbey Road">
                <h3>Abbey Road</h3>
                <p>The Beatles</p>
                <p>$40.00</p>
                
                <button class="btn-add" onclick="addToCart({
                    id: 'alb003', 
                    name: 'Abbey Road', 
                    artist: 'The Beatles', 
                    format: 'vinyl', 
                    price: 40.00, 
                    image: 'albumcovers/abbeyroad.png'
                })">ADD TO CART</button>
            </div>

        </section>
        
        <%@ include file="/WEB-INF/fragments/footer.jspf" %>
        
        <script src="js/cart-logic.js"></script>
        
    </body>
</html>