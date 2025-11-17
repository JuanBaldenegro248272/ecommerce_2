package itson.ecommerce.persistencia.dtos;

public class EditarProductoDTO {
    private Long id;
    private Long albumId;
    private String albumNombre;
    private String albumImagenUrl;
    private String artistaNombre;
    private String formato;
    private Float precio;
    private Integer stock;
    private Boolean esDisponible;
    private String descripcion;

    public EditarProductoDTO() {
    }

    public EditarProductoDTO(Long id, Long albumId, String albumNombre, String albumImagenUrl, 
                            String artistaNombre, String formato, Float precio, Integer stock, 
                            Boolean esDisponible, String descripcion) {
        this.id = id;
        this.albumId = albumId;
        this.albumNombre = albumNombre;
        this.albumImagenUrl = albumImagenUrl;
        this.artistaNombre = artistaNombre;
        this.formato = formato;
        this.precio = precio;
        this.stock = stock;
        this.esDisponible = esDisponible;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getAlbumNombre() {
        return albumNombre;
    }

    public void setAlbumNombre(String albumNombre) {
        this.albumNombre = albumNombre;
    }

    public String getAlbumImagenUrl() {
        return albumImagenUrl;
    }

    public void setAlbumImagenUrl(String albumImagenUrl) {
        this.albumImagenUrl = albumImagenUrl;
    }

    public String getArtistaNombre() {
        return artistaNombre;
    }

    public void setArtistaNombre(String artistaNombre) {
        this.artistaNombre = artistaNombre;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Boolean getEsDisponible() {
        return esDisponible;
    }

    public void setEsDisponible(Boolean esDisponible) {
        this.esDisponible = esDisponible;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}