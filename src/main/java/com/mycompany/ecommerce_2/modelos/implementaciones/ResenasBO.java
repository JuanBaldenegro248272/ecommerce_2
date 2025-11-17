/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce_2.modelos.implementaciones;

import com.mycompany.ecommerce_2.modelos.IResenasBO;
import itson.ecommerce.persistencia.entidades.EstadoResena;
import itson.ecommerce.persistencia.entidades.Resena;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;
import itson.ecommerce.persistencia.implementaciones.Persistencia;
import itson.ecommerce.persistencia.interfaces.IPersistencia; 
import java.util.List;

/**
 *
 * @author victoria
 */

public class ResenasBO implements IResenasBO {
    private final IPersistencia persistencia;

    public ResenasBO() {
        this.persistencia = new Persistencia();
    }
    
    @Override
    public List<Resena> buscarResenas(String termino, EstadoResena estado) throws PersistenciaException {
        return this.persistencia.buscarResenas(termino, estado);
    }

    @Override
    public Resena consultar(Long id) throws PersistenciaException {
        return this.persistencia.consultarResena(id);
    }

    @Override
    public Resena actualizar(Resena resena) throws PersistenciaException {
        return this.persistencia.actualizarResena(resena);
    }

    @Override
    public boolean eliminar(Long idResena) throws PersistenciaException {
        return this.persistencia.eliminarResena(idResena);
    }
}