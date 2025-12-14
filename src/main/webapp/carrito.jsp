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
                    <a href="index.jsp" class="btn-empty" style="text-decoration: none; background: #eee; padding: 10px 20px; color: black;">CONTINUE SHOPPING</a>
                </div>
            </div>
            
            <aside class="cart-summary" style="margin-top: 40px; border: 1px solid #eee; padding: 20px;">
                <h3>CART TOTAL</h3>
                <div class="summary-total" style="display: flex; justify-content: space-between; font-size: 1.2em; font-weight: bold; margin: 20px 0;">
                    <span>TOTAL</span>
                    <span class="total-value" id="total-carrito">$0.00</span>
                </div>
                <div class="summary-actions">
                    <button class="btn-checkout" style="width: 100%; padding: 15px; background: #fca5a5; border: none; font-weight: bold; cursor: pointer;">PROCEED TO CHECKOUT</button>
                </div>
            </aside>
        </section>
        
        <%@ include file="/WEB-INF/fragments/footer.jspf" %>

        <script>
            // ID del carrito hardcodeado para pruebas
            const ID_CARRITO_ACTUAL = 1;

            document.addEventListener("DOMContentLoaded", () => {
                cargarCarrito();
            });

            async function cargarCarrito() {
                try {
                    // Llamada a la API REST (Asegurate de tener CarritoResource.java creado)
                    const response = await fetch(`api/carrito/${ID_CARRITO_ACTUAL}`);
                    
                    if (!response.ok) {
                        mostrarVacio();
                        return;
                    }

                    const carrito = await response.json();
                    renderizarTabla(carrito);
                } catch (error) {
                    console.error("Error:", error);
                    document.getElementById("lista-productos").innerHTML = 
                        `<tr><td colspan="4" style="color:red; text-align:center;">Error cargando el carrito. Revisa la consola (F12).</td></tr>`;
                }
            }

            function renderizarTabla(carrito) {
                // --- DEBUG: ESTO TE DIRÁ QUÉ DATOS LLEGAN ---
                console.log("Datos del carrito recibidos:", carrito);
                // ---------------------------------------------

                const tbody = document.getElementById("lista-productos");
                const totalSpan = document.getElementById("total-carrito");
                
                // Actualizar total
                // Si el total viene nulo, ponemos 0
                let total = carrito.total ? carrito.total : 0;
                totalSpan.innerText = "$" + total.toFixed(2);

                // Verificación de seguridad para la lista
                if (!carrito.detalles || carrito.detalles.length === 0) {
                    mostrarVacio();
                    return;
                }

                let html = "";
                carrito.detalles.forEach(d => {
                    const subtotal = (d.precio * d.cantidad).toFixed(2);
                    
                    // CORRECCION DE IMAGEN: 
                    // Intenta usar 'img' (DTO), si no existe usa 'imagenUrl' (Entidad), si no, una por defecto
                    const imagenFinal = d.img || d.imagenUrl || 'default.png';

                    html += `
                        <tr class="cart-row" style="border-bottom: 1px solid #ccc;">
                            <td style="padding: 15px 0;">
                                <div class="cart-item" style="display: flex; align-items: center; gap: 15px;">
                                    <button onclick="eliminarProducto(${d.id})" 
                                       class="remove-btn" 
                                       style="border:none; background:none; color: red; font-weight: bold; font-size: 18px; cursor:pointer;">
                                       ✕
                                    </button>
                                    
                                    <img src="albumcovers/${imagenFinal}" 
                                         class="cart-thumb" 
                                         style="width: 80px; height: 80px; object-fit: cover;"
                                         alt="cover">
                                    
                                    <div class="product-info">
                                        <p class="product-name" style="font-weight: bold; margin: 0;">${d.nombreAlbum || d.nombre}</p>
                                        <p class="product-artist" style="margin: 0; color: #666;">${d.nombreArtista || d.artista}</p>
                                    </div>
                                </div>
                            </td>
                            <td class="price" style="text-align: center;">$${d.precio}</td>
                            <td style="text-align: center;">
                                <div class="qty">
                                    <span style="border: 1px solid #ccc; padding: 5px 15px;">${d.cantidad}</span>
                                </div>
                            </td>
                            <td class="subtotal" style="text-align: center; font-weight: bold;">$${subtotal}</td>
                        </tr>
                    `;
                });

                tbody.innerHTML = html;
            }

            function mostrarVacio() {
                document.getElementById("lista-productos").innerHTML = `
                    <tr>
                        <td colspan="4" style="text-align:center; padding: 40px;">
                            <p>Tu carrito está vacío.</p>
                            <a href="index.jsp" style="color: blue; text-decoration: underline;">Volver a la tienda</a>
                        </td>
                    </tr>`;
                document.getElementById("total-carrito").innerText = "$0.00";
            }

            async function eliminarProducto(idDetalle) {
                if(!confirm("¿Eliminar este producto?")) return;

                try {
                    const response = await fetch(`api/carrito/${ID_CARRITO_ACTUAL}/producto/${idDetalle}`, {
                        method: 'DELETE'
                    });

                    if (response.ok) {
                        cargarCarrito(); 
                    } else {
                        alert("No se pudo eliminar el producto.");
                    }
                } catch (error) {
                    console.error(error);
                    alert("Error de conexión.");
                }
            }
        </script>
        
    </body>
</html>