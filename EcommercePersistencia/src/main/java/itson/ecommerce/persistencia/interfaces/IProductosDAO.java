/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.ecommerce.persistencia.interfaces;

import itson.ecommerce.persistencia.dtos.NuevoProductoDTO;

/**
 *
 * @author Dana Chavez
 */
public interface IProductosDAO {

    public NuevoProductoDTO crear(NuevoProductoDTO dto);
    
}
