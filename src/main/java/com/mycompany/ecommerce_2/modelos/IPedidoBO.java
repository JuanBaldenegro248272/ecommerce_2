/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce_2.modelos;

import com.mycompany.ecommerce_2.exceptions.BusinessException;
import itson.ecommerce.persistencia.dtos.PedidoDTO;
import java.util.List;

/**
 *
 * @author jrasc
 */
public interface IPedidoBO {
    public List<PedidoDTO> obtenerTodosPedidos() throws BusinessException;
}
