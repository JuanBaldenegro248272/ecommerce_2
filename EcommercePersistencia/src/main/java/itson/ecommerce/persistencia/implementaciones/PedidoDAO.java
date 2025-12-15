/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.implementaciones;

import itson.ecommerce.persistencia.entidades.Carrito;
import itson.ecommerce.persistencia.entidades.Cliente;
import itson.ecommerce.persistencia.entidades.EstadoPedido;
import itson.ecommerce.persistencia.entidades.Pago;
import itson.ecommerce.persistencia.entidades.Pedido;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;
import itson.ecommerce.persistencia.interfaces.IPedidoDAO;
import itson.ecommerce.persistencia.utils.ManejadorConexiones;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author jrasc
 */
public class PedidoDAO implements IPedidoDAO {

    public PedidoDAO() {
    }

    @Override
    public List<Pedido> obtenerTodos() throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            em.getTransaction().begin();
            String jpql = "SELECT p FROM Pedido p "
                    + "LEFT JOIN FETCH p.cliente "
                    + "LEFT JOIN FETCH p.direccion "
                    + "LEFT JOIN FETCH p.pago";
            List<Pedido> pedidos = em.createQuery(jpql, Pedido.class).getResultList();
            return pedidos;
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener los pedidos " + e.getMessage());
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public Pedido actualizarEstado(Long idPedido, String nuevoEstado) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            em.getTransaction().begin();

            Pedido pedido = em.find(Pedido.class, idPedido);
            if (pedido == null) {
                throw new PersistenciaException("No se encontro el pedido " + idPedido);
            }
            pedido.setEstado(EstadoPedido.valueOf(nuevoEstado));
            em.getTransaction().commit();
            return pedido;
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al actualizar el pedido", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Pedido crearPedido(Pedido nuevoPedido, Carrito carritoAEliminar) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(nuevoPedido);
            Carrito carritoGestionado = em.merge(carritoAEliminar);
            em.remove(carritoGestionado);
            em.getTransaction().commit();
            return nuevoPedido;
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("No se pudo completar el pedido", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Pedido> obtenerPedidosUsuario(String correo) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            String jpql = "SELECT p FROM Pedido p "
                    + "JOIN FETCH p.cliente c "
                    + "LEFT JOIN FETCH p.direccion "
                    + "LEFT JOIN FETCH p.pago "
                    + "WHERE c.correoElectronico = :correo "
                    + "ORDER BY p.fechaCompra DESC";
            TypedQuery<Pedido> query = em.createQuery(jpql, Pedido.class);
            query.setParameter("correo", correo);
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener los pedidos del usuario: " + correo, e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
