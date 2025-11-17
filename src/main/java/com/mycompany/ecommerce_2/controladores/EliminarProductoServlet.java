/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.ecommerce_2.controladores;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import com.mycompany.ecommerce_2.modelos.IProductosBO;
import com.mycompany.ecommerce_2.modelos.implementaciones.ProductosBO;
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
@WebServlet(name = "EliminarProductoServlet", urlPatterns = {"/admin/productos/eliminar"})
public class EliminarProductoServlet extends HttpServlet {
    
    private IProductosBO productosBO;
    
    @Override
    public void init() throws ServletException {
        System.out.println("🚀 INICIALIZANDO EliminarProductoServlet");
        IPersistencia persistencia = new Persistencia();
        this.productosBO = new ProductosBO(persistencia);
        System.out.println("✅ EliminarProductoServlet inicializado correctamente");
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
            out.println("<title>Servlet EliminarProductoServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EliminarProductoServlet at " + request.getContextPath() + "</h1>");
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
            System.out.println("Intentando eliminar producto con ID: " + id);
            
            productosBO.eliminarProducto(id);
            
            System.out.println("Producto eliminado correctamente");
            response.sendRedirect(request.getContextPath() + "/admin/productos?success=3");
            
        } catch (NumberFormatException ex) {
            System.out.println("ID inválido: " + idStr);
            response.sendRedirect(request.getContextPath() + "/admin/productos?error=id_invalido");
            
        } catch (BusinessException ex) {
            System.out.println("BusinessException: " + ex.getMessage());
            String mensaje = java.net.URLEncoder.encode(ex.getMessage(), "UTF-8");
            response.sendRedirect(request.getContextPath() + "/admin/productos?error=" + mensaje);
            
        } catch (Exception ex) {
            System.out.println("Exception general: " + ex.getMessage());
            ex.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/productos?error=error_eliminar");
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
        doGet(request, response);
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
