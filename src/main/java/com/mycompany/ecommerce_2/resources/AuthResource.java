/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce_2.resources;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import com.mycompany.ecommerce_2.modelos.IUsuarioBO;
import com.mycompany.ecommerce_2.modelos.implementaciones.UsuarioBO;
import itson.ecommerce.persistencia.dtos.CredencialesDTO;
import itson.ecommerce.persistencia.dtos.UsuarioDTO;
import itson.ecommerce.persistencia.implementaciones.Persistencia;
import itson.ecommerce.persistencia.interfaces.IPersistencia;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("auth")
public class AuthResource {

    @Context
    private HttpServletRequest request;

    private IUsuarioBO usuarioBO;

    public AuthResource() {
        IPersistencia persistencia = new Persistencia();
        this.usuarioBO = new UsuarioBO(persistencia);
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(CredencialesDTO credenciales) {
        try {
            if (credenciales == null || credenciales.getCorreo() == null || credenciales.getContrasena() == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"Datos incompletos\"}")
                        .build();
            }

            UsuarioDTO usuario = usuarioBO.login(credenciales.getCorreo(), credenciales.getContrasena());
            //punto de quiebre
            HttpSession session = request.getSession(true); 
            session.setAttribute("usuarioLogueado", usuario);

            return Response.ok(usuario).build();

        } catch (BusinessException e) {
            return Response.status(Response.Status.UNAUTHORIZED) 
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR) 
                    .entity("{\"error\": \"Error interno del servidor\"}")
                    .build();
        }
    }
    
}
