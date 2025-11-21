/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.interfaces;

import itson.ecommerce.persistencia.entidades.Cliente;
import itson.ecommerce.persistencia.entidades.EstadoPedido;
import itson.ecommerce.persistencia.entidades.Pedido;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;
import java.util.List;

/**
 *
 * @author jrasc
 */
public interface IPedidoDAO {
    public List<Pedido> obtenerTodos() throws PersistenciaException;
    public Pedido actualizarEstado(Long idPedido, String nuevoEstado) throws PersistenciaException;
    public Pedido crearPedido(Cliente cliente, float total, EstadoPedido estado) throws PersistenciaException;
}
