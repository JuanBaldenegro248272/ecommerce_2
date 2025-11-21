/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia;

import itson.ecommerce.persistencia.dtos.NuevoProductoDTO;
import itson.ecommerce.persistencia.entidades.Administrador;
import itson.ecommerce.persistencia.entidades.Album;
import itson.ecommerce.persistencia.entidades.Artista;
import itson.ecommerce.persistencia.entidades.Carrito;
import itson.ecommerce.persistencia.entidades.Cliente;
import itson.ecommerce.persistencia.entidades.Direccion;
import itson.ecommerce.persistencia.entidades.EstadoPedido;
import itson.ecommerce.persistencia.entidades.EstadoResena;
import itson.ecommerce.persistencia.entidades.Genero;
import itson.ecommerce.persistencia.entidades.Producto;
import itson.ecommerce.persistencia.implementaciones.AlbumDAO;
import itson.ecommerce.persistencia.implementaciones.ArtistaDAO;
import itson.ecommerce.persistencia.implementaciones.GenerosDAO;
import itson.ecommerce.persistencia.implementaciones.PedidoDAO;
import itson.ecommerce.persistencia.implementaciones.ProductosDAO;
import itson.ecommerce.persistencia.implementaciones.ResenasDAO;
import itson.ecommerce.persistencia.implementaciones.UsuarioDAO;
import itson.ecommerce.persistencia.interfaces.IAlbumDAO;
import itson.ecommerce.persistencia.interfaces.IArtistaDAO;
import itson.ecommerce.persistencia.interfaces.IGenerosDAO;
import itson.ecommerce.persistencia.interfaces.IPedidoDAO;
import itson.ecommerce.persistencia.interfaces.IProductosDAO;
import itson.ecommerce.persistencia.interfaces.IResenasDAO;
import itson.ecommerce.persistencia.interfaces.IUsuarioDAO;
import itson.ecommerce.persistencia.mapper.ProductoMapper;
import java.util.Arrays;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) {

        IUsuarioDAO usuarioDAO = new UsuarioDAO();
        IGenerosDAO generosDAO = new GenerosDAO();
        IArtistaDAO artistaDAO = new ArtistaDAO();
        IAlbumDAO albumDAO = new AlbumDAO();
        IProductosDAO productosDAO = new ProductosDAO();
        IResenasDAO resenasDAO = new ResenasDAO();
        IPedidoDAO pedidoDAO = new PedidoDAO();

        try {
            Administrador admin = new Administrador();
            admin.setNombre("Admin Seeder");
            admin.setCorreoElectronico("admin@store.com");
            admin.setContrasena("admin123");
            admin.setEsActiva(true);
            usuarioDAO.guardar(admin);
        } catch (Exception e) {
            System.out.println("El administrador ya existe o no se pudo crear.");
        }

        try {
            Genero rock = new Genero();
            rock.setNombre("Rock");
            generosDAO.crear(rock);

            Genero indie = new Genero();
            indie.setNombre("Indie");
            generosDAO.crear(indie);

            Genero pop = new Genero();
            pop.setNombre("Pop");
            generosDAO.crear(pop);

            Artista artista = new Artista();
            artista.setNombreArtistico("chalino sanchez");
            artistaDAO.guardar(artista);

            Album album = new Album();
            album.setNombre("Chalino enfierrado");
            album.setDescripcion("Alguito bien la bandita santa cruz");
            album.setFechaLanzamiento(Calendar.getInstance());
            album.setImagenUrl("https://is1-ssl.mzstatic.com/image/thumb/Music113/v4/f0/87/64/f087645c-9472-3369-8923-91c3d24eaf16/197187333509.jpg/1200x1200bf-60.jpg");
            album.setArtista(artista);
            album.setCanciones(Arrays.asList(
                    "El perron",
                    "El rafi",
                    "Dios ayudame ya me quiero dormir"
            ));

            albumDAO.crear(album, artista);

            NuevoProductoDTO p1 = new NuevoProductoDTO();
            p1.setStock(12);
            p1.setEsDisponible(true);
            p1.setPrecio(599f);
            p1.setFormato("VINYL");
            p1.setDescripcion("El disco mas perron");
            p1.setAlbumId(album.getId());

            productosDAO.crear(p1);

            Producto productoEntity = ProductoMapper.toEntity(p1, album);

            Cliente chalino = new Cliente();
            chalino.setNombre("Chalino Sanchez");
            chalino.setCorreoElectronico("Chalino@simon.com");
            chalino.setContrasena("fake123");
            chalino.setEsActiva(true);
            chalino.setTelefono("1234567890");

            Direccion direccion = new Direccion();
            direccion.setCalle("Calle de las Nieves");
            direccion.setCiudad("Obregon");
            direccion.setCodigoPostal(85000);
            direccion.setColonia("Centro");
            direccion.setEstado("Sonora");
            chalino.setDireccion(direccion);
            
            Carrito carrito = new Carrito();
            chalino.setCarrito(carrito);

            usuarioDAO.guardar(chalino);

            pedidoDAO.crearPedido(chalino, 0, EstadoPedido.ENVIADO);

            resenasDAO.crearResena(chalino, productoEntity, 5, "Hola", EstadoResena.PENDIENTE);

            System.out.println("Datos insertados correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
