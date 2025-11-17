/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.ecommerce_2.modelos;

import itson.ecommerce.persistencia.entidades.EstadoResena;
import itson.ecommerce.persistencia.entidades.Resena;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;
import java.util.List;

/**
 *
 * @author victoria
 */
public interface IResenasBO {
    public List<Resena> buscarResenas(String termino, EstadoResena estado) throws PersistenciaException;

    public Resena consultar(Long id) throws PersistenciaException;

    public Resena actualizar(Resena resena) throws PersistenciaException;

    public boolean eliminar(Long idResena) throws PersistenciaException;
}
