package itson.ecommerce.persistencia.interfaces;

import itson.ecommerce.persistencia.entidades.Carrito;
import itson.ecommerce.persistencia.entidades.DetalleCarrito;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;

public interface ICarritosDAO {

    Carrito buscarPorId(Long id) throws PersistenciaException;

    Carrito crear(Carrito carrito) throws PersistenciaException;

    void actualizar(Carrito carrito) throws PersistenciaException;

    void guardarDetalle(DetalleCarrito detalle) throws PersistenciaException;

    void actualizarDetalle(DetalleCarrito detalle) throws PersistenciaException;

    void eliminarDetalle(Long idDetalle) throws PersistenciaException;
}