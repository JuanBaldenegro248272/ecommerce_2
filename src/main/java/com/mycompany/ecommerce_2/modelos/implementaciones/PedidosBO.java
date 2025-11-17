/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce_2.modelos.implementaciones;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import com.mycompany.ecommerce_2.modelos.IPedidoBO;
import itson.ecommerce.persistencia.dtos.PedidoDTO;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;
import itson.ecommerce.persistencia.interfaces.IPersistencia;
import java.util.List;

/**
 *
 * @author jrasc
 */
public class PedidosBO implements IPedidoBO {

    private IPersistencia persistencia;

    public PedidosBO(IPersistencia persistencia) {
        this.persistencia = persistencia;
    }

    @Override
    public List<PedidoDTO> obtenerTodosPedidos() throws BusinessException {
        try {
            return persistencia.obtenerTodosPedidos();
        } catch (PersistenciaException ex) {
            throw new BusinessException("No se pudieron obtener los productos");
        }
    }

    @Override
    public PedidoDTO actualizarEstadoPedido(Long id, String nuevoEstado) throws BusinessException {
        try {
            return persistencia.actualizarEstadoPedido(id, nuevoEstado);
        } catch (PersistenciaException ex) {
            throw new BusinessException("No se pudo actualizar el estado del producto");
        }
    }

}
