/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia;

import itson.ecommerce.persistencia.entidades.Album;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        
        try {
                emf = Persistence.createEntityManagerFactory("com.mycompany_EcommercePersistencia_jar_1.0-SNAPSHOTPU");
                em = emf.createEntityManager();

                em.getTransaction().begin();

                Long id = 1L;
                Album album = em.find(Album.class, id);

                if (album == null) {
                    System.out.println("album con id " + id + " NO encontrado");
                } else {
                    System.out.println("album encontrado: " + album.getNombre());
                }

                em.getTransaction().commit();

            } catch (Exception e) {
                System.err.println("Error:");
                e.printStackTrace();
            } finally {
                if (em != null) em.close();
                if (emf != null) emf.close();
            }
        }
}
