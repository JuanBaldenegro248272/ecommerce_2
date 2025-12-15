/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
document.addEventListener('DOMContentLoaded', () => {
    const btnEditar = document.getElementById('btn-editar');
    const btnCancelar = document.getElementById('btn-cancelar');
    const btnGuardar = document.getElementById('btn-guardar');
    // Seleccionamos el input de nombre
    const inputNombre = document.getElementById('nombre');
    
    // Al hacer clic en EDITAR
    btnEditar.addEventListener('click', () => {
        // 1. Habilitar el campo de nombre
        inputNombre.disabled = false;
        inputNombre.focus(); // Poner el cursor ahí

        // 2. Cambiar botones
        btnEditar.style.display = 'none';
        btnGuardar.style.display = 'inline-block';
        btnCancelar.style.display = 'inline-block';
    });

    // Al hacer clic en CANCELAR
    btnCancelar.addEventListener('click', () => {
        // La forma más fácil de cancelar es recargar la página para que vuelvan los datos originales del servidor
        location.reload();
    });

    // El botón GUARDAR es tipo "submit", así que el formulario se enviará solo al Servlet.
});

