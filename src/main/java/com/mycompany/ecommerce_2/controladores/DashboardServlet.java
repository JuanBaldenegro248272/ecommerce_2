/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.ecommerce_2.controladores;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import com.mycompany.ecommerce_2.modelos.IPedidoBO;
import com.mycompany.ecommerce_2.modelos.IProductosBO;
import com.mycompany.ecommerce_2.modelos.IResenasBO;
import com.mycompany.ecommerce_2.modelos.implementaciones.PedidosBO;
import com.mycompany.ecommerce_2.modelos.implementaciones.ProductosBO;
import com.mycompany.ecommerce_2.modelos.implementaciones.ResenasBO;
import itson.ecommerce.persistencia.dtos.PedidoDTO;
import itson.ecommerce.persistencia.dtos.ProductoListaDTO;
import itson.ecommerce.persistencia.entidades.EstadoResena;
import itson.ecommerce.persistencia.entidades.Resena;
import itson.ecommerce.persistencia.implementaciones.Persistencia;
import itson.ecommerce.persistencia.interfaces.IPersistencia;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Gael
 */
@WebServlet(name = "DashboardServlet", urlPatterns = {"/admin/dashboard"})
public class DashboardServlet extends HttpServlet {
    
    private IProductosBO productosBO;
    private IPedidoBO pedidosBO;
    private IResenasBO resenasBO;
    
    public void init() throws ServletException {
       IPersistencia persistencia = new Persistencia();
        this.productosBO = new ProductosBO(persistencia);
        this.pedidosBO = new PedidosBO(persistencia);
        this.resenasBO = new ResenasBO();
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
            out.println("<title>Servlet DashboardServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DashboardServlet at " + request.getContextPath() + "</h1>");
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
        //mock BD
        try {
            List<ProductoListaDTO> productos = productosBO.obtenerTodosProductos();
            int totalProductos = productos.size();
            
            List<PedidoDTO> pedidos = pedidosBO.obtenerTodosPedidos();
            
            long pedidosPendientesCuenta = pedidos.stream().filter(p -> "PENDIENTE".equalsIgnoreCase(p.getEstado())).count();
            List<PedidoDTO> pedidosRecientes = pedidos.stream().limit(2).collect(Collectors.toList());
            List<Resena> resenasPendientes = resenasBO.buscarResenas(null, EstadoResena.PENDIENTE);
            int totalResenasPendientes = resenasPendientes.size();
            List<Resena> resenasWidget = resenasPendientes.stream().limit(2).collect(Collectors.toList());

            int totalUsuarios = 0; 
            request.setAttribute("totalUsuarios", totalUsuarios); 
            request.setAttribute("totalProductos", totalProductos);
            request.setAttribute("totalPedidosPendientes", pedidosPendientesCuenta);
            request.setAttribute("totalResenasNuevas", totalResenasPendientes);
            
            request.setAttribute("pedidosRecientes", pedidosRecientes);
            request.setAttribute("resenasPendientes", resenasWidget);

        } catch (Exception ex) {
            request.setAttribute("error", "Error al cargar el dashboard: " + ex.getMessage());
            ex.printStackTrace();
        }

        request.getRequestDispatcher("/dashboard-admin.jsp").forward(request, response);
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
