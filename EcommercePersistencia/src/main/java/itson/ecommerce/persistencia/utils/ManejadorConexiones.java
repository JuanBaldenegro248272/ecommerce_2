/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Dana Chavez
 */
public class ManejadorConexiones {

    private static EntityManagerFactory emf;
    public static boolean isTestMode = false;

    public static synchronized EntityManager getEntityManager() {

        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(
                "com.mycompany_EcommercePersistencia_jar_1.0-SNAPSHOTPU"
            );
        }

        return emf.createEntityManager();
    }
}

