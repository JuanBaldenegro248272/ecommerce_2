/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('loginForm');
    const errorMessageDiv = document.getElementById('error-message');

    if (loginForm) {
        loginForm.addEventListener('submit', async (event) => {
            event.preventDefault();
            
            errorMessageDiv.style.display = 'none';
            errorMessageDiv.textContent = '';

            const correo = document.getElementById('email-login').value;
            const contrasena = document.getElementById('password-login').value;

            const credenciales = {
                correo: correo,
                contrasena: contrasena
            };

            try {
                const response = await fetch('resources/auth/login', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(credenciales)
                });

                if (response.ok) {
                    const usuario = await response.json();
                    console.log('Login exitoso:', usuario);
                    
                    window.location.href = 'index.jsp'; 
                } else {
                    const errorData = await response.json();
                    throw new Error(errorData.error || 'Error al iniciar sesión');
                }

            } catch (error) {
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
