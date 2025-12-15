/* * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.getElementById('loginForm').addEventListener('submit', async function (event) {
    event.preventDefault();
    const correo = document.querySelector('input[name="correo"]').value;
    const contrasena = document.querySelector('input[name="contrasena"]').value;

    const datos = {correo, contrasena};

    try {
        const response = await fetch('resources/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(datos)
        });

        if (response.ok) {
            const data = await response.json();
            
            // 1. Guardar Token
            localStorage.setItem('jwt_token', data.token);
            
            // 2. Guardar Nombre de usuario si existe
            if (data.usuario) {
                localStorage.setItem('usuario_nombre', data.usuario);
            }
            
            // 3. Guardar ID de usuario si existe
            if (data.idUsuario) {
                localStorage.setItem('idUsuario', data.idUsuario);
            }
            
            alert("Login exitoso");
            
            // 4. Lógica de Redirección Corregida
            // Verifica si es admin o cliente y redirige
            if (correo.includes("admin")) {
                window.location.href = 'dashboard-admin.jsp';
            } else {
                // Ahora sí redirige al cliente al index
                window.location.href = 'index.jsp';
            }

        } else {
            const errorData = await response.json();
            alert("Error: " + (errorData.error || "Credenciales inválidas"));
        }

    } catch (error) {
        console.error("Error en login:", error);
        alert("Error de conexión con el servidor.");
    }
});