/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce_2.modelos.implementaciones;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import com.mycompany.ecommerce_2.modelos.IUsuarioBO;
import itson.ecommerce.persistencia.dtos.ClienteDTO;
import itson.ecommerce.persistencia.dtos.UsuarioDTO;
import itson.ecommerce.persistencia.entidades.Usuario;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;
import itson.ecommerce.persistencia.interfaces.IPersistencia;
import itson.ecommerce.persistencia.mapper.UsuarioMapper;
import itson.ecommerce.persistencia.utils.SeguridadUtil;

/**
 *
 * @author Gael
 */
public class UsuarioBO implements IUsuarioBO {

    private final IPersistencia persistencia;

    public UsuarioBO(IPersistencia persistencia) {
        this.persistencia = persistencia;
    }

    @Override
    public UsuarioDTO login(String correo, String contrasena) throws BusinessException {
        if (correo == null || correo.isBlank()) {
            throw new BusinessException("Debe ingresar un correo.");
        }
        if (contrasena == null || contrasena.isBlank()) {
            throw new BusinessException("Debe ingresar una contraseña.");
        }

        try {
            Usuario usuario = persistencia.buscarPorCorreo(correo);

            if (usuario == null) {
                throw new BusinessException("Correo o contraseña incorrectos.");
            }
            String hashAlmacenado = usuario.getHashContrasena().trim();
            if (!SeguridadUtil.verificarHash(contrasena, hashAlmacenado)) {
                throw new BusinessException("Correo o contraseña incorrectos.");
            }

            if (!Boolean.TRUE.equals(usuario.isEsActiva())) {
                throw new BusinessException("La cuenta no está activa.");
            }

            return UsuarioMapper.toDTO(usuario);

        } catch (PersistenciaException ex) {
            throw new BusinessException("Error en el sistema al intentar iniciar sesión.", ex);
        }
    }

    @Override
    public ClienteDTO actualizarCliente(ClienteDTO clienteDTO) throws BusinessException {
        if (clienteDTO == null) {
            throw new BusinessException("La información del cliente es requerida.");
        }
        if (clienteDTO.getId() == null) {
            throw new BusinessException("El ID del cliente es requerido.");
        }

        try {
            return persistencia.actualizarCliente(clienteDTO);

        } catch (PersistenciaException ex) {
            throw new BusinessException("Error al actualizar la información del cliente.", ex);
        }
    }

    @Override
    public ClienteDTO obtenerClientePorCorreo(String correo) throws BusinessException {
        if (correo == null || correo.isBlank()) {
            throw new BusinessException("El correo es requerido.");
        }
        try {
            return persistencia.obtenerClientePorCorreo(correo);
        } catch (PersistenciaException ex) {
            throw new BusinessException("Error al buscar cliente.", ex);
        }
    }

    @Override
    public ClienteDTO obtenerDatosCliente(Long idUsuario) throws BusinessException {
        if (idUsuario == null) {
            throw new BusinessException("El ID del usuario es requerido para cargar el perfil.");
        }
        try {
            ClienteDTO cliente = persistencia.obtenerClientePorId(idUsuario);
            return cliente;
        } catch (PersistenciaException ex) {
            throw new BusinessException("Error al intentar obtener los datos del cliente.", ex);
        }
    }
}
