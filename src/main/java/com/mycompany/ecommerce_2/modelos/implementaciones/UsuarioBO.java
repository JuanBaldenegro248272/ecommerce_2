/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce_2.modelos.implementaciones;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import itson.ecommerce.persistencia.entidades.Usuario;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;
import itson.ecommerce.persistencia.interfaces.IPersistencia;
import itson.ecommerce.persistencia.utils.SeguridadUtil;

/**
 *
 * @author Gael
 */
public class UsuarioBO {
        
    private IPersistencia persistencia;
    
    
    public Usuario login (String correo, String contrasena) throws BusinessException, PersistenciaException{
        
        if (correo == null || correo.isBlank()) {
            throw new BusinessException("Debe ingresar un correo.");
        }
        if (contrasena == null || correo.isBlank()) {
            throw new BusinessException("Debe ingresar una contraseña.");
        }
        
        Usuario usuario = persistencia.buscarPorCorreo(correo);
        
        if (usuario == null) {
            throw new BusinessException("Correo o contraseña incorrectos.");
        }
        
        boolean match = SeguridadUtil.verificarHash(contrasena, usuario.getHashContrasena());
        
        if (!match) {
            throw new BusinessException("Correo o contraseña incorrectos.");
        }
        if (!Boolean.TRUE.equals(usuario.isEsActiva())) {
            throw new BusinessException("La cuenta no está activa.");
        }
        return usuario;
    }
    
}
