/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


document.addEventListener("DOMContentLoaded", () => {
    cargarProductos();
});

async function cargarProductos() {
    const containerStore = document.getElementById("contenedor-productos");
    const containerRec = document.getElementById("container-recommended");
    const containerRock = document.getElementById("container-rock");
    const containerPop = document.getElementById("container-pop");

    if (!containerStore && !containerRec)
        return;

    try {
        const response = await fetch('resources/productos');
        if (!response.ok)
            throw new Error("Error de conexión");

        const productos = await response.json();
        const grupos = {};
        productos.forEach(p => {
            const idAlbum = p.albumId;
            if (!grupos[idAlbum]) {
                grupos[idAlbum] = [];
            }
            grupos[idAlbum].push(p);
        });

        const listaAgrupada = Object.values(grupos);

        if (containerStore) {
            containerStore.innerHTML = "";
            if (listaAgrupada.length === 0) {
                containerStore.innerHTML = "<p>No hay productos.</p>";
            } else {
                listaAgrupada.forEach(grupo => {
                    containerStore.appendChild(crearTarjetaAgrupada(grupo, false));
                });
            }
        }

        if (containerRec) {
            const popList = listaAgrupada.slice(0, 5);
            const rockList = listaAgrupada.slice(5, 10);
            const recList = listaAgrupada.slice(10, 15);

            if (containerPop) {
                containerPop.innerHTML = "";
                popList.forEach(g => containerPop.appendChild(crearTarjetaAgrupada(g, true)));
            }
            if (containerRock) {
                containerRock.innerHTML = "";
                rockList.forEach(g => containerRock.appendChild(crearTarjetaAgrupada(g, true)));
            }
            if (containerRec) {
                containerRec.innerHTML = "";
                recList.reverse().forEach(g => containerRec.appendChild(crearTarjetaAgrupada(g, true)));
            }
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
        let iconPath = getIconPath(variante.formato);
        iconosHtml += `
            <img src="${iconPath}" 
                 title="Disponible en ${variante.formato}" 
                 style="width: 24px; height: auto; margin-right: 5px;">
        `;
    });

    const linkDetalle = `details.jsp?id=${pPrincipal.albumId}`;

    const html = `
        <a href="${linkDetalle}" class="album-link">
            <img src="${imgUrl}" alt="${pPrincipal.albumNombre}" onerror="this.src='icons/cdicon.png'" style="width: 100%; height: auto;">
            <h3>${pPrincipal.albumNombre}</h3>
            <p>${pPrincipal.artistaNombre}</p>
            
            <div class="card-footer">
                <div style="display:flex; align-items:center;">
                    ${iconosHtml}
                </div>
                
                ${!esIndex ? `<span class="card-price">$${pPrincipal.precio}</span>` : ''} 
            </div>
        </a>
        <button class="btn-add" onclick="agregarAlCarrito(${pPrincipal.idProducto})">
            ADD TO CART
        </button>
    `;

    el.innerHTML = html;
    return el;
}

function getIconPath(formato) {
    if (!formato)
        return 'icons/cdicon.png';
    const fmt = formato.toUpperCase();
    if (fmt === 'VINYL')
        return 'icons/vinylicon.png';
    if (fmt === 'CASSETTE')
        return 'icons/casseteicon.png';
    if (fmt === 'CD')
        return 'icons/cdicon.png';
    return 'icons/cdicon.png';
}

async function agregarAlCarrito(idProducto) {
    try {
        const response = await fetch(`resources/carrito/agregar?idProducto=${idProducto}`, {
            method: 'POST'
        });

        if (response.ok) {
            const modal = document.getElementById('cartModal');
            if (modal)
                modal.style.display = 'flex';
            else
                alert("¡Producto agregado!");
        } else {
            const txt = await response.text();
            alert("Error: " + txt);
        }
    } catch (error) {
        console.error(error);
        alert("Error de conexión.");
    }
}

function closeModal() {
    const modal = document.getElementById('cartModal');
    if (modal)
        modal.style.display = 'none';
}