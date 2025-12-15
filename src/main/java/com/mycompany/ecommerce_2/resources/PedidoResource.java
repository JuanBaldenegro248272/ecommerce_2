/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.ecommerce_2.resources;

import com.mycompany.ecommerce_2.modelos.IPedidoBO;
import com.mycompany.ecommerce_2.modelos.implementaciones.PedidosBO;
import itson.ecommerce.persistencia.dtos.PedidoDTO;
import itson.ecommerce.persistencia.implementaciones.Persistencia;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.GET;
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
            return Response.serverError().entity("Error al consultar pedidos.").build();
        }
    }
}
