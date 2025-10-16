/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import java.util.Date;

/**
 *
 * @author victoria
 */
public class Pago {

    private Long id;
    private String tipoPago;
    private String nombreTitular;
    private String apellidoTitular;
    private String numero;
    private Date fechaExpiracion;
    private String cvv;
    private String noReferencia;
    private Pedido pedido;

    public Pago() {
    }

    public Pago(Long id, String tipoPago, String nombreTitular, String apellidoTitular, String numero, Date fechaExpiracion, String cvv, String noReferencia, Pedido pedido) {
        this.id = id;
        this.tipoPago = tipoPago;
        this.nombreTitular = nombreTitular;
        this.apellidoTitular = apellidoTitular;
        this.numero = numero;
        this.fechaExpiracion = fechaExpiracion;
        this.cvv = cvv;
        this.noReferencia = noReferencia;
        this.pedido = pedido;
    }

    public Pago(String tipoPago, String nombreTitular, String apellidoTitular, String numero, Date fechaExpiracion, String cvv, String noReferencia, Pedido pedido) {
        this.tipoPago = tipoPago;
        this.nombreTitular = nombreTitular;
        this.apellidoTitular = apellidoTitular;
        this.numero = numero;
        this.fechaExpiracion = fechaExpiracion;
        this.cvv = cvv;
        this.noReferencia = noReferencia;
        this.pedido = pedido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getNoReferencia() {
        return noReferencia;
    }

    public void setNoReferencia(String noReferencia) {
        this.noReferencia = noReferencia;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pago)) {
            return false;
        }
        Pago other = (Pago) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dominio.Pago[ id=" + id + " ]";
    }

}
