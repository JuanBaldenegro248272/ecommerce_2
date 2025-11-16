/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.implementaciones;

import itson.ecommerce.persistencia.entidades.EstadoResena;
import itson.ecommerce.persistencia.entidades.Resena;
import itson.ecommerce.persistencia.interfaces.IResenasDAO;
import itson.ecommerce.persistencia.utils.ManejadorConexiones; // ¡Importamos tu clase!
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * * @author victoria
 */
public class ResenasDAO implements IResenasDAO {

    @Override
    public List<Resena> buscarResenas(String termino, EstadoResena estado) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        
        Map<String, Object> parametros = new HashMap<>();
        
        String jpql = "SELECT r FROM Resena r JOIN FETCH r.cliente JOIN FETCH r.producto WHERE 1=1 ";
        if (termino != null && !termino.trim().isEmpty()) {
            jpql += "AND LOWER(r.comentario) LIKE LOWER(:termino) ";
            parametros.put("termino", "%" + termino + "%");
        }
        if (estado != null) {
            jpql += "AND r.estado = :estado ";
            parametros.put("estado", estado);
        }
        jpql += "ORDER BY r.estado ASC, r.id ASC";
        try {
            TypedQuery<Resena> query = em.createQuery(jpql, Resena.class);
            for (Map.Entry<String, Object> entry : parametros.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
            
            return query.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Resena consultar(Long id) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            return em.find(Resena.class, id);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Resena actualizar(Resena resena) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            em.getTransaction().begin();
            Resena actualizada = em.merge(resena);
            em.getTransaction().commit();
            return actualizada;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public boolean eliminar(Long idResena) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            em.getTransaction().begin();
            Resena resena = em.find(Resena.class, idResena);
            if (resena != null) {
                em.remove(resena);
                em.getTransaction().commit();
                return true;
            } else {
                em.getTransaction().rollback();
                return false; 
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false; 
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}