/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.entidades;

import itson.ecommerce.persistencia.utils.JasyptUtil;
import itson.ecommerce.persistencia.utils.SeguridadUtil;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Dana Chavez
 */
@Entity
@Table (name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;
    
    @Column (name = "nombre", length = 150, nullable = false)
    private String nombre;
    
    @Column (name = "correo_electronico", unique = true, length = 100, nullable = false)
    private String correoElectronico;
    
    @Column (name = "contrasena", length = 255, nullable = false)
    private String contrasenaEncriptada;
    
    @Column (name = "hash_contrasena", nullable = false, length = 64)
    private String hashContrasena;
    
    @Transient
    private String contrasena;
    
    @Column (name = "es_activa")
    private Boolean esActiva;

    public Usuario() {
    }
    
    public Usuario(Long id, String nombre, String correoElectronico, String contrasenaEncriptada, String hashContrasena, String contrasena, boolean esActiva) {
        this.id = id;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.contrasenaEncriptada = contrasenaEncriptada;
        this.hashContrasena = hashContrasena;
        this.contrasena = contrasena;
        this.esActiva = esActiva;
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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
    
    public String getContrasena() {
        if (this.contrasena == null && this.contrasenaEncriptada != null) {
            this.contrasena = JasyptUtil.decrypt(this.contrasenaEncriptada);
        }
        return this.contrasena;    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
        this.contrasenaEncriptada = (contrasena != null) ? JasyptUtil.encrypt(contrasena) : null;
        this.hashContrasena = (contrasena != null) ? SeguridadUtil.generarHash(contrasena) : null;
    }
    
    public String getHashContrasena() {
        return hashContrasena;
    }

    public void setHashContrasena(String hashContrasena) {
        this.hashContrasena = hashContrasena;
    }

    public boolean isEsActiva() {
        return esActiva;
    }

    public void setEsActiva(boolean esActiva) {
        this.esActiva = esActiva;
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
        return "itson.ecommerce.persistencia.entidades.Usuario[ id=" + id + " ]";
    }
    
}
