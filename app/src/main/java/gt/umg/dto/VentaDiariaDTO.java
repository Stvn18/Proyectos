package gt.umg.dto;

/**
 * Created by Steven Vargas and Dulce Cajas on 3/06/16.
 */
public class VentaDiariaDTO {

    private String empresaNombre;
    private String servicioNombre;
    private int cantidadContratada;
    private int contratoCostoTotal;

    public VentaDiariaDTO() {
    }

    public VentaDiariaDTO(String empresaNombre, String servicioNombre, int cantidadContratada, int contratoCostoTotal) {
        this.empresaNombre = empresaNombre;
        this.servicioNombre = servicioNombre;
        this.cantidadContratada = cantidadContratada;
        this.contratoCostoTotal = contratoCostoTotal;
    }

    public String getEmpresaNombre() {
        return empresaNombre;
    }

    public void setEmpresaNombre(String empresaNombre) {
        this.empresaNombre = empresaNombre;
    }

    public String getServicioNombre() {
        return servicioNombre;
    }

    public void setServicioNombre(String servicioNombre) {
        this.servicioNombre = servicioNombre;
    }

    public int getCantidadContratada() {
        return cantidadContratada;
    }

    public void setCantidadContratada(int cantidadContratada) {
        this.cantidadContratada = cantidadContratada;
    }

    public int getContratoCostoTotal() {
        return contratoCostoTotal;
    }

    public void setContratoCostoTotal(int contratoCostoTotal) {
        this.contratoCostoTotal = contratoCostoTotal;
    }

}
