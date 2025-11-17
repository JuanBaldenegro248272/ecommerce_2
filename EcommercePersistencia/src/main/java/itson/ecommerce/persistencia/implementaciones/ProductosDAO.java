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
import javax.persistence.TypedQuery;

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

    @Override
    public List<Producto> obtenerTodos() {
        EntityManager em = null;
        try {
            em = ManejadorConexiones.getEntityManager();

            String jpql = "SELECT DISTINCT p FROM Producto p " +
                         "LEFT JOIN FETCH p.album a " +
                         "LEFT JOIN FETCH a.artista art " +
                         "LEFT JOIN FETCH a.generos g " +
                         "LEFT JOIN FETCH g.genero " +
                         "ORDER BY p.id DESC";
            
            TypedQuery<Producto> query = em.createQuery(jpql, Producto.class);
            return query.getResultList();
            
        } catch (Exception ex) {
            System.out.println("Error al obtener productos: " + ex.getMessage());
            throw new RuntimeException("Error al obtener productos", ex);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Producto> buscarPorNombre(String termino) {
        EntityManager em = null;
        try {
            em = ManejadorConexiones.getEntityManager();
            
            String jpql = "SELECT DISTINCT p FROM Producto p " +
                         "LEFT JOIN FETCH p.album a " +
                         "LEFT JOIN FETCH a.artista art " +
                         "LEFT JOIN FETCH a.generos g " +
                         "LEFT JOIN FETCH g.genero " +
                         "WHERE LOWER(a.nombre) LIKE LOWER(:termino) " +
                         "OR LOWER(art.nombreArtistico) LIKE LOWER(:termino) " +
                         "ORDER BY p.id DESC";
            
            TypedQuery<Producto> query = em.createQuery(jpql, Producto.class);
            query.setParameter("termino", "%" + termino + "%");
            
            return query.getResultList();
            
        } catch (Exception ex) {
            System.out.println("Error al buscar productos: " + ex.getMessage());
            throw new RuntimeException("Error al buscar productos", ex);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void eliminar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo.");
        }
        
        EntityManager em = null;
        try {
            em = ManejadorConexiones.getEntityManager();
            em.getTransaction().begin();

            Producto producto = em.find(Producto.class, id);
            
            if (producto == null) {
                throw new IllegalArgumentException("Producto no encontrado con ID: " + id);
            }
            
            System.out.println("Producto encontrado: " + producto.getAlbum().getNombre() + 
                             " - Formato: " + producto.getFormato());

            em.remove(producto);
            em.flush();
            em.getTransaction().commit();
            
            System.out.println("Producto eliminado exitosamente");
            
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR al eliminar producto: " + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("Error al eliminar el producto", ex);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public Producto obtenerPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo.");
        }
        
        EntityManager em = null;
        try {
            em = ManejadorConexiones.getEntityManager();
            
            String jpql = "SELECT p FROM Producto p " +
                         "LEFT JOIN FETCH p.album a " +
                         "LEFT JOIN FETCH a.artista art " +
                         "WHERE p.id = :id";
            
            TypedQuery<Producto> query = em.createQuery(jpql, Producto.class);
            query.setParameter("id", id);
            
            Producto producto = query.getSingleResult();

            return producto;
            
        } catch (javax.persistence.NoResultException ex) {
            System.out.println("Producto no encontrado con ID: " + id);
            throw new IllegalArgumentException("Producto no encontrado con ID: " + id);
        } catch (Exception ex) {
            System.out.println("ERROR al obtener producto: " + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("Error al obtener el producto", ex);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void actualizar(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        
        EntityManager em = null;
        try {
            em = ManejadorConexiones.getEntityManager();
            em.getTransaction().begin();
            
            Producto productoActualizado = em.merge(producto);
            em.flush();
            em.getTransaction().commit();
            
            
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR al actualizar producto: " + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("Error al actualizar el producto", ex);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

}
