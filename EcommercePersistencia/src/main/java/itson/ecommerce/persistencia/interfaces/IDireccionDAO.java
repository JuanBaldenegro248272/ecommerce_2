/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.ecommerce.persistencia.interfaces;

import itson.ecommerce.persistencia.entidades.Direccion;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;

/**
 *
 * @author jrasc
 */
public interface IDireccionDAO {

    public Direccion buscarPorId(Long idDireccion) throws PersistenciaException;
}
