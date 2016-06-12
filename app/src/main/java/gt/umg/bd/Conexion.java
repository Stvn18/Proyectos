package gt.umg.bd;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Locale;

import gt.umg.configuracion.configuracion;

/**
 * Created by Steven Vargas and Dulce Cajas on 31/05/16.
 */
public class Conexion {

    private static Connection conexion;

    public static void abrirConexion() throws Exception {

        boolean abreConexion = false;

        if (conexion == null) {
            abreConexion = true;
        } else {
            if (conexion.isClosed()) {
                abreConexion = true;
            }
        }

        if (abreConexion) {

            Locale.setDefault(new Locale("es", "ES"));

            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Conexion.conexion = DriverManager.getConnection("jdbc:oracle:thin:@" + configuracion.ipServidor + ":1521:XE", "BDD_NUBE", "123");
        }

    }

    public static void cerrarConexion() throws Exception {
        if (conexion != null) {
            conexion.close();
            conexion = null;
        }
    }

    public static Connection getConexion() {
        return conexion;
    }

}
