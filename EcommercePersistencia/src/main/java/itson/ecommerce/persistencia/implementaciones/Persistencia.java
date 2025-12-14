package itson.ecommerce.persistencia.implementaciones;

import itson.ecommerce.persistencia.dtos.*;
import itson.ecommerce.persistencia.entidades.*;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;
import itson.ecommerce.persistencia.interfaces.*;
import itson.ecommerce.persistencia.mapper.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Persistencia implements IPersistencia {

    private IProductosDAO productosDAO;
    private IUsuarioDAO usuarioDAO;
    private IResenasDAO resenasDAO;
    private IPedidoDAO pedidosDAO;
    private IAlbumDAO albumDAO;
    private IArtistaDAO artistaDAO;
    private IGenerosDAO generosDAO;
    private ICarritosDAO carritosDAO;
    private IPagoDAO pagoDAO;
    private IDireccionDAO direccionDAO;

    public Persistencia() {
        this.productosDAO = new ProductosDAO();
        this.pedidosDAO = new PedidoDAO();
        this.resenasDAO = new ResenasDAO();
        this.albumDAO = new AlbumDAO();
        this.artistaDAO = new ArtistaDAO();
        this.usuarioDAO = new UsuarioDAO();
        this.generosDAO = new GenerosDAO();
        this.carritosDAO = new CarritosDAO();
    }

    // --- MÉTODOS DEL CARRITO ---
    @Override
    public Carrito crearCarrito(Carrito c) throws PersistenciaException {
        return carritosDAO.crear(c);
    }

    @Override
    public Carrito buscarCarritoPorId(Long id) throws PersistenciaException {
        return carritosDAO.buscarPorId(id);
    }

    @Override
    public void agregarDetalleCarrito(DetalleCarrito d) throws PersistenciaException {
        carritosDAO.guardarDetalle(d);
    }

    @Override
    public void actualizarDetalleCarrito(DetalleCarrito d) throws PersistenciaException {
        carritosDAO.actualizarDetalle(d);
    }

    @Override
    public void eliminarDetalleCarrito(Long id) throws PersistenciaException {
        carritosDAO.eliminarDetalle(id);
    }

    @Override
    public void actualizarCarrito(Carrito c) throws PersistenciaException {
        carritosDAO.actualizar(c);
    }

    @Override
    public Producto buscarProductoPorIdEntity(Long id) throws PersistenciaException {
        // Necesitamos la entidad directa para relacionarla con el carrito
        try {
            return productosDAO.obtenerPorId(id);
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener entidad producto", e);
        }
    }

    // --- MÉTODOS DE PRODUCTOS, ÁLBUMES, USUARIOS, ETC. ---
    @Override
    public NuevoProductoDTO crearProducto(NuevoProductoDTO dto) throws PersistenciaException {
        return productosDAO.crear(dto);
    }

    @Override
    public List<ProductoListaDTO> obtenerTodosProductos() throws PersistenciaException {
        try {
            return productosDAO.obtenerTodos().stream()
                    .map(ProductoMapper::toListaDTO)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new PersistenciaException("Error al obtener productos", ex);
        }
    }

    @Override
    public List<ProductoListaDTO> buscarProductos(String termino) throws PersistenciaException {
        try {
            return productosDAO.buscarPorNombre(termino).stream()
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
            throw new PersistenciaException("Error al eliminar producto", ex);
        }
    }

    @Override
    public List<ResenaListaDTO> obtenerTodasResenas() throws PersistenciaException {
        try {
            return resenasDAO.obtenerTodas().stream()
                    .map(ResenaMapper::toListaDTO)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
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
                } catch (Exception e) {
                    // Si el estado no es válido, se ignora
                }
            }
            return resenasDAO.buscarPorFiltros(termino, estado).stream()
                    .map(ResenaMapper::toListaDTO)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar reseñas", ex);
        }
    }

    @Override
    public void aprobarResena(Long id) throws PersistenciaException {
        try {
            Resena r = resenasDAO.obtenerPorId(id);
            r.setEstado(EstadoResena.APROBADA);
            resenasDAO.actualizar(r);
        } catch (Exception ex) {
            throw new PersistenciaException("Error al aprobar reseña", ex);
        }
    }

    @Override
    public void eliminarResena(Long id) throws PersistenciaException {
        try {
            resenasDAO.eliminar(id);
        } catch (Exception ex) {
            throw new PersistenciaException("Error al eliminar reseña", ex);
        }
    }

    @Override
    public EditarProductoDTO obtenerProductoPorId(Long id) throws PersistenciaException {
        try {
            return ProductoMapper.toEditarDTO(productosDAO.obtenerPorId(id));
        } catch (Exception ex) {
            throw new PersistenciaException("Error al obtener producto", ex);
        }
    }

    @Override
    public Usuario buscarPorCorreo(String correo) throws PersistenciaException {
        return usuarioDAO.buscarPorCorreo(correo);
    }

    @Override
    public Usuario guardar(Usuario usuario) throws PersistenciaException {
        return usuarioDAO.guardar(usuario);
    }

    @Override
    public void actualizarProducto(EditarProductoDTO dto) throws PersistenciaException {
        try {
            Producto p = productosDAO.obtenerPorId(dto.getId());
            ProductoMapper.updateEntity(p, dto);
            productosDAO.actualizar(p);
        } catch (Exception ex) {
            throw new PersistenciaException("Error al actualizar producto", ex);
        }
    }

    @Override
    public List<PedidoDTO> obtenerTodosPedidos() throws PersistenciaException {
        try {
            return pedidosDAO.obtenerTodos().stream()
                    .map(PedidoMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new PersistenciaException("Error al obtener pedidos", ex);
        }
    }

    @Override
    public PedidoDTO actualizarEstadoPedido(Long idPedido, String nuevoEstado) throws PersistenciaException {
        try {
            Pedido p = pedidosDAO.actualizarEstado(idPedido, nuevoEstado);
            return PedidoMapper.toDTO(p);
        } catch (Exception ex) {
            throw new PersistenciaException("Error al actualizar estado pedido", ex);
        }
    }

    @Override
    public List<Artista> consultarArtistasTodos() throws PersistenciaException {
        try {
            return artistaDAO.obtenerTodos();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar artistas", ex);
        }
    }

    @Override
    public List<AlbumDTO> obtenerTodosAlbumes() throws PersistenciaException {
        try {
            return albumDAO.consultarTodos().stream()
                    .map(AlbumMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new PersistenciaException("Error al obtener álbumes", ex);
        }
    }

    @Override
    public List<AlbumDTO> buscarAlbumes(String termino) throws PersistenciaException {
        try {
            List<AlbumDTO> list = new ArrayList<>();
            for (Album a : albumDAO.buscar(termino)) {
                list.add(AlbumMapper.toDTO(a));
            }
            return list;
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar álbumes", ex);
        }
    }

    @Override
    public AlbumDTO consultarAlbum(Long id) throws PersistenciaException {
        try {
            return AlbumMapper.toDTO(albumDAO.consultar(id));
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar álbum", ex);
        }
    }

    @Override
    public AlbumDTO actualizarAlbum(AlbumDTO dto) throws PersistenciaException {
        try {
            Album a = AlbumMapper.toEntity(dto);
            return AlbumMapper.toDTO(albumDAO.actualizar(a));
        } catch (Exception ex) {
            throw new PersistenciaException("Error al actualizar álbum", ex);
        }
    }

    @Override
    public boolean eliminarAlbum(Long id) throws PersistenciaException {
        try {
            return albumDAO.eliminar(id);
        } catch (Exception ex) {
            throw new PersistenciaException("Error al eliminar álbum", ex);
        }
    }

    @Override
    public List<GeneroDTO> obtenerTodosGeneros() throws PersistenciaException {
        try {
            return generosDAO.obtenerTodos().stream()
                    .map(GeneroMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new PersistenciaException("Error al obtener géneros", ex);
        }
    }

    @Override
    public void crearGenero(String nombre) throws PersistenciaException {
        try {
            Genero g = new Genero();
            g.setNombre(nombre);
            generosDAO.crear(g);
        } catch (Exception ex) {
            throw new PersistenciaException("Error al crear género", ex);
        }
    }

    @Override
    public void actualizarGenero(Long id, String nombre) throws PersistenciaException {
        try {
            Genero g = generosDAO.obtenerPorId(id);
            g.setNombre(nombre);
            generosDAO.actualizar(g);
        } catch (Exception ex) {
            throw new PersistenciaException("Error al actualizar género", ex);
        }
    }

    @Override
    public void eliminarGenero(Long id) throws PersistenciaException {
        try {
            generosDAO.eliminar(id);
        } catch (Exception ex) {
            throw new PersistenciaException("Error al eliminar género", ex);
        }
    }

    @Override
    public List<ArtistaSimpleDTO> obtenerTodosArtistas() throws PersistenciaException {
        try {
            return artistaDAO.obtenerTodos().stream()
                    .map(a -> new ArtistaSimpleDTO(a.getId(), a.getNombreArtistico()))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new PersistenciaException("Error al obtener artistas simples", ex);
        }
    }

    @Override
    public void crearAlbum(NuevoAlbumDTO dto) throws PersistenciaException {
        try {
            Artista a = artistaDAO.obtenerPorId(dto.getIdArtista());
            Album al = new Album();
            al.setNombre(dto.getNombre());
            al.setDescripcion(dto.getDescripcion());
            al.setFechaLanzamiento(dto.getFechaLanzamiento());
            al.setImagenUrl(dto.getImagenUrl());
            al.setArtista(a);
            al.setCanciones(dto.getCanciones());
            albumDAO.crear(al, a);
        } catch (Exception ex) {
            throw new PersistenciaException("Error al crear álbum", ex);
        }
    }

    @Override
    public Usuario buscarPorId(Long id) throws PersistenciaException {
        try {
            return usuarioDAO.buscarPorId(id);
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar usuario.");
        }
    }

    public ClienteDTO obtenerClienteDTO(String correo) throws PersistenciaException {
        try {
            if (correo == null || correo.isBlank()) {
                return null;
            }
            Usuario cliente = usuarioDAO.buscarPorCorreo(correo);
            if (cliente == null) {
                return null;
            }
            ClienteDTO dto = new ClienteDTO();
            dto.setId(cliente.getId());
            dto.setNombre(cliente.getNombre());
            dto.setCorreoElectronico(cliente.getCorreoElectronico());
            return dto;
        } catch (PersistenciaException ex) {
            throw new PersistenciaException("Error al obtener Cliente por correo.", ex);
        }
    }

    @Override
    public Usuario actualizar(Usuario usuario) throws PersistenciaException {
        try {
            return usuarioDAO.actualizar(usuario);
        } catch (Exception e) {
            throw new PersistenciaException("Error al actualizar el usuario.");
        }
    }

    public CarritoDTO obtenerCarritoDTO(Long idCarrito) throws PersistenciaException {
        try {
            Carrito carrito = carritosDAO.buscarPorId(idCarrito);
            if (carrito == null) {
                return null;
            }
            int items = carritosDAO.contarDetalles(idCarrito);
            CarritoDTO dto = new CarritoDTO();
            dto.setId(carrito.getId());
            dto.setTotal(carrito.getTotal());
            dto.setCantidadItems(items);
            return dto;
        } catch (PersistenciaException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PersistenciaException("Error al mapear CarritoDTO.", ex);
        }
    }

    @Override
    public PedidoDTO crearPedido(PedidoDTO pedidoDTO, String correo) throws PersistenciaException {
        try {

            Carrito carrito = carritosDAO.buscarPorId(pedidoDTO.getIdCarrito());
            if (carrito == null) {
                throw new PersistenciaException("Carrito no encontrado.");
            }
            Usuario usuario = usuarioDAO.buscarPorCorreo(correo);
            if (usuario == null) {
                throw new PersistenciaException("Usuario/Cliente no encontrado.");
            }
            Direccion direccion = direccionDAO.buscarPorId(pedidoDTO.getIdDireccion());
            if (direccion == null) {
                throw new PersistenciaException("Dirección no encontrada.");
            }
            Pago pago = pagoDAO.buscarPorId(pedidoDTO.getIdPago());
            if (pago == null) {
                throw new PersistenciaException("Pago no encontrado.");
            }
            if (!(usuario instanceof Cliente)) {
                throw new PersistenciaException("El usuario encontrado no es Cliente.");
            }

            Pedido pedidoEntity = new Pedido();
            pedidoEntity.setCliente((Cliente) usuario);
            pedidoEntity.setDireccion(direccion);
            pedidoEntity.setPago(pago);
            pedidoEntity.setTotal(carrito.getTotal());
            pedidoEntity.setFechaCompra(java.util.Calendar.getInstance());
            pedidoEntity.setEstado(EstadoPedido.PENDIENTE);

            Pedido guardado = pedidosDAO.crearPedido(pedidoEntity, carrito);
            return PedidoMapper.toDTO(guardado);

        } catch (PersistenciaException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PersistenciaException("Error inesperado al crear pedido.", ex);
        }
    }
}
