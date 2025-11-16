/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.ecommerce.persistencia.interfaces;

import itson.ecommerce.persistencia.dtos.NuevoProductoDTO;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;

/**
 *
 * @author Dana Chavez
 */
public interface IPersistencia {
    
    public abstract NuevoProductoDTO crearProducto(NuevoProductoDTO dto) throws PersistenciaException;

}
