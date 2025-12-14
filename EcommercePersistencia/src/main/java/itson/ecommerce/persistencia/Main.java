/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia;

import itson.ecommerce.persistencia.dtos.NuevoProductoDTO;
import itson.ecommerce.persistencia.entidades.Administrador;
import itson.ecommerce.persistencia.entidades.Album;
import itson.ecommerce.persistencia.entidades.Artista;
import itson.ecommerce.persistencia.entidades.Genero;
import itson.ecommerce.persistencia.entidades.GeneroAlbum;
import itson.ecommerce.persistencia.implementaciones.AlbumDAO;
import itson.ecommerce.persistencia.implementaciones.ArtistaDAO;
import itson.ecommerce.persistencia.implementaciones.GenerosDAO;
import itson.ecommerce.persistencia.implementaciones.ProductosDAO;
import itson.ecommerce.persistencia.implementaciones.UsuarioDAO;
import itson.ecommerce.persistencia.interfaces.IAlbumDAO;
import itson.ecommerce.persistencia.interfaces.IArtistaDAO;
import itson.ecommerce.persistencia.interfaces.IGenerosDAO;
import itson.ecommerce.persistencia.interfaces.IProductosDAO;
import itson.ecommerce.persistencia.interfaces.IUsuarioDAO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        IUsuarioDAO usuarioDAO = new UsuarioDAO();
        IGenerosDAO generosDAO = new GenerosDAO();
        IArtistaDAO artistaDAO = new ArtistaDAO();
        IAlbumDAO albumDAO = new AlbumDAO();
        IProductosDAO productosDAO = new ProductosDAO();

        try {
            try {
                Administrador admin = new Administrador();
                admin.setNombre("Admin Seeder");
                admin.setCorreoElectronico("admin@store.com");
                admin.setContrasena("admin123");
                admin.setEsActiva(true);
                usuarioDAO.guardar(admin);
                System.out.println("Admin verificado.");
            } catch (Exception e) {
                System.out.println("Nota: El admin ya existía o hubo un error al crearlo.");
            }
            Genero rock = new Genero();
            rock.setNombre("Rock");
            generosDAO.crear(rock);
            Genero pop = new Genero();
            pop.setNombre("Pop");
            generosDAO.crear(pop);
            Genero indie = new Genero();
            indie.setNombre("Indie");
            generosDAO.crear(indie);
            Genero alt = new Genero();
            alt.setNombre("Alternative");
            generosDAO.crear(alt);
            Genero rnb = new Genero();
            rnb.setNombre("R&B");
            generosDAO.crear(rnb);
            Genero kpop = new Genero();
            kpop.setNombre("K-Pop");
            generosDAO.crear(kpop);
            Genero reg = new Genero();
            reg.setNombre("Reggaeton");
            generosDAO.crear(reg);

            Map<String, Artista> mapaArtistas = new HashMap<>();
            Object[][] catalogo = {
                {"Fleetwood Mac", "Rumours", "rumours.png", 500f, rock},
                {"Nirvana", "Nevermind", "nevermind.png", 550f, rock},
                {"Amy Winehouse", "Back To Black", "back2black.png", 600f, rnb},
                {"Sabrina Carpenter", "Short n' Sweet", "shortnsweet.png", 450f, pop},
                {"Taylor Swift", "folklore", "folklore.png", 700f, indie},
                {"Arctic Monkeys", "AM", "am.png", 550f, rock},
                {"Interpol", "Turn on the Bright Lights", "turnbright.png", 520f, indie},
                {"The Neighbourhood", "Wiped Out!", "wipedout.png", 480f, alt},
                {"Radiohead", "the bends", "thebends.png", 500f, rock},
                {"Slowdive", "Souvlaki", "souvlaki.png", 490f, indie},
                {"Olivia Rodrigo", "Guts", "guts.png", 550f, pop},
                {"Tate McRae", "So Close to What", "soclose.png", 450f, pop},
                {"Sabrina Carpenter", "Man’s Best Friend", "mansbf.png", 460f, pop},
                {"Charli xcx", "brat", "brat.png", 500f, pop},
                {"Lady Gaga", "The Fame", "thefame.png", 580f, pop}
            };

            // 4. Recorrer catálogo creando combinaciones VARIADAS
            for (int i = 0; i < catalogo.length; i++) {
                Object[] item = catalogo[i];

                String nombreArtista = (String) item[0];
                String nombreAlbum = (String) item[1];
                String imagen = (String) item[2];
                Float precioBase = (Float) item[3];
                Genero generoObj = (Genero) item[4];
                Artista artista = mapaArtistas.get(nombreArtista);
                if (artista == null) {
                    artista = new Artista();
                    artista.setNombreArtistico(nombreArtista);
                    artistaDAO.guardar(artista);
                    mapaArtistas.put(nombreArtista, artista);
                }

                Album album = new Album();
                album.setNombre(nombreAlbum);
                album.setDescripcion("Álbum " + nombreAlbum);
                album.setFechaLanzamiento(Calendar.getInstance());
                album.setImagenUrl(imagen);
                album.setArtista(artista);
                album.setCanciones(Arrays.asList("Canción A", "Canción B", "Canción C"));

                GeneroAlbum generoAlbum = new GeneroAlbum();
                generoAlbum.setAlbum(album);
                generoAlbum.setGenero(generoObj);

                if (album.getGeneros() == null) {
                    album.setGeneros(new ArrayList<>());
                }
                album.getGeneros().add(generoAlbum);

                albumDAO.crear(album, artista);

                List<String> formatosAInsertar = new ArrayList<>();
                int variedad = i % 5;

                switch (variedad) {
                    case 0:
                        formatosAInsertar.add("VINYL");
                        break;
                    case 1:
                        formatosAInsertar.add("VINYL");
                        formatosAInsertar.add("CD");
                        break;
                    case 2:
                        formatosAInsertar.add("VINYL");
                        formatosAInsertar.add("CD");
                        formatosAInsertar.add("CASSETTE");
                        break;
                    case 3:
                        formatosAInsertar.add("CD");
                        break;
                    case 4:
                        formatosAInsertar.add("VINYL");
                        formatosAInsertar.add("CASSETTE");
                        break;
                }

                for (String fmt : formatosAInsertar) {
                    NuevoProductoDTO prod = new NuevoProductoDTO();
                    prod.setStock(10);
                    prod.setEsDisponible(true);
                    prod.setAlbumId(album.getId());
                    prod.setFormato(fmt);

                    if (fmt.equals("VINYL")) {
                        prod.setPrecio(precioBase);
                        prod.setDescripcion("Vinilo de " + nombreAlbum);
                    } else if (fmt.equals("CD")) {
                        prod.setPrecio(Math.round(precioBase * 0.6f * 100.0f) / 100.0f);
                        prod.setDescripcion("CD de " + nombreAlbum);
                    } else if (fmt.equals("CASSETTE")) {
                        prod.setPrecio(Math.round(precioBase * 0.4f * 100.0f) / 100.0f);
                        prod.setDescripcion("Cassette de " + nombreAlbum);
                    }
                    productosDAO.crear(prod);
                }

                System.out.println("Insertado: " + nombreAlbum + " -> Formatos: " + formatosAInsertar);
            }

            System.out.println("--- Carga de base de datos finalizada con éxito ---");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
