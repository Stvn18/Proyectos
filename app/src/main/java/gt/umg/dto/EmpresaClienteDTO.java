package gt.umg.dto;

/**
 * Created by Steven Vargas and Dulce Cajas  on 3/06/16.
 */
public class EmpresaClienteDTO {

    /*
    SELECT NOMBRE, DIRECCION, IP_PUBLICA, DISTRIBUIDO, ID_PAIS, TIPO_EMPRESA_ID
    FROM BDD_NUBE.EMPRESA_CLIENTE
    WHERE ID_EMPRESA=10;
    */

    private int id;
    private String nombre;
    private String direccion;
    private String ipPublica;
    private String distribuido;
    private int idPais;
    private int tipoEmpresaId;

    public EmpresaClienteDTO() {
    }

    public EmpresaClienteDTO(int id, String nombre, String direccion, String ipPublica, String distribuido, int idPais, int tipoEmpresaId) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ipPublica = ipPublica;
        this.distribuido = distribuido;
        this.idPais = idPais;
        this.tipoEmpresaId = tipoEmpresaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getIpPublica() {
        return ipPublica;
    }

    public void setIpPublica(String ipPublica) {
        this.ipPublica = ipPublica;
    }

    public String getDistribuido() {
        return distribuido;
    }

    public void setDistribuido(String distribuido) {
        this.distribuido = distribuido;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public int getTipoEmpresaId() {
        return tipoEmpresaId;
    }

    public void setTipoEmpresaId(int tipoEmpresaId) {
        this.tipoEmpresaId = tipoEmpresaId;
    }
}
