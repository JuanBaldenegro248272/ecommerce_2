/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.implementaciones;

import itson.ecommerce.persistencia.dtos.NuevaResenaDTO;
import itson.ecommerce.persistencia.dtos.NuevoProductoDTO;
import itson.ecommerce.persistencia.dtos.ProductoListaDTO;
import itson.ecommerce.persistencia.entidades.EstadoResena;
import itson.ecommerce.persistencia.entidades.Producto;
import itson.ecommerce.persistencia.entidades.Resena;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;
import itson.ecommerce.persistencia.interfaces.IPersistencia;
import itson.ecommerce.persistencia.interfaces.IProductosDAO;
import itson.ecommerce.persistencia.interfaces.IResenasDAO;
import itson.ecommerce.persistencia.mapper.ProductoMapper;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Dana Chavez
 */
public class Persistencia implements IPersistencia {
    private IProductosDAO productosDAO;
    private IResenasDAO resenasDAO;

    public Persistencia() {
        this.productosDAO = new ProductosDAO();
        this.resenasDAO = new ResenasDAO();
    }

    @Override
    public NuevoProductoDTO crearProducto(NuevoProductoDTO dto) throws PersistenciaException {
        return productosDAO.crear(dto);
    }

    @Override
    public List<ProductoListaDTO> obtenerTodosProductos() throws PersistenciaException {
        try {
            List<Producto> productos = productosDAO.obtenerTodos();
            return productos.stream()
                .map(ProductoMapper::toListaDTO)
                .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new PersistenciaException("Error al obtener productos", ex);
        }
    }

    @Override
    public List<ProductoListaDTO> buscarProductos(String termino) throws PersistenciaException {
        try {
            List<Producto> productos = productosDAO.buscarPorNombre(termino);
            return productos.stream()
                .map(ProductoMapper::toListaDTO)
                .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar productos", ex);
        }
    }

    @Override
    public void eliminarProducto(Long id) throws PersistenciaException {
        try {
            productosDAO.eliminar(id);
        } catch (Exception ex) {
            System.out.println("ERROR en Persistencia.eliminarProducto: " + ex.getMessage());
            throw new PersistenciaException("Error al eliminar producto", ex);
        }
    }
    
    @Override
    public List<Resena> buscarResenas(String termino, EstadoResena estado) throws PersistenciaException {
        try {
            return this.resenasDAO.buscarResenas(termino, estado);
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar reseñas: " + e.getMessage());
        }
    }

    @Override
    public Resena consultarResena(Long id) throws PersistenciaException {
        try {
            return this.resenasDAO.consultar(id);
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar reseña: " + e.getMessage());
        }
    }

    @Override
    public Resena actualizarResena(Resena resena) throws PersistenciaException {
        try {
            return this.resenasDAO.actualizar(resena);
        } catch (Exception e) {
            throw new PersistenciaException("Error al actualizar reseña: " + e.getMessage());
        }
    }

    @Override
    public boolean eliminarResena(Long idResena) throws PersistenciaException {
        try {
            return this.resenasDAO.eliminar(idResena);
        } catch (Exception e) {
            throw new PersistenciaException("Error al eliminar reseña: " + e.getMessage());
        }
    }
    
    @Override
    public Resena crearResena(NuevaResenaDTO dto) throws PersistenciaException {
        try {            
            return this.resenasDAO.crear(dto);
        } catch (Exception e) {
            throw new PersistenciaException("Error al crear la reseña: " + e.getMessage());
        }
    }
}
