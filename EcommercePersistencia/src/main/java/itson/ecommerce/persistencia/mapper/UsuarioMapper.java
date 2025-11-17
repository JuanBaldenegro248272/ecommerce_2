/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.mapper;

import itson.ecommerce.persistencia.dtos.UsuarioDTO;
import itson.ecommerce.persistencia.entidades.Usuario;

/**
 *
 * @author Gael
 */
public class UsuarioMapper {

   
    public static UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(
            usuario.getId(),
            usuario.getNombre(),
            usuario.getCorreoElectronico(),
            usuario.isEsActiva()
        );
    }

}

