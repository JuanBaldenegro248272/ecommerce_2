/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce_2.modelos.implementaciones;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import com.mycompany.ecommerce_2.modelos.IUsuarioBO;
import itson.ecommerce.persistencia.dtos.ClienteDTO;
import itson.ecommerce.persistencia.dtos.UsuarioDTO;
import itson.ecommerce.persistencia.entidades.Cliente;
import itson.ecommerce.persistencia.entidades.Direccion;
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

    private IPersistencia persistencia;

    public UsuarioBO(IPersistencia persistencia) {
        this.persistencia = persistencia;
    }

    public UsuarioDTO login(String correo, String contrasena) throws BusinessException, PersistenciaException {
        try {
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
        }catch (PersistenciaException ex) {
                throw new BusinessException("No se pudieron obtener los productos.");
            }
        }
    public UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO) throws BusinessException, PersistenciaException {
        try {
            
        
        if (usuarioDTO.getId() == null) {
            throw new BusinessException("El ID del usuario es requerido para actualizar.");
        }

        Usuario usuarioEntidad = persistencia.buscarPorCorreo(usuarioDTO.getCorreoElectronico());

        if (usuarioEntidad == null) {
            throw new BusinessException("Usuario no encontrado.");
        }

        
        Usuario usuarioActualizado = persistencia.actualizar(usuarioEntidad);

        return UsuarioMapper.toDTO(usuarioActualizado);
        }catch (PersistenciaException ex) {
            throw new BusinessException("No se pudieron obtener los productos.");
        }
    }
    
    public ClienteDTO obtenerDatosCliente(Long idUsuario)throws BusinessException, PersistenciaException {
        try {
        return persistencia.obtenerClientePorId(idUsuario); 
    } catch (PersistenciaException ex) {
            throw new BusinessException("No se encontraron los datos del cliente.");
        }
    }
    
    public void actualizarCliente(ClienteDTO clienteDTO)  {
     try {

        Usuario usuario = persistencia.buscarPorId(clienteDTO.getId());
        
        if (usuario instanceof Cliente) {
            Cliente cliente = (Cliente) usuario;
         
            cliente.setNombre(clienteDTO.getNombre());
            cliente.setTelefono(clienteDTO.getTelefono());
            
            Direccion dir = cliente.getDireccion();
            if (dir == null) {
                dir = new Direccion();
                cliente.setDireccion(dir);
            }
            dir.setCalle(clienteDTO.getCalle());
            dir.setCiudad(clienteDTO.getCiudad());
            persistencia.actualizar(cliente);
        }
    } catch (Exception e) {
        System.err.println("Error al actualizar su usuario: " + e.getMessage());
        e.printStackTrace();
    }
}
}
