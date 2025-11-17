package itson.ecommerce.persistencia.entidades;

import itson.ecommerce.persistencia.entidades.GeneroAlbum;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-11-16T18:10:29", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Genero.class)
public class Genero_ { 

    public static volatile SingularAttribute<Genero, Long> id;
    public static volatile SingularAttribute<Genero, String> nombre;
    public static volatile ListAttribute<Genero, GeneroAlbum> generosAlbum;

}