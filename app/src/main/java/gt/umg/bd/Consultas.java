package gt.umg.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import gt.umg.dto.PaisDTO;
import gt.umg.dto.TipoEmpresaDTO;

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

        String sql = "select usuario, pass from usuario where usuario = '" + usuario + "'";

        ResultSet resultado = getDatos(sql);

        String _usuario = "";
        String _password = "";

        if (resultado != null) {
            resultado.beforeFirst();
            while (resultado.next()) {
                _usuario = resultado.getString("usuario");
                _password = resultado.getString("pass");
            }
        }

        if (!_usuario.equals("") && !_password.equals("")) {

            if (_password.equals(password)) {
                return "OK";
            } else {
                return "Contraseña incorrecta";
            }

        } else {
            return "El usuario no existe";
        }
    }

    public String crearUsuario(String usuario, String password) throws Exception {

        String sql;

        sql = "select count(*) from USUARIO where usuario = '" + usuario + "'";

        Object o = getDato(sql);

        int count = 0;

        if (o != null) {
            count = Integer.parseInt(o.toString());
        }

        if (count == 0) {
            sql = "INSERT INTO USUARIO (id_usuario, usuario, pass) VALUES (seq_usuario.nextval, '" + usuario + "', '" + password + "')";

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


}
