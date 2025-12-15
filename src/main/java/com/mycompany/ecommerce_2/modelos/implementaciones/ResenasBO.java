/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce_2.modelos.implementaciones;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import com.mycompany.ecommerce_2.modelos.IResenasBO;
import itson.ecommerce.persistencia.dtos.NuevaResenaDTO;
import itson.ecommerce.persistencia.dtos.ResenaListaDTO;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;
import itson.ecommerce.persistencia.interfaces.IPersistencia;
import java.util.List;

/**
 *
 * @author victoria
 */
public class ResenasBO implements IResenasBO {

    private IPersistencia persistencia;

    public ResenasBO(IPersistencia persistencia) {
        this.persistencia = persistencia;
    }

    @Override
    public List<ResenaListaDTO> obtenerTodasResenas() throws BusinessException {
        try {
            List<ResenaListaDTO> resenas = persistencia.obtenerTodasResenas();
            return resenas;
        } catch (PersistenciaException ex) {
            throw new BusinessException("No se pudieron obtener las reseñas.");
        }
    }

    @Override
    public List<ResenaListaDTO> buscarResenas(String termino, String estado) throws BusinessException {
        try {
            List<ResenaListaDTO> resenas = persistencia.buscarResenas(termino, estado);
            return resenas;
        } catch (PersistenciaException ex) {
            throw new BusinessException("No se pudieron buscar las reseñas.");
        }
    }

    @Override
    public void aprobarResena(Long id) throws BusinessException {
        if (id == null || id <= 0) {
            throw new BusinessException("El ID de la reseña no es válido.");
        }
        try {
            persistencia.aprobarResena(id);
        } catch (PersistenciaException ex) {
            throw new BusinessException("No se pudo aprobar la reseña.");
        }
    }

    @Override
    public void eliminarResena(Long id) throws BusinessException {
        if (id == null || id <= 0) {
            throw new BusinessException("El ID de la reseña no es válido.");
        }

        try {
            persistencia.eliminarResena(id);
        } catch (PersistenciaException ex) {
            throw new BusinessException("No se pudo eliminar la reseña.");
        }
    }

    @Override
    public void publicarResena(NuevaResenaDTO nuevaResena) throws BusinessException {
        if (nuevaResena.getCalificacion() == null || nuevaResena.getCalificacion() < 1 || nuevaResena.getCalificacion() > 5) {
            throw new BusinessException("La calificación debe ser entre 1 y 5 estrellas.");
        }
        if (nuevaResena.getComentario() == null || nuevaResena.getComentario().trim().isEmpty()) {
            throw new BusinessException("El comentario no puede estar vacío.");
        }
        try {
            persistencia.crearResena(nuevaResena);
        } catch (PersistenciaException ex) {
            throw new BusinessException("Error al publicar la reseña: " + ex.getMessage());
        }
    }
}
