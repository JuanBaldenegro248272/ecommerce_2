/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.implementaciones;

import itson.ecommerce.persistencia.entidades.Genero;
import itson.ecommerce.persistencia.interfaces.IGenerosDAO;
import itson.ecommerce.persistencia.utils.ManejadorConexiones;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Dana Chavez
 */
public class GenerosDAO implements IGenerosDAO {

    @Override
    public List<Genero> obtenerTodos() {
        EntityManager em = null;
        try {
            em = ManejadorConexiones.getEntityManager();

            String jpql = "SELECT DISTINCT g FROM Genero g " +
                         "LEFT JOIN FETCH g.generosAlbum ga " +
                         "ORDER BY g.nombre ASC";
            
            TypedQuery<Genero> query = em.createQuery(jpql, Genero.class);
            List<Genero> resultado = query.getResultList();

            return resultado;
            
        } catch (Exception ex) {
            System.out.println("ERROR en obtenerTodos(): " + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("Error al obtener géneros", ex);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    @Override
    public Genero obtenerPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo.");
        }
        
        EntityManager em = null;
        try {
            em = ManejadorConexiones.getEntityManager();

            Genero genero = em.find(Genero.class, id);
            
            if (genero == null) {
                throw new IllegalArgumentException("Género no encontrado con ID: " + id);
            }

            return genero;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al obtener el género", ex);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    @Override
    public void crear(Genero genero) {
        if (genero == null) {
            throw new IllegalArgumentException("El género no puede ser nulo.");
        }
        
        EntityManager em = null;
        try {
            em = ManejadorConexiones.getEntityManager();
            em.getTransaction().begin();

            em.persist(genero);
            em.flush();
            em.getTransaction().commit();

        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            ex.printStackTrace();
            throw new RuntimeException("Error al crear el género", ex);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    @Override
    public void actualizar(Genero genero) {
        if (genero == null) {
            throw new IllegalArgumentException("El género no puede ser nulo.");
        }
        
        EntityManager em = null;
        try {
            em = ManejadorConexiones.getEntityManager();
            em.getTransaction().begin();

            em.merge(genero);
            em.flush();
            em.getTransaction().commit();

        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR al actualizar género: " + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("Error al actualizar el género", ex);
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

            Genero genero = em.find(Genero.class, id);
            
            if (genero == null) {
                throw new IllegalArgumentException("Género no encontrado con ID: " + id);
            }
            
            em.remove(genero);
            em.flush();
            em.getTransaction().commit();

        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR al eliminar género: " + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("Error al eliminar el género", ex);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
