/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.ecommerce_2.resources;

import com.mycompany.ecommerce_2.modelos.implementaciones.CarritoBO;
import itson.ecommerce.persistencia.dtos.CarritoDTO;
import itson.ecommerce.persistencia.implementaciones.Persistencia;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author jrasc
 */
@Path("carrito")
@RequestScoped
public class CarritoResource {

    private final CarritoBO carritoBO;

    public CarritoResource() {
        this.carritoBO = new CarritoBO(new Persistencia());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCarrito(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long idCarrito = (Long) session.getAttribute("idCarrito");

        if (idCarrito == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("No hay carrito activo").build();
        }

        try {
            CarritoDTO carrito = carritoBO.obtenerCarrito(idCarrito);
            return Response.ok(carrito).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("agregar")
    public Response agregarProducto(
            @QueryParam("idProducto") Long idProducto,
            @Context HttpServletRequest request) {

        HttpSession session = request.getSession();
        Long idCarrito = (Long) session.getAttribute("idCarrito");

        try {
            if (idCarrito == null) {
                CarritoDTO nuevo = carritoBO.crearCarrito();
                session.setAttribute("idCarrito", nuevo.getId());
                idCarrito = nuevo.getId();
            }
            carritoBO.agregarProducto(idCarrito, idProducto, 1);
            return Response.ok("Producto agregado correctamente").build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al agregar: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("eliminar/{idDetalle}")
    public Response eliminarProducto(
            @PathParam("idDetalle") Long idDetalle,
            @Context HttpServletRequest request) {

        HttpSession session = request.getSession();
        Long idCarrito = (Long) session.getAttribute("idCarrito");

        if (idCarrito == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("No hay carrito").build();
        }
        try {
            carritoBO.eliminarProducto(idCarrito, idDetalle);
            return Response.ok("Producto eliminado").build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
