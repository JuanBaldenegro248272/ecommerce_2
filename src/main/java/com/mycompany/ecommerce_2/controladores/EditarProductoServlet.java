/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.ecommerce_2.controladores;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import com.mycompany.ecommerce_2.modelos.IProductosBO;
import com.mycompany.ecommerce_2.modelos.implementaciones.ProductosBO;
import itson.ecommerce.persistencia.dtos.EditarProductoDTO;
import itson.ecommerce.persistencia.implementaciones.Persistencia;
import itson.ecommerce.persistencia.interfaces.IPersistencia;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Dana Chavez
 */
@WebServlet(name = "EditarProductoServlet", urlPatterns = {"/admin/productos/editar"})
public class EditarProductoServlet extends HttpServlet {
    
    private IProductosBO productosBO;
    
    @Override
    public void init() throws ServletException {
        IPersistencia persistencia = new Persistencia();
        this.productosBO = new ProductosBO(persistencia);
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
            out.println("<title>Servlet EditarProductoServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditarProductoServlet at " + request.getContextPath() + "</h1>");
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

        String idStr = request.getParameter("id");
        
        if (idStr == null || idStr.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/admin/productos?error=id_invalido");
            return;
        }
        
        try {
            Long id = Long.valueOf(idStr);
            System.out.println("Obteniendo producto con ID: " + id);
            
            EditarProductoDTO producto = productosBO.obtenerProductoPorId(id);
            
            System.out.println("Producto obtenido: " + producto.getAlbumNombre());
            
            request.setAttribute("producto", producto);
            request.getRequestDispatcher("/editar-producto.jsp").forward(request, response);
            
        } catch (NumberFormatException ex) {
            System.out.println("ID inválido: " + idStr);
            response.sendRedirect(request.getContextPath() + "/admin/productos?error=id_invalido");
            
        } catch (BusinessException ex) {
            System.out.println("BusinessException: " + ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/admin/productos?error=" + 
                                java.net.URLEncoder.encode(ex.getMessage(), "UTF-8"));
        } catch (Exception ex) {
            System.out.println("Exception general: " + ex.getMessage());
            ex.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/productos?error=error_cargar");
        }
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
        
        EditarProductoDTO dto = new EditarProductoDTO();
        
        try {
            String idStr = request.getParameter("id");
            dto.setId(idStr != null && !idStr.isBlank() ? Long.valueOf(idStr) : null);
            
            System.out.println("Actualizando producto ID: " + dto.getId());
            
        } catch (NumberFormatException nfe) {
            dto.setId(null);
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
            productosBO.actualizarProducto(dto);
            response.sendRedirect(request.getContextPath() + "/admin/productos?success=2");
            
        } catch (BusinessException be) {
            System.out.println("BusinessException: " + be.getMessage());
            request.setAttribute("error", be.getMessage());
            request.setAttribute("producto", dto);
            request.getRequestDispatcher("/editar-producto.jsp").forward(request, response);
            
        } catch (Exception ex) {
            System.out.println("Exception general: " + ex.getMessage());
            request.setAttribute("error", "Ocurrió un error interno al actualizar el producto.");
            request.setAttribute("producto", dto);
            ex.printStackTrace();
            request.getRequestDispatcher("/editar-producto.jsp").forward(request, response);
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
    
    private String trimToNull(String str) {
        return (str != null && !str.trim().isEmpty()) ? str.trim() : null;
    }
    
    private String trimToEmpty(String str) {
        return (str != null) ? str.trim() : "";
    }
}
