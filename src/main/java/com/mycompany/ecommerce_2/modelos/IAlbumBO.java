/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.ecommerce_2.modelos;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import itson.ecommerce.persistencia.dtos.AlbumDTO;
import itson.ecommerce.persistencia.dtos.ArtistaSimpleDTO;
import itson.ecommerce.persistencia.dtos.GeneroDTO;
import itson.ecommerce.persistencia.dtos.NuevoAlbumDTO;
import java.util.List;

/**
 *
 * @author victoria
 */
public interface IAlbumBO {
    List<AlbumDTO > buscar(String termino);
    List<AlbumDTO> obtenerTodoAlbumes() throws BusinessException;
    AlbumDTO consultar(Long id);
    AlbumDTO actualizar(AlbumDTO dto);
    boolean eliminar(Long id);
    
    List<ArtistaSimpleDTO> obtenerTodosArtistas() throws BusinessException;
    List<GeneroDTO> obtenerTodosGeneros() throws BusinessException;
    void crearAlbum(NuevoAlbumDTO dto) throws BusinessException;
}
