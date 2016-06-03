package gt.umg.dto;

/**
 * Created by wilver on 2/06/16.
 */
public class MenuDTO {

    private int id;
    private int icono;
    private String descripcion;

    public MenuDTO() {
    }

    public MenuDTO(int id, int icono, String descripcion) {
        this.id = id;
        this.icono = icono;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
