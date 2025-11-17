/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce_2.modelos.implementaciones;

import com.mycompany.ecommerce_2.modelos.IArtistaBO;
import itson.ecommerce.persistencia.entidades.Artista;
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
public class ArtistaBO implements IArtistaBO {
    private final IPersistencia persistencia;

    public ArtistaBO() {
        this.persistencia = new Persistencia();
    }

    @Override
    public List<Artista> consultarTodos() {
        try {
            return this.persistencia.consultarArtistasTodos();
        } catch (PersistenciaException ex) {
            Logger.getLogger(ArtistaBO.class.getName()).log(Level.SEVERE, "Error al consultar artistas", ex);
            return null;
        }
    }
}
