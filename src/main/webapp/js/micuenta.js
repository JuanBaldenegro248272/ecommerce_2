/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
document.addEventListener('DOMContentLoaded', function() {
    const btnEditar = document.getElementById('btn-editar');
    const btnCancelar = document.getElementById('btn-cancelar');
    const btnGuardar = document.getElementById('btn-guardar');
    const inputs = document.querySelectorAll('#form-perfil input');
    
    function toggleEdicion(activar) {
        inputs.forEach(input => {
            if(input.name !== 'email') {
                input.disabled = !activar;
            }
        });

        if (activar) {
            btnEditar.style.display = 'none';
            btnGuardar.style.display = 'inline-block';
            btnCancelar.style.display = 'inline-block';
        } else {
            btnEditar.style.display = 'inline-block';
            btnGuardar.style.display = 'none';
             btnCancelar.style.display = 'inline-block'; 
        }
    }
    btnEditar.addEventListener('click', function(e) {
        e.preventDefault();
        toggleEdicion(true);
    });

    btnCancelar.addEventListener('click', function() {
        location.reload(); 
    });
});
