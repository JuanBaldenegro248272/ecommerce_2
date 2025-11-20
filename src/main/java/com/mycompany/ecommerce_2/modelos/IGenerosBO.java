/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.ecommerce_2.modelos;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import itson.ecommerce.persistencia.dtos.GeneroDTO;
import java.util.List;

/**
 *
 * @author Dana Chavez
 */
public interface IGenerosBO {
    
    public abstract List<GeneroDTO> obtenerTodosGeneros() throws BusinessException;
    
    public abstract void crearGenero(String nombre) throws BusinessException;
    
    public abstract void actualizarGenero(Long id, String nombre) throws BusinessException;
    
    public abstract void eliminarGenero(Long id) throws BusinessException;    
}
