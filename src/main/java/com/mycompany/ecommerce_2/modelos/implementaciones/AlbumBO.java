/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce_2.modelos.implementaciones;

import com.mycompany.ecommerce_2.modelos.IAlbumBO;
import itson.ecommerce.persistencia.dtos.AlbumDTO;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;
import itson.ecommerce.persistencia.implementaciones.Persistencia;
import itson.ecommerce.persistencia.interfaces.IPersistencia;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author victoria
 */
public class AlbumBO implements IAlbumBO {
    private final IPersistencia persistencia;
    private static final Logger LOG = Logger.getLogger(AlbumBO.class.getName());

    public AlbumBO() {
        this.persistencia = new Persistencia();
    }

    @Override
    public List<AlbumDTO> buscar(String termino) {
        try {
            return this.persistencia.buscarAlbumes(termino);
        } catch (PersistenciaException ex) {
            LOG.log(Level.SEVERE, "Error al buscar álbumes", ex);
            return null; 
        }
    }

    @Override
    public AlbumDTO consultar(Long id) {
        try {
            return this.persistencia.consultarAlbum(id);
        } catch (PersistenciaException ex) {
            LOG.log(Level.SEVERE, "Error al consultar álbum por ID", ex);
            return null;
        }
    }

    @Override
    public AlbumDTO crear(AlbumDTO album) {
        try {
            return this.persistencia.crearAlbum(album);
        } catch (PersistenciaException ex) {
            LOG.log(Level.SEVERE, "Error al crear álbum", ex);
            return null;
        }
    }

    @Override
    public AlbumDTO actualizar(AlbumDTO album) {
        try {
            return this.persistencia.actualizarAlbum(album);
        } catch (PersistenciaException ex) {
            LOG.log(Level.SEVERE, "Error al actualizar álbum", ex);
            return null;
        }
    }

    @Override
    public boolean eliminar(Long id) {
        try {
            return this.persistencia.eliminarAlbum(id);
        } catch (PersistenciaException ex) {
            LOG.log(Level.SEVERE, "Error al eliminar álbum", ex);
            return false;
        }
    }
}
