/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.ecommerce.persistencia.interfaces;

import itson.ecommerce.persistencia.entidades.Artista;
import java.util.List;

/**
 *
 * @author victoria
 */
public interface IArtistaDAO {
    public abstract List<Artista> obtenerTodos();
    public abstract Artista obtenerPorId(Long id);
}
