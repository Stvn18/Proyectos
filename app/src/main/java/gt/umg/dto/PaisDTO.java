package gt.umg.dto;

/**
 * Create by Steven Vargas, Dulce Cajas on 2/06/16.
 */
public class PaisDTO {

    private int idPais;
    private String nombre;

    public PaisDTO() {
    }

    public PaisDTO(int idPais, String nombre) {
        this.idPais = idPais;
        this.nombre = nombre;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
