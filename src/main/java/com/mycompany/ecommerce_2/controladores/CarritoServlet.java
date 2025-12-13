package com.mycompany.ecommerce_2.controladores;

import com.mycompany.ecommerce_2.modelos.implementaciones.CarritoBO;
import itson.ecommerce.persistencia.dtos.CarritoDTO;
import itson.ecommerce.persistencia.implementaciones.Persistencia;
import java.io.IOException;

// USAMOS JAKARTA (GlassFish 7 / Tomcat 10)
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "CarritoServlet", urlPatterns = {"/carrito", "/carrito/agregar", "/carrito/eliminar"})
public class CarritoServlet extends HttpServlet {

    private CarritoBO carritoBO;

    @Override
    public void init() throws ServletException {
        // Inicializamos la lógica de negocio
        this.carritoBO = new CarritoBO(new Persistencia());
    }

    // GET: Se usa para VER el carrito o para ELIMINAR un producto
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String path = request.getServletPath();
        
        // Si la URL es /carrito/eliminar, ejecutamos la lógica de borrado
        if (path.equals("/carrito/eliminar")) {
            eliminarProducto(request, response);
            return;
        }

        // Si no, mostramos el carrito
        HttpSession session = request.getSession();
        Long idCarrito = (Long) session.getAttribute("idCarrito");
        CarritoDTO carrito = null;

        try {
            if (idCarrito != null) {
                carrito = carritoBO.obtenerCarrito(idCarrito);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("carritoReal", carrito);
        request.getRequestDispatcher("/carrito.jsp").forward(request, response);
    }

    // POST: Se usa EXCLUSIVAMENTE para AGREGAR productos (Llamado desde el JavaScript)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Long idCarrito = (Long) session.getAttribute("idCarrito");
        String idProdStr = request.getParameter("idProducto");

        try {
            // 1. Si el usuario no tiene carrito en sesión, creamos uno nuevo en BD
            if (idCarrito == null) {
                CarritoDTO nuevo = carritoBO.crearCarrito();
                session.setAttribute("idCarrito", nuevo.getId());
                idCarrito = nuevo.getId();
            }
            
            // 2. Agregamos el producto al carrito existente
            if (idProdStr != null) {
                // (idCarrito, idProducto, cantidad)
                carritoBO.agregarProducto(idCarrito, Long.parseLong(idProdStr), 1);
            }
            
            // 3. Respondemos con éxito (200 OK) para que el JavaScript muestre el Modal
            response.setStatus(200); 
            
        } catch (Exception e) {
            e.printStackTrace();
            // Si algo falla, enviamos error 500
            response.sendError(500, "Error interno: " + e.getMessage());
        }
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HttpSession session = request.getSession();
            Long idCarrito = (Long) session.getAttribute("idCarrito");
            String idDetalle = request.getParameter("id");
            
            if (idCarrito != null && idDetalle != null) {
                carritoBO.eliminarProducto(idCarrito, Long.parseLong(idDetalle));
            }
            // Redirigimos de vuelta a la vista del carrito
            response.sendRedirect(request.getContextPath() + "/carrito");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/carrito");
        }
    }
}