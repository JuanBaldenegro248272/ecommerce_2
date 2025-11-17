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
    public List<Artista> consultarTodos() {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            TypedQuery<Artista> query = em.createQuery("SELECT a FROM Artista a ORDER BY a.nombreArtistico ASC", Artista.class);
            return query.getResultList();
        } finally {
            if (em != null) em.close();
        }
    }
}
