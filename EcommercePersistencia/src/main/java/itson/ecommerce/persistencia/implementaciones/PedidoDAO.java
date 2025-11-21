/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.implementaciones;

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
    public Pedido crearPedido(Cliente cliente, float total, EstadoPedido estado) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            em.getTransaction().begin();
            Pedido pedido = new Pedido();
            pedido.setCliente(cliente);
            pedido.setDireccion(cliente.getDireccion());
            pedido.setEstado(estado);
            pedido.setFechaCompra(Calendar.getInstance());
            pedido.setTotal(total);
            Pago pago = new Pago();
            pago.setFecha(Calendar.getInstance());

            pedido.setPago(pago);
            em.persist(pedido);
            em.getTransaction().commit();
            return pedido;
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al insertar el pedido", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
