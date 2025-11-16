package itson.ecommerce.persistencia.entidades;

import itson.ecommerce.persistencia.entidades.Album;
import itson.ecommerce.persistencia.entidades.DetalleCarrito;
import itson.ecommerce.persistencia.entidades.DetallePedido;
import itson.ecommerce.persistencia.entidades.FormatoProducto;
import itson.ecommerce.persistencia.entidades.Resena;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-11-15T18:15:11", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Producto.class)
public class Producto_ { 

    public static volatile SingularAttribute<Producto, String> descripcion;
    public static volatile SingularAttribute<Producto, Boolean> esDisponible;
    public static volatile SingularAttribute<Producto, Float> precio;
    public static volatile SingularAttribute<Producto, Album> album;
    public static volatile SingularAttribute<Producto, FormatoProducto> formato;
    public static volatile ListAttribute<Producto, Resena> resenas;
    public static volatile SingularAttribute<Producto, Long> id;
    public static volatile ListAttribute<Producto, DetallePedido> detallesPedido;
    public static volatile SingularAttribute<Producto, Integer> stock;
    public static volatile ListAttribute<Producto, DetalleCarrito> detallesCarrito;
    public static volatile SingularAttribute<Producto, String> imagenUrl;

}