package itson.ecommerce.persistencia.dtos;

import java.util.ArrayList;
import java.util.List;

public class CarritoDTO {

    private Long id;
    private Float total;
    private List<DetalleCarritoDTO> detalles;
    private int cantidadItems;

    public CarritoDTO() {
        this.detalles = new ArrayList<>();
        this.total = 0.0f;
        this.cantidadItems = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public List<DetalleCarritoDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleCarritoDTO> detalles) {
        this.detalles = detalles;
    }

    public int getCantidadItems() {
        return cantidadItems;
    }

    public void setCantidadItems(int cantidadItems) {
        this.cantidadItems = cantidadItems;
    }

}
