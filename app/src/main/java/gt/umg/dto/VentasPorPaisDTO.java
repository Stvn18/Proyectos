package gt.umg.dto;

/**
 * Created by wilver on 12/06/16.
 */
public class VentasPorPaisDTO {

    String pais;
    Integer vendido;

    public VentasPorPaisDTO() {
    }

    public VentasPorPaisDTO(String pais, Integer vendido) {
        this.pais = pais;
        this.vendido = vendido;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Integer getVendido() {
        return vendido;
    }

    public void setVendido(Integer vendido) {
        this.vendido = vendido;
    }
}
