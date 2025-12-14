package itson.ecommerce.persistencia.implementaciones;

import itson.ecommerce.persistencia.entidades.Carrito;
import itson.ecommerce.persistencia.entidades.DetalleCarrito;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;
import itson.ecommerce.persistencia.interfaces.ICarritosDAO;
import itson.ecommerce.persistencia.utils.ManejadorConexiones;
import javax.persistence.EntityManager;

public class CarritosDAO implements ICarritosDAO{

    public Carrito buscarPorId(Long id) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            Carrito c = em.find(Carrito.class, id);
            if (c != null) {
                em.refresh(c);
            }
            return c;
        } catch (Exception e) {
            throw new PersistenciaException("Error", e);
        } finally {
            em.close();
        }
    }

    public Carrito crear(Carrito c) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return c;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error", e);
        } finally {
            em.close();
        }
    }

    public void actualizar(Carrito c) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error", e);
        } finally {
            em.close();
        }
    }

    public void guardarDetalle(DetalleCarrito d) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(d);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error", e);
        } finally {
            em.close();
        }
    }

    public void actualizarDetalle(DetalleCarrito d) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(d);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error", e);
        } finally {
            em.close();
        }
    }

    public void eliminarDetalle(Long id) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            em.getTransaction().begin();
            DetalleCarrito d = em.find(DetalleCarrito.class, id);
            if (d != null) {
                em.remove(d);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error", e);
        } finally {
            em.close();
        }
    }

    public int contarDetalles(Long idCarrito) throws PersistenciaException {
        EntityManager em = null;
        try {
            em = ManejadorConexiones.getEntityManager();
            Long count = em.createQuery(
                    "SELECT COUNT(d) FROM DetalleCarrito d WHERE d.carrito.id = :id",
                    Long.class
            ).setParameter("id", idCarrito).getSingleResult();
            return count == null ? 0 : count.intValue();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al contar detalles de carrito.", ex);
        } finally {
            try {
                if (em != null && em.isOpen()) {
                    em.close();
                }
            } catch (Exception ignore) {
            }
        }
    }
}
