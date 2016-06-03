package gt.umg.dto;

/**
 * Created by Steven Vargas, Dulce Cajas on 1/06/16.
 */
public class TipoEmpresaDTO {

    private int tipoEmpresaId;
    private String tipoEmpresa;

    public TipoEmpresaDTO() {
    }

    public TipoEmpresaDTO(int tipoEmpresaId, String tipoEmpresa) {
        this.tipoEmpresaId = tipoEmpresaId;
        this.tipoEmpresa = tipoEmpresa;
    }

    public int getTipoEmpresaId() {
        return tipoEmpresaId;
    }

    public void setTipoEmpresaId(int tipoEmpresaId) {
        this.tipoEmpresaId = tipoEmpresaId;
    }

    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }
}
