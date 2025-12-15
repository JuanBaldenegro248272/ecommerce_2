/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
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
            localStorage.setItem('jwt_token', data.token);
            if (data.usuario)
                localStorage.setItem('usuario_nombre', data.usuario);
            alert("Login exitoso");
            if (correo.includes("admin")) {
                window.location.href = 'dashboard-admin.jsp';
            }
            if (data.idUsuario) {
                localStorage.setItem('idUsuario', data.idUsuario);
            } else {
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