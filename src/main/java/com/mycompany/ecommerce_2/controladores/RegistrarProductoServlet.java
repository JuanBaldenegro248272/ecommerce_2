/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.ecommerce_2.controladores;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import com.mycompany.ecommerce_2.modelos.IAlbumBO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.mycompany.ecommerce_2.modelos.IProductosBO;
import com.mycompany.ecommerce_2.modelos.implementaciones.AlbumBO;
import com.mycompany.ecommerce_2.modelos.implementaciones.ProductosBO;
import itson.ecommerce.persistencia.dtos.AlbumDTO;
import itson.ecommerce.persistencia.dtos.NuevoProductoDTO;
import itson.ecommerce.persistencia.implementaciones.Persistencia;
import itson.ecommerce.persistencia.interfaces.IPersistencia;
import java.util.List;

/**
 *
 * @author Dana Chavez
 */
@WebServlet(name = "RegistrarProducto", urlPatterns = {"/admin/productos/nuevo"})
public class RegistrarProductoServlet extends HttpServlet {

    private IProductosBO productosBO;
    private IAlbumBO albumBO;

    public RegistrarProductoServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        IPersistencia fachada = new Persistencia();
        productosBO = new ProductosBO(fachada);
        albumBO = new AlbumBO(fachada);
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
            out.println("<title>Servlet RegistrarProducto</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistrarProducto at " + request.getContextPath() + "</h1>");
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
            List<AlbumDTO> listaAlbumes = albumBO.obtenerTodoAlbumes();
            System.out.println(listaAlbumes.size());
            request.setAttribute("listaAlbumes", listaAlbumes);
        } catch (BusinessException ex) {
            request.setAttribute("error", ex.getMessage());
        }
        request.getRequestDispatcher("/nuevo-producto.jsp").forward(request, response);
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

        NuevoProductoDTO dto = new NuevoProductoDTO();

        try {
            String albumIdStr = request.getParameter("albumId");
            dto.setAlbumId(albumIdStr != null && !albumIdStr.isBlank() ? Long.valueOf(albumIdStr) : null);
        } catch (NumberFormatException nfe) {
            dto.setAlbumId(null);
        }

        dto.setFormato(trimToNull(request.getParameter("formato")));

        try {
            String precioStr = request.getParameter("precio");
            dto.setPrecio(precioStr != null && !precioStr.isBlank() ? Float.valueOf(precioStr) : null);
        } catch (NumberFormatException nfe) {
            dto.setPrecio(null);
        }

        try {
            String stockStr = request.getParameter("stock");
            dto.setStock(stockStr != null && !stockStr.isBlank() ? Integer.valueOf(stockStr) : null);
        } catch (NumberFormatException nfe) {
            dto.setStock(null);
        }

        String esDisp = request.getParameter("esDisponible");
        dto.setEsDisponible(esDisp != null && !esDisp.isBlank() ? Boolean.valueOf(esDisp) : null);

        dto.setDescripcion(trimToEmpty(request.getParameter("descripcion")));

        try {
            NuevoProductoDTO creado = productosBO.crearProducto(dto);

            response.sendRedirect(request.getContextPath() + "/admin/productos?success=1");
        } catch (BusinessException be) {
            request.setAttribute("error", be.getMessage());
            request.setAttribute("dto", dto);
            request.getRequestDispatcher("/nuevo-producto.jsp")
                    .forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("error", "Ocurrio un error interno al crear el producto.");
            request.setAttribute("dto", dto);
            ex.printStackTrace();
            request.getRequestDispatcher("/nuevo-producto.jsp")
                    .forward(request, response);
        }
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

    // Helpers
    private String trimToNull(String s) {
        if (s == null) {
            return null;
        }
        s = s.trim();
        return s.isEmpty() ? null : s;
    }

    private String trimToEmpty(String s) {
        return s == null ? "" : s.trim();
    }

}
