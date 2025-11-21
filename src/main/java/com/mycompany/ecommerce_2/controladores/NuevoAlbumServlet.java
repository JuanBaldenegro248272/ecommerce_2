/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.ecommerce_2.controladores;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import com.mycompany.ecommerce_2.modelos.IAlbumBO;
import com.mycompany.ecommerce_2.modelos.implementaciones.AlbumBO;
import itson.ecommerce.persistencia.dtos.ArtistaSimpleDTO;
import itson.ecommerce.persistencia.dtos.GeneroDTO;
import itson.ecommerce.persistencia.dtos.NuevoAlbumDTO;
import itson.ecommerce.persistencia.implementaciones.Persistencia;
import itson.ecommerce.persistencia.interfaces.IPersistencia;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dana Chavez
 */
@WebServlet(name = "NuevoAlbumServlet", urlPatterns = {"/admin/albums/nuevo"})
public class NuevoAlbumServlet extends HttpServlet {
    
    private IAlbumBO albumesBO;
    
    @Override
    public void init() throws ServletException {
        IPersistencia persistencia = new Persistencia();
        this.albumesBO = new AlbumBO(persistencia);
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegistrarAlbumServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistrarAlbumServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<ArtistaSimpleDTO> artistas = albumesBO.obtenerTodosArtistas();
            List<GeneroDTO> generos = albumesBO.obtenerTodosGeneros();
            
            request.setAttribute("artistas", artistas);
            request.setAttribute("generos", generos);            
            
        } catch (BusinessException ex) {
            System.out.println("BusinessException: " + ex.getMessage());
            request.setAttribute("error", ex.getMessage());
        } catch (Exception ex) {
            request.setAttribute("error", "Ocurrió un error al cargar el formulario");
            ex.printStackTrace();
        }
        
        request.getRequestDispatcher("/nuevo-album.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        NuevoAlbumDTO dto = new NuevoAlbumDTO();
        
        try {
            dto.setNombre(trimToNull(request.getParameter("nombre")));
            dto.setDescripcion(trimToEmpty(request.getParameter("descripcion")));
            dto.setImagenUrl(trimToNull(request.getParameter("imagenUrl")));

            String artistaIdStr = request.getParameter("idArtista");
            if (artistaIdStr != null && !artistaIdStr.isEmpty()) {
                dto.setIdArtista(Long.valueOf(artistaIdStr));
            }

            String fechaStr = request.getParameter("fechaLanzamiento");
            if (fechaStr != null && !fechaStr.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar fecha = Calendar.getInstance();
                fecha.setTime(sdf.parse(fechaStr));
                dto.setFechaLanzamiento(fecha);
            }

            String[] generosIds = request.getParameterValues("generos");
            if (generosIds != null && generosIds.length > 0) {
                List<Long> idsGeneros = new ArrayList<>();
                for (String id : generosIds) {
                    if (id != null && !id.isEmpty()) {
                        idsGeneros.add(Long.valueOf(id));
                    }
                }
                dto.setIdsGeneros(idsGeneros);
            }

            List<String> canciones = new ArrayList<>();
            int i = 0;
            String cancion;
            while ((cancion = request.getParameter("cancion_" + i)) != null) {
                if (!cancion.trim().isEmpty()) {
                    canciones.add(cancion.trim());
                }
                i++;
            }
            
            if (!canciones.isEmpty()) {
                dto.setCanciones(canciones);
            }

            albumesBO.crearAlbum(dto);

            response.sendRedirect(request.getContextPath() + "/admin/albums?success=1");
            
        } catch (BusinessException be) {
            try {
                List<ArtistaSimpleDTO> artistas = albumesBO.obtenerTodosArtistas();
                List<GeneroDTO> generos = albumesBO.obtenerTodosGeneros();
                request.setAttribute("artistas", artistas);
                request.setAttribute("generos", generos);
            } catch (Exception ex) {
            }
            
            request.setAttribute("error", be.getMessage());
            request.setAttribute("dto", dto);
            request.getRequestDispatcher("/nuevo-album.jsp").forward(request, response);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            
            request.setAttribute("error", "Ocurrió un error interno al crear el álbum.");
            request.setAttribute("dto", dto);
            request.getRequestDispatcher("/nuevo-album.jsp").forward(request, response);
        }
    }
    
    private String trimToNull(String str) {
        return (str != null && !str.trim().isEmpty()) ? str.trim() : null;
    }
    
    private String trimToEmpty(String str) {
        return (str != null) ? str.trim() : "";
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
