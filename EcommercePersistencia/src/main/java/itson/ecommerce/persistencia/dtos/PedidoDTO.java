/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.dtos;

import java.util.Calendar;

/**
 *
 * @author jrasc
 */
public class PedidoDTO {

    private Long id;
    private String estado;
    private Calendar fechaCompra;
    private Float total;
    private String nombreCliente;
    private String correoCliente;
    private String direccion;
    private String tipoPago;
    private Long idCliente;
    private Long idDireccion;
    private Long idPago;

    public PedidoDTO(Long id, String estado, Calendar fechaCompra, Float total, String nombreCliente, String correoCliente, String direccion, String tipoPago, Long idCliente, Long idDireccion, Long idPago) {
        this.id = id;
        this.estado = estado;
        this.fechaCompra = fechaCompra;
        this.total = total;
        this.nombreCliente = nombreCliente;
        this.correoCliente = correoCliente;
        this.direccion = direccion;
        this.tipoPago = tipoPago;
        this.idCliente = idCliente;
        this.idDireccion = idDireccion;
        this.idPago = idPago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Calendar getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Calendar fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Long idDireccion) {
        this.idDireccion = idDireccion;
    }

    public Long getIdPago() {
        return idPago;
    }

    public void setIdPago(Long idPago) {
        this.idPago = idPago;
    }

}
