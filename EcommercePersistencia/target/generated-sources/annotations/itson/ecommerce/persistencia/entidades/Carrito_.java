package itson.ecommerce.persistencia.entidades;

import itson.ecommerce.persistencia.entidades.DetalleCarrito;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-11-16T02:54:19", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Carrito.class)
public class Carrito_ { 

    public static volatile SingularAttribute<Carrito, Float> total;
    public static volatile ListAttribute<Carrito, DetalleCarrito> detalles;
    public static volatile SingularAttribute<Carrito, Long> id;

}