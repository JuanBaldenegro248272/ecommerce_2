/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


var productosGlobales = [];

document.addEventListener("DOMContentLoaded", () => {
    cargarProductos();
});

async function cargarProductos() {
    const containerStore = document.getElementById("contenedor-productos");
    const containerRec = document.getElementById("container-recommended");
    const containerFormato = document.getElementById("contenedorProductos");

    if (!containerStore && !containerRec && !containerFormato)
        return;

    try {
        const response = await fetch('resources/productos');
        if (!response.ok)
            throw new Error("Error de conexión");

        const productos = await response.json();

        const grupos = {};
        productos.forEach(p => {
            const idAlbum = p.albumId || p.idProducto;
            if (!grupos[idAlbum])
                grupos[idAlbum] = [];
            grupos[idAlbum].push(p);
        });

        const listaAgrupada = Object.values(grupos);

        productosGlobales = listaAgrupada;

        if (containerFormato) {
            containerFormato.innerHTML = "";
            const urlParams = new URLSearchParams(window.location.search);
            const generoUrl = urlParams.get('genero');
            let listaParaMostrar = listaAgrupada;
            if (generoUrl) {
                console.log("Filtrando por género:", generoUrl);
                listaParaMostrar = listaAgrupada.filter(grupo => {
                    const p = grupo[0];
                    if (generoBuscado == "new" || generoBuscado == "nuevos") {
                        const fechaActual = new Date();
                        const anioActual = fechaActual.getFullYear();
                        return p.
                    }
                    return grupo.some(p => {
                        if (p.generos && Array.isArray(p.generos)) {
                            return p.generos.some(g => g.toLowerCase().includes(generoUrl.toLowerCase()));
                        }
                        return (p.descripcion + " " + p.nombre).toLowerCase().includes(generoUrl.toLowerCase());
                    });
                });
            }
            if (listaParaMostrar.length === 0) {
                containerFormato.innerHTML = `<p style="text-align:center; width:100%; margin-top:20px;">No se encontraron productos de ${generoUrl}.</p>`;
            } else {
                listaParaMostrar.forEach(grupo => {
                    const tarjeta = crearTarjetaAgrupada(grupo, false);
                    tarjeta.style.width = "100%";
                    containerFormato.appendChild(tarjeta);
                });
            }
        }

        if (containerStore) {
            containerStore.innerHTML = "";
            listaAgrupada.forEach(grupo => containerStore.appendChild(crearTarjetaAgrupada(grupo, false)));
        }

        if (containerRec) {
            const popList = listaAgrupada.slice(0, 5);
        }

    } catch (error) {
        console.error("Error:", error);
    }
}

function crearTarjetaAgrupada(grupo, esIndex) {
    const pPrincipal = grupo[0];
    const el = document.createElement("article");
    el.className = "album";

    const imgUrl = pPrincipal.albumImagenUrl ? `albumcovers/${pPrincipal.albumImagenUrl}` : 'icons/cdicon.png';

    let iconosHtml = '';
    grupo.forEach(variante => {
        let fmt = variante.formato || (variante.formatoProducto ? variante.formatoProducto.nombre : '');
        let iconPath = getIconPath(fmt);
        iconosHtml += `<img src="${iconPath}" title="${fmt}" style="width: 24px; margin-right: 5px;">`;
    });

    const linkDetalle = `details.html?id=${pPrincipal.idProducto}`;

    el.innerHTML = `
        <a href="${linkDetalle}" class="album-link" style="text-decoration:none; color:inherit;">
            <img src="${imgUrl}" onerror="this.src='icons/cdicon.png'" style="width: 100%; height: auto;">
            <h3>${pPrincipal.albumNombre || pPrincipal.nombre}</h3>
            <p>${pPrincipal.artistaNombre || 'Artista'}</p>
            <div class="card-footer" style="display:flex; justify-content:space-between; align-items:center; margin-top:5px;">
                <div style="display:flex;">${iconosHtml}</div>
                ${!esIndex ? `<span class="card-price" style="font-weight:bold;">$${pPrincipal.precio}</span>` : ''}
            </div>
        </a>
        <button class="btn-add" onclick="agregarAlCarrito(${pPrincipal.idProducto})" style="width:100%; margin-top:10px;">
            ADD TO CART
        </button>
    `;
    return el;
}

function getIconPath(formato) {
    if (!formato)
        return 'icons/cdicon.png';
    const fmt = formato.toUpperCase();
    if (fmt.includes('VINYL') || fmt.includes('LP') || fmt.includes('VINILO'))
        return 'icons/vinylicon.png';
    if (fmt.includes('CASSETTE'))
        return 'icons/casseteicon.png';
    return 'icons/cdicon.png';
}

async function agregarAlCarrito(idProducto) {
    try {
        const response = await fetch(`resources/carrito/agregar?idProducto=${idProducto}`, {method: 'POST'});
        if (response.ok) {
            const modal = document.getElementById('cartModal');
            if (modal)
                modal.style.display = 'flex';
            else
                alert("¡Producto agregado!");
        } else {
            alert("Error al agregar");
        }
    } catch (error) {
        console.error(error);
    }
}

function closeModal() {
    const modal = document.getElementById('cartModal');
    if (modal)
        modal.style.display = 'none';
}