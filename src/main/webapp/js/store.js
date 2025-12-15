

var productosGlobales = [];

document.addEventListener("DOMContentLoaded", () => {
    cargarProductos();
});

async function cargarProductos() {
    // Referencias a contenedores (store.jsp y formatos)
    const containerStore = document.getElementById("contenedor-productos");
    const containerFormato = document.getElementById("contenedorProductos");
    
    const containerRec = document.getElementById("container-recommended");
    const containerRock = document.getElementById("container-rock");
    const containerPop = document.getElementById("container-pop");
    if (!containerStore && !containerRec && !containerFormato && !containerRock && !containerPop)
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
                    return grupo.some(p => {
                        if (p.generos && Array.isArray(p.generos)) {
                            // Ajuste para leer género sea string o objeto
                            return p.generos.some(g => {
                                const nombreG = typeof g === 'string' ? g : g.nombre;
                                return nombreG.toLowerCase().includes(generoUrl.toLowerCase());
                            });
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
            containerRec.innerHTML = "";
            const popList = listaAgrupada.slice(0, 5);
            popList.forEach(grupo => {
                containerRec.appendChild(crearTarjetaAgrupada(grupo, true));
            });
        }

        if (containerRock) {
            containerRock.innerHTML = "";
            const rockList = listaAgrupada.filter(grupo => 
                grupo.some(p => p.generos && p.generos.some(g => {
                    const val = typeof g === 'string' ? g : g.nombre;
                    return val.toLowerCase().includes('rock') || val.toLowerCase().includes('alternativo');
                }))
            ).slice(0, 5);
            
            rockList.forEach(grupo => {
                containerRock.appendChild(crearTarjetaAgrupada(grupo, true));
            });
        }
        if (containerPop) {
            containerPop.innerHTML = "";
            const popGenreList = listaAgrupada.filter(grupo => 
                grupo.some(p => p.generos && p.generos.some(g => {
                    const val = typeof g === 'string' ? g : g.nombre;
                    return val.toLowerCase().includes('pop');
                }))
            ).slice(0, 5);
            
            popGenreList.forEach(grupo => {
                containerPop.appendChild(crearTarjetaAgrupada(grupo, true));
            });
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

    const linkDetalle = `details.jsp?id=${pPrincipal.idProducto}`;

    const priceHtml = !esIndex
            ? `<span class="card-price" style="font-weight:bold;">$${pPrincipal.precio}</span>`
            : '';

    el.innerHTML = `
    <a href="${linkDetalle}" class="album-link" style="text-decoration:none; color:inherit;">
        <img src="${imgUrl}" onerror="this.src='icons/cdicon.png'" style="width: 100%; height: auto;">
        <h3>${pPrincipal.albumNombre || pPrincipal.nombre}</h3>
        <p>${pPrincipal.artistaNombre || 'Artista'}</p>
        <div class="card-footer" style="display:flex; justify-content:space-between; align-items:center; margin-top:5px;">
            <div style="display:flex;">${iconosHtml}</div>
            ${priceHtml}
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