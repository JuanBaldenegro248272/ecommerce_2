/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener('DOMContentLoaded', async () => {
    const params = new URLSearchParams(window.location.search);
    const idProducto = params.get('id');

    if (!idProducto) {
        alert("Producto no especificado");
        window.location.href = 'store.jsp';
        return;
    }

    try {
        const response = await fetch(`api/productos/${idProducto}`);
        if (!response.ok)
            throw new Error("Error al cargar producto");

        const producto = await response.json();
        document.getElementById('img-detalle').src = producto.imagenUrl;
        document.getElementById('nombre-detalle').textContent = producto.nombre;
        document.getElementById('artista-detalle').textContent = producto.artista;
        document.getElementById('precio-detalle').textContent = `$${producto.precio}`;
        document.getElementById('desc-detalle').textContent = producto.descripcion;
        document.getElementById('btn-agregar-carrito').onclick = () => agregarAlCarrito(producto.id);

    } catch (error) {
        console.error(error);
    }
});

async function enviarResena() {
    const params = new URLSearchParams(window.location.search);
    const idProducto = params.get('id');
    const idUsuarioLogueado = localStorage.getItem('idUsuario');
    if (!idUsuarioLogueado) {
        alert("Debes iniciar sesión para dejar una reseña.");
        window.location.href = "login.jsp";
        return;
    }

    const comentario = document.getElementById('txt-comentario').value;
    const calificacion = document.getElementById('sel-calificacion').value;
    const data = {
        idCliente: parseInt(idUsuarioLogueado),
        idProducto: parseInt(idProducto),
        calificacion: parseInt(calificacion),
        comentario: comentario
    };

    try {
        const resp = await fetch('api/resenas', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (resp.ok) {
            alert("Reseña en espera de aprobación para ser publicada");
            location.reload();
        } else {
            const errorData = await resp.json();
            alert("Error: " + (errorData.error || "No se pudo enviar la reseña"));
        }
    } catch (error) {
        console.error("Error de red:", error);
        alert("Hubo un problema de conexión al enviar la reseña.");
    }
}


