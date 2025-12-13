package itson.ecommerce.persistencia.dtos;

public class DetalleCarritoDTO {
    private Long id;
    private Long idProducto;
    private String nombre;
    private String artista;
    private String imagenUrl;
    private Float precio;
    private Integer cantidad;
    private Float subtotal;

    public DetalleCarritoDTO() {}

    public DetalleCarritoDTO(Long id, Long idProducto, String nombre, String artista, String imagenUrl, Float precio, Integer cantidad) {
        this.id = id;
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.artista = artista;
        this.imagenUrl = imagenUrl;
        this.precio = precio;
        this.cantidad = cantidad;
        this.subtotal = precio * cantidad;
    }
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdProducto() { return idProducto; }
    public void setIdProducto(Long idProducto) { this.idProducto = idProducto; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getArtista() { return artista; }
    public void setArtista(String artista) { this.artista = artista; }
    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }
    public Float getPrecio() { return precio; }
    public void setPrecio(Float precio) { this.precio = precio; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public Float getSubtotal() { return subtotal; }
    public void setSubtotal(Float subtotal) { this.subtotal = subtotal; }
}