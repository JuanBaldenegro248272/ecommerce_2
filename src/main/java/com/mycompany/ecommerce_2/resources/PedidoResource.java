/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.ecommerce_2.resources;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import com.mycompany.ecommerce_2.modelos.IPedidoBO;
import com.mycompany.ecommerce_2.modelos.implementaciones.PedidosBO;
import itson.ecommerce.persistencia.dtos.PedidoDTO;
import itson.ecommerce.persistencia.implementaciones.Persistencia;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.List;

/**
 * REST Web Service
 *
 * @author jrasc
 */
@Path("pedidos")
@RequestScoped
public class PedidoResource {

    private IPedidoBO pedidosBO;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PedidoResource
     */
    public PedidoResource() {
        this.pedidosBO = new PedidosBO(new Persistencia());
    }

    @GET
    @Path("mis-pedidos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerMisPedidos(@Context SecurityContext securityContext) {
        try {
            Principal principal = securityContext.getUserPrincipal();
            if (principal == null) {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Usuario no autenticado").build();
            }

            String correoUsuario = principal.getName();
            List<PedidoDTO> pedidos = pedidosBO.obtenerPedidosUsuario(correoUsuario);

            return Response.ok(pedidos).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("Error al consultar pedidos.").build();
        }
    }

    @POST
    @Path("realizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response realizarPedido(PedidoDTO pedidoDTO, @Context SecurityContext securityContext) {
        try {
            // 1. Obtener el usuario del token (SecurityContext)
            Principal principal = securityContext.getUserPrincipal();
            
            if (principal == null) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"error\": \"Debe iniciar sesión para comprar.\"}").build();
            }

            String correoUsuario = principal.getName();

            // 2. Llamar a la lógica de negocio para crear el pedido
            PedidoDTO pedidoCreado = pedidosBO.crearPedido(pedidoDTO, correoUsuario);

            // 3. Retornar éxito
            return Response.ok(pedidoCreado).build();

        } catch (BusinessException ex) {
            // Errores de validación (ej: falta dirección, carrito vacío)
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + ex.getMessage() + "\"}").build();
        } catch (Exception ex) {
            // Errores inesperados (ej: base de datos caída)
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error interno al procesar el pedido.\"}").build();
        }
    }
}