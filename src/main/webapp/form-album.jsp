<%-- 
    Document   : form-album
    Created on : Nov 16, 2025, 9:52:32 PM
    Author     : victoria
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${empty album.id ? 'Nuevo' : 'Editar'} Álbum</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/admin.css">
</head>
<body>
    <div class="admin-dashboard">
        
        <%@include file="/WEB-INF/fragments/sidebarAdmin.jspf" %>

        <main class="admin-main">
            <div class="header-container">
                <div>
                    <h1 class="page-title">${empty album.id ? 'Nuevo Álbum' : 'Editar Álbum'}</h1>
                    <p class="page-sub">Completa la información del álbum.</p>
                </div>
            </div>

            <div class="panel">
                <form method="POST" action="${pageContext.request.contextPath}/admin/albumes/guardar">
                    <c:if test="${not empty album.id}">
                        <input type="hidden" name="id" value="${album.id}">
                    </c:if>

                    <div class="field">
                        <label for="nombre">Nombre del Álbum</label>
                        <input type="text" id="nombre" name="nombre" value="${album.nombre}" required>
                    </div>
                    <div class="grid-2">
                        <div class="field">
                            <label for="idArtista">Artista</label>
                            <select id="idArtista" name="idArtista" required>
                                <option value="">Selecciona un artista</option>
                                <c:forEach var="artista" items="${artistas}">
                                    <option value="${artista.id}" ${album.artista.id == artista.id ? 'selected' : ''}>
                                        ${artista.nombreArtistico}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="field">
                            <label for="fechaLanzamiento">Fecha de Lanzamiento</label>
                            <fmt:formatDate value="${album.fechaLanzamiento.time}" pattern="yyyy-MM-dd" var="fechaHTML" />
                            <input type="date" id="fechaLanzamiento" name="fechaLanzamiento" value="${fechaHTML}" required>
                        </div>
                    </div>
                    
                    <div class="field">
                        <label for="imagenUrl">URL de la Portada</label>
                        <input type="text" id="imagenUrl" name="imagenUrl" value="${album.imagenUrl}" required>
                    </div>

                    <div class="field">
                        <label for="descripcion">Descripción</label>
                        <textarea id="descripcion" name="descripcion" rows="4">${album.descripcion}</textarea>
                    </div>

                    <div class="actions">
                        <a href="${pageContext.request.contextPath}/admin/albumes" class="btn">Cancelar</a>
                        <button type="submit" class="btn primary">Guardar Álbum</button>
                    </div>
                </form>
            </div>
        </main>
    </div>
</body>
</html>
