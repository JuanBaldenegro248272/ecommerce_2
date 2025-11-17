/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.ecommerce_2.modelos;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import itson.ecommerce.persistencia.dtos.EditarProductoDTO;
import itson.ecommerce.persistencia.dtos.NuevoProductoDTO;
import itson.ecommerce.persistencia.dtos.ProductoListaDTO;
import java.util.List;

/**
 *
 * @author Dana Chavez
 */
public interface IProductosBO {
    public abstract NuevoProductoDTO crearProducto(NuevoProductoDTO dto) throws BusinessException;
       
    public abstract List<ProductoListaDTO> obtenerTodosProductos() throws BusinessException;

    public abstract List<ProductoListaDTO> buscarProductos(String termino) throws BusinessException;
    
    public abstract void eliminarProducto(Long id) throws BusinessException;
    
    public abstract EditarProductoDTO obtenerProductoPorId(Long id) throws BusinessException;
    
    public abstract void actualizarProducto(EditarProductoDTO dto) throws BusinessException;
}
