/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Dana Chavez
 */
@Entity
@Table (name = "pagos_tarjeta")
@PrimaryKeyJoinColumn (name = "id_pago")
public class PagoTarjeta extends Pago implements Serializable {
    
    @Column (name = "nombre_titular", length = 50, nullable = false)
    private String nombreTitular;
    
    @Column (name = "apellido_titular", length = 50, nullable = false)
    private String apellidoTitular;

    public PagoTarjeta() {
    }

    public PagoTarjeta(String nombreTitular, String apellidoTitular) {
        this.nombreTitular = nombreTitular;
        this.apellidoTitular = apellidoTitular;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public String getApellidoTitular() {
        return apellidoTitular;
    }

    public void setApellidoTitular(String apellidoTitular) {
        this.apellidoTitular = apellidoTitular;
    }   
    
}
