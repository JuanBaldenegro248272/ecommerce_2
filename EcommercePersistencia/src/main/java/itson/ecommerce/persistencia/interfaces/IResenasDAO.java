/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.ecommerce.persistencia.interfaces;

import itson.ecommerce.persistencia.entidades.EstadoResena;
import itson.ecommerce.persistencia.entidades.Resena;
import java.util.List;

/**
 * * @author victoria 
 */
public interface IResenasDAO {

    List<Resena> buscarResenas(String termino, EstadoResena estado);
    Resena consultar(Long id);
    Resena actualizar(Resena resena);
    boolean eliminar(Long idResena);
}