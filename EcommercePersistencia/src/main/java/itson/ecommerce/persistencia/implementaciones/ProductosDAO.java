/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.implementaciones;

import itson.ecommerce.persistencia.dtos.NuevoProductoDTO;
import itson.ecommerce.persistencia.entidades.Album;
import itson.ecommerce.persistencia.entidades.Producto;
import itson.ecommerce.persistencia.interfaces.IProductosDAO;
import itson.ecommerce.persistencia.mapper.ProductoMapper;
import itson.ecommerce.persistencia.utils.ManejadorConexiones;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Dana Chavez
 */
public class ProductosDAO implements IProductosDAO {

    public ProductosDAO() {
    }

   @Override
    public NuevoProductoDTO crear(NuevoProductoDTO dto) {

        if (dto == null) {
            throw new IllegalArgumentException("El DTO no puede ser null.");
        }

        EntityManager em = null;

        try {
            em = ManejadorConexiones.getEntityManager();
            em.getTransaction().begin();

            if (dto.getAlbumId() == null) {
                throw new IllegalArgumentException("albumId no puede ser nulo.");
            }

            Album album = em.find(Album.class, dto.getAlbumId());
            if (album == null) {
                throw new IllegalArgumentException("Álbum no encontrado con id: " + dto.getAlbumId());
            }

            Producto producto = ProductoMapper.toEntity(dto, album);
            if (producto.getFormato() == null) {
                throw new IllegalArgumentException("Formato inválido: " + dto.getFormato());
            }

            em.persist(producto);
            em.flush();

            em.getTransaction().commit();

            NuevoProductoDTO result = ProductoMapper.toDto(producto);

            return result;

        } catch (Exception ex) {

            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println(ex.getMessage());
            throw new RuntimeException("Error al crear el producto en persistencia", ex);

        } finally {

            if (em != null && em.isOpen()) {
                em.close();
            }

        }
    }

}
