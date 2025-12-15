/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.ecommerce_2.resources;

import com.mycompany.ecommerce_2.modelos.IProductosBO;
import com.mycompany.ecommerce_2.modelos.IResenasBO;
import com.mycompany.ecommerce_2.modelos.implementaciones.ProductosBO;
import com.mycompany.ecommerce_2.modelos.implementaciones.ResenasBO;
import itson.ecommerce.persistencia.dtos.NuevaResenaDTO;
import itson.ecommerce.persistencia.dtos.NuevoProductoDTO;
import itson.ecommerce.persistencia.dtos.ProductoListaDTO;
import itson.ecommerce.persistencia.implementaciones.Persistencia;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * REST Web Service
 *
 * @author jrasc
 */
@Path("productos")
@RequestScoped
public class ProductosResource {

    private final IProductosBO productosBO;
    private final IResenasBO resenasBO;

    public ProductosResource() {
        this.productosBO = new ProductosBO(new Persistencia());
        this.resenasBO = new ResenasBO(new Persistencia());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerProductos() {
        try {
            List<ProductoListaDTO> productos = productosBO.obtenerTodosProductos();
            return Response.ok(productos).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity("Error al cargar productos").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response devolverProductoId(Long id) {
        try {
            NuevoProductoDTO producto = productosBO.devolverProducto(id);
            return Response.ok(producto).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity("Error al cargar productos").build();
        }
    }
}
