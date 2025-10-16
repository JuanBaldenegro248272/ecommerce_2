/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

/**
 *
 * @author jrasc
 */
public class Resena {

    private Long id;
    private String comentario;
    private Integer calificacion;
    private Usuario usuario;

    public Resena() {
    }

    public Resena(Long id, String comentario, Integer calificacion, Usuario usuario) {
        this.id = id;
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.usuario = usuario;
    }

    public Resena(String comentario, Integer calificacion, Usuario usuario) {
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof Resena)) {
            return false;
        }
        Resena other = (Resena) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dominio.Resena[ id=" + id + " ]";
    }

}
