/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.ecommerce.persistencia.interfaces;

import itson.ecommerce.persistencia.entidades.Genero;
import java.util.List;

/**
 *
 * @author Dana Chavez
 */
public interface IGenerosDAO {
    public abstract List<Genero> obtenerTodos();
    
    public abstract Genero obtenerPorId(Long id);
    
    public abstract void crear(Genero genero);
    
    public abstract void actualizar(Genero genero);
    
    public abstract void eliminar(Long id);   
}
