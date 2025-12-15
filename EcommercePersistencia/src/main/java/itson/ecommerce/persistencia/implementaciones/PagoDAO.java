/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.implementaciones;

import itson.ecommerce.persistencia.entidades.Pago;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;
import itson.ecommerce.persistencia.interfaces.IPagoDAO;
import itson.ecommerce.persistencia.utils.ManejadorConexiones;
import javax.persistence.EntityManager;

/**
 *
 * @author jrasc
 */
public class PagoDAO implements IPagoDAO{

    public Pago buscarPorId(Long idPago) throws PersistenciaException {
        EntityManager em = null;
        try {
            if (idPago == null) {
                throw new PersistenciaException("idPago no puede ser null.");
            }
            em = ManejadorConexiones.getEntityManager();
            return em.find(Pago.class, idPago);
        } catch (PersistenciaException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar Pago por id.", ex);
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
