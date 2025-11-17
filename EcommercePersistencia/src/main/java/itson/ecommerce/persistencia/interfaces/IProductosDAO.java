/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.ecommerce.persistencia.interfaces;

import itson.ecommerce.persistencia.dtos.NuevoProductoDTO;
import itson.ecommerce.persistencia.entidades.Producto;
import java.util.List;

/**
 *
 * @author Dana Chavez
 */
public interface IProductosDAO {

    public abstract NuevoProductoDTO crear(NuevoProductoDTO dto);
    
    public abstract List<Producto> obtenerTodos();
    
    public abstract List<Producto> buscarPorNombre(String termino);
    
    public abstract void eliminar(Long id);
    
    public abstract Producto obtenerPorId(Long id);
    
    public abstract void actualizar(Producto producto);
}
