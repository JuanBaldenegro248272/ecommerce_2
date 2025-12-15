/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
document.addEventListener('DOMContentLoaded', function () {
    const btnEditar = document.getElementById('btn-editar');
    const btnCancelar = document.getElementById('btn-cancelar');
    const btnGuardar = document.getElementById('btn-guardar');
    const inputs = document.querySelectorAll('#form-perfil input');

    function toggleEdicion(activar) {
        inputs.forEach(input => {
            if (input.name !== 'email' && input.name !== 'id') { // Protegemos email e ID
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
            btnCancelar.style.display = 'none'; // Corregido: Ocultar cancelar al salir de edición
        }
    }

    if (btnEditar) {
        btnEditar.addEventListener('click', function (e) {
            e.preventDefault();
            toggleEdicion(true);
        });
    }

    if (btnCancelar) {
        btnCancelar.addEventListener('click', function (e) {
            e.preventDefault();
            location.reload();
        });
    }

    if (btnGuardar) {
        btnGuardar.addEventListener('click', function (e) {
            e.preventDefault();

            const datosUsuario = {
                nombre: document.getElementById('nombre').value,
                telefono: document.getElementById('telefono').value,
                calle: document.getElementById('calle').value,
                ciudad: document.getElementById('ciudad').value,
                estado: document.getElementById('estado').value,
                codigoPostal: document.getElementById('codigoPostal').value
            };

            fetch('api/usuario/actualizar', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(datosUsuario)
            })
                    .then(response => {
                        if (response.ok) {
                            return response.json();
                        } else {
                            throw new Error('Error en la actualización');
                        }
                    })
                    .then(data => {
                        alert('Perfil actualizado correctamente');
                        toggleEdicion(false);
                        location.reload();
                    })
                    .catch(error => {
                        console.error('Error:', error);
                        alert('Hubo un error al guardar los cambios.');
                    });
        });
    }
});