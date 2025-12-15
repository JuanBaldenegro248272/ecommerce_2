/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.implementaciones;

import itson.ecommerce.persistencia.dtos.ClienteDTO;
import itson.ecommerce.persistencia.entidades.Cliente;
import itson.ecommerce.persistencia.entidades.Usuario;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;
import itson.ecommerce.persistencia.interfaces.IUsuarioDAO;
import itson.ecommerce.persistencia.utils.ManejadorConexiones;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
    
   public Usuario actualizar(Usuario usuario) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            em.getTransaction().begin();
            Usuario usuarioActualizado = em.merge(usuario);
            em.getTransaction().commit();
            return usuarioActualizado;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al actualizar el usuario", ex);
        } finally {
            em.close();
        }
    }
    
    public Usuario buscarPorId(Long id) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }
 

    public ClienteDTO obtenerClientePorId(Long idUsuario) throws PersistenciaException {
         EntityManager em = ManejadorConexiones.getEntityManager();
        
         try {
        TypedQuery<Cliente> query = em.createQuery(
            "SELECT c FROM Cliente c WHERE c.id = :idUsuario", Cliente.class);
        query.setParameter("idUsuario", idUsuario);
        
        Cliente cliente = query.getSingleResult();
        
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNombre(cliente.getNombre());
        dto.setCorreoElectronico(cliente.getCorreoElectronico()); 
        
        dto.setTelefono(cliente.getTelefono());
        
        if (cliente.getDireccion() != null) {
            dto.setIdDireccion(cliente.getDireccion().getId());
            dto.setCalle(cliente.getDireccion().getCalle());
            dto.setCiudad(cliente.getDireccion().getCiudad());
            dto.setCodigoPostal(cliente.getDireccion().getCodigoPostal());
            dto.setColonia(cliente.getDireccion().getColonia());
            dto.setEstado(cliente.getDireccion().getEstado());
            
        }
        
        return dto;
        
    } catch (Exception ex) {
        throw new PersistenciaException("Error al obtener datos del cliente", ex);
    } finally {
        em.close();
    }
}

}
