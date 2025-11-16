/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.utils;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 *
 * @author Dana Chavez
 */
public class JasyptUtil {
    
    public static String encrypt(String data) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword("wonderlandRestauranteADMIN25");
        return encryptor.encrypt(data);
    }
    
    public static String decrypt(String encryptedData) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword("wonderlandRestauranteADMIN25");
        return encryptor.decrypt(encryptedData);
    }
}