/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.ecommerce_2.resources;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import com.mycompany.ecommerce_2.modelos.ICarritoBO;
import com.mycompany.ecommerce_2.modelos.IPedidoBO;
import com.mycompany.ecommerce_2.modelos.IUsuarioBO;
import com.mycompany.ecommerce_2.modelos.implementaciones.CarritoBO;
import com.mycompany.ecommerce_2.modelos.implementaciones.PedidosBO;
import com.mycompany.ecommerce_2.modelos.implementaciones.UsuarioBO;
import itson.ecommerce.persistencia.dtos.PedidoDTO;
import itson.ecommerce.persistencia.implementaciones.Persistencia;
import itson.ecommerce.persistencia.interfaces.IPersistencia;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.logging.Logger;

/**
 * REST Web Service
 *
 * @author jrasc
 */
@Path("checkOut")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class CheckOutResource {

    private static final Logger LOG = Logger.getLogger(CheckOutResource.class.getName());
    private final IPedidoBO pedidosBO;
    private final IUsuarioBO usuarioBO;
    private final ICarritoBO carritoBO;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CheckOutResource
     */
    public CheckOutResource() {
        IPersistencia persistencia = new Persistencia();
        this.pedidosBO = new PedidosBO(persistencia);
        this.usuarioBO = new UsuarioBO(persistencia);
        this.carritoBO = new CarritoBO(persistencia);
    }

    @POST
    @Path("/checkout")
    public Response checkout(PedidoDTO pedidoDTO, @Context SecurityContext security) {
        try {
            String correo = null;
            Principal p = security.getUserPrincipal();
            if (p != null) {
                correo = p.getName();
            }
            if (correo == null || correo.isBlank()) {
                return Response.status(Response.Status.UNAUTHORIZED).entity("No autenticado").build();
            }
            PedidoDTO pedido = pedidosBO.crearPedido(pedidoDTO, correo);
            return Response.ok(pedido).build();
        } catch (BusinessException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
}
