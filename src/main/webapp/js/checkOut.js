/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

const CHECKOUT_URL = "http://localhost:8080/ecommerce_2/api/checkout";

function show(el) {
    if (!el)
        return;
    el.style.display = "block";
}

function hide(el) {
    if (!el)
        return;
    el.style.display = "none";
}

function updatePaymentDetails() {
    const method = document.querySelector('input[name="payment_method"]:checked')?.value;

    const card = document.querySelector("#card-details");
    const transfer = document.querySelector("#transfer-details");
    const cod = document.querySelector("#cod-details");

    hide(card);
    hide(transfer);
    hide(cod);
    if (method === "tarjeta")
        show(card);
    if (method === "transferencia")
        show(transfer);
    if (method === "contra_entrega")
        show(cod);
}

function numOrNull(v) {
    const n = Number(v);
    return Number.isFinite(n) && n > 0 ? n : null;
}

async function postCheckout( { idCarrito, idDireccion, idPago, correo }) {
    const body = {
        correo,
        pedido: {idCarrito, idDireccion, idPago}
    };

    const res = await fetch(CHECKOUT_URL, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(body)
    });

    const text = await res.text();
    let data;
    try {
        data = JSON.parse(text);
    } catch {
        data = text;
    }

    if (!res.ok) {
        throw new Error(typeof data === "string" ? data : (data?.message ?? text));
    }

    return data;
}

document.addEventListener("DOMContentLoaded", () => {
    updatePaymentDetails();
    document.querySelectorAll('input[name="payment_method"]').forEach(radio => {
        radio.addEventListener("change", updatePaymentDetails);
    });

    const btn = document.querySelector("#btn-checkout");
    const btnMobile = document.querySelector("#mobile-submit-btn");
    const status = document.querySelector("#checkout-status");

    const setStatus = (msg) => {
        if (status)
            status.textContent = msg;
    };

    async function handleCheckout() {
        const idCarrito = numOrNull(document.querySelector("#idCarrito")?.value);
        const idDireccion = numOrNull(document.querySelector("#idDireccion")?.value);
        const idPago = numOrNull(document.querySelector("#idPago")?.value);
        const correo = (document.querySelector("#correo")?.value || "").trim();

        if (!idCarrito || !idDireccion || !idPago) {
            setStatus("Faltan ids: idCarrito / idDireccion / idPago.");
            return;
        }
        if (!correo) {
            setStatus("Falta correo.");
            return;
        }

        try {
            btn && (btn.disabled = true);
            btnMobile && (btnMobile.disabled = true);
            setStatus("Procesando checkout...");

            const result = await postCheckout({idCarrito, idDireccion, idPago, correo});
            setStatus("Pedido creado correctamente.");
            console.log("Pedido:", result);

        } catch (err) {
            setStatus("Error: " + err.message);
            console.error(err);
        } finally {
            btn && (btn.disabled = false);
            btnMobile && (btnMobile.disabled = false);
        }
    }

    btn?.addEventListener("click", handleCheckout);
    btnMobile?.addEventListener("click", handleCheckout);

    function show(el) {
        if (el)
            el.style.display = "block";
    }
    function hide(el) {
        if (el)
            el.style.display = "none";
    }

    function updatePaymentDetails() {
        const radios = Array.from(document.querySelectorAll('input[name="payment_method"]'));
        if (radios.length === 0) {
            console.warn("No encontré radios con name='payment_method'");
            return;
        }

        // ✅ Asegurar que haya uno seleccionado
        let checked = radios.find(r => r.checked);
        if (!checked) {
            radios[0].checked = true;
            checked = radios[0];
        }

        const method = checked.value;

        const card = document.getElementById("card-details");
        const transfer = document.getElementById("transfer-details");
        const cod = document.getElementById("cod-details");

        // Oculta todo
        hide(card);
        hide(transfer);
        hide(cod);

        // Muestra el seleccionado
        if (method === "tarjeta")
            show(card);
        else if (method === "transferencia")
            show(transfer);
        else if (method === "contra_entrega")
            show(cod);

        console.log("payment_method =", method);
    }

    document.addEventListener("DOMContentLoaded", () => {
        updatePaymentDetails();

        // ✅ Event delegation (funciona aunque hagas click en label o el contenedor)
        document.addEventListener("change", (e) => {
            if (e.target && e.target.matches('input[name="payment_method"]')) {
                updatePaymentDetails();
            }
        });
    });
});
