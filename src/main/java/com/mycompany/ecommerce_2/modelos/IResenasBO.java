/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.ecommerce_2.modelos;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import itson.ecommerce.persistencia.dtos.ResenaListaDTO;
import java.util.List;

/**
 *
 * @author victoria
 */
public interface IResenasBO {
    public abstract List<ResenaListaDTO> obtenerTodasResenas() throws BusinessException;
    
    public abstract List<ResenaListaDTO> buscarResenas(String termino, String estado) throws BusinessException;
    
    public abstract void aprobarResena(Long id) throws BusinessException;
    
    public abstract void eliminarResena(Long id) throws BusinessException;
}
