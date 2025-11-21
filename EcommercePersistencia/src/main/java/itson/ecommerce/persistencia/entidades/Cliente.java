/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Dana Chavez
 */
@Entity
@Table(name = "clientes")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Cliente extends Usuario implements Serializable {

    @Column(name = "telefono", length = 12, nullable = false)
    private String telefono;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_direccion", nullable = false)
    private Direccion direccion;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_carrito", nullable = false)
    private Carrito carrito;

    @OneToMany(mappedBy = "cliente")
    private List<Resena> resenas;

    public Cliente() {
    }

    public Cliente(String telefono, Direccion direccion, Carrito carrito, List<Resena> resenas, Long id, String nombre, String correoElectronico, String contrasenaEncriptada, String hashContrasena, String contrasena, boolean esActiva) {
        super(id, nombre, correoElectronico, contrasenaEncriptada, hashContrasena, contrasena, esActiva);
        this.telefono = telefono;
        this.direccion = direccion;
        this.carrito = carrito;
        this.resenas = resenas;
    }

    public Cliente(String telefono, Direccion direccion, Carrito carrito, List<Resena> resenas) {
        this.telefono = telefono;
        this.direccion = direccion;
        this.carrito = carrito;
        this.resenas = resenas;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public List<Resena> getResenas() {
        return resenas;
    }

    public void setResenas(List<Resena> resenas) {
        this.resenas = resenas;
    }

}
