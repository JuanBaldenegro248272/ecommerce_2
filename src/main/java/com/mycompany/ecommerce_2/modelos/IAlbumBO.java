/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.ecommerce_2.modelos;

import itson.ecommerce.persistencia.entidades.Album;
import java.util.List;

/**
 *
 * @author victoria
 */
public interface IAlbumBO {
    List<Album> buscar(String termino);
    Album consultar(Long id);
    Album crear(Album album);
    Album actualizar(Album album);
    boolean eliminar(Long id);
}
