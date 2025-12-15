/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


document.addEventListener("DOMContentLoaded", () => {
    if (document.getElementById("lista-productos")) {
        console.log("Intentando cargar carrito...");
        cargarCarrito();
    }
});

async function cargarCarrito() {
    try {
        const response = await fetch('resources/carrito');
        const carrito = await response.json();
        console.log("LO QUE LLEGÓ DE JAVA:", carrito);

        if (response.status === 404 || !response.ok) {
            mostrarVacio();
            return;
        }
        renderizarTabla(carrito);

    } catch (error) {
        console.error("Error cargando carrito:", error);
        const tbody = document.getElementById("lista-productos");
        if (tbody) {
            tbody.innerHTML = `<tr><td colspan="4" style="color:red; text-align:center;">
                Error de conexión. Asegúrate de haber iniciado sesión.
            </td></tr>`;
        }
    }
}

function renderizarTabla(carrito) {
    const tbody = document.getElementById("lista-productos");
    const totalSpan = document.getElementById("total-carrito");

    if (!tbody || !totalSpan)
        return;

    let total = carrito.total || 0;
    totalSpan.innerText = "$" + total.toFixed(2);

    if (!carrito.detalles || carrito.detalles.length === 0) {
        mostrarVacio();
        return;
    }

    let html = "";
    carrito.detalles.forEach(d => {
        const subtotal = (d.precio * d.cantidad).toFixed(2);
        const imagenFinal = d.img || d.imagenUrl || 'default.png';
        const nombreProducto = d.nombreAlbum || d.nombre || "Producto";
        const nombreArtista = d.nombreArtista || d.artista || "";

        html += `
            <tr class="cart-row" style="border-bottom: 1px solid #ccc;">
                <td style="padding: 15px 0;">
                    <div class="cart-item" style="display: flex; align-items: center; gap: 15px;">
                        <button onclick="eliminarProducto(${d.id})" 
                           class="remove-btn" 
                           style="border:none; background:none; color: red; font-weight: bold; font-size: 18px; cursor:pointer;"
                           title="Eliminar producto">
                           ✕
                        </button>
                        
                        <img src="albumcovers/${imagenFinal}" 
                             class="cart-thumb" 
                             style="width: 80px; height: 80px; object-fit: cover;"
                             onerror="this.src='icons/cdicon.png'"
                             alt="cover">
            
                        <div class="product-info">
                            <p class="product-name" style="font-weight: bold; margin: 0;">${nombreProducto}</p>
                            <p class="product-artist" style="margin: 0; color: #666;">${nombreArtista}</p>
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
    const tbody = document.getElementById("lista-productos");
    const totalSpan = document.getElementById("total-carrito");

    if (tbody) {
        tbody.innerHTML = `
            <tr>
                <td colspan="4" style="text-align:center; padding: 40px;">
                    <p>Tu carrito está vacío.</p>
                    <a href="store.jsp" style="color: blue; text-decoration: underline;">Volver a la tienda</a>
                </td>
            </tr>`;
    }
    if (totalSpan) {
        totalSpan.innerText = "$0.00";
    }
}

async function eliminarProducto(idDetalle) {
    if (!confirm("¿Estás seguro de eliminar este producto del carrito?")) {
        return;
    }

    try {
        const response = await fetch(`resources/carrito/eliminar/${idDetalle}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            cargarCarrito();
        } else {
            const msg = await response.text();
            alert("No se pudo eliminar: " + msg);
        }
    } catch (error) {
        console.error("Error eliminando:", error);
        alert("Error de conexión al intentar eliminar.");
    }
}

async function addToCart(product) {
    const idProducto = product.id || product;
    try {
        const response = await fetch(`resources/carrito/agregar?idProducto=${idProducto}`, {
            method: 'POST'
        });

        if (response.ok) {
            alert("¡Producto agregado al carrito!");
        } else {
            const mensaje = await response.text();
            alert("Error al agregar: " + mensaje);
        }
    } catch (error) {
        console.error("Error:", error);
        alert("Error de red al agregar al carrito.");
    }
}