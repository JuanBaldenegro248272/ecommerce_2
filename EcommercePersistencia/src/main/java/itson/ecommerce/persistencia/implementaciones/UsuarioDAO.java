/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.implementaciones;

import itson.ecommerce.persistencia.entidades.Usuario;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;
import itson.ecommerce.persistencia.interfaces.IUsuarioDAO;
import itson.ecommerce.persistencia.utils.ManejadorConexiones;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Gael
 */
public class UsuarioDAO implements IUsuarioDAO{
    
    
  public Usuario guardar(Usuario usuario) throws PersistenciaException{
  
    EntityManager em = ManejadorConexiones.getEntityManager();
      try {
          em.getTransaction().begin();
          if (usuario.getId() == null) {
              em.persist(usuario);
          }else{
          usuario = em.merge(usuario);
          }
          em.getTransaction().commit();
          return usuario;
      } catch (Exception ex) {
            throw new PersistenciaException("Error al guardar el usuario: " + usuario, ex);
      }finally{
          em.close();
      }        
      }
  
   public Usuario buscarPorCorreo(String correo) throws PersistenciaException {
       
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.correoElectronico = :correo",
                Usuario.class
            );
            query.setParameter("correo", correo);

            return query.getResultStream().findFirst().orElse(null);

        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar el usuario por correo: " + correo, ex);
        } finally {
            em.close();
        }
    }
    
    
}
