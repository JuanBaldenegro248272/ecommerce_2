<%-- 
    Document   : checkout
    Created on : Dec 13, 2025, 2:56:00 PM
    Author     : jrasc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Checkout | Confirmar Compra</title>
        <link rel="stylesheet" href="styles/global.css">
        <link rel="stylesheet" href="styles/styles.css">
    </head>
    <body>

        <main class="checkout-container">
            <div class="checkout-main">
                <h1>Confirmación de Pedido</h1>

                <form id="checkout-form" class="checkout-form">
                    <input type="hidden" id="idCarrito" value="${idCarrito}">
                    <input type="hidden" id="idDireccion" value="${idDireccion}">
                    <input type="hidden" id="idPago" value="${idPago}">
                    <input type="hidden" id="correo" value="${correo}">

                    <h2>Datos de Envío</h2>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="nombre">Nombre</label>
                            <input type="text" id="nombre" name="nombre" required>
                        </div>
                        <div class="form-group">
                            <label for="apellido">Apellido</label>
                            <input type="text" id="apellido" name="apellido" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="direccion">Dirección</label>
                        <input type="text" id="direccion" name="direccion" placeholder="Calle, número, colonia..." required>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="ciudad">Ciudad</label>
                            <input type="text" id="ciudad" name="ciudad" required>
                        </div>
                        <div class="form-group">
                            <label for="codigo_postal">Código Postal</label>
                            <input type="text" id="codigo_postal" name="codigo_postal" pattern="[0-9]{5}" title="5 dígitos numéricos" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="telefono">Teléfono</label>
                        <input type="tel" id="telefono" name="telefono" required>
                    </div>

                    <h2>Métodos de Pago</h2>
                    <div id="payment-section">

                        <div class="payment-option">
                            <label>
                                <input type="radio" name="payment_method" value="tarjeta" checked>
                                Tarjeta de Crédito / Débito
                            </label>
                            <div id="card-details" class="payment-details">
                                <div class="form-group">
                                    <label>Número de Tarjeta</label>
                                    <input type="text" name="card_number" placeholder="0000 0000 0000 0000">
                                </div>
                                <div class="form-row">
                                    <div class="form-group">
                                        <label>Vencimiento (MM/AA)</label>
                                        <input type="text" name="card_expiry" placeholder="MM/AA">
                                    </div>
                                    <div class="form-group">
                                        <label>CVC</label>
                                        <input type="text" name="card_cvc" placeholder="123">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="payment-option">
                            <label>
                                <input type="radio" name="payment_method" value="transferencia">
                                Transferencia Bancaria
                            </label>
                            <div id="transfer-details" class="payment-details">
                                <p>Realiza tu pago a la cuenta CLABE: <strong>1234 5678 9012 3456</strong> (BanPaís).<br>
                                    Tu pedido se procesará una vez confirmado el pago.</p>
                            </div>
                        </div>

                        <div class="payment-option">
                            <label>
                                <input type="radio" name="payment_method" value="contra_entrega">
                                Pago Contra Entrega (Efectivo)
                            </label>
                            <div id="cod-details" class="payment-details" style="display:none;">
                                <p>Paga en efectivo al repartidor cuando recibas tu paquete.</p>
                            </div>
                        </div>
                    </div>
                    <button type="button" class="checkout-submit-btn" style="display:none;" id="mobile-submit-btn">
                        Realizar Pedido
                    </button>
                    <div id="checkout-status""></div>
                </form>
            </div>

            <aside class="checkout-sidebar">
                <h3>Resumen del Pedido</h3>

                <div class="summary-details">
                    <div class="summary-line">
                        <span>Subtotal</span>
                        <span>$ ${subtotal}</span> 
                    </div>
                    <div class="summary-line">
                        <span>Envío</span>
                        <span>$ ${costoEnvio}</span>
                    </div>
                    <div class="summary-line">
                        <span>Impuestos</span>
                        <span>$ ${impuestos}</span>
                    </div>
                </div>

                <div class="summary-total">
                    <span>TOTAL</span>
                    <span class="total-value">$ ${totalFinal}</span>
                </div>
                <button type="button" id="btn-checkout" class="checkout-submit-btn">
                    Realizar Pedido
                </button>
            </aside>
        </main>
        <script src="js/checkOut.js"></script>
    </body>
</html>