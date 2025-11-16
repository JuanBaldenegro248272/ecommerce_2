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
@Table (name = "contra_entregas")
@PrimaryKeyJoinColumn (name = "id_pago")
public class ContraEntrega extends Pago implements Serializable {
    
    @Column (name = "codigo_referencia", unique = true, length = 50, nullable = false)
    private String codigoReferencia;

    public ContraEntrega() {
    }

    public ContraEntrega(String codigoReferencia) {
        this.codigoReferencia = codigoReferencia;
    }
    
}
