package com.mycompany.ecommerce_2.modelos;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import itson.ecommerce.persistencia.dtos.CarritoDTO;

public interface ICarritoBO {
    
    CarritoDTO crearCarrito() throws BusinessException;

    CarritoDTO obtenerCarrito(Long idCarrito) throws BusinessException;

    void agregarProducto(Long idCarrito, Long idProducto, Integer cantidad) throws BusinessException;

    void eliminarProducto(Long idCarrito, Long idDetalle) throws BusinessException;

    void vaciarCarrito(Long idCarrito) throws BusinessException;
}