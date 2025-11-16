/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.implementaciones;

import itson.ecommerce.persistencia.dtos.NuevoProductoDTO;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;
import itson.ecommerce.persistencia.interfaces.IPersistencia;
import itson.ecommerce.persistencia.interfaces.IProductosDAO;

/**
 *
 * @author Dana Chavez
 */
public class Persistencia implements IPersistencia {
    private IProductosDAO productosDAO;

    public Persistencia() {
        this.productosDAO = new ProductosDAO();
    }

    @Override
    public NuevoProductoDTO crearProducto(NuevoProductoDTO dto) throws PersistenciaException {
        return productosDAO.crear(dto);
    }
}
