/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.ecommerce.persistencia.interfaces;

import itson.ecommerce.persistencia.dtos.AlbumDTO;
import itson.ecommerce.persistencia.dtos.EditarProductoDTO;
import itson.ecommerce.persistencia.dtos.GeneroDTO;
import itson.ecommerce.persistencia.dtos.NuevoProductoDTO;
import itson.ecommerce.persistencia.dtos.PedidoDTO;
import itson.ecommerce.persistencia.dtos.ProductoListaDTO;
import itson.ecommerce.persistencia.dtos.ResenaListaDTO;
import itson.ecommerce.persistencia.entidades.Artista;
import itson.ecommerce.persistencia.entidades.Usuario;
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

    public abstract void eliminarProducto(Long id) throws PersistenciaException;

    public abstract List<ResenaListaDTO> obtenerTodasResenas() throws PersistenciaException;

    public abstract List<ResenaListaDTO> buscarResenas(String termino, String estado) throws PersistenciaException;

    public abstract void aprobarResena(Long id) throws PersistenciaException;

    public boolean eliminarResena(Long idResena) throws PersistenciaException;

    public Resena crearResena(NuevaResenaDTO dto) throws PersistenciaException;
    
    public abstract EditarProductoDTO obtenerProductoPorId(Long id) throws PersistenciaException;

    public abstract void actualizarProducto(EditarProductoDTO dto) throws PersistenciaException;

    public Usuario buscarPorCorreo(String correo) throws PersistenciaException;

    public Usuario guardar(Usuario usuario) throws PersistenciaException;

    public abstract List<PedidoDTO> obtenerTodosPedidos() throws PersistenciaException;

    List<Artista> consultarArtistasTodos() throws PersistenciaException;

    List<AlbumDTO> buscarAlbumes(String termino) throws PersistenciaException;

    List<AlbumDTO> obtenerTodosAlbumes() throws PersistenciaException;

    AlbumDTO consultarAlbum(Long id) throws PersistenciaException;

    AlbumDTO crearAlbum(AlbumDTO dto) throws PersistenciaException;

    AlbumDTO actualizarAlbum(AlbumDTO dto) throws PersistenciaException;

    boolean eliminarAlbum(Long id) throws PersistenciaException;

    public PedidoDTO actualizarEstadoPedido(Long idPedido, String nuevoEstado) throws PersistenciaException;

    public abstract List<GeneroDTO> obtenerTodosGeneros() throws PersistenciaException;

    public abstract void crearGenero(String nombre) throws PersistenciaException;

    public abstract void actualizarGenero(Long id, String nombre) throws PersistenciaException;

    public abstract void eliminarGenero(Long id) throws PersistenciaException;

}
