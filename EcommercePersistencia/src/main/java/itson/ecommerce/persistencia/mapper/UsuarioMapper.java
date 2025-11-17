/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.mapper;

import itson.ecommerce.persistencia.dtos.UsuarioDTO;
import itson.ecommerce.persistencia.entidades.Administrador;
import itson.ecommerce.persistencia.entidades.Usuario;

/**
 *
 * @author Gael
 */
public class UsuarioMapper {
    
    public static UsuarioDTO toDTO(Usuario usuario){
        if (usuario == null) {
            return null;
        }
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setCorreoElectronico(usuario.getCorreoElectronico());
        dto.setEsActiva(usuario.isEsActiva());
        
        if (usuario instanceof Administrador) {
            dto.setRol("ADMIN");
        }else{
            dto.setRol("CLIENTE");
        }
        return dto;
    
    }

}

