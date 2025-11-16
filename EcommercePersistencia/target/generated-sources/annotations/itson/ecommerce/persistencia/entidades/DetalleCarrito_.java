package itson.ecommerce.persistencia.entidades;

import itson.ecommerce.persistencia.entidades.Carrito;
import itson.ecommerce.persistencia.entidades.Producto;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-11-16T14:43:59", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(DetalleCarrito.class)
public class DetalleCarrito_ { 

    public static volatile SingularAttribute<DetalleCarrito, Long> id;
    public static volatile SingularAttribute<DetalleCarrito, Integer> cantidad;
    public static volatile SingularAttribute<DetalleCarrito, Producto> producto;
    public static volatile SingularAttribute<DetalleCarrito, Carrito> carrito;

}