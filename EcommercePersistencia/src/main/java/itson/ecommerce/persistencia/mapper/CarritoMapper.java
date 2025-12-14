package itson.ecommerce.persistencia.mapper;

import itson.ecommerce.persistencia.dtos.CarritoDTO;
import itson.ecommerce.persistencia.dtos.DetalleCarritoDTO;
import itson.ecommerce.persistencia.entidades.Carrito;
import itson.ecommerce.persistencia.entidades.DetalleCarrito;
import java.util.ArrayList;
import java.util.List;

public class CarritoMapper {

    public static CarritoDTO toDTO(Carrito entidad) {
        if (entidad == null) {
            return null;
        }

        CarritoDTO dto = new CarritoDTO();
        dto.setId(entidad.getId());
        dto.setTotal(entidad.getTotal());

        List<DetalleCarritoDTO> detalles = new ArrayList<>();
        int totalItems = 0; // Variable para contar

        if (entidad.getDetalles() != null) {
            for (DetalleCarrito d : entidad.getDetalles()) {
                String nombre = "Desconocido";
                String artista = "Varios";
                String img = "default.png";

                // Verificamos que no sean nulos para evitar NullPointerException
                if (d.getProducto() != null) {
                    if(d.getProducto().getAlbum() != null){
                        nombre = d.getProducto().getAlbum().getNombre();
                        
                        // IMPORTANTE: Aquí se obtiene la imagen. Si falla, ponemos default
                        if(d.getProducto().getAlbum().getImagenUrl() != null){
                             img = d.getProducto().getAlbum().getImagenUrl();
                        }
                        
                        if (d.getProducto().getAlbum().getArtista() != null) {
                            artista = d.getProducto().getAlbum().getArtista().getNombreArtistico();
                        }
                    }
                }
                
                Long prodId = (d.getProducto() != null) ? d.getProducto().getId() : 0L;
                Float precio = (d.getProducto() != null) ? d.getProducto().getPrecio() : 0f;

                detalles.add(new DetalleCarritoDTO(
                    d.getId(), 
                    prodId, 
                    nombre, 
                    artista, 
                    img, 
                    precio, 
                    d.getCantidad()
                ));
                
                // Sumamos la cantidad de este producto al total
                totalItems += d.getCantidad();
            }
        }
        
        dto.setDetalles(detalles);
        dto.setCantidadItems(totalItems); // ¡ESTA LÍNEA FALTABA!

        return dto;
    }
}