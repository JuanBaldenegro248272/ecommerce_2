package itson.ecommerce.persistencia.entidades;

import itson.ecommerce.persistencia.entidades.Album;
import itson.ecommerce.persistencia.entidades.Genero;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-11-16T18:22:02", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(GeneroAlbum.class)
public class GeneroAlbum_ { 

    public static volatile SingularAttribute<GeneroAlbum, Album> album;
    public static volatile SingularAttribute<GeneroAlbum, Genero> genero;
    public static volatile SingularAttribute<GeneroAlbum, Long> id;

}