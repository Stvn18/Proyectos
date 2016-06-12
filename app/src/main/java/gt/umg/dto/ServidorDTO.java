package gt.umg.dto;

/**
 * Created by Steven Vargas and Dulce Cajas on 3/06/16.
 */
public class ServidorDTO {

    private int idServ;
    private String nombre;
    private String cpu;
    private String totalDD;
    private String sistemaOp;
    private String rendimiento;
    private String marca;
    private int idTserv;
    private int idUbi;
    private String ram;

    public ServidorDTO() {
    }

    public ServidorDTO(int idServ, String nombre, String cpu, String totalDD, String sistemaOp, String rendimiento, String marca, int idTserv, int idUbi) {
        this.idServ = idServ;
        this.nombre = nombre;
        this.cpu = cpu;
        this.totalDD = totalDD;
        this.sistemaOp = sistemaOp;
        this.rendimiento = rendimiento;
        this.marca = marca;
        this.idTserv = idTserv;
        this.idUbi = idUbi;
    }

    public int getIdServ() {
        return idServ;
    }

    public void setIdServ(int idServ) {
        this.idServ = idServ;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getTotalDD() {
        return totalDD;
    }

    public void setTotalDD(String totalDD) {
        this.totalDD = totalDD;
    }

    public String getSistemaOp() {
        return sistemaOp;
    }

    public void setSistemaOp(String sistemaOp) {
        this.sistemaOp = sistemaOp;
    }

    public String getRendimiento() {
        return rendimiento;
    }

    public void setRendimiento(String rendimiento) {
        this.rendimiento = rendimiento;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getIdTserv() {
        return idTserv;
    }

    public void setIdTserv(int idTserv) {
        this.idTserv = idTserv;
    }

    public int getIdUbi() {
        return idUbi;
    }

    public void setIdUbi(int idUbi) {
        this.idUbi = idUbi;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

}
