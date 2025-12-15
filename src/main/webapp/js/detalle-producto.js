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
        const response = await fetch(`resources/productos/${idProducto}`);
        if (!response.ok)
            throw new Error("Error al cargar producto");

        const producto = await response.json();
        const img = document.getElementById('img-detalle');
        if (img)
            img.src = producto.albumImagenUrl ? `albumcovers/${producto.albumImagenUrl}` : 'icons/cdicon.png';

        const nombre = document.getElementById('nombre-detalle');
        if (nombre)
            nombre.textContent = producto.albumNombre || producto.nombre;

        const artista = document.getElementById('artista-detalle');
        if (artista)
            artista.textContent = producto.artistaNombre;

        const desc = document.getElementById('desc-detalle');
        if (desc)
            desc.textContent = producto.descripcion;

        const precio = document.getElementById('precio-detalle');
        if (precio)
            precio.textContent = `$${producto.precio}`;

        const btnAgregar = document.getElementById('btn-agregar-carrito');
        if (btnAgregar) {
            btnAgregar.onclick = () => agregarAlCarritoDetalle(producto.idProducto);
        }
        cargarRecomendados(idProducto);

    } catch (error) {
        console.error("Error:", error);
    }
});

async function cargarRecomendados(idActual) {
    try {
        const response = await fetch('resources/productos');
        const productos = await response.json();

        let otrosProductos = productos.filter(p => p.idProducto !== idActual);
        const albumesVistos = new Set();
        const recomendadosUnicos = otrosProductos.filter(p => {
            const clave = p.albumId || p.albumNombre;
            if (albumesVistos.has(clave)) {
                return false; 
            }
            albumesVistos.add(clave);
            return true;
        }).slice(0, 4);
        const contenedor = document.getElementById('contenedor-recomendados');
        if (!contenedor)
            return;

        contenedor.innerHTML = "";

        recomendadosUnicos.forEach(p => {
            const card = document.createElement('div');
            card.className = 'album-card-simple'; // Nueva clase para controlar estilo individual

            const imgUrl = p.albumImagenUrl ? `albumcovers/${p.albumImagenUrl}` : 'icons/cdicon.png';

            card.innerHTML = `
                <a href="details.jsp?id=${p.idProducto}">
                    <div class="card-image-wrapper">
                        <img src="${imgUrl}" alt="${p.albumNombre}">
                    </div>
                    <h4>${p.albumNombre}</h4>
                    <p>${p.artistaNombre}</p>
                    <span class="price">$${p.precio}</span>
                </a>
            `;
            contenedor.appendChild(card);
        });

    } catch (error) {
        console.error("Error cargando recomendados:", error);
    }
}

async function agregarAlCarritoDetalle(idProducto) {
    const inputCantidad = document.getElementById('cantidad');
    const cantidad = inputCantidad ? parseInt(inputCantidad.value) : 1;

    try {
        for (let i = 0; i < cantidad; i++) {
            await fetch(`resources/carrito/agregar?idProducto=${idProducto}`, {method: 'POST'});
        }

        const modal = document.getElementById('cartModal');
        if (modal) {
            modal.style.display = 'flex';
        } else {
            alert(`¡Se añadieron ${cantidad} productos al carrito!`);
        }

    } catch (error) {
        console.error(error);
        alert("Error al conectar con el carrito");
    }
}