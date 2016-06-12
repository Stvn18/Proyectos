package gt.umg.dto;

/**
 * Created by Steven Vargas and Dulce Cajas on 3/06/16.
 */
public class SimpleSpinnerAdapterDTO {

    private int id;
    private String descripcion;

    public SimpleSpinnerAdapterDTO() {
    }

    public SimpleSpinnerAdapterDTO(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
