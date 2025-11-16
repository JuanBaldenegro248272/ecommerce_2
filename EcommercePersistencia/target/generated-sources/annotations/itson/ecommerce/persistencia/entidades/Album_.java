package itson.ecommerce.persistencia.entidades;

import itson.ecommerce.persistencia.entidades.Artista;
import itson.ecommerce.persistencia.entidades.GeneroAlbum;
import itson.ecommerce.persistencia.entidades.Producto;
import java.util.Calendar;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-11-15T18:15:11", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Album.class)
public class Album_ { 

    public static volatile SingularAttribute<Album, String> descripcion;
    public static volatile SingularAttribute<Album, Calendar> fechaLanzamiento;
    public static volatile ListAttribute<Album, String> canciones;
    public static volatile SingularAttribute<Album, Artista> artista;
    public static volatile ListAttribute<Album, GeneroAlbum> generos;
    public static volatile SingularAttribute<Album, Long> id;
    public static volatile SingularAttribute<Album, String> nombre;
    public static volatile ListAttribute<Album, Producto> productos;

}