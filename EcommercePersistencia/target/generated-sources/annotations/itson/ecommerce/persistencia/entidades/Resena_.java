package itson.ecommerce.persistencia.entidades;

import itson.ecommerce.persistencia.entidades.Cliente;
import itson.ecommerce.persistencia.entidades.EstadoResena;
import itson.ecommerce.persistencia.entidades.Producto;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-11-16T15:56:49", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Resena.class)
public class Resena_ { 

    public static volatile SingularAttribute<Resena, Cliente> cliente;
    public static volatile SingularAttribute<Resena, EstadoResena> estado;
    public static volatile SingularAttribute<Resena, Integer> calificacion;
    public static volatile SingularAttribute<Resena, Long> id;
    public static volatile SingularAttribute<Resena, Producto> producto;
    public static volatile SingularAttribute<Resena, String> comentario;

}