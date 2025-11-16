/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce_2.modelos.implementaciones;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import com.mycompany.ecommerce_2.modelos.IProductosBO;
import itson.ecommerce.persistencia.dtos.NuevoProductoDTO;
import itson.ecommerce.persistencia.dtos.ProductoListaDTO;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;
import itson.ecommerce.persistencia.interfaces.IPersistencia;
import java.util.List;

/**
 *
 * @author Dana Chavez
 */
public class ProductosBO implements IProductosBO{
    
    private IPersistencia persistencia;
    
    private static final int LIMITE_DESCRIPCION = 255;

    public ProductosBO(IPersistencia persistencia) {
        this.persistencia = persistencia;
    }

    @Override
    public NuevoProductoDTO crearProducto(NuevoProductoDTO dto) throws BusinessException {

        if (dto.getAlbumId() == null) {
            throw new BusinessException("Debes seleccionar un álbum.");
        }

        if (dto.getFormato() == null || dto.getFormato().isEmpty()) {
            throw new BusinessException("Debes seleccionar un formato.");
        }

        if (dto.getPrecio() == null || dto.getPrecio() < 0) {
            throw new BusinessException("El precio no puede ser negativo.");
        }

        if (dto.getStock() == null || dto.getStock() < 0) {
            throw new BusinessException("El stock no puede ser negativo.");
        }

        if (dto.getDescripcion() == null || dto.getDescripcion().trim().isEmpty()) {
            throw new BusinessException("Debes escribir una descripción.");
        }

        if (dto.getDescripcion().length() > LIMITE_DESCRIPCION) {
            throw new BusinessException("La descripción excede los " + LIMITE_DESCRIPCION + " caracteres.");
        }

        try {
            return persistencia.crearProducto(dto);

        } catch (PersistenciaException ex) {
            throw new BusinessException("No se pudo registrar el producto.");
        }
    }

    @Override
    public List<ProductoListaDTO> obtenerTodosProductos() throws BusinessException {
        try {
            return persistencia.obtenerTodosProductos();
        } catch (PersistenciaException ex) {
            throw new BusinessException("No se pudieron obtener los productos.");
        }
    }

    @Override
    public List<ProductoListaDTO> buscarProductos(String termino) throws BusinessException {
        if (termino == null || termino.trim().isEmpty()) {
            return obtenerTodosProductos();
        }
        
        try {
            return persistencia.buscarProductos(termino.trim());
        } catch (PersistenciaException ex) {
            throw new BusinessException("No se pudieron buscar los productos.");
        }
    }
   
}
