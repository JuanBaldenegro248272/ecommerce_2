/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.mapper;

import itson.ecommerce.persistencia.dtos.PedidoDTO;
import itson.ecommerce.persistencia.entidades.Cliente;
import itson.ecommerce.persistencia.entidades.Direccion;
import itson.ecommerce.persistencia.entidades.EstadoPedido;
import itson.ecommerce.persistencia.entidades.Pago;
import itson.ecommerce.persistencia.entidades.Pedido;

/**
 *
 * @author jrasc
 */
public class PedidoMapper {

    public PedidoMapper() {
    }

    public static PedidoDTO toDTO(Pedido pedido) {

        if (pedido == null) {
            return null;
        }

        Long idCliente = null;
        String nombreCliente = null;
        String correoCliente = null;

        if (pedido.getCliente() != null) {
            idCliente = pedido.getCliente().getId();

            if (pedido.getCliente() != null) {
                nombreCliente = pedido.getCliente().getNombre();
                correoCliente = pedido.getCliente().getCorreoElectronico();
            }
        }

        Long idDireccion = null;
        String direccion = null;

        if (pedido.getDireccion() != null) {
            idDireccion = pedido.getDireccion().getId();

            String calle = pedido.getDireccion().getCalle();
            String ciudad = pedido.getDireccion().getCiudad();
            String colonia = pedido.getDireccion().getColonia();
            Integer codigoPostal = pedido.getDireccion().getCodigoPostal();

            direccion = calle + ciudad + colonia + codigoPostal;
        }

        Long idPago = null;
        String tipoPago = null;

        if (pedido.getPago() != null) {
            idPago = pedido.getPago().getId();
            tipoPago = pedido.getPago().getClass().getSimpleName();
        }

        return new PedidoDTO(
                pedido.getId(),
                pedido.getEstado().name(),
                pedido.getFechaCompra(),
                pedido.getTotal(),
                nombreCliente,
                correoCliente,
                direccion,
                tipoPago,
                idCliente,
                idDireccion,
                idPago
        );
    }

    public static Pedido toEntity(
            PedidoDTO dto,
            Cliente cliente,
            Direccion direccion,
            Pago pago
    ) {
        if (dto == null) {
            return null;
        }

        Pedido pedido = new Pedido();

        pedido.setId(dto.getId());
        pedido.setEstado(EstadoPedido.valueOf(dto.getEstado()));
        pedido.setFechaCompra(dto.getFechaCompra());
        pedido.setTotal(dto.getTotal());

        pedido.setCliente(cliente);
        pedido.setDireccion(direccion);
        pedido.setPago(pago);

        return pedido;
    }

}
