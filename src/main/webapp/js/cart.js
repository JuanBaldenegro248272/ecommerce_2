/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


async function addToCart(product) {
    const idProducto = product.id;

    try {
        const response = await fetch('resources/carrito/agregar?idProducto=' + idProducto, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (response.ok) {
            alert(`¡${product.name} agregado al carrito!`);
        } else {
            const mensaje = await response.text();
            alert("No se pudo agregar: " + mensaje);
        }

    } catch (error) {
        console.error("Error de conexión:", error);
        alert("Error de red. Verifica que el servidor esté corriendo.");
    }
}

async function removeFromCart(idDetalle) {
    if (!confirm("¿Estás seguro de eliminar este producto?")) {
        return;
    }

    try {
        const response = await fetch(`resources/carrito/eliminar/${idDetalle}`, {
            method: 'DELETE'
        });
        if (response.ok) {
            window.location.reload();
        } else {
            alert("Error al eliminar el producto.");
        }

    } catch (error) {
        console.error("Error:", error);
        alert("Error de conexión.");
    }
}