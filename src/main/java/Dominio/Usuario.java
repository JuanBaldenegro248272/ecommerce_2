/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import java.util.List;

/**
 *
 * @author jrasc
 */
public class Usuario {

    private Long id;
    private String nombre;
    private String constrasenia;
    private String correoElectronico;
    private String direccion;
    private String telefono;
    private boolean esActiva;
    private Rol rol;
    private List<Pedido> compras;
    private List<Resena> resenas;
    private Carrito carrito;

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String constrasenia, String correoElectronico, String direccion, String telefono, boolean esActiva, Rol rol, List<Pedido> compras, List<Resena> resenas, Carrito carrito) {
        this.id = id;
        this.nombre = nombre;
        this.constrasenia = constrasenia;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
        this.telefono = telefono;
        this.esActiva = esActiva;
        this.rol = rol;
        this.compras = compras;
        this.resenas = resenas;
        this.carrito = carrito;
    }

    public Usuario(String nombre, String constrasenia, String correoElectronico, String direccion, String telefono, boolean esActiva, Rol rol, List<Pedido> compras, List<Resena> resenas, Carrito carrito) {
        this.nombre = nombre;
        this.constrasenia = constrasenia;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
        this.telefono = telefono;
        this.esActiva = esActiva;
        this.rol = rol;
        this.compras = compras;
        this.resenas = resenas;
        this.carrito = carrito;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getConstrasenia() {
        return constrasenia;
    }

    public void setConstrasenia(String constrasenia) {
        this.constrasenia = constrasenia;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isEsActiva() {
        return esActiva;
    }

    public void setEsActiva(boolean esActiva) {
        this.esActiva = esActiva;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Pedido> getCompras() {
        return compras;
    }

    public void setCompras(List<Pedido> compras) {
        this.compras = compras;
    }

    public List<Resena> getResenas() {
        return resenas;
    }

    public void setResenas(List<Resena> resenas) {
        this.resenas = resenas;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dominio.Usuario[ id=" + id + " ]";
    }

}
