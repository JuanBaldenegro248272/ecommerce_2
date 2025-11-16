package itson.ecommerce.persistencia.entidades;

import itson.ecommerce.persistencia.entidades.Album;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-11-16T14:43:59", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Artista.class)
public class Artista_ { 

    public static volatile ListAttribute<Artista, Album> albums;
    public static volatile SingularAttribute<Artista, String> nombreArtistico;
    public static volatile SingularAttribute<Artista, Long> id;

}