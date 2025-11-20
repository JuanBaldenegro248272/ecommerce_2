/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.ecommerce_2.controladores;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import com.mycompany.ecommerce_2.modelos.IGenerosBO;
import com.mycompany.ecommerce_2.modelos.implementaciones.GenerosBO;
import itson.ecommerce.persistencia.dtos.GeneroDTO;
import itson.ecommerce.persistencia.implementaciones.Persistencia;
import itson.ecommerce.persistencia.interfaces.IPersistencia;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dana Chavez
 */
@WebServlet(name = "ListarCategoriasServlet", urlPatterns = {"/admin/categorias"})
public class ListarCategoriasServlet extends HttpServlet {
    
    private IGenerosBO generosBO;
    
    @Override
    public void init() throws ServletException {
        IPersistencia persistencia = new Persistencia();
        this.generosBO = new GenerosBO(persistencia);
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
            out.println("<title>Servlet ListarCategoriasServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListarCategoriasServlet at " + request.getContextPath() + "</h1>");
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
        
        List<GeneroDTO> generos = new ArrayList<>();
        
        try {
            generos = generosBO.obtenerTodosGeneros();
            
            request.setAttribute("listaGeneros", generos);

            String success = request.getParameter("success");
            if ("1".equals(success)) {
                request.setAttribute("mensaje", "Categoría creada exitosamente");
            } else if ("2".equals(success)) {
                request.setAttribute("mensaje", "Categoría actualizada exitosamente");
            } else if ("3".equals(success)) {
                request.setAttribute("mensaje", "Categoría eliminada exitosamente");
            }

            String error = request.getParameter("error");
            if ("id_invalido".equals(error)) {
                request.setAttribute("error", "ID de categoría inválido");
            } else if ("error_eliminar".equals(error)) {
                request.setAttribute("error", "Ocurrió un error al eliminar la categoría");
            } else if (error != null && !error.isEmpty()) {
                try {
                    String mensajeDecodificado = java.net.URLDecoder.decode(error, "UTF-8");
                    request.setAttribute("error", mensajeDecodificado);
                } catch (Exception e) {
                    request.setAttribute("error", "Error desconocido");
                }
            }
            
        } catch (BusinessException ex) {
            System.out.println("BusinessException: " + ex.getMessage());
            request.setAttribute("error", ex.getMessage());
            request.setAttribute("listaGeneros", generos);
        } catch (Exception ex) {
            System.out.println("Exception general: " + ex.getMessage());
            request.setAttribute("error", "Ocurrió un error al cargar las categorías");
            request.setAttribute("listaGeneros", generos);
            ex.printStackTrace();
        }
        
        request.getRequestDispatcher("/genero-admin.jsp").forward(request, response);
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
