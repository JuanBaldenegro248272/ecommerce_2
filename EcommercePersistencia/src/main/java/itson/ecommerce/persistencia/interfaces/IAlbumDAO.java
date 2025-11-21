/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.ecommerce.persistencia.interfaces;

import itson.ecommerce.persistencia.entidades.Album;
import itson.ecommerce.persistencia.entidades.Artista;
import java.util.List;

/**
 *
 * @author victoria
 */
public interface IAlbumDAO {
    List<Album> consultarTodos();
    List<Album> buscar(String termino);
    Album consultar(Long id);
    void crear(Album album, Artista artista);
    Album actualizar(Album album);
    boolean eliminar(Long id);
}
