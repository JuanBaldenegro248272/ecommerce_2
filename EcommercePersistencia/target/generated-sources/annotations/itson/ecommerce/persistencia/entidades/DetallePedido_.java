package itson.ecommerce.persistencia.entidades;

import itson.ecommerce.persistencia.entidades.Pedido;
import itson.ecommerce.persistencia.entidades.Producto;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-11-16T18:22:01", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(DetallePedido.class)
public class DetallePedido_ { 

    public static volatile SingularAttribute<DetallePedido, Float> precioUnitario;
    public static volatile SingularAttribute<DetallePedido, Pedido> pedido;
    public static volatile SingularAttribute<DetallePedido, Long> id;
    public static volatile SingularAttribute<DetallePedido, Integer> cantidad;
    public static volatile SingularAttribute<DetallePedido, Producto> producto;

}