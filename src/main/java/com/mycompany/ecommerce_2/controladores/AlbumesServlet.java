package com.mycompany.ecommerce_2.controladores;

import com.mycompany.ecommerce_2.modelos.IAlbumBO;
import com.mycompany.ecommerce_2.modelos.IArtistaBO;
import com.mycompany.ecommerce_2.modelos.implementaciones.AlbumBO;
import com.mycompany.ecommerce_2.modelos.implementaciones.ArtistaBO;
import itson.ecommerce.persistencia.dtos.AlbumDTO;
import itson.ecommerce.persistencia.entidades.Album;
import itson.ecommerce.persistencia.entidades.Artista;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "AlbumesServlet", urlPatterns = {"/admin/albumes/*"})
public class AlbumesServlet extends HttpServlet {

    private final IAlbumBO albumBO = new AlbumBO();
    private final IArtistaBO artistaBO = new ArtistaBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        String jspPage;

        if (pathInfo == null || pathInfo.equals("/")) {
            String termino = request.getParameter("buscar");
            List<AlbumDTO> albumes = albumBO.buscar(termino);
            request.setAttribute("listaAlbumes", albumes);
            request.setAttribute("terminoBusqueda", termino);
            jspPage = "/albumes-admin.jsp";

        } else if (pathInfo.equals("/nuevo")) {
            List<Artista> artistas = artistaBO.consultarTodos();
            request.setAttribute("artistas", artistas);
            request.setAttribute("album", new Album());
            jspPage = "/form-album.jsp";

        } else if (pathInfo.startsWith("/editar")) {
            try {
                Long id = Long.parseLong(request.getParameter("id"));
                AlbumDTO album = albumBO.consultar(id);
                List<Artista> artistas = artistaBO.consultarTodos();

                request.setAttribute("album", album);
                request.setAttribute("artistas", artistas);
                jspPage = "/form-album.jsp";
            } catch (Exception e) {
                response.sendRedirect(request.getContextPath() + "/admin/albumes");
                return;
            }

        } else if (pathInfo.startsWith("/eliminar")) {
            // Ruta: /admin/albumes/eliminar
            try {
                Long id = Long.parseLong(request.getParameter("id"));
                albumBO.eliminar(id);
                request.getSession().setAttribute("mensaje", "Álbum eliminado correctamente.");
            } catch (Exception e) {
                request.getSession().setAttribute("error", "No se pudo eliminar el álbum.");
            }
            response.sendRedirect(request.getContextPath() + "/admin/albumes");
            return;

        } else {
            response.sendRedirect(request.getContextPath() + "/admin/albumes");
            return;
        }

        request.getRequestDispatcher(jspPage).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        HttpSession session = request.getSession();

        if (pathInfo.equals("/guardar")) {
            try {
                String idStr = request.getParameter("id");
                String nombre = request.getParameter("nombre");
                String descripcion = request.getParameter("descripcion");
                String imagenUrl = request.getParameter("imagenUrl");
                Long idArtista = Long.parseLong(request.getParameter("idArtista"));

                String fechaStr = request.getParameter("fechaLanzamiento");
                Calendar fecha = Calendar.getInstance();
                fecha.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr));

                Artista artista = new Artista();
                artista.setId(idArtista);

                AlbumDTO albumDto;
                boolean esNuevo = (idStr == null || idStr.isEmpty());

                if (esNuevo) {
                    albumDto = new AlbumDTO(
                            null,
                            nombre,
                            descripcion,
                            fecha,
                            imagenUrl,
                            idArtista,
                            null,
                            null, 
                            null, 
                            null 
                    );
                    session.setAttribute("mensaje", "Álbum creado correctamente.");
                } else {
                    albumDto = albumBO.consultar(Long.parseLong(idStr));
                    session.setAttribute("mensaje", "Álbum actualizado correctamente.");
                }

                albumDto.setNombre(nombre);
                albumDto.setDescripcion(descripcion);
                albumDto.setImagenUrl(imagenUrl);
                albumDto.setFechaLanzamiento(fecha);
                albumDto.setIdArtista(idArtista);

                if (esNuevo) {
                    albumBO.crear(albumDto);
                } else {
                    albumBO.actualizar(albumDto);
                }

            } catch (Exception e) {
                session.setAttribute("error", "Error al guardar el álbum: " + e.getMessage());
            }
        }

        response.sendRedirect(request.getContextPath() + "/admin/albumes");
    }
}
