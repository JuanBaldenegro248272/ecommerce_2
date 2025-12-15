/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce_2.resources;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import com.mycompany.ecommerce_2.modelos.IUsuarioBO;
import com.mycompany.ecommerce_2.modelos.implementaciones.UsuarioBO;
import itson.ecommerce.persistencia.dtos.ClienteDTO;
import itson.ecommerce.persistencia.dtos.UsuarioDTO;
import itson.ecommerce.persistencia.implementaciones.Persistencia;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author Gael
 */
@Path("usuario")
public class UsuarioResource {

    @Context
    private HttpServletRequest request;

    private final IUsuarioBO usuarioBO;

    public UsuarioResource() {
        this.usuarioBO = new UsuarioBO(new Persistencia());
    }

    @GET
    @Path("perfil")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPerfil() {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        UsuarioDTO usuarioSesion = (UsuarioDTO) session.getAttribute("usuarioLogueado");

        try {
            ClienteDTO cliente = usuarioBO.obtenerDatosCliente(usuarioSesion.getId());
            if (cliente != null) {
                return Response.ok(cliente).build();
            } else {
                return Response.ok(usuarioSesion).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error al cargar perfil\"}").build();
        }
    }

    @PUT
    @Path("actualizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarPerfil(ClienteDTO datosNuevos) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        UsuarioDTO usuarioSesion = (UsuarioDTO) session.getAttribute("usuarioLogueado");
        datosNuevos.setId(usuarioSesion.getId());
        datosNuevos.setCorreoElectronico(usuarioSesion.getCorreoElectronico());

        try {
            ClienteDTO resultado = usuarioBO.actualizarCliente(datosNuevos);
            usuarioSesion.setNombre(resultado.getNombre());
            session.setAttribute("usuarioLogueado", usuarioSesion);
            return Response.ok(resultado).build();

        } catch (BusinessException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error interno al actualizar\"}").build();
        }
    }
}
