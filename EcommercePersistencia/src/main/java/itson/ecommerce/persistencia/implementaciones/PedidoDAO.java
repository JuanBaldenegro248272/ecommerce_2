/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.implementaciones;

import itson.ecommerce.persistencia.entidades.Pedido;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;
import itson.ecommerce.persistencia.interfaces.IPedidoDAO;
import itson.ecommerce.persistencia.utils.ManejadorConexiones;
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
            String jpql = "SELECT p FROM Pedido p "
                    + "JOIN FETCH p.cliente "
                    + "JOIN FETCH p.direccion "
                    + "JOIN FETCH p.pago";
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

}
