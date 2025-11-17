package com.mycompany.ecommerce_2.controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mycompany.ecommerce_2.modelos.IResenasBO;
import com.mycompany.ecommerce_2.modelos.implementaciones.ResenasBO;
import itson.ecommerce.persistencia.entidades.EstadoResena;
import itson.ecommerce.persistencia.entidades.Resena;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;
import java.util.List;

/**
 *
 * @author victoria 
 */

@WebServlet(name = "ModeracionResenasServlet", urlPatterns = {"/admin/moderacion-resenas"}) 
public class ModeracionResenasServlet extends HttpServlet {
    
    private final IResenasBO resenasBO;

    public ModeracionResenasServlet() {
        this.resenasBO = new ResenasBO(); 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String filtroEstadoStr = request.getParameter("filtroEstado");
        String terminoBusqueda = request.getParameter("terminoBusqueda");
        EstadoResena filtroEstado = null;

        if (filtroEstadoStr != null && !filtroEstadoStr.isEmpty()) {
            try {
                filtroEstado = EstadoResena.valueOf(filtroEstadoStr);
            } catch (IllegalArgumentException e) {}
        }
        
        try {
            List<Resena> listaResenas = resenasBO.buscarResenas(terminoBusqueda, filtroEstado);
            request.setAttribute("listaResenas", listaResenas);
            request.setAttribute("filtroEstadoActual", filtroEstadoStr);
            request.setAttribute("terminoBusquedaActual", terminoBusqueda);
            String urlJsp = "/moderacion_reseñas.jsp";  
            request.getRequestDispatcher(urlJsp).forward(request, response);

        } catch (PersistenciaException e) {
            response.sendError(500, "Error al consultar reseñas");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        
        try {
            if (accion != null) {
                switch (accion) {
                    case "aprobar":
                        procesarAprobar(request);
                        break;
                    case "eliminar":
                        procesarEliminar(request);
                        break;
                }
            }
        } catch (PersistenciaException e) {
            request.getSession().setAttribute("errorModeracion", "Error al procesar la acción");
        }
        
        if ("buscar".equals(accion)) {
            String filtro = request.getParameter("filtroEstado");
            String termino = request.getParameter("terminoBusqueda");
            response.sendRedirect(String.format("moderacion-resenas?filtroEstado=%s&terminoBusqueda=%s",
                    filtro != null ? filtro : "", termino != null ? termino : ""));
        } else {
            response.sendRedirect("moderacion-resenas"); 
        }
    }

    private void procesarAprobar(HttpServletRequest request) throws PersistenciaException {
        Long idResena = Long.parseLong(request.getParameter("idResena")); 
        Resena resena = resenasBO.consultar(idResena);
        
        if (resena != null) {
            resena.setEstado(EstadoResena.APROBADA);
            resenasBO.actualizar(resena);
        }
    }

    private void procesarEliminar(HttpServletRequest request) throws PersistenciaException {
        Long idResena = Long.parseLong(request.getParameter("idResena"));
        resenasBO.eliminar(idResena);
    }
    
    @Override
    public String getServletInfo() {
        return "Servlet para moderación de reseñas";
    }
    
}