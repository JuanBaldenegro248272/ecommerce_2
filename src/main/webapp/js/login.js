/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('loginForm');
    const errorMessageDiv = document.getElementById('error-message');

    if (loginForm) {
        loginForm.addEventListener('submit', async (event) => {
            // 1. Evitar que el formulario se envíe de forma tradicional (recargando la página)
            event.preventDefault();
            
            // Limpiar mensajes de error previos
            errorMessageDiv.style.display = 'none';
            errorMessageDiv.textContent = '';

            // 2. Obtener los datos de los campos
            const correo = document.getElementById('email-login').value;
            const contrasena = document.getElementById('password-login').value;

            // 3. Crear el objeto JSON a enviar
            const credenciales = {
                correo: correo,
                contrasena: contrasena
            };

            try {
                // 4. Realizar la petición POST a la API REST
                const response = await fetch('resources/auth/login', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(credenciales)
                });

                // 5. Procesar la respuesta
                if (response.ok) {
                    // Login exitoso (Status 200)
                    const usuario = await response.json();
                    console.log('Login exitoso:', usuario);
                    
                    // Redirigir a la página principal o store
                    window.location.href = 'index.jsp'; 
                } else {
                    // Error de autenticación (Status 401, 400, 500)
                    const errorData = await response.json();
                    throw new Error(errorData.error || 'Error al iniciar sesión');
                }

            } catch (error) {
                // Mostrar el error en pantalla
                console.error('Error:', error);
                errorMessageDiv.textContent = error.message;
                errorMessageDiv.style.display = 'block';
                errorMessageDiv.style.color = '#721c24';
                errorMessageDiv.style.backgroundColor = '#f8d7da';
                errorMessageDiv.style.padding = '10px';
                errorMessageDiv.style.borderRadius = '5px';
                errorMessageDiv.style.marginBottom = '15px';
                errorMessageDiv.style.textAlign = 'center';
            }
        });
    }
});
