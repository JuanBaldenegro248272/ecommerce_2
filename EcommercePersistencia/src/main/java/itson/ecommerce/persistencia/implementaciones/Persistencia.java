/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.ecommerce.persistencia.implementaciones;

import itson.ecommerce.persistencia.dtos.AlbumDTO;
import itson.ecommerce.persistencia.dtos.ArtistaSimpleDTO;
import itson.ecommerce.persistencia.dtos.EditarProductoDTO;
import itson.ecommerce.persistencia.dtos.GeneroDTO;
import itson.ecommerce.persistencia.dtos.NuevoAlbumDTO;
import itson.ecommerce.persistencia.dtos.NuevoProductoDTO;
import itson.ecommerce.persistencia.dtos.PedidoDTO;
import itson.ecommerce.persistencia.dtos.ProductoListaDTO;
import itson.ecommerce.persistencia.dtos.ResenaListaDTO;
import itson.ecommerce.persistencia.entidades.Album;
import itson.ecommerce.persistencia.entidades.Artista;
import itson.ecommerce.persistencia.entidades.EstadoResena;
import itson.ecommerce.persistencia.entidades.Genero;
import itson.ecommerce.persistencia.entidades.GeneroAlbum;
import itson.ecommerce.persistencia.entidades.Pedido;
import itson.ecommerce.persistencia.entidades.Producto;
import itson.ecommerce.persistencia.entidades.Resena;
import itson.ecommerce.persistencia.entidades.Usuario;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;
import itson.ecommerce.persistencia.interfaces.IAlbumDAO;
import itson.ecommerce.persistencia.interfaces.IArtistaDAO;
import itson.ecommerce.persistencia.interfaces.IGenerosDAO;
import itson.ecommerce.persistencia.interfaces.IPedidoDAO;
import itson.ecommerce.persistencia.interfaces.IPersistencia;
import itson.ecommerce.persistencia.interfaces.IProductosDAO;
import itson.ecommerce.persistencia.interfaces.IResenasDAO;
import itson.ecommerce.persistencia.mapper.AlbumMapper;
import itson.ecommerce.persistencia.interfaces.IUsuarioDAO;
import itson.ecommerce.persistencia.mapper.GeneroMapper;
import itson.ecommerce.persistencia.mapper.PedidoMapper;
import itson.ecommerce.persistencia.mapper.ProductoMapper;
import itson.ecommerce.persistencia.mapper.ResenaMapper;
import itson.ecommerce.persistencia.utils.ManejadorConexiones;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;

/**
 *
 * @author Dana Chavez
 */
public class Persistencia implements IPersistencia {

    private IProductosDAO productosDAO;
    private IUsuarioDAO usuarioDAO;
    private IResenasDAO resenasDAO;
    private IPedidoDAO pedidosDAO;
    private IAlbumDAO albumDAO;
    private IArtistaDAO artistaDAO;
    private IGenerosDAO generosDAO;

    public Persistencia() {
        this.productosDAO = new ProductosDAO();
        this.pedidosDAO = new PedidoDAO();
        this.resenasDAO = new ResenasDAO();
        this.albumDAO = new AlbumDAO();
        this.artistaDAO = new ArtistaDAO();
        this.usuarioDAO = new UsuarioDAO();
        this.generosDAO = new GenerosDAO();
    }

    @Override
    public NuevoProductoDTO crearProducto(NuevoProductoDTO dto) throws PersistenciaException {
        return productosDAO.crear(dto);
    }

    @Override
    public List<ProductoListaDTO> obtenerTodosProductos() throws PersistenciaException {
        try {
            List<Producto> productos = productosDAO.obtenerTodos();
            return productos.stream()
                    .map(ProductoMapper::toListaDTO)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new PersistenciaException("Error al obtener productos", ex);
        }
    }

    @Override
    public List<ProductoListaDTO> buscarProductos(String termino) throws PersistenciaException {
        try {
            List<Producto> productos = productosDAO.buscarPorNombre(termino);
            return productos.stream()
                    .map(ProductoMapper::toListaDTO)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar productos", ex);
        }
    }

    @Override
    public void eliminarProducto(Long id) throws PersistenciaException {
        try {
            productosDAO.eliminar(id);
        } catch (Exception ex) {
            System.out.println("ERROR en Persistencia.eliminarProducto: " + ex.getMessage());
            throw new PersistenciaException("Error al eliminar producto", ex);
        }
    }

    @Override
    public List<ResenaListaDTO> obtenerTodasResenas() throws PersistenciaException {
        try {
            List<Resena> resenas = resenasDAO.obtenerTodas();
            List<ResenaListaDTO> dtos = resenas.stream()
                    .map(ResenaMapper::toListaDTO)
                    .collect(Collectors.toList());
            return dtos;
        } catch (Exception ex) {
            System.out.println("ERROR en Persistencia: " + ex.getMessage());
            throw new PersistenciaException("Error al obtener reseñas", ex);
        }
    }

    @Override
    public List<ResenaListaDTO> buscarResenas(String termino, String estadoStr) throws PersistenciaException {
        try {
            EstadoResena estado = null;
            if (estadoStr != null && !estadoStr.trim().isEmpty()) {
                try {
                    estado = EstadoResena.valueOf(estadoStr);
                } catch (IllegalArgumentException e) {

                }
            }
            List<Resena> resenas = resenasDAO.buscarPorFiltros(termino, estado);
            List<ResenaListaDTO> dtos = resenas.stream()
                    .map(ResenaMapper::toListaDTO)
                    .collect(Collectors.toList());
            return dtos;
        } catch (Exception ex) {
            System.out.println("ERROR en Persistencia: " + ex.getMessage());
            throw new PersistenciaException("Error al buscar reseñas", ex);
        }
    }

    @Override
    public void aprobarResena(Long id) throws PersistenciaException {
        try {
            Resena resena = resenasDAO.obtenerPorId(id);
            resena.setEstado(EstadoResena.APROBADA);
            resenasDAO.actualizar(resena);

            System.out.println("Reseña aprobada en persistencia");
        } catch (Exception ex) {
            System.out.println("ERROR en Persistencia: " + ex.getMessage());
            throw new PersistenciaException("Error al aprobar reseña", ex);
        }
    }

    @Override
    public void eliminarResena(Long id) throws PersistenciaException {
        try {
            resenasDAO.eliminar(id);
        } catch (Exception ex) {
            System.out.println("ERROR en Persistencia: " + ex.getMessage());
            throw new PersistenciaException("Error al eliminar reseña", ex);
        }
    }

    @Override
    public EditarProductoDTO obtenerProductoPorId(Long id) throws PersistenciaException {
        try {

            Producto producto = productosDAO.obtenerPorId(id);
            EditarProductoDTO dto = ProductoMapper.toEditarDTO(producto);

            return dto;
        } catch (Exception ex) {
            System.out.println("ERROR en Persistencia.obtenerProductoPorId: " + ex.getMessage());
            throw new PersistenciaException("Error al obtener producto", ex);
        }
    }

    @Override
    public Usuario buscarPorCorreo(String correo) throws PersistenciaException {
        try {

            return this.usuarioDAO.buscarPorCorreo(correo);

        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar por correo: " + e.getMessage());
        }
    }

    public Usuario guardar(Usuario usuario) throws PersistenciaException {
        try {

            return this.usuarioDAO.guardar(usuario);

        } catch (Exception e) {
            throw new PersistenciaException("Error al guardar usuario: " + e.getMessage());

        }
    }

    @Override
    public void actualizarProducto(EditarProductoDTO dto) throws PersistenciaException {
        try {
            Producto producto = productosDAO.obtenerPorId(dto.getId());

            ProductoMapper.updateEntity(producto, dto);

            productosDAO.actualizar(producto);

            System.out.println("Producto actualizado en persistencia");
        } catch (Exception ex) {
            System.out.println("ERROR en Persistencia.actualizarProducto: " + ex.getMessage());
            throw new PersistenciaException("Error al actualizar producto", ex);
        }
    }

    @Override
    public List<PedidoDTO> obtenerTodosPedidos() throws PersistenciaException {

        try {
            List<Pedido> pedidos = pedidosDAO.obtenerTodos();
            return pedidos.stream().map(PedidoMapper::toDTO).collect(Collectors.toList());
        } catch (Exception ex) {
            throw new PersistenciaException("Error al obtener pedidos ", ex);
        }

    }

    @Override
    public PedidoDTO actualizarEstadoPedido(Long idPedido, String nuevoEstado) throws PersistenciaException {
        try {
            Pedido pedidoActualizado = pedidosDAO.actualizarEstado(idPedido, nuevoEstado);
            return PedidoMapper.toDTO(pedidoActualizado);
        } catch (Exception e) {
            throw new PersistenciaException("Error al actualizar el pedido", e);
        }
    }

    @Override
    public List<Artista> consultarArtistasTodos() throws PersistenciaException {
        try {
            return this.artistaDAO.obtenerTodos();
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar artistas", e);
        }
    }

    @Override
    public List<AlbumDTO> obtenerTodosAlbumes() throws PersistenciaException {
        try {
            List<Album> albumes = albumDAO.consultarTodos();
            return albumes.stream().map(AlbumMapper::toDTO).toList();
        } catch (Exception e) {
            throw new PersistenciaException("No se pudieron obtener los albumes", e);
        }
    }

    @Override
    public List<AlbumDTO> buscarAlbumes(String termino) throws PersistenciaException {
        try {

            List<Album> albumEntidad = albumDAO.buscar(termino);
            List<AlbumDTO> albumDTO = new ArrayList<>();
            for (Album album : albumEntidad) {
                AlbumDTO dto = AlbumMapper.toDTO(album);
                albumDTO.add(dto);
            }
            return albumDTO;
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar álbumes", e);
        }
    }

    @Override
    public AlbumDTO consultarAlbum(Long id) throws PersistenciaException {
        try {
            Album album = albumDAO.consultar(id);
            return AlbumMapper.toDTO(album);
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar álbum", e);
        }
    }

    @Override
    public AlbumDTO actualizarAlbum(AlbumDTO dto) throws PersistenciaException {
        try {
            Album album = AlbumMapper.toEntity(dto);

            Album actualizado = albumDAO.actualizar(album);
            return AlbumMapper.toDTO(actualizado);
        } catch (Exception e) {
            throw new PersistenciaException("Error al actualizar álbum", e);
        }
    }

    @Override
    public boolean eliminarAlbum(Long id) throws PersistenciaException {
        try {
            return this.albumDAO.eliminar(id);
        } catch (Exception e) {
            throw new PersistenciaException("Error al eliminar álbum", e);
        }
    }

    @Override
    public List<GeneroDTO> obtenerTodosGeneros() throws PersistenciaException {
        try {

            List<Genero> generos = generosDAO.obtenerTodos();
            List<GeneroDTO> dtos = generos.stream()
                    .map(GeneroMapper::toDTO)
                    .collect(Collectors.toList());

            return dtos;
        } catch (Exception ex) {
            System.out.println("ERROR en Persistencia: " + ex.getMessage());
            throw new PersistenciaException("Error al obtener géneros", ex);
        }
    }

    @Override
    public void crearGenero(String nombre) throws PersistenciaException {
        try {

            Genero genero = new Genero();
            genero.setNombre(nombre);

            generosDAO.crear(genero);

        } catch (Exception ex) {
            System.out.println("ERROR en Persistencia: " + ex.getMessage());
            throw new PersistenciaException("Error al crear género", ex);
        }
    }

    @Override
    public void actualizarGenero(Long id, String nombre) throws PersistenciaException {
        try {
            Genero genero = generosDAO.obtenerPorId(id);
            genero.setNombre(nombre);

            generosDAO.actualizar(genero);

        } catch (Exception ex) {
            System.out.println("ERROR en Persistencia: " + ex.getMessage());
            throw new PersistenciaException("Error al actualizar género", ex);
        }
    }

    @Override
    public void eliminarGenero(Long id) throws PersistenciaException {
        try {
            generosDAO.eliminar(id);
        } catch (Exception ex) {
            System.out.println("ERROR en Persistencia: " + ex.getMessage());
            throw new PersistenciaException("Error al eliminar género", ex);
        }
    }

    @Override
    public List<ArtistaSimpleDTO> obtenerTodosArtistas() throws PersistenciaException {
        try {
            List<Artista> artistas = artistaDAO.obtenerTodos();
            List<ArtistaSimpleDTO> dtos = artistas.stream()
                    .map(a -> new ArtistaSimpleDTO(a.getId(), a.getNombreArtistico()))
                    .collect(Collectors.toList());

            return dtos;
        } catch (Exception ex) {
            throw new PersistenciaException("Error al obtener artistas", ex);
        }
    }

    @Override
    public void crearAlbum(NuevoAlbumDTO dto) throws PersistenciaException {
        try {
            Artista artista = artistaDAO.obtenerPorId(dto.getIdArtista());
            if (artista == null) {
                throw new IllegalArgumentException("Artista no encontrado");
            }

            Album album = new Album();
            album.setNombre(dto.getNombre());
            album.setDescripcion(dto.getDescripcion());
            album.setFechaLanzamiento(dto.getFechaLanzamiento());
            album.setImagenUrl(dto.getImagenUrl());
            album.setArtista(artista);
            album.setCanciones(dto.getCanciones());

            albumDAO.crear(album, artista);

            if (dto.getIdsGeneros() != null && !dto.getIdsGeneros().isEmpty()) {
                EntityManager em = null;
                try {
                    em = ManejadorConexiones.getEntityManager();
                    em.getTransaction().begin();

                    for (Long idGenero : dto.getIdsGeneros()) {
                        Genero genero = em.find(Genero.class, idGenero);
                        if (genero != null) {
                            GeneroAlbum generoAlbum = new GeneroAlbum();
                            generoAlbum.setGenero(genero);
                            generoAlbum.setAlbum(album);
                            em.persist(generoAlbum);
                        }
                    }

                    em.getTransaction().commit();
                } catch (Exception ex) {
                    if (em != null && em.getTransaction().isActive()) {
                        em.getTransaction().rollback();
                    }
                    throw ex;
                } finally {
                    if (em != null && em.isOpen()) {
                        em.close();
                    }
                }
            }

        } catch (Exception ex) {
            System.out.println("ERROR en Persistencia: " + ex.getMessage());
            throw new PersistenciaException("Error al crear álbum", ex);
        }
    }
}
