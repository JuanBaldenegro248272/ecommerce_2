/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


document.addEventListener("DOMContentLoaded", () => {
    cargarMisPedidos();
});

async function cargarMisPedidos() {
    const table = document.getElementById("tabla-pedidos");
    const tbody = document.getElementById("lista-pedidos-body");
    const loading = document.getElementById("loading-msg");
    const errorMsg = document.getElementById("error-msg");
    const emptyMsg = document.getElementById("empty-msg");

    const token = localStorage.getItem('jwt_token');

    if (!token) {
        loading.style.display = 'none';
        errorMsg.textContent = "Debes iniciar sesión para ver tus pedidos.";
        errorMsg.style.display = 'block';
        return;
    }

    try {
        const response = await fetch('resources/pedidos/mis-pedidos', {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error("Error al obtener pedidos: " + response.statusText);
        }

        const pedidos = await response.json();
        loading.style.display = 'none';

        if (pedidos.length === 0) {
            emptyMsg.style.display = 'block';
            return;
        }

        table.style.display = 'table';
        tbody.innerHTML = "";

        pedidos.forEach(p => {
            const tr = document.createElement("tr");
            const fecha = new Date(p.fechaCompra).toLocaleDateString("es-MX", {
                year: 'numeric', month: 'long', day: 'numeric'
            });
            let claseEstado = "estado-badge";
            if (p.estado === "PENDIENTE")
                claseEstado += " estado-pendiente";
            else if (p.estado === "ENTREGADO" || p.estado === "ENVIADO")
                claseEstado += " estado-completado";
            else
                claseEstado += " estado-cancelado";

            tr.innerHTML = `
                <td>#${p.id}</td>
                <td>${fecha}</td>
                <td><span class="${claseEstado}">${p.estado}</span></td>
                <td>$${p.total.toFixed(2)}</td>
                <td>
                    <button class="btn-ver" onclick="verDetallePedido(${p.id})">Ver Detalles</button>
                </td>
            `;
            tbody.appendChild(tr);
        });

    } catch (error) {
        console.error(error);
        loading.style.display = 'none';
        errorMsg.textContent = "Hubo un problema cargando tu historial.";
        errorMsg.style.display = 'block';
    }
}

function verDetallePedido(id) {
    alert("Funcionalidad de detalle para el pedido #" + id + " (A implementar vista detallada)");
}