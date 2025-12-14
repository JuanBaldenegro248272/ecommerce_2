package com.mycompany.ecommerce_2.resources;

import itson.ecommerce.persistencia.dtos.CarritoDTO;
import itson.ecommerce.persistencia.entidades.Carrito;
import itson.ecommerce.persistencia.entidades.Producto;
import itson.ecommerce.persistencia.entidades.DetalleCarrito;
import itson.ecommerce.persistencia.mapper.CarritoMapper;
import jakarta.ws.rs.*; 
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import javax.persistence.*; 

import java.util.ArrayList;


@Path("/carrito")
public class CarritoResource {

    private static EntityManagerFactory emf;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("ecommercePU");
        } catch (Throwable t) {
            System.err.println("--- ERROR CRÍTICO AL INICIAR EMF ---");
            t.printStackTrace();
        }
    }

    // --- GET: VER CARRITO ---
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCarrito(@PathParam("id") Long id) {
        if (emf == null) return Response.status(500).entity("Error de conexión").build();
        
        EntityManager em = emf.createEntityManager();
        try {
            Carrito carrito = em.find(Carrito.class, id);
            
            if (carrito == null) {
                return Response.ok("{\"detalles\": [], \"total\": 0.0}").build();
            }
            
            em.refresh(carrito);
            CarritoDTO dto = CarritoMapper.toDTO(carrito);
            return Response.ok(dto).build();
            
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("Error al leer carrito").build();
        } finally {
            em.close();
        }
    }

    // --- POST: AGREGAR PRODUCTO ---
    @POST
    @Path("/{idCarrito}/producto/{idProducto}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarProducto(@PathParam("idCarrito") Long idCarrito, 
                                    @PathParam("idProducto") Long idProducto) {
        
        System.out.println("--- API: Agregando Producto " + idProducto + " al Carrito " + idCarrito + " ---");

        if (emf == null) return Response.status(500).entity("DB Error").build();

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Carrito carrito = em.find(Carrito.class, idCarrito);
            if (carrito == null) {
                carrito = new Carrito();
                carrito.setTotal(0.0f);
                carrito.setDetalles(new ArrayList<>());
                em.persist(carrito);
                em.flush();
            }

            Producto producto = em.find(Producto.class, idProducto);
            if (producto == null) {
                tx.rollback();
                return Response.status(Response.Status.NOT_FOUND).entity("Producto no existe").build();
            }

            if (carrito.getDetalles() == null) {
                carrito.setDetalles(new ArrayList<>());
            }

            DetalleCarrito detalle = null;
            for (DetalleCarrito d : carrito.getDetalles()) {
                if (d.getProducto() != null && d.getProducto().getId().equals(idProducto)) {
                    detalle = d;
                    break;
                }
            }

            if (detalle != null) {
                detalle.setCantidad(detalle.getCantidad() + 1);
                em.merge(detalle);
            } else {
                detalle = new DetalleCarrito();
                detalle.setCarrito(carrito);
                detalle.setProducto(producto);
                detalle.setCantidad(1);
                carrito.getDetalles().add(detalle);
                em.persist(detalle);
            }

            double totalCalculado = 0.0;
            for (DetalleCarrito d : carrito.getDetalles()) {
                if (d.getProducto() != null && d.getProducto().getPrecio() != null) {
                    totalCalculado += (d.getProducto().getPrecio() * d.getCantidad());
                }
            }
            carrito.setTotal((float) totalCalculado);
            em.merge(carrito);

            tx.commit();
            return Response.ok("{\"mensaje\": \"Agregado\", \"nuevoTotal\": " + carrito.getTotal() + "}").build();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
            return Response.status(500).entity("Error interno").build();
        } finally {
            em.close();
        }
    }
    
    @DELETE
    @Path("/{idCarrito}/producto/{idDetalle}")
    public Response eliminarProducto(@PathParam("idCarrito") Long idCarrito,
                                     @PathParam("idDetalle") Long idDetalle) {
        if (emf == null) return Response.serverError().build();
        
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            DetalleCarrito d = em.find(DetalleCarrito.class, idDetalle);
            
            if (d != null) {
                Carrito c = d.getCarrito();
                c.getDetalles().remove(d);
                
                // --- CORRECCIÓN DE LA LÍNEA QUE FALLABA ---
                // Simplemente obtenemos el valor. Java convierte float a double automáticamente.
                double totalActual = c.getTotal(); 
                
                double precio = (d.getProducto().getPrecio() != null) ? d.getProducto().getPrecio() : 0.0;
                double nuevoTotal = totalActual - (precio * d.getCantidad());
                
                c.setTotal((float) (nuevoTotal < 0 ? 0.0 : nuevoTotal));
                
                em.merge(c);
                em.remove(d);
            }
            tx.commit();
            return Response.ok().build();
        } catch (Exception e) {
            if(tx.isActive()) tx.rollback();
            e.printStackTrace();
            return Response.serverError().build();
        } finally {
            em.close();
        }
    }
}