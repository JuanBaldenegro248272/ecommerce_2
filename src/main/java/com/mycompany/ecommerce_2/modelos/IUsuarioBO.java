/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.ecommerce_2.modelos;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import itson.ecommerce.persistencia.dtos.UsuarioDTO;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;

/**
 *
 * @author Gael
 */
public interface IUsuarioBO {
    
        public UsuarioDTO login(String correo, String contrasena) throws BusinessException, PersistenciaException;

    
}
