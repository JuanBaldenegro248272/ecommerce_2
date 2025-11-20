/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce_2.modelos.implementaciones;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import com.mycompany.ecommerce_2.modelos.IGenerosBO;
import itson.ecommerce.persistencia.dtos.GeneroDTO;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;
import itson.ecommerce.persistencia.interfaces.IPersistencia;
import java.util.List;

/**
 *
 * @author Dana Chavez
 */
public class GenerosBO implements IGenerosBO {
    
    private IPersistencia persistencia;
    
    public GenerosBO(IPersistencia persistencia) {
        this.persistencia = persistencia;
    }
    
    @Override
    public List<GeneroDTO> obtenerTodosGeneros() throws BusinessException {
        try {
            List<GeneroDTO> generos = persistencia.obtenerTodosGeneros();
            return generos;
        } catch (PersistenciaException ex) {
            throw new BusinessException("No se pudieron obtener los géneros.");
        }
    }
    
    @Override
    public void crearGenero(String nombre) throws BusinessException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new BusinessException("El nombre del género no puede estar vacío.");
        }
        
        if (nombre.length() > 50) {
            throw new BusinessException("El nombre del género no puede exceder 50 caracteres.");
        }
        
        try {
            persistencia.crearGenero(nombre.trim());
        } catch (PersistenciaException ex) {
            throw new BusinessException("No se pudo crear el género.");
        }
    }
    
    @Override
    public void actualizarGenero(Long id, String nombre) throws BusinessException {

        if (id == null || id <= 0) {
            throw new BusinessException("El ID del género no es válido.");
        }
        
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new BusinessException("El nombre del género no puede estar vacío.");
        }
        
        if (nombre.length() > 50) {
            throw new BusinessException("El nombre del género no puede exceder 50 caracteres.");
        }
        
        try {
            persistencia.actualizarGenero(id, nombre.trim());
        } catch (PersistenciaException ex) {
            throw new BusinessException("No se pudo actualizar el género.");
        }
    }
    
    @Override
    public void eliminarGenero(Long id) throws BusinessException {
        if (id == null || id <= 0) {
            throw new BusinessException("El ID del género no es válido.");
        }
        
        try {
            persistencia.eliminarGenero(id);
        } catch (PersistenciaException ex) {

            if (ex.getMessage() != null && 
                (ex.getMessage().contains("foreign key") || 
                 ex.getMessage().contains("constraint"))) {
                throw new BusinessException("No se puede eliminar el género porque tiene álbums asociados.");
            }
            
            throw new BusinessException("No se pudo eliminar el género.");
        }
    }
}
