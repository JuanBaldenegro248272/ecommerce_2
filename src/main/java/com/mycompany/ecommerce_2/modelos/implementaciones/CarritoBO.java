package com.mycompany.ecommerce_2.modelos.implementaciones;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import com.mycompany.ecommerce_2.modelos.ICarritoBO;
import itson.ecommerce.persistencia.dtos.CarritoDTO;
import itson.ecommerce.persistencia.dtos.DetalleCarritoDTO;
import itson.ecommerce.persistencia.entidades.Carrito;
import itson.ecommerce.persistencia.entidades.DetalleCarrito;
import itson.ecommerce.persistencia.entidades.Producto;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;
import itson.ecommerce.persistencia.interfaces.IPersistencia;
import java.util.ArrayList;
import java.util.List;

public class CarritoBO implements ICarritoBO {

    private final IPersistencia persistencia;

    public CarritoBO(IPersistencia persistencia) {
        this.persistencia = persistencia;
    }

    @Override
    public CarritoDTO crearCarrito() throws BusinessException {
        try {
            Carrito c = new Carrito();
            c.setTotal(0.0f);
            c.setDetalles(new ArrayList<>());
            return convertirADTO(persistencia.crearCarrito(c));
        } catch (PersistenciaException e) { throw new BusinessException(e.getMessage()); }
    }

    @Override
    public CarritoDTO obtenerCarrito(Long id) throws BusinessException {
        try {
            return convertirADTO(persistencia.buscarCarritoPorId(id));
        } catch (PersistenciaException e) { throw new BusinessException(e.getMessage()); }
    }

    @Override
    public void agregarProducto(Long idCarrito, Long idProducto, Integer cantidad) throws BusinessException {
        try {
            Carrito carrito = persistencia.buscarCarritoPorId(idCarrito);
            Producto producto = persistencia.buscarProductoPorIdEntity(idProducto);

            if(carrito == null) throw new BusinessException("Carrito no encontrado");
            if(producto == null) throw new BusinessException("Producto no encontrado");

            boolean existe = false;
            if(carrito.getDetalles() != null) {
                for(DetalleCarrito d : carrito.getDetalles()) {
                    if(d.getProducto().getId().equals(idProducto)) {
                        d.setCantidad(d.getCantidad() + cantidad);
                        
                        // AQUÍ YA NO DARÁ ERROR PORQUE EL MÉTODO YA EXISTE EN LA INTERFAZ
                        persistencia.actualizarDetalleCarrito(d);
                        
                        existe = true;
                        break;
                    }
                }
            }

            if(!existe) {
                DetalleCarrito nuevo = new DetalleCarrito();
                nuevo.setCarrito(carrito);
                nuevo.setProducto(producto);
                nuevo.setCantidad(cantidad);
                persistencia.agregarDetalleCarrito(nuevo);
                
                if(carrito.getDetalles() == null) carrito.setDetalles(new ArrayList<>());
                carrito.getDetalles().add(nuevo);
            }
            recalcularTotal(carrito);

        } catch (Exception e) {
            throw new BusinessException("Error al agregar: " + e.getMessage());
        }
    }
    
    @Override
    public void eliminarProducto(Long idCarrito, Long idDetalle) throws BusinessException {
        try {
            persistencia.eliminarDetalleCarrito(idDetalle);
            Carrito carrito = persistencia.buscarCarritoPorId(idCarrito);
            if(carrito.getDetalles() != null) {
                carrito.getDetalles().removeIf(d -> d.getId().equals(idDetalle));
            }
            recalcularTotal(carrito);
        } catch (Exception e) {
            throw new BusinessException("Error al eliminar");
        }
    }
    
    @Override
    public void vaciarCarrito(Long idCarrito) throws BusinessException {
        try {
            Carrito c = persistencia.buscarCarritoPorId(idCarrito);
            if(c != null && c.getDetalles() != null) {
                for(DetalleCarrito d : c.getDetalles()) persistencia.eliminarDetalleCarrito(d.getId());
                c.setTotal(0f);
                persistencia.actualizarCarrito(c);
            }
        } catch (PersistenciaException e) { throw new BusinessException("Error al vaciar"); }
    }

    private void recalcularTotal(Carrito carrito) throws Exception {
        float total = 0f;
        if(carrito.getDetalles() != null) {
            for(DetalleCarrito d : carrito.getDetalles()) {
                total += d.getCantidad() * d.getProducto().getPrecio();
            }
        }
        carrito.setTotal(total);
        persistencia.actualizarCarrito(carrito);
    }

    private CarritoDTO convertirADTO(Carrito entidad) {
        if(entidad == null) return null;
        CarritoDTO dto = new CarritoDTO();
        dto.setId(entidad.getId());
        dto.setTotal(entidad.getTotal());
        
        List<DetalleCarritoDTO> detalles = new ArrayList<>();
        if(entidad.getDetalles() != null) {
            for(DetalleCarrito d : entidad.getDetalles()) {
                String nombre = "Desconocido";
                String artista = "";
                String img = "default.png";
                if(d.getProducto().getAlbum() != null) {
                    nombre = d.getProducto().getAlbum().getNombre();
                    img = d.getProducto().getAlbum().getImagenUrl();
                    if(d.getProducto().getAlbum().getArtista() != null) artista = d.getProducto().getAlbum().getArtista().getNombreArtistico();
                }
                detalles.add(new DetalleCarritoDTO(d.getId(), d.getProducto().getId(), nombre, artista, img, d.getProducto().getPrecio(), d.getCantidad()));
            }
        }
        dto.setDetalles(detalles);
        return dto;
    }
}