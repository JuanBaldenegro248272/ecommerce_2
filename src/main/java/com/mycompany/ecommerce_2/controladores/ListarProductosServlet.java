/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.ecommerce_2.controladores;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import com.mycompany.ecommerce_2.modelos.IProductosBO;
import com.mycompany.ecommerce_2.modelos.implementaciones.ProductosBO;
import itson.ecommerce.persistencia.dtos.ProductoListaDTO;
import itson.ecommerce.persistencia.implementaciones.Persistencia;
import itson.ecommerce.persistencia.interfaces.IPersistencia;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Dana Chavez
 */
@WebServlet(name = "ListarProductosServlet", urlPatterns = {"/admin/productos"})
public class ListarProductosServlet extends HttpServlet {
         
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
            out.println("<title>Servlet ListarProductosServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListarProductosServlet at " + request.getContextPath() + "</h1>");
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
        
        request.setCharacterEncoding("UTF-8");
        
        String termino = request.getParameter("buscar");
        List<ProductoListaDTO> productos = new ArrayList<>();
        
        try {
            if (termino != null && !termino.trim().isEmpty()) {
                productos = productosBO.buscarProductos(termino);
            } else {
                productos = productosBO.obtenerTodosProductos();
            }
            
            request.setAttribute("listaProductos", productos);
            request.setAttribute("terminoBusqueda", termino);

            String success = request.getParameter("success");
            if ("1".equals(success)) {
                request.setAttribute("mensaje", "Producto creado exitosamente");
            } else if ("2".equals(success)) {
                request.setAttribute("mensaje", "Producto actualizado exitosamente");
            }
            
        } catch (BusinessException ex) {
            request.setAttribute("error", ex.getMessage());
            request.setAttribute("listaProductos", productos);
        } catch (Exception ex) {
            request.setAttribute("error", "Ocurrió un error al cargar los productos");
            request.setAttribute("listaProductos", productos);
            ex.printStackTrace();
        }
        
        request.getRequestDispatcher("/productos-admin.jsp").forward(request, response);
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
        processRequest(request, response);
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
