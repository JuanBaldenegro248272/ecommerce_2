/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

const CHECKOUT_URL = "http://localhost:8080/ecommerce_2/resources/checkout";
const CART_URL = "resources/carrito";

document.addEventListener("DOMContentLoaded", () => {
    cargarDatosResumen();
    setupPaymentTabs();
    setupSubmitButton();
});

async function cargarDatosResumen() {
    try {
        const response = await fetch(CART_URL);
        if (!response.ok)
            throw new Error("Error cargando carrito");

        const carrito = await response.json();
        const inputIdCarrito = document.getElementById("idCarrito");
        if (inputIdCarrito && carrito.id) {
            inputIdCarrito.value = carrito.id;
        }

        const total = carrito.total || 0;
        const envio = 50.00;
        const subtotal = total;
        const totalFinal = subtotal + envio;

        document.getElementById("summary-subtotal").innerText = "$" + subtotal.toFixed(2);
        document.getElementById("summary-shipping").innerText = "$" + envio.toFixed(2);
        document.getElementById("summary-total").innerText = "$" + totalFinal.toFixed(2);

    } catch (error) {
        console.error("Error cargando resumen:", error);
        document.getElementById("summary-total").innerText = "$ -.--";
    }
}

function setupPaymentTabs() {
    const radios = document.querySelectorAll('input[name="payment_method"]');

    function updateVisibility() {
        const selected = document.querySelector('input[name="payment_method"]:checked')?.value;
        document.querySelectorAll('.payment-details').forEach(el => el.style.display = 'none');

        if (selected === 'tarjeta')
            document.getElementById('card-details').style.display = 'block';
        if (selected === 'transferencia')
            document.getElementById('transfer-details').style.display = 'block';
        if (selected === 'contra_entrega')
            document.getElementById('cod-details').style.display = 'block';
    }

    radios.forEach(r => r.addEventListener('change', updateVisibility));
    updateVisibility(); // Ejecutar al inicio
}

function setupSubmitButton() {
    const btn = document.getElementById("btn-checkout");
    if (btn)
        btn.addEventListener("click", handleCheckout);
}

async function handleCheckout() {
    const status = document.getElementById("checkout-status");
    const setStatus = (msg) => {
        if (status)
            status.textContent = msg;
    };

    const idCarrito = document.getElementById("idCarrito")?.value;
    const idDireccion = document.getElementById("idDireccion")?.value || "1";
    const idPago = "1";
    const correo = document.getElementById("correo")?.value || "usuario@ejemplo.com";

    if (!idCarrito) {
        setStatus("Error: No se identificó el carrito. Recarga la página.");
        return;
    }

    try {
        setStatus("Procesando...");
        const token = localStorage.getItem('jwt_token');
        if (!token) {
            setStatus("Error: No has iniciado sesión");
            return;
        }

        const body = {
            idCarrito: Number(idCarrito),
            idDireccion: Number(idDireccion),
            idPago: Number(idPago)
        };

        const res = await fetch(CHECKOUT_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token  // <--- AGREGAR ESTA LÍNEA
            },
            body: JSON.stringify(body)
        });
        if (res.ok) {
            setStatus("¡Pedido creado correctamente!");
            alert("Gracias por tu compra. Tu pedido ha sido registrado.");
            window.location.href = "store.jsp"; // Redirigir a la tienda
        } else {
            const txt = await res.text();
            setStatus("Error al crear pedido: " + txt);
        }
    } catch (err) {
        console.error(err);
        setStatus("Error de conexión.");
    }
}