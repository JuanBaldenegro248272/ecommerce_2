const CART_KEY = 'ecostore_cart_v1';


/**
 * Obtiene el carrito actual desde LocalStorage
 * Retorna un Array de objetos.
 */
function getCart() {
    const cart = localStorage.getItem(CART_KEY);
    return cart ? JSON.parse(cart) : [];
}

/**
 * Guarda el carrito en LocalStorage
 */
function saveCart(cart) {
    localStorage.setItem(CART_KEY, JSON.stringify(cart));
}

/**
 * Agrega un producto al carrito.
 * Lógica similar a tu DetalleCarrito.java pero en JS
 * @param {Object} product - { id, name, artist, format, price, image }
 */
function addToCart(product) {
    let cart = getCart();
    const existingItem = cart.find(item => item.id === product.id);

    if (existingItem) {
        existingItem.quantity += 1; 
    } else {
        cart.push({ ...product, quantity: 1 });
    }

    saveCart(cart);
    
    alert(`Product successfully added to your cart:\n${product.name} - ${product.artist}`); 

}

/**
 * Actualiza la cantidad (+ o -)
 */
function updateQuantity(productId, change) {
    let cart = getCart();
    const item = cart.find(i => i.id === productId);

    if (item) {
        item.quantity += change;
        if (item.quantity < 1) item.quantity = 1; // Mínimo 1
        saveCart(cart);
        renderCart(); // Volver a pintar la tabla
    }
}

/**
 * Elimina un producto del carrito
 */
function removeFromCart(productId) {
    let cart = getCart();
    cart = cart.filter(item => item.id !== productId); // Filtramos el que queremos borrar
    saveCart(cart);
    renderCart(); // Volver a pintar
}

// --- 2. LÓGICA DE VISTA (RENDERIZADO EN CARRITO.JSP) ---

function renderCart() {
    const tableBody = document.querySelector('.cart-table tbody');
    const subtotalElement = document.querySelector('.summary-value'); // Donde dice el subtotal
    const totalElement = document.querySelector('.total-value'); // Donde dice el total
    
    // Si no estamos en la página del carrito (tableBody es null), salimos
    if (!tableBody) return;

    const cart = getCart();
    tableBody.innerHTML = ''; // Limpiamos la tabla (borramos lo hardcodeado)

    let totalAmount = 0;

    if (cart.length === 0) {
        tableBody.innerHTML = '<tr><td colspan="4" style="text-align:center; padding: 30px; font-size: 1.2rem;">Your cart is empty</td></tr>';
    } else {
        cart.forEach(item => {
            const itemSubtotal = item.price * item.quantity;
            totalAmount += itemSubtotal;

            // Generamos el HTML exacto que tienes en tu JSP
            const row = `
                <tr class="cart-row">
                    <td>
                        <div class="cart-item">
                            <button class="remove-btn" onclick="removeFromCart('${item.id}')">✕</button>
                            <img src="${item.image}" alt="${item.name}" class="cart-thumb">
                            <div class="product-info">
                                <p class="product-name">${item.name}</p>
                                <p class="product-artist">${item.artist}</p>
                                <span class="format-icon">${item.format}</span>
                            </div>
                        </div>
                    </td>
                    <td class="price">$${item.price.toFixed(2)}</td>
                    <td>
                        <div class="qty">
                            <button class="qty-btn" onclick="updateQuantity('${item.id}', -1)">−</button>
                            <input class="qty-input" type="text" value="${item.quantity}" readonly>
                            <button class="qty-btn" onclick="updateQuantity('${item.id}', 1)">+</button>
                        </div>
                    </td>
                    <td class="subtotal">$${itemSubtotal.toFixed(2)}</td>
                </tr>
            `;
            tableBody.innerHTML += row;
        });
    }

    // Actualizar Totales en el Sidebar
    if(subtotalElement) subtotalElement.textContent = `$${totalAmount.toFixed(2)}`;
    if(totalElement) totalElement.textContent = `$${totalAmount.toFixed(2)}`;
}

// Inicialización cuando carga la página
document.addEventListener('DOMContentLoaded', () => {
    renderCart(); // Intentar pintar el carrito

    // Botón de Vaciar Carrito (Empty Cart)
    const emptyBtn = document.querySelector('.btn-empty');
    if(emptyBtn) {
        emptyBtn.addEventListener('click', () => {
            if(confirm("Are you sure you want to empty your cart?")) {
                saveCart([]); // Guardar array vacío
                renderCart(); // Repintar
            }
        });
    }
});