package itson.ecommerce.persistencia.interfaces;

import itson.ecommerce.persistencia.dtos.*;
import itson.ecommerce.persistencia.entidades.*;
import itson.ecommerce.persistencia.exceptions.PersistenciaException;
import java.util.List;

public interface IPersistencia {

    // --- MÉTODOS DEL CARRITO (LOS QUE TE FALTABAN) ---
    public Carrito crearCarrito(Carrito c) throws PersistenciaException;
    public Carrito buscarCarritoPorId(Long id) throws PersistenciaException;
    public Producto buscarProductoPorIdEntity(Long id) throws PersistenciaException;
    public void agregarDetalleCarrito(DetalleCarrito d) throws PersistenciaException;
    public void actualizarDetalleCarrito(DetalleCarrito d) throws PersistenciaException; // <--- AQUÍ ESTÁ EL QUE FALLABA
    public void eliminarDetalleCarrito(Long idDetalle) throws PersistenciaException;
    public void actualizarCarrito(Carrito c) throws PersistenciaException;

    // --- PRODUCTOS ---
    public NuevoProductoDTO crearProducto(NuevoProductoDTO dto) throws PersistenciaException;
    public List<ProductoListaDTO> obtenerTodosProductos() throws PersistenciaException;
    public List<ProductoListaDTO> buscarProductos(String termino) throws PersistenciaException;
    public void eliminarProducto(Long id) throws PersistenciaException;
    public EditarProductoDTO obtenerProductoPorId(Long id) throws PersistenciaException;
    public void actualizarProducto(EditarProductoDTO dto) throws PersistenciaException;
    public ProductoListaDTO devolverProducto(Long id) throws PersistenciaException;

    // --- RESEÑAS ---
    public List<ResenaListaDTO> obtenerTodasResenas() throws PersistenciaException;
    public List<ResenaListaDTO> buscarResenas(String termino, String estado) throws PersistenciaException;
    public void aprobarResena(Long id) throws PersistenciaException;
    public void eliminarResena(Long id) throws PersistenciaException;
    public void crearResena(NuevaResenaDTO nuevaResena) throws PersistenciaException;
    
    // --- USUARIOS ---
    public Usuario buscarPorCorreo(String correo) throws PersistenciaException;
    public Usuario guardar(Usuario usuario) throws PersistenciaException;
    public Usuario buscarPorId(Long id) throws PersistenciaException ;
    public ClienteDTO actualizarCliente(ClienteDTO clienteDTO) throws PersistenciaException;
    public ClienteDTO obtenerClientePorId(Long id) throws PersistenciaException;
    public ClienteDTO obtenerClientePorCorreo(String correo) throws PersistenciaException;

    // --- PEDIDOS ---
    public List<PedidoDTO> obtenerTodosPedidos() throws PersistenciaException;
    public PedidoDTO actualizarEstadoPedido(Long idPedido, String nuevoEstado) throws PersistenciaException;
    CarritoDTO obtenerCarritoDTO(Long idCarrito) throws PersistenciaException;
    public PedidoDTO crearPedido(PedidoDTO pedidoDTO, String correo) throws PersistenciaException;
    public List<PedidoDTO> obtenerPedidosUsuario(String correo) throws PersistenciaException;

    // --- ALBUMES Y ARTISTAS ---
    List<Artista> consultarArtistasTodos() throws PersistenciaException;
    List<AlbumDTO> buscarAlbumes(String termino) throws PersistenciaException;
    List<AlbumDTO> obtenerTodosAlbumes() throws PersistenciaException;
    AlbumDTO consultarAlbum(Long id) throws PersistenciaException;
    List<ArtistaSimpleDTO> obtenerTodosArtistas() throws PersistenciaException;
    void crearAlbum(NuevoAlbumDTO dto) throws PersistenciaException;
    AlbumDTO actualizarAlbum(AlbumDTO dto) throws PersistenciaException;
    boolean eliminarAlbum(Long id) throws PersistenciaException;

    // --- GÉNEROS ---
    public List<GeneroDTO> obtenerTodosGeneros() throws PersistenciaException;
    public void crearGenero(String nombre) throws PersistenciaException;
    public void actualizarGenero(Long id, String nombre) throws PersistenciaException;
    public void eliminarGenero(Long id) throws PersistenciaException;
}