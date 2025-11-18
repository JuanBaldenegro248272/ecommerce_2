/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia;

import itson.ecommerce.persistencia.entidades.Administrador;
import itson.ecommerce.persistencia.entidades.Album;
import itson.ecommerce.persistencia.entidades.Usuario;
import itson.ecommerce.persistencia.implementaciones.UsuarioDAO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        
        UsuarioDAO usuario = new UsuarioDAO();
        
        try {
                
               
                

                String correo = "admin@correo.com";
                Usuario admin = usuario.buscarPorCorreo(correo);

                if (admin == null) {
                    System.out.println("admin con correo " + correo + " NO encontrado");
                } else {
                    System.out.println("album encontrado: " + admin.getNombre() + admin.getId());
                }

                

            } catch (Exception e) {
                System.err.println("Error:");
                e.printStackTrace();
            } finally {
                
            }
        }
}
