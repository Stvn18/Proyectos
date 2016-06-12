package gt.umg.dto;

/**
 * Created by Steven Vargas and Dulce Cajas on 3/06/16.
 */
public class ServiciosDTO {

    private int idServicio;
    private String servicio;

    public ServiciosDTO() {
    }

    public ServiciosDTO(int idServicio, String servicio) {
        this.idServicio = idServicio;
        this.servicio = servicio;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

}
