/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.mapper;

import itson.ecommerce.persistencia.dtos.ClienteDTO;
import itson.ecommerce.persistencia.dtos.UsuarioDTO;
import itson.ecommerce.persistencia.entidades.Carrito;
import itson.ecommerce.persistencia.entidades.Cliente;
import itson.ecommerce.persistencia.entidades.Direccion;

/**
 *
 * @author jrasc
 */
public class ClienteMapper {

    public static ClienteDTO toDTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        UsuarioDTO baseDTO = UsuarioMapper.toDTO(cliente);
        ClienteDTO dto = new ClienteDTO();
        dto.setId(baseDTO.getId());
        dto.setNombre(baseDTO.getNombre());
        dto.setCorreoElectronico(baseDTO.getCorreoElectronico());
        dto.setEsActiva(baseDTO.getEsActiva());
        dto.setRol(baseDTO.getRol());
        dto.setTelefono(cliente.getTelefono());
        if (cliente.getDireccion() != null) {
            dto.setIdDireccion(cliente.getDireccion().getId());
            dto.setCalle(cliente.getDireccion().getCalle());
            dto.setCiudad(cliente.getDireccion().getCiudad());
        }
        if (cliente.getCarrito() != null) {
            dto.setIdCarrito(cliente.getCarrito().getId());
        }

        return dto;
    }

    public static Cliente toEntity(ClienteDTO dto) {
        if (dto == null) {
            return null;
        }
        Cliente entidad = new Cliente();
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setCorreoElectronico(dto.getCorreoElectronico());
        entidad.setEsActiva(dto.getEsActiva());
        entidad.setTelefono(dto.getTelefono());
        return entidad;
    }

    public static Cliente toEntity(ClienteDTO dto, Direccion direccion, Carrito carrito) {
        Cliente entidad = toEntity(dto);
        if (entidad != null) {
            entidad.setDireccion(direccion);
            entidad.setCarrito(carrito);
        }
        return entidad;
    }
}
