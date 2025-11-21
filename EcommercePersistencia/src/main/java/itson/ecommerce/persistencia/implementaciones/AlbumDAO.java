/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.implementaciones;

import itson.ecommerce.persistencia.entidades.Album;
import itson.ecommerce.persistencia.interfaces.IAlbumDAO;
import itson.ecommerce.persistencia.utils.ManejadorConexiones;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author victoria
 */
public class AlbumDAO implements IAlbumDAO{
    
    @Override
    public List<Album> consultarTodos() {
        return buscar(null); 
    }

    @Override
    public List<Album> buscar(String termino) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            String jpql = "SELECT a FROM Album a JOIN FETCH a.artista ORDER BY a.nombre ASC";
            if (termino != null && !termino.trim().isEmpty()) {
                jpql = "SELECT a FROM Album a JOIN FETCH a.artista ar "
                     + "WHERE LOWER(a.nombre) LIKE LOWER(:termino) OR LOWER(ar.nombreArtistico) LIKE LOWER(:termino) "
                     + "ORDER BY a.nombre ASC";
            }
            
            TypedQuery<Album> query = em.createQuery(jpql, Album.class);
            
            if (termino != null && !termino.trim().isEmpty()) {
                query.setParameter("termino", "%" + termino + "%");
            }
            return query.getResultList();
        } finally {
            if (em != null) em.close();
        }
    }

    @Override
    public Album consultar(Long id) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            return em.find(Album.class, id);
        } finally {
            if (em != null) em.close();
        }
    }

    @Override
    public void crear(Album album) {
        if (album == null) {
            throw new IllegalArgumentException("El álbum no puede ser nulo.");
        }
        
        EntityManager em = null;
        try {
            em = ManejadorConexiones.getEntityManager();
            em.getTransaction().begin();

            em.persist(album);
            em.flush();
            
            em.getTransaction().commit();
            
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            ex.printStackTrace();
            throw new RuntimeException("Error al crear el álbum", ex);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public Album actualizar(Album album) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            em.getTransaction().begin();
            Album actualizado = em.merge(album);
            em.getTransaction().commit();
            return actualizado;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            return null;
        } finally {
            if (em != null) em.close();
        }
    }

    @Override
    public boolean eliminar(Long id) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            em.getTransaction().begin();
            Album album = em.find(Album.class, id);
            if (album != null) {
                em.remove(album);
                em.getTransaction().commit();
                return true;
            }
            em.getTransaction().rollback();
            return false;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            return false;
        } finally {
            if (em != null) em.close();
        }
    }
    
}
