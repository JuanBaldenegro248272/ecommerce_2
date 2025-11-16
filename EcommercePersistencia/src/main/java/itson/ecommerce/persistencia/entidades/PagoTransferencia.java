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
@Table (name = "pagos_transferencia")
@PrimaryKeyJoinColumn (name = "id_pago")
public class PagoTransferencia extends Pago implements Serializable {
    
    @Column (name = "spei", length = 50, nullable = false)
    private String spei;
    
    @Column (name = "banco", length = 25, nullable = false)
    private String banco;

    public PagoTransferencia() {
    }

    public PagoTransferencia(String spei, String banco) {
        this.spei = spei;
        this.banco = banco;
    }

    public String getSpei() {
        return spei;
    }

    public void setSpei(String spei) {
        this.spei = spei;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }
    
}
