package com.mycompany.ecommerce_2.controladores;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.mycompany.ecommerce_2.modelos.IResenasBO;
import com.mycompany.ecommerce_2.modelos.implementaciones.ResenasBO;
import itson.ecommerce.persistencia.dtos.ResenaListaDTO;
import itson.ecommerce.persistencia.entidades.EstadoResena;
import itson.ecommerce.persistencia.entidades.Resena;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;
import itson.ecommerce.persistencia.implementaciones.Persistencia;
import itson.ecommerce.persistencia.interfaces.IPersistencia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victoria 
 */

@WebServlet(name = "ModeracionResenasServlet", urlPatterns = {"/admin/moderacion-resenas"})
public class ModeracionResenasServlet extends HttpServlet {
    
    private IResenasBO resenasBO;
    
    @Override
    public void init() throws ServletException {
        IPersistencia persistencia = new Persistencia();
        this.resenasBO = new ResenasBO(persistencia);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        String filtroEstado = request.getParameter("filtroEstado");
        String terminoBusqueda = request.getParameter("terminoBusqueda");
        List<ResenaListaDTO> resenas = new ArrayList<>();
        
        try {
            if ((terminoBusqueda != null && !terminoBusqueda.trim().isEmpty()) || 
                (filtroEstado != null && !filtroEstado.trim().isEmpty())) {
                resenas = resenasBO.buscarResenas(terminoBusqueda, filtroEstado);
            } else {
                resenas = resenasBO.obtenerTodasResenas();
            }
            
            request.setAttribute("listaResenas", resenas);
            request.setAttribute("filtroEstadoActual", filtroEstado);
            request.setAttribute("terminoBusquedaActual", terminoBusqueda);

            String success = request.getParameter("success");
            if ("1".equals(success)) {
                request.setAttribute("mensaje", "Reseña aprobada exitosamente");
            } else if ("2".equals(success)) {
                request.setAttribute("mensaje", "Reseña eliminada exitosamente");
            }

            String error = request.getParameter("error");
            if ("id_invalido".equals(error)) {
                request.setAttribute("error", "ID de reseña inválido");
            } else if ("error_aprobar".equals(error)) {
                request.setAttribute("error", "Ocurrió un error al aprobar la reseña");
            } else if ("error_eliminar".equals(error)) {
                request.setAttribute("error", "Ocurrió un error al eliminar la reseña");
            } else if (error != null && !error.isEmpty()) {
                try {
                    String mensajeDecodificado = java.net.URLDecoder.decode(error, "UTF-8");
                    request.setAttribute("error", mensajeDecodificado);
                } catch (Exception e) {
                    request.setAttribute("error", "Error desconocido");
                }
            }
            
        } catch (BusinessException ex) {
            request.setAttribute("error", ex.getMessage());
            request.setAttribute("listaResenas", resenas);
        } catch (Exception ex) {
            request.setAttribute("error", "Ocurrió un error al cargar las reseñas");
            request.setAttribute("listaResenas", resenas);
            ex.printStackTrace();
        }
        request.getRequestDispatcher("/moderacion-resenas.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        String accion = request.getParameter("accion");

        if ("buscar".equals(accion)) {
            String filtro = request.getParameter("filtroEstado");
            String termino = request.getParameter("terminoBusqueda");
            
            StringBuilder url = new StringBuilder("moderacion-resenas?");
            if (filtro != null && !filtro.isEmpty()) {
                url.append("filtroEstado=").append(filtro);
            }
            if (termino != null && !termino.isEmpty()) {
                if (url.toString().endsWith("?")) {
                    url.append("terminoBusqueda=").append(termino);
                } else {
                    url.append("&terminoBusqueda=").append(termino);
                }
            }
            
            response.sendRedirect(url.toString());
            return;
        }

        String idStr = request.getParameter("idResena");
        
        if (idStr == null || idStr.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/admin/moderacion-resenas?error=id_invalido");
            return;
        }
        
        try {
            Long id = Long.valueOf(idStr);
            
            if ("aprobar".equals(accion)) {
                resenasBO.aprobarResena(id);
                response.sendRedirect(request.getContextPath() + "/admin/moderacion-resenas?success=1");
                
            } else if ("eliminar".equals(accion)) {
                resenasBO.eliminarResena(id);
                response.sendRedirect(request.getContextPath() + "/admin/moderacion-resenas?success=2");
                
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/moderacion-resenas");
            }
            
        } catch (NumberFormatException ex) {
            response.sendRedirect(request.getContextPath() + "/admin/moderacion-resenas?error=id_invalido");
            
        } catch (BusinessException ex) {
            String mensaje = java.net.URLEncoder.encode(ex.getMessage(), "UTF-8");
            response.sendRedirect(request.getContextPath() + "/admin/moderacion-resenas?error=" + mensaje);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            String errorTipo = "aprobar".equals(accion) ? "error_aprobar" : "error_eliminar";
            response.sendRedirect(request.getContextPath() + "/admin/moderacion-resenas?error=" + errorTipo);
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Servlet para moderación de reseñas";
    }
}