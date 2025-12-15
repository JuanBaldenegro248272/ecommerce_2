/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.ecommerce_2.resources;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import com.mycompany.ecommerce_2.modelos.IResenasBO;
import com.mycompany.ecommerce_2.modelos.implementaciones.ResenasBO;
import itson.ecommerce.persistencia.dtos.NuevaResenaDTO;
import itson.ecommerce.persistencia.implementaciones.Persistencia;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author jrasc
 */
@Path("resenas")
public class ResenasResource {

    private IResenasBO resenasBO;

    public ResenasResource() {
        this.resenasBO = new ResenasBO(new Persistencia());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearResena(NuevaResenaDTO nuevaResena) {
        try {
            resenasBO.publicarResena(nuevaResena);
            return Response.status(Response.Status.CREATED)
                    .entity("{\"message\": \"Reseña publicada correctamente.\"}")
                    .build();
        } catch (BusinessException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + ex.getMessage() + "\"}")
                    .build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error interno al publicar la reseña.\"}")
                    .build();
        }
    }
}
