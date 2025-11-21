package com.mycompany.ecommerce_2.controladores;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import com.mycompany.ecommerce_2.modelos.IAlbumBO;
import com.mycompany.ecommerce_2.modelos.implementaciones.AlbumBO;
import itson.ecommerce.persistencia.dtos.AlbumDTO;
import itson.ecommerce.persistencia.dtos.ArtistaSimpleDTO;
import itson.ecommerce.persistencia.dtos.GeneroDTO;
import itson.ecommerce.persistencia.implementaciones.Persistencia;
import itson.ecommerce.persistencia.interfaces.IPersistencia;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@WebServlet(name = "AlbumesServlet", urlPatterns = {"/admin/albums", "/admin/albums/*"})
public class AlbumesServlet extends HttpServlet {

    private IAlbumBO albumesBO;

    @Override
    public void init() throws ServletException {
        IPersistencia persistencia = new Persistencia();
        this.albumesBO = new AlbumBO(persistencia);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            listarAlbumes(request, response);
            return;
        }

        if (pathInfo.equals("/nuevo")) {
            mostrarFormularioNuevo(request, response);
            return;
        }

        if (pathInfo.equals("/editar")) {
            mostrarFormularioEditar(request, response);
            return;
        }

        if (pathInfo.equals("/eliminar")) {
            eliminarAlbum(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/admin/albums");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String pathInfo = request.getPathInfo();
        System.out.println("PathInfo: " + pathInfo);

        if (pathInfo != null && pathInfo.equals("/guardar")) {
            guardarAlbum(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/admin/albums");
    }

    private void listarAlbumes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String termino = request.getParameter("buscar");
        List<AlbumDTO> albums = new ArrayList<>();

        try {
            if (termino != null && !termino.trim().isEmpty()) {
                albums = albumesBO.buscar(termino);
            } else {
                albums = albumesBO.obtenerTodoAlbumes();
            }

            request.setAttribute("listaAlbumes", albums);
            request.setAttribute("terminoBusqueda", termino);

            String success = request.getParameter("success");
            if ("1".equals(success)) {
                request.setAttribute("mensaje", "Álbum creado exitosamente");
            } else if ("2".equals(success)) {
                request.setAttribute("mensaje", "Álbum actualizado exitosamente");
            } else if ("3".equals(success)) {
                request.setAttribute("mensaje", "Álbum eliminado exitosamente");
            }

            String error = request.getParameter("error");
            if (error != null && !error.isEmpty()) {
                try {
                    String mensajeDecodificado = java.net.URLDecoder.decode(error, "UTF-8");
                    request.setAttribute("error", mensajeDecodificado);
                } catch (Exception e) {
                    request.setAttribute("error", "Error desconocido");
                }
            }

        } catch (Exception ex) {
            System.out.println("Exception general: " + ex.getMessage());
            request.setAttribute("error", "Ocurrió un error al cargar los álbumes");
            request.setAttribute("listaAlbumes", albums);
            ex.printStackTrace();
        }

        request.getRequestDispatcher("/albumes-admin.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<ArtistaSimpleDTO> artistas = albumesBO.obtenerTodosArtistas();
            List<GeneroDTO> generos = albumesBO.obtenerTodosGeneros();

            request.setAttribute("artistas", artistas);
            request.setAttribute("generos", generos);


        } catch (BusinessException ex) {
            request.setAttribute("error", ex.getMessage());
        } catch (Exception ex) {
            request.setAttribute("error", "Ocurrió un error al cargar el formulario");
            ex.printStackTrace();
        }

        request.getRequestDispatcher("/nuevo-album.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");

        if (idStr == null || idStr.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/admin/albums?error=id_invalido");
            return;
        }

        try {
            Long id = Long.valueOf(idStr);

            AlbumDTO album = albumesBO.consultar(id);

            List<ArtistaSimpleDTO> artistas = albumesBO.obtenerTodosArtistas();
            List<GeneroDTO> generos = albumesBO.obtenerTodosGeneros();

            request.setAttribute("album", album);
            request.setAttribute("artistas", artistas);
            request.setAttribute("generos", generos);


            request.getRequestDispatcher("/editar-album.jsp").forward(request, response);

        } catch (NumberFormatException ex) {
            response.sendRedirect(request.getContextPath() + "/admin/albums?error=id_invalido");

        } catch (BusinessException ex) {
            response.sendRedirect(request.getContextPath() + "/admin/albums?error="
                    + java.net.URLEncoder.encode(ex.getMessage(), "UTF-8"));

        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/albums?error=error_cargar");
        }
    }

    private void eliminarAlbum(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");

        if (idStr == null || idStr.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/admin/albums?error=id_invalido");
            return;
        }

        try {
            Long id = Long.valueOf(idStr);

            //TODO ELIMINAR ALBUM

            response.sendRedirect(request.getContextPath() + "/admin/albums?success=3");

        } catch (NumberFormatException ex) {
            System.out.println("ID inválido: " + idStr);
            response.sendRedirect(request.getContextPath() + "/admin/albums?error=id_invalido");

        } catch (Exception ex) {
            System.out.println("Exception general: " + ex.getMessage());
            ex.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/albums?error=error_eliminar");
        }
    }

    private void guardarAlbum(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        boolean esNuevo = (idStr == null || idStr.trim().isEmpty());

        if (esNuevo) {
            request.getRequestDispatcher("/admin/albums/nuevo").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/albums?success=2");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para gestión de álbumes";
    }
}
