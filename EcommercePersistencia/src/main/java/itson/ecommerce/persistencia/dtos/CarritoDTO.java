package itson.ecommerce.persistencia.dtos;
import java.util.ArrayList;
import java.util.List;

public class CarritoDTO {
    private Long id;
    private Float total;
    private List<DetalleCarritoDTO> detalles;

    public CarritoDTO() {
        this.detalles = new ArrayList<>();
        this.total = 0.0f;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Float getTotal() { return total; }
    public void setTotal(Float total) { this.total = total; }
    public List<DetalleCarritoDTO> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleCarritoDTO> detalles) { this.detalles = detalles; }
}