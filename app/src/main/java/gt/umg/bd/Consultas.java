package gt.umg.bd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gt.umg.configuracion.configuracion;
import gt.umg.dto.EmpresaClienteDTO;
import gt.umg.dto.PaisDTO;
import gt.umg.dto.ServiciosDTO;
import gt.umg.dto.ServidorDTO;
import gt.umg.dto.TipoEmpresaDTO;
import gt.umg.dto.VentaDiariaDTO;
import gt.umg.dto.VentasPorPaisDTO;

/**
 * Created by Steven Vargas, Dulce Cajas on 1/06/16.
 */
public class Consultas {

    Statement stmt;

    private void ejecuta(String sql) throws Exception {
        Conexion.abrirConexion();

        stmt = Conexion.getConexion().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.execute(sql);
    }

    private ResultSet getDatos(String sql) throws Exception {

        Conexion.abrirConexion();

        stmt = Conexion.getConexion().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet datos = stmt.executeQuery(sql);

        return datos;
    }

    private Object getDato(String sql) throws Exception {


        Object resultado = null;

        Conexion.abrirConexion();

        stmt = Conexion.getConexion().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet datos = stmt.executeQuery(sql);

        if (datos != null) {
            datos.beforeFirst();
            while (datos.next()) {
                resultado = datos.getObject(1);
            }
        }

        return resultado;

    }

    public String login(String usuario, String password) throws Exception {

        String sql = "select usuario, pass, id_usuario from usuario where usuario = '" + usuario + "'";

        ResultSet resultado = getDatos(sql);

        String _usuario = "";
        String _password = "";

        int idUsuario = 0;

        if (resultado != null) {
            resultado.beforeFirst();
            while (resultado.next()) {
                _usuario = resultado.getString("usuario");
                _password = resultado.getString("pass");
                idUsuario = resultado.getInt("id_usuario");
            }
        }

        if (!_usuario.equals("") && !_password.equals("")) {

            if (_password.equals(password)) {

                configuracion.idUsuario = idUsuario;

                return "OK";
            } else {
                return "Contraseña incorrecta";
            }

        } else {
            return "El usuario no existe";
        }
    }

    public String crearUsuario(String usuario, String password, int limiteVenta) throws Exception {

        String sql;

        sql = "select count(*) from USUARIO where usuario = '" + usuario + "'";

        Object o = getDato(sql);

        int count = 0;

        if (o != null) {
            count = Integer.parseInt(o.toString());
        }

        if (count == 0) {
            sql = "INSERT INTO USUARIO (id_usuario, usuario, pass, limite_venta) VALUES (seq_usuario.nextval, '" + usuario + "', '" + password + "', " + limiteVenta + ")";

            ejecuta(sql);

            return "OK";

        } else {

            return "El usuario ya existe";

        }

    }

    public List<PaisDTO> getPaises() throws Exception {
        List<PaisDTO> paises = new ArrayList<>();

        String sql = "select id_pais, nombre from PAIS";

        ResultSet respuesta = getDatos(sql);

        if (respuesta != null) {

            respuesta.beforeFirst();

            while (respuesta.next()) {

                PaisDTO pais = new PaisDTO();

                pais.setIdPais(respuesta.getInt("id_pais"));
                pais.setNombre(respuesta.getString("nombre"));

                paises.add(pais);

            }

        }

        return paises;

    }


    public List<TipoEmpresaDTO> getTiposEmpresa() throws Exception {

        List<TipoEmpresaDTO> tipos = new ArrayList<>();

        ResultSet respuesta = getDatos("select tipo_empresa_id, tipo_empresa from TIPO_EMPRESA");

        if (respuesta != null) {

            respuesta.beforeFirst();

            while (respuesta.next()) {

                TipoEmpresaDTO tipo = new TipoEmpresaDTO();

                tipo.setTipoEmpresaId(respuesta.getInt("tipo_empresa_id"));
                tipo.setTipoEmpresa(respuesta.getString("tipo_empresa"));

                tipos.add(tipo);

            }

        }

        return tipos;

    }

    public String crearCliente(String nombre, String direccion, String ip, boolean distribuido, int idPais, int tipoEmpresa) throws Exception {

        String sql;

        sql = "select count(*) from EMPRESA_CLIENTE where nombre = '" + nombre + "'";

        Object o = getDato(sql);

        int count = 0;

        if (o != null) {
            count = Integer.parseInt(o.toString());
        }

        if (count == 0) {

            sql = "INSERT INTO EMPRESA_CLIENTE(id_empresa, nombre, direccion, ip_publica, distribuido, id_pais, tipo_empresa_id) VALUES (seq_cliente.nextval,'" + nombre + "', '" + direccion + "', '" + ip + "', '" + (distribuido ? "S" : "N") + "', " + idPais + ", " + tipoEmpresa + ")";

            ejecuta(sql);

            return "OK";

        } else {
            return "Ya existe un cliente con el nombre " + nombre;
        }
    }


    public List<ServiciosDTO> getServicios() throws Exception {

        List<ServiciosDTO> respuesta = new ArrayList<>();

        ResultSet resultSet = getDatos("SELECT ID_SERVICIO, SERVICIO FROM servicio");

        if (resultSet != null) {

            resultSet.beforeFirst();

            while (resultSet.next()) {
                ServiciosDTO servicio = new ServiciosDTO();
                servicio.setIdServicio(resultSet.getInt("ID_SERVICIO"));
                servicio.setServicio(resultSet.getString("SERVICIO"));

                respuesta.add(servicio);
            }

        }

        return respuesta;
    }

    public List<EmpresaClienteDTO> getEmpresaCliente() throws Exception {

        List<EmpresaClienteDTO> respuesta = new ArrayList<>();

        ResultSet resultSet = getDatos("SELECT ID_EMPRESA, NOMBRE, DIRECCION, IP_PUBLICA, DISTRIBUIDO, ID_PAIS, TIPO_EMPRESA_ID " +
                "FROM BDD_NUBE.EMPRESA_CLIENTE ");

        if (resultSet != null) {
            resultSet.beforeFirst();

            while (resultSet.next()) {

                EmpresaClienteDTO empresa = new EmpresaClienteDTO();

                empresa.setId(resultSet.getInt("ID_EMPRESA"));
                empresa.setNombre(resultSet.getString("NOMBRE"));
                empresa.setDireccion(resultSet.getString("DIRECCION"));
                empresa.setIpPublica(resultSet.getString("IP_PUBLICA"));
                empresa.setDistribuido(resultSet.getString("DISTRIBUIDO"));
                empresa.setIdPais(resultSet.getInt("ID_PAIS"));
                empresa.setTipoEmpresaId(resultSet.getInt("TIPO_EMPRESA_ID"));

                respuesta.add(empresa);
            }
        }

        return respuesta;
    }

    public List<ServidorDTO> getServidores() throws Exception {

        List<ServidorDTO> respuesta = new ArrayList<>();
        ResultSet resultSet = getDatos("SELECT NOMBRE, CPU, TOTAL_DD, RAM, SISTEMA_OP, RENDIMIENTO, MARCA, ID_TSERV, ID_UBI FROM BDD_NUBE.SERVIDOR");

        if (resultSet != null) {
            resultSet.beforeFirst();

            while (resultSet.next()) {

                ServidorDTO servidor = new ServidorDTO();
                servidor.setNombre(resultSet.getString("NOMBRE"));
                servidor.setCpu(resultSet.getString("CPU"));
                servidor.setTotalDD(resultSet.getString("TOTAL_DD"));
                servidor.setRam(resultSet.getString("RAM"));
                servidor.setSistemaOp(resultSet.getString("SISTEMA_OP"));
                servidor.setRendimiento(resultSet.getString("RENDIMIENTO"));
                servidor.setMarca(resultSet.getString("MARCA"));
                servidor.setIdServ(resultSet.getInt("ID_TSERV"));
                servidor.setIdUbi(resultSet.getInt("ID_UBI"));

                respuesta.add(servidor);

            }
        }

        return respuesta;

    }

    public int getVendidoPorUsuario() throws Exception {
        String sql = " SELECT COALESCE(SUM(TAMANO.CANTIDAD_CONTRATADA),0) VENDIDO " +
                " FROM CONTRATO_EMPRESA CONTRATO " +
                "   INNER JOIN TAMANO_ESPACIO_CONTRATADO TAMANO " +
                "  ON CONTRATO.id_contrato = TAMANO.id_contrato " +
                " WHERE CONTRATO.id_usuario = " + configuracion.idUsuario;


        Object o = getDato(sql);
        int vendido = 0;
        if (o != null) {
            vendido = Integer.parseInt(o.toString());
        }

        return vendido;

    }

    public int getCantidadVentaPermitidaPorUsuario() throws Exception {

        String sql = "select limite_venta from usuario where id_usuario = " + configuracion.idUsuario;

        int cantidadPermitida = 0;
        Object o = getDato(sql);

        if (o != null) {
            cantidadPermitida = Integer.parseInt(o.toString());
        }

        return cantidadPermitida;

    }

    public void crearContrato(int idEmpresa, int idServicio, int costoTotal, String fecha, int cantidad, int servidor) throws Exception {

        String sql = "INSERT INTO BDD_NUBE.CONTRATO_EMPRESA (ID_CONTRATO, ID_EMPRESA, ID_SERVICIO, USADO_TOTAL, COSTO_TOTAL, FECHA, id_usuario) VALUES " +
                "(seq_contrato.nextval, " + idEmpresa + ", " + idServicio + ", 0, " + costoTotal + ", TO_DATE('" + fecha + "', 'dd/MM/yyyy'), " + configuracion.idUsuario + " )";

        ejecuta(sql);

        int max = 0;

        Object o = getDato(" select max(ID_CONTRATO) from  BDD_NUBE.CONTRATO_EMPRESA where id_usuario = " + configuracion.idUsuario);

        if (o != null) {
            max = Integer.parseInt(o.toString());
        }

        sql = "INSERT INTO BDD_NUBE.TAMANO_ESPACIO_CONTRATADO " +
                " (ID_ESPACIO_C, CANTIDAD_CONTRATADA, ID_CONTRATO, ID_SERV) " +
                " VALUES(seq_tamano.nextval, " + cantidad + ", " + max + ", " + servidor + ") ";

        ejecuta(sql);

        sql = "update TAM_ALMAC set USADO_V = USADO_V + " + cantidad + " where ID_SERV =  " + servidor;

        ejecuta(sql);
    }

    public List<VentaDiariaDTO> getVentaDiariaByFecha(String fecha) throws Exception {

        List<VentaDiariaDTO> respuesta = new ArrayList<>();

        String sql = " SELECT EMPRESA_CLIENTE.nombre, SERVICIO.servicio, TAMANO_ESPACIO_CONTRATADO.cantidad_contratada, CONTRATO_EMPRESA.costo_total " +
                " FROM CONTRATO_EMPRESA " +
                "   INNER JOIN SERVICIO " +
                "  ON SERVICIO.ID_SERVICIO = CONTRATO_EMPRESA.ID_SERVICIO " +
                "   INNER JOIN TAMANO_ESPACIO_CONTRATADO " +
                "  ON TAMANO_ESPACIO_CONTRATADO.ID_CONTRATO = CONTRATO_EMPRESA.ID_CONTRATO " +
                "   INNER JOIN EMPRESA_CLIENTE " +
                "  ON EMPRESA_CLIENTE.ID_EMPRESA = CONTRATO_EMPRESA.ID_EMPRESA " +
                " WHERE CONTRATO_EMPRESA.fecha = TO_DATE ('" + fecha + "', 'dd/MM/yyyy') " +
                " AND CONTRATO.id_usuario = " + configuracion.idUsuario;

        ResultSet resultSet = getDatos(sql);

        if (resultSet != null) {
            resultSet.beforeFirst();

            while (resultSet.next()) {

                VentaDiariaDTO venta = new VentaDiariaDTO();

                venta.setEmpresaNombre(resultSet.getString("NOMBRE"));
                venta.setServicioNombre(resultSet.getString("SERVICIO"));
                venta.setCantidadContratada(resultSet.getInt("CANTIDAD_CONTRATADA"));
                venta.setContratoCostoTotal(resultSet.getInt("COSTO_TOTAL"));

                respuesta.add(venta);

            }
        }

        return respuesta;

    }

    public List<VentasPorPaisDTO> getVendidoPorPais() throws Exception {

        List<VentasPorPaisDTO> resultado = new ArrayList<>();

        String sql = " SELECT PAIS.nombre PAIS, SUM(TAMANO.CANTIDAD_CONTRATADA) VENDIDO " +
                " FROM EMPRESA_CLIENTE EMPRESA  " +
                "   INNER JOIN CONTRATO_EMPRESA CONTRATO " +
                " ON EMPRESA.id_empresa = CONTRATO.id_empresa  " +
                "   INNER JOIN PAIS " +
                "  ON PAIS.id_pais = EMPRESA.id_pais " +
                "   INNER JOIN TAMANO_ESPACIO_CONTRATADO TAMANO  " +
                "  ON CONTRATO.id_contrato = TAMANO.id_contrato " +
                " GROUP BY PAIS.NOMBRE ";

        ResultSet resultSet = getDatos(sql);

        if (resultSet != null) {
            resultSet.beforeFirst();

            while (resultSet.next()) {

                VentasPorPaisDTO vendido = new VentasPorPaisDTO();

                vendido.setPais(resultSet.getString("PAIS"));
                vendido.setVendido(resultSet.getInt("VENDIDO"));

                resultado.add(vendido);

            }
        }

        return resultado;
    }

    public Object[] getEspacioUsado() throws Exception {

        Object[] resultado = new Object[]{};

        String sql = " SELECT SUM(TAM_ALMAC.USADO_V) ESPACIO_VENDIDO, SUM((DDSERVIDOR.TAMANO_TOTAL-TAM_ALMAC.USADO_V)) ESPACIO_POR_VENDER " +
                " FROM TAM_ALMAC " +
                "   JOIN DDSERVIDOR " +
                "  ON DDSERVIDOR.ID_DD = TAM_ALMAC.ID_DD ";

        ResultSet resultSet = getDatos(sql);

        if (resultSet != null) {

            resultSet.beforeFirst();

            while (resultSet.next()) {

                resultado = new Object[]{resultSet.getInt("ESPACIO_VENDIDO"), resultSet.getInt("ESPACIO_POR_VENDER")};

            }

        }

        return resultado;

    }

}
