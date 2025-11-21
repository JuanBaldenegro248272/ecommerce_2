<%-- 
    Document   : nuevo-album
    Created on : Nov 20, 2025, 8:56:17 PM
    Author     : Dana Chavez
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/admin.css">
        <title>Nuevo Álbum - ECOstore</title>
    </head>
    <body>
        <div class="app">
            <%@ include file="/WEB-INF/fragments/sidebarAdmin.jspf" %>

            <main class="main">
                <div class="header-container">
                    <div class="back-btn">
                        <img src="${pageContext.request.contextPath}/icons/iconvolver.png" 
                             alt="Volver" 
                             style="width:30px;height:30px;object-fit:contain;cursor:pointer;" 
                             onclick="location.href = '${pageContext.request.contextPath}/admin/albums'" />
                    </div>
                    <div>
                        <h2 class="page-title">Nuevo álbum</h2>
                        <div class="page-sub">Agrega un nuevo álbum al catálogo</div>
                    </div>
                </div>

                <c:if test="${not empty error}">
                    <div id="alerta" class="alert error">
                        ✕ ${error}
                    </div>
                </c:if>

                <section class="panel">
                    <form action="${pageContext.request.contextPath}/admin/albums/nuevo" method="post">

                        <h3 style="margin: 0 0 20px 0; color: var(--color-primary); border-bottom: 2px solid #e0e0e0; padding-bottom: 10px;">
                            Información del Álbum
                        </h3>

                        <div class="grid-2">
                            <div class="field">
                                <label>Nombre del Álbum *</label>
                                <input type="text" 
                                       name="nombre" 
                                       placeholder="Ej: Abbey Road" 
                                       value="${dto.nombre}"
                                       required />
                            </div>

                            <div class="field">
                                <label>Artista *</label>
                                <select name="idArtista" required>
                                    <option value="">Seleccionar artista</option>
                                    <c:forEach var="artista" items="${artistas}">
                                        <option value="${artista.id}" ${dto.idArtista == artista.id ? 'selected' : ''}>
                                            ${artista.nombreArtistico}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="grid-2">
                            <div class="field">
                                <label>Año de Lanzamiento *</label>
                                <input type="date" 
                                       name="fechaLanzamiento" 
                                       required />
                            </div>

                            <div class="field">
                                <label>URL de Imagen de Portada *</label>
                                <input type="url" 
                                       name="imagenUrl" 
                                       placeholder="https://ejemplo.com/portada.jpg" 
                                       value="${dto.imagenUrl}"
                                       required />
                            </div>
                        </div>

                        <div class="field">
                            <label>Descripción</label>
                            <textarea name="descripcion" 
                                      placeholder="Describe el álbum..."
                                      rows="3">${dto.descripcion}</textarea>
                        </div>

                        <h3 style="margin: 30px 0 20px 0; color: var(--color-primary); border-bottom: 2px solid #e0e0e0; padding-bottom: 10px;">
                            Categoría
                        </h3>

                        <div class="field">
                            <label>Selecciona las categorías del álbum *</label>
                            <div class="generos-grid">
                                <c:forEach var="genero" items="${generos}">
                                    <div class="genero-checkbox">
                                        <input type="checkbox" 
                                               name="generos" 
                                               value="${genero.id}" 
                                               id="genero_${genero.id}">
                                        <label for="genero_${genero.id}">${genero.nombre}</label>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>

                        <h3 style="margin: 30px 0 20px 0; color: var(--color-primary); border-bottom: 2px solid #e0e0e0; padding-bottom: 10px;">
                            Lista de canciones
                        </h3>

                        <div class="field">
                            <div id="canciones-container" class="canciones-list">
                                <div class="cancion-item">
                                    <div class="cancion-numero">1</div>
                                    <input type="text" 
                                           name="cancion_0" 
                                           placeholder="Nombre de la canción"
                                           class="cancion-input" />
                                </div>
                            </div>
                            <button type="button" class="btn-add-cancion" onclick="agregarCancion()">
                                + Agregar Canción
                            </button>
                        </div>

                        <div class="actions">
                            <button type="submit" class="btn primary">Crear Álbum</button>
                            <a href="${pageContext.request.contextPath}/admin/albums" class="btn">Cancelar</a>
                        </div>
                    </form>
                </section>
            </main>
        </div>

        <script>
            document.addEventListener('DOMContentLoaded', function () {
                const alerta = document.getElementById('alerta');
                if (alerta) {
                    setTimeout(function () {
                        alerta.classList.add('fade-out');
                        setTimeout(function () {
                            alerta.remove();
                        }, 500);
                    }, 3000);
                }
            });

            let cancionCount = 1;

            function agregarCancion() {
                const container = document.getElementById('canciones-container');
                const nuevaCancion = document.createElement('div');
                nuevaCancion.className = 'cancion-item';
                nuevaCancion.innerHTML = `
                    <div class="cancion-numero">${cancionCount + 1}</div>
                    <input type="text" 
                           name="cancion_${cancionCount}" 
                           placeholder="Nombre de la canción"
                           class="cancion-input" />
                    <button type="button" class="btn-remove-cancion" onclick="eliminarCancion(this)">
                        ✕
                    </button>
                `;
                container.appendChild(nuevaCancion);
                cancionCount++;
                actualizarNumeros();
            }

            function eliminarCancion(btn) {
                btn.parentElement.remove();
                actualizarNumeros();
            }

            function actualizarNumeros() {
                const items = document.querySelectorAll('.cancion-item');
                items.forEach((item, index) => {
                    const numero = item.querySelector('.cancion-numero');
                    if (numero) {
                        numero.textContent = index + 1;
                    }
                    const input = item.querySelector('.cancion-input');
                    if (input) {
                        input.name = 'cancion_' + index;
                    }
                });
                cancionCount = items.length;
            }
        </script>
    </body>
</html>
