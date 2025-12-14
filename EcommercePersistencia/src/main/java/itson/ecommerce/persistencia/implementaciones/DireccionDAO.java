/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.implementaciones;

import itson.ecommerce.persistencia.entidades.Direccion;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;
import itson.ecommerce.persistencia.utils.ManejadorConexiones;
import javax.persistence.EntityManager;

/**
 *
 * @author jrasc
 */
public class DireccionDAO {

    public Direccion buscarPorId(Long idDireccion) throws PersistenciaException {
        EntityManager em = null;
        try {
            if (idDireccion == null) {
                throw new PersistenciaException("idDireccion no puede ser null.");
            }
            em = ManejadorConexiones.getEntityManager();
            return em.find(Direccion.class, idDireccion);
        } catch (PersistenciaException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar Dirección por id.", ex);
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
