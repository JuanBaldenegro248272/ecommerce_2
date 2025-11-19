/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia;

import itson.ecommerce.persistencia.entidades.Administrador;
import itson.ecommerce.persistencia.implementaciones.UsuarioDAO;
import itson.ecommerce.persistencia.interfaces.IUsuarioDAO;

public class Main {

    public static void main(String[] args) {

        IUsuarioDAO usuarioDAO = new UsuarioDAO();
        final String correoAdmin = "admin@seeder.com";
        final String contrasenia = "password123";

        try {
            Administrador nuevoAdmin = new Administrador();
            nuevoAdmin.setNombre("Admin Seeder");
            nuevoAdmin.setCorreoElectronico(correoAdmin);
            nuevoAdmin.setContrasena(contrasenia);
            nuevoAdmin.setEsActiva(true);
            usuarioDAO.guardar(nuevoAdmin);

        } catch (Exception e) {
            System.err.println("Error al crear al admin");
            e.printStackTrace();
        }
    }
}
