/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.implementaciones;

import itson.ecommerce.persistencia.entidades.Artista;
import itson.ecommerce.persistencia.interfaces.IArtistaDAO;
import itson.ecommerce.persistencia.utils.ManejadorConexiones;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author victoria
 */
public class ArtistaDAO implements IArtistaDAO{
    
    @Override
    public List<Artista> obtenerTodos() {
        EntityManager em = null;
        try {
            em = ManejadorConexiones.getEntityManager();
            
            String jpql = "SELECT a FROM Artista a ORDER BY a.nombreArtistico ASC";
            TypedQuery<Artista> query = em.createQuery(jpql, Artista.class);
            
            return query.getResultList();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al obtener artistas", ex);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    @Override
    public Artista obtenerPorId(Long id) {
        EntityManager em = null;
        try {
            em = ManejadorConexiones.getEntityManager();
            return em.find(Artista.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al obtener artista", ex);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
