<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/admin.css">
        <title>Nuevo Producto</title>
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
                             onclick="location.href = '${pageContext.request.contextPath}/admin/productos'" />
                    </div>
                    <div>
                        <h2 class="page-title">Nuevo producto</h2>
                        <div class="page-sub">Agrega un nuevo producto al catálogo</div>
                    </div>
                </div>

                <c:if test="${not empty error}">
                    <div class="alert error">${error}</div>
                </c:if>

                <section class="panel">
                    <form action="${pageContext.request.contextPath}/admin/productos/nuevo" method="post">
                        <div class="field">
                            <label>Álbum *</label>
                            <c:choose>
                                <c:when test="${empty param.albumId}">
                                    <button type="button"
                                            class="select-album-btn"
                                            onclick="document.getElementById('dialogAlbumes').showModal()">
                                        <img src="${pageContext.request.contextPath}/icons/search.png"
                                             alt="buscar"
                                             style="width:16px;height:16px;" />
                                        Seleccionar Álbum
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <div class="album-selected-card">

                                        <div class="album-selected-info">

                                            <div class="album-cover">
                                                <c:if test="${not empty param.albumImagen}">
                                                    <img src="${param.albumImagen}" alt="${param.albumNombre}">
                                                </c:if>
                                            </div>

                                            <div class="album-selected-text">
                                                <p class="album-selected-title">${param.albumNombre}</p>
                                                <p class="album-selected-artist">${param.albumArtista}</p>

                                                <div class="album-selected-tags">
                                                    <c:if test="${not empty param.albumDescripcion}">
                                                        <span class="album-tag">${param.albumDescripcion}</span>
                                                    </c:if>

                                                    <c:if test="${not empty param.albumAnio}">
                                                        <span class="album-tag album-tag-muted">${param.albumAnio}</span>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                        <button type="button"
                                                class="album-change-btn"
                                                onclick="document.getElementById('dialogAlbumes').showModal()">
                                            Cambiar
                                        </button>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                            <input type="hidden" name="albumId" value="${param.albumId}" required />
                        </div>

                        <div class="grid-2">
                            <div class="field">
                                <label>Formato *</label>
                                <select name="formato" required>
                                    <option value="VINYL" ${dto.formato == 'VINYL' ? 'selected' : ''}>Vinilo</option>
                                    <option value="VINYL_COLOR" ${dto.formato == 'VINYL_COLOR' ? 'selected' : ''}>Vinilo Color</option>
                                    <option value="CD" ${dto.formato == 'CD' ? 'selected' : ''}>CD</option>
                                    <option value="CASSETTE" ${dto.formato == 'CASSETTE' ? 'selected' : ''}>Cassette</option>
                                </select>
                            </div>

                            <div class="field">
                                <label>Stock *</label>
                                <input type="number" 
                                       name="stock" 
                                       value="${dto.stock != null ? dto.stock : 0}" 
                                       min="0" 
                                       required />
                            </div>
                        </div>

                        <div class="grid-2">
                            <div class="field">
                                <label>Precio ($) *</label>
                                <input type="number" 
                                       name="precio" 
                                       value="${dto.precio != null ? dto.precio : 0}" 
                                       step="0.01" 
                                       min="0" 
                                       required />
                            </div>

                            <div class="field">
                                <label>Estado *</label>
                                <select name="esDisponible" required>
                                    <option value="true" ${dto.esDisponible == true ? 'selected' : ''}>Activo</option>
                                    <option value="false" ${dto.esDisponible == false ? 'selected' : ''}>Inactivo</option>
                                </select>
                            </div>
                        </div>

                        <div class="field">
                            <label>Descripción *</label>
                            <textarea name="descripcion" 
                                      placeholder="Describe el producto..." 
                                      required>${dto.descripcion}</textarea>
                        </div>

                        <div class="actions">
                            <button type="submit" class="btn primary">Crear Producto</button>
                            <a href="${pageContext.request.contextPath}/admin/productos" class="btn">Cancelar</a>
                        </div>
                    </form>
                </section>
                <dialog id="dialogAlbumes" class="album-dialog">

                    <div class="album-dialog-header">
                        <h3>Seleccionar álbum</h3>
                        <button type="button"
                                class="album-dialog-close"
                                onclick="document.getElementById('dialogAlbumes').close()">
                            ✕
                        </button>
                    </div>

                    <div class="album-dialog-body">
                        <div class="album-search-wrapper">
                            <span class="album-search-icon">
                                <img src="${pageContext.request.contextPath}/icons/searchicon.png" 
                                     alt="Buscar" 
                                     style="width:16px;height:16px;">
                            </span>
                            <input type="text"
                                   class="album-search-input"
                                   placeholder="Buscar álbumes...">
                        </div>
                        <c:if test="${not empty listaAlbumes}">
                            <div class="album-list">
                                <c:forEach var="a" items="${listaAlbumes}">
                                    <form method="get"
                                          action="${pageContext.request.contextPath}/admin/productos/nuevo"
                                          class="album-item-form">
                                        <input type="hidden" name="albumId" value="${a.id}" />
                                        <input type="hidden" name="albumNombre" value="${a.nombre}" />
                                        <input type="hidden" name="albumArtista" value="${a.nombreArtista}" />
                                        <input type="hidden" name="albumImagen" value="${a.imagenUrl}" />
                                        <input type="hidden" name="albumDescripcion" value="${a.descripcion}" />
                                        <button type="submit" class="album-item">
                                            <div class="album-cover">
                                                <img src="${a.imagenUrl}">
                                            </div>
                                            <div class="album-info">
                                                <h3 class="album-title">${a.nombre}</h3>
                                                <p class="album-artist">${a.nombreArtista}</p>

                                                <div class="album-tags">
                                                    <c:if test="${not empty a.descripcion}">
                                                        <span class="album-tag">${a.descripcion}</span>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </button>
                                    </form>
                                </c:forEach>
                            </div>
                        </c:if>
                        <c:if test="${empty listaAlbumes}">
                            <p>No hay álbumes disponibles.</p>
                        </c:if>

                    </div>
                </dialog>
            </main>
        </div>
    </body>
</html>