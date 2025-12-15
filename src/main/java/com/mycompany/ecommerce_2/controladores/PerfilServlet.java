/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.ecommerce_2.controladores;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import com.mycompany.ecommerce_2.modelos.IUsuarioBO;
import com.mycompany.ecommerce_2.modelos.implementaciones.UsuarioBO;
import itson.ecommerce.persistencia.dtos.UsuarioDTO;
import itson.ecommerce.persistencia.implementaciones.Persistencia;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Gael
 */
@WebServlet(name = "PerfilServlet", urlPatterns = {"/PerfilServlet"})
public class PerfilServlet extends HttpServlet {
    
    private IUsuarioBO usuarioBO;
    public void init()throws ServletException{
        this.usuarioBO = new UsuarioBO(new Persistencia());
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
            out.println("<title>Servlet PerfilServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PerfilServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(false);
        UsuarioDTO usuarioSesion = (session != null) ? (UsuarioDTO) session.getAttribute("usuarioLogueado") : null;

       
        if (usuarioSesion == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            //UsuarioDTO usuarioFresco = usuarioBO.buscarPorId(usuarioSesion.getId()); 
            
          
            request.setAttribute("datosUsuario", usuarioSesion);

            request.getRequestDispatcher("micuenta.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp"); 
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
        HttpSession session = request.getSession(false);
        UsuarioDTO usuarioSesion = (session != null) ? (UsuarioDTO) session.getAttribute("usuarioLogueado") : null;

        if (usuarioSesion == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String nuevoNombre = request.getParameter("nombre");

        try {
            
            UsuarioDTO datosAActualizar = new UsuarioDTO();
            datosAActualizar.setId(usuarioSesion.getId()); 
            datosAActualizar.setNombre(nuevoNombre);

            UsuarioDTO usuarioActualizado = usuarioBO.actualizarUsuario(datosAActualizar);

            session.setAttribute("usuarioLogueado", usuarioActualizado);

            request.setAttribute("mensajeExito", "¡Perfil actualizado correctamente!");
            doGet(request, response); 

        } catch (BusinessException e) {
            request.setAttribute("error", e.getMessage());
            doGet(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error interno al actualizar.");
            doGet(request, response);
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

}
