/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.ecommerce.persistencia.interfaces;

import itson.ecommerce.persistencia.entidades.Usuario;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;

/**
 *
 * @author Gael
 */
public interface IUsuarioDAO {


      public Usuario guardar(Usuario usuario) throws PersistenciaException;
      public Usuario buscarPorCorreo(String correo) throws PersistenciaException;

}
