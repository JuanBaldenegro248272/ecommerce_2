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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


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
                Album album = albumBO.consultar(id);
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

                Album album;
                boolean esNuevo = (idStr == null || idStr.isEmpty());

                if (esNuevo) {
                    album = new Album();
                    session.setAttribute("mensaje", "Álbum creado correctamente.");
                } else {
                    album = albumBO.consultar(Long.parseLong(idStr));
                    session.setAttribute("mensaje", "Álbum actualizado correctamente.");
                }
                
                album.setNombre(nombre);
                album.setDescripcion(descripcion);
                album.setImagenUrl(imagenUrl);
                album.setFechaLanzamiento(fecha);
                album.setArtista(artista);

                if (esNuevo) {
                    albumBO.crear(album);
                } else {
                    albumBO.actualizar(album);
                }

            } catch (Exception e) {
                session.setAttribute("error", "Error al guardar el álbum: " + e.getMessage());
            }
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/albumes");
    }
}