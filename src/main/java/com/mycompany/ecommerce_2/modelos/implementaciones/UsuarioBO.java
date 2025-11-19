/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce_2.modelos.implementaciones;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import com.mycompany.ecommerce_2.modelos.IUsuarioBO;
import itson.ecommerce.persistencia.dtos.UsuarioDTO;
import itson.ecommerce.persistencia.entidades.Usuario;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;
import itson.ecommerce.persistencia.interfaces.IPersistencia;
import itson.ecommerce.persistencia.mapper.UsuarioMapper;

/**
 *
 * @author Gael
 */
public class UsuarioBO implements IUsuarioBO {

    private IPersistencia persistencia;

    public UsuarioBO(IPersistencia persistencia) {
        this.persistencia = persistencia;
    }

    public UsuarioDTO login(String correo, String contrasena) throws BusinessException, PersistenciaException {

        if (correo == null || correo.isBlank()) {
            throw new BusinessException("Debe ingresar un correo.");
        }
        if (contrasena == null || contrasena.isBlank()) {
            throw new BusinessException("Debe ingresar una contraseña.");
        }

        Usuario usuario = persistencia.buscarPorCorreo(correo);

        if (usuario == null) {
            throw new BusinessException("Correo o contraseña incorrectos.");
        }
        String hashAlmacenado = usuario.getHashContrasena().trim();
        boolean match = SeguridadUtil.verificarHash(contrasena, hashAlmacenado);

        if (!match) {
            throw new BusinessException("Correo o contraseña incorrectos.");
        }

        if (!Boolean.TRUE.equals(usuario.isEsActiva())) {
            throw new BusinessException("La cuenta no está activa.");
        }

        return UsuarioMapper.toDTO(usuario);
    }
}
