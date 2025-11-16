/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.ecommerce.persistencia.interfaces;

import itson.ecommerce.persistencia.dtos.NuevoProductoDTO;
import itson.ecommerce.persistencia.dtos.ProductoListaDTO;
import itson.ecommerce.persistencia.entidades.EstadoResena;
import itson.ecommerce.persistencia.entidades.Resena;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;
import java.util.List;

/**
 *
 * @author Dana Chavez
 */
public interface IPersistencia {
    
    public abstract NuevoProductoDTO crearProducto(NuevoProductoDTO dto) throws PersistenciaException;
    
    public abstract List<ProductoListaDTO> obtenerTodosProductos() throws PersistenciaException;

    public abstract List<ProductoListaDTO> buscarProductos(String termino) throws PersistenciaException;
    
    public List<Resena> buscarResenas(String termino, EstadoResena estado) throws PersistenciaException;

    public Resena consultarResena(Long id) throws PersistenciaException;

    public Resena actualizarResena(Resena resena) throws PersistenciaException;

    public boolean eliminarResena(Long idResena) throws PersistenciaException;
}
