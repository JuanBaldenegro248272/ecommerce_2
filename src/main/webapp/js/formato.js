/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function filtrarProductos(filtro) {
    const contenedor = document.getElementById("contenedorProductos");
    if (!window.productosGlobales || window.productosGlobales.length === 0) {
        console.log("Esperando productos o lista vacía...");
        return;
    }

    contenedor.innerHTML = "";

    if (filtro === 'todos') {
        renderizar(window.productosGlobales);
    } else {
        const filtrados = window.productosGlobales.filter(grupo => {
            return grupo.some(p => {
                const fmtOriginal = p.formato || (p.formatoProducto ? p.formatoProducto.nombre : '');
                const fmt = fmtOriginal.toLowerCase();
                const filtroMin = filtro.toLowerCase();
                if (filtroMin === 'vinilo') {
                    return fmt.includes('vinyl') || fmt.includes('lp') || fmt.includes('vinilo');
                }
                return fmt.includes(filtroMin);
            });
        });

        renderizar(filtrados);
    }
}

function buscarProductos() {
    const texto = document.getElementById('inputBusqueda').value.toLowerCase();
    const contenedor = document.getElementById("contenedorProductos");
    contenedor.innerHTML = "";

    if (!window.productosGlobales)
        return;

    const filtrados = window.productosGlobales.filter(grupo => {
        const p = grupo[0];
        const album = (p.albumNombre || p.nombre || '').toLowerCase();
        const artista = (p.artistaNombre || (p.artista ? p.artista.nombre : '') || '').toLowerCase();

        return album.includes(texto) || artista.includes(texto);
    });

    renderizar(filtrados);
}

function renderizar(lista) {
    const contenedor = document.getElementById("contenedorProductos");

    if (lista.length === 0) {
        contenedor.innerHTML = "<p style='text-align:center; width:100%;'>No se encontraron productos con este filtro.</p>";
        return;
    }

    lista.forEach(grupo => {
        if (typeof crearTarjetaAgrupada === 'function') {
            const tarjeta = crearTarjetaAgrupada(grupo, false);
            tarjeta.style.width = "100%";
            contenedor.appendChild(tarjeta);
        }
    });
}