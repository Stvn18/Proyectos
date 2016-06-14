package gt.umg.proyectofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import gt.umg.adapters.EmpresaClienteAdapter;
import gt.umg.adapters.ServiciosAdapter;
import gt.umg.adapters.ServidoresAdapter;
import gt.umg.bd.Consultas;
import gt.umg.dto.EmpresaClienteDTO;
import gt.umg.dto.ServiciosDTO;
import gt.umg.dto.ServidorDTO;

/**
 * Created by Steven Vargas, Dulce Cajas on 1/06/16.
 */
public class CrearContratoActivity extends AppCompatActivity {


    private Spinner servicios;
    private Spinner empresas;
    private Spinner servidores;
    private EditText costo;
    private EditText cantidad;
    private EditText fecha;
    private Button botonCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_contrato);

        servicios = (Spinner) findViewById(R.id.CrearContratoServicio);
        servidores = (Spinner) findViewById(R.id.CrearContratoServidor);
        empresas = (Spinner) findViewById(R.id.CrearContratoEmpresa);

        costo = (EditText) findViewById(R.id.CrearContratoCosto);
        cantidad = (EditText) findViewById(R.id.CrearContratoCantidad);
        fecha = (EditText) findViewById(R.id.CrearContratoFecha);
        botonCrear = (Button) findViewById(R.id.CrearContratoBotonCrear);

        botonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                crear();

            }
        });

        Consultas c = new Consultas();

        try {
            List<ServiciosDTO> listServicios = c.getServicios();
            List<EmpresaClienteDTO> listEmpresas = c.getEmpresaCliente();
            List<ServidorDTO> listServidores = c.getServidores();

            ServiciosAdapter serviciosAdapter = new ServiciosAdapter(this, listServicios);
            servicios.setAdapter(serviciosAdapter);

            ServidoresAdapter servidoresAdapter = new ServidoresAdapter(this, listServidores);
            servidores.setAdapter(servidoresAdapter);

            EmpresaClienteAdapter EmpresaClienteAdapter = new EmpresaClienteAdapter(this, listEmpresas);
            empresas.setAdapter(EmpresaClienteAdapter);

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaActual = new Date();
            fecha.setText(format.format(fechaActual));

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private void crear() {

        try {

            if (costo.getText().toString().equals("")) {
                Toast.makeText(this, "Ingrese el costo", Toast.LENGTH_SHORT).show();
                return;
            }

            if (cantidad.getText().toString().equals("")) {
                Toast.makeText(this, "Ingrese la cantidad de espacio", Toast.LENGTH_SHORT).show();
                return;
            }

            if (fecha.getText().toString().equals("")) {
                Toast.makeText(this, "Ingrese la fecha", Toast.LENGTH_SHORT).show();
                return;
            }

            Consultas c = new Consultas();

            /*
            * Valida que la cantidad vendida no sea mayor a la cantidad que el vendedor puede vender
            * */
            DecimalFormat formatoNumero = new DecimalFormat("###,###");
            int cantidadVendida = c.getVendidoPorUsuario();
            int cantidadPermitida = c.getCantidadVentaPermitidaPorUsuario();

            if ((cantidadVendida + Integer.parseInt(cantidad.getText().toString())) > cantidadPermitida) {
                Toast.makeText(this, "No puede vender mas de lo permitido, usted lleva vendido " + formatoNumero.format(cantidadVendida), Toast.LENGTH_SHORT).show();
                return;
            }

            ServiciosDTO _servicio = (ServiciosDTO) servicios.getSelectedItem();
            EmpresaClienteDTO _empresa = (EmpresaClienteDTO) empresas.getSelectedItem();
            ServidorDTO _servidor = (ServidorDTO) servidores.getSelectedItem();

            c.crearContrato(_empresa.getId(), _servicio.getIdServicio(), Integer.parseInt(costo.getText().toString()), fecha.getText().toString(), Integer.parseInt(cantidad.getText().toString()), _servidor.getIdServ());

            Toast.makeText(this, "Contrato ingresado exitosamente", Toast.LENGTH_SHORT).show();

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaActual = new Date();

            costo.setText("");
            cantidad.setText("");
            fecha.setText(format.format(fechaActual));

            servicios.setSelection(0);
            servidores.setSelection(0);
            empresas.setSelection(0);

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

}
