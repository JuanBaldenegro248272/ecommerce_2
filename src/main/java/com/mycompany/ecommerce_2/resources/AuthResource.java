/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce_2.resources;

import com.mycompany.ecommerce_2.modelos.implementaciones.UsuarioBO;
import itson.ecommerce.persistencia.dtos.CredencialesDTO;
import itson.ecommerce.persistencia.dtos.UsuarioDTO;
import itson.ecommerce.persistencia.implementaciones.Persistencia;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import service.SecurityService;

@Path("auth")
public class AuthResource {

    private final UsuarioBO usuarioBO;

    public AuthResource() {
        this.usuarioBO = new UsuarioBO(new Persistencia());
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(CredencialesDTO credenciales) {
        try {
            UsuarioDTO usuario = usuarioBO.login(credenciales.getCorreo(), credenciales.getContrasena());

            if (usuario != null) {
                boolean esAdmin = "ADMINISTRADOR".equalsIgnoreCase(usuario.getRol());

                String token = SecurityService.generateToken(usuario.getCorreoElectronico(), esAdmin);
                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                response.put("mensaje", "Autenticación exitosa");
                response.put("usuario", usuario.getNombre());

                return Response.ok(response).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"error\":\"Credenciales incorrectas\"}").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"Error interno en el servidor\"}").build();
        }
    }
}
