/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.implementaciones;

import itson.ecommerce.persistencia.dtos.NuevaResenaDTO;
import itson.ecommerce.persistencia.entidades.Cliente;
import itson.ecommerce.persistencia.entidades.EstadoResena;
import itson.ecommerce.persistencia.entidades.Producto;
import itson.ecommerce.persistencia.entidades.Resena;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;
import itson.ecommerce.persistencia.interfaces.IResenasDAO;
import itson.ecommerce.persistencia.utils.ManejadorConexiones;
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
    public List<Resena> obtenerTodas() {
        EntityManager em = null;
        try {
            em = ManejadorConexiones.getEntityManager();

            String jpql = "SELECT DISTINCT r FROM Resena r "
                    + "LEFT JOIN FETCH r.cliente c "
                    + "LEFT JOIN FETCH r.producto p "
                    + "LEFT JOIN FETCH p.album a "
                    + "LEFT JOIN FETCH a.artista art "
                    + "ORDER BY r.id DESC";

            TypedQuery<Resena> query = em.createQuery(jpql, Resena.class);
            List<Resena> resultado = query.getResultList();

            return resultado;

        } catch (Exception ex) {
            System.out.println("ERROR en obtenerTodas(): " + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("Error al obtener reseñas", ex);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Resena> buscarPorFiltros(String termino, EstadoResena estado) {
        EntityManager em = null;
        try {
            em = ManejadorConexiones.getEntityManager();

            System.out.println("Término: " + termino + ", Estado: " + estado);

            StringBuilder jpql = new StringBuilder(
                    "SELECT DISTINCT r FROM Resena r "
                    + "LEFT JOIN FETCH r.cliente c "
                    + "LEFT JOIN FETCH r.producto p "
                    + "LEFT JOIN FETCH p.album a "
                    + "LEFT JOIN FETCH a.artista art "
                    + "WHERE 1=1 "
            );

            if (termino != null && !termino.trim().isEmpty()) {
                jpql.append("AND LOWER(r.comentario) LIKE LOWER(:termino) ");
            }

            if (estado != null) {
                jpql.append("AND r.estado = :estado ");
            }

            jpql.append("ORDER BY r.id DESC");

            TypedQuery<Resena> query = em.createQuery(jpql.toString(), Resena.class);

            if (termino != null && !termino.trim().isEmpty()) {
                query.setParameter("termino", "%" + termino + "%");
            }

            if (estado != null) {
                query.setParameter("estado", estado);
            }

            List<Resena> resultado = query.getResultList();

            return resultado;

        } catch (Exception ex) {
            System.out.println("ERROR en buscarPorFiltros(): " + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("Error al buscar reseñas", ex);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public Resena obtenerPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo.");
        }

        EntityManager em = null;
        try {
            em = ManejadorConexiones.getEntityManager();

            String jpql = "SELECT r FROM Resena r "
                    + "LEFT JOIN FETCH r.cliente "
                    + "LEFT JOIN FETCH r.producto "
                    + "WHERE r.id = :id";

            TypedQuery<Resena> query = em.createQuery(jpql, Resena.class);
            query.setParameter("id", id);

            Resena resena = query.getSingleResult();

            return resena;

        } catch (javax.persistence.NoResultException ex) {
            System.out.println("Reseña no encontrada con ID: " + id);
            throw new IllegalArgumentException("Reseña no encontrada con ID: " + id);
        } catch (Exception ex) {
            System.out.println("ERROR al obtener reseña: " + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("Error al obtener la reseña", ex);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void actualizar(Resena resena) {
        if (resena == null) {
            throw new IllegalArgumentException("La reseña no puede ser nula.");
        }

        EntityManager em = null;
        try {
            em = ManejadorConexiones.getEntityManager();
            em.getTransaction().begin();

            em.merge(resena);
            em.flush();
            em.getTransaction().commit();

        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR al actualizar reseña: " + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("Error al actualizar la reseña", ex);
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

            Resena resena = em.find(Resena.class, id);

            if (resena == null) {
                throw new IllegalArgumentException("Reseña no encontrada con ID: " + id);
            }

            em.remove(resena);
            em.flush();
            em.getTransaction().commit();

        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR al eliminar reseña: " + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("Error al eliminar la reseña", ex);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public Resena crearResena(Cliente cliente, Producto producto, int estrellas, String comentario, EstadoResena estado) throws PersistenciaException{
        EntityManager em = ManejadorConexiones.getEntityManager();     
        try{
            em.getTransaction().begin();
            Resena resena = new Resena();
            resena.setCliente(cliente);
            resena.setCalificacion(estrellas);
            resena.setProducto(producto);
            resena.setComentario(comentario);
            resena.setEstado(estado);
            em.persist(resena);
            return resena;
        }catch(Exception e){
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al insertar a la resena", e);
        }finally{
            if (em != null) {
                em.close();
            }
        }
    }

}
