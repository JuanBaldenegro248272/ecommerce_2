<%-- 
    Document   : generoAdmin
    Created on : Nov 15, 2025, 9:05:45 PM
    Author     : jrasc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ECOstore - Administrador</title>
        <link rel="stylesheet" href="styles/styles.css">
    </head>
    <body>
        <div class="admin-main">

            <div class="categorias-container">

                <div class="categorias-header">
                    <div>
                        <h2>Gestión de Categorías</h2>
                        <p>Organiza los productos por categorías</p>
                    </div>

                    <button class="btn-nueva">+ Nueva Categoría</button>
                </div>

                <div class="grid-categorias">

                    <div class="categoria-card">
                        <div class="categoria-icono">
                            <img src="icons/tagAdmin.png">
                        </div>

                        <h3>Rock</h3>
                        <span class="descripcion">Rock clásico y moderno</span>

                        <div class="categoria-acciones">
                            <button class="btn-editar">
                                <img src="icons/editAdmin.png">
                                Editar
                            </button>
                            <button class="btn-eliminar">
                                <img src="icons/deleteAdmin.png">
                            </button>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </body>
</html>
