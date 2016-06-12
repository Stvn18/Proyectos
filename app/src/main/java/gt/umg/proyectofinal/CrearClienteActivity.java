package gt.umg.proyectofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import gt.umg.adapters.PaisAdapter;
import gt.umg.adapters.TipoEmpresaAdapter;
import gt.umg.bd.Consultas;
import gt.umg.dto.PaisDTO;
import gt.umg.dto.TipoEmpresaDTO;

public class CrearClienteActivity extends AppCompatActivity {


    EditText txtNombreCliente;
    EditText txtDireccion;
    EditText txtIpPublica;
    CheckBox chkDistribuido;
    Spinner listPaises;
    Spinner listTiposEmpresa;
    Button botonCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cliente);


        txtNombreCliente = (EditText) findViewById(R.id.crearCLienteNombre);
        txtDireccion = (EditText) findViewById(R.id.crearClienteDireccion);
        txtIpPublica = (EditText) findViewById(R.id.crearClienteIpPublica);
        chkDistribuido = (CheckBox) findViewById(R.id.CrearClienteDistribuido);
        listPaises = (Spinner) findViewById(R.id.CrearClientePais);
        listTiposEmpresa = (Spinner) findViewById(R.id.CrearClienteTipoEmpresa);
        botonCrear = (Button) findViewById(R.id.crearClienteBotonCrear);

        try {

            Consultas c = new Consultas();

            List<PaisDTO> paises = c.getPaises();
            List<TipoEmpresaDTO> tiposEmpresa = c.getTiposEmpresa();

            PaisAdapter paisAdapter = new PaisAdapter(this, paises);
            TipoEmpresaAdapter tipoEmpresaAdapter = new TipoEmpresaAdapter(this, tiposEmpresa);

            listPaises.setAdapter(paisAdapter);
            listTiposEmpresa.setAdapter(tipoEmpresaAdapter);

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        botonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearCliente();
            }
        });

    }

    public void crearCliente() {

        try {
            if (txtNombreCliente.getText().toString().equals("")) {
                Toast.makeText(this, "Ingrese el nombre del cliente", Toast.LENGTH_SHORT).show();
                return;
            }

            if (txtDireccion.getText().toString().equals("")) {
                Toast.makeText(this, "Ingrese la direccion del cliente", Toast.LENGTH_SHORT).show();
                return;
            }

            if (txtIpPublica.getText().toString().equals("")) {
                Toast.makeText(this, "Ingrese IP publica del cliente", Toast.LENGTH_SHORT).show();
                return;
            }


            PaisDTO pais = (PaisDTO) listPaises.getSelectedItem();
            TipoEmpresaDTO tipoEmpresa = (TipoEmpresaDTO) listTiposEmpresa.getSelectedItem();

            Consultas c = new Consultas();

            String respuesta = c.crearCliente(txtNombreCliente.getText().toString(), txtDireccion.getText().toString(), txtIpPublica.getText().toString(), chkDistribuido.isChecked(), pais.getIdPais(), tipoEmpresa.getTipoEmpresaId());

            if (respuesta.equals("OK")) {

                txtNombreCliente.setText("");
                txtDireccion.setText("");
                txtIpPublica.setText("");
                chkDistribuido.setChecked(false);

                listPaises.setSelection(0);
                listTiposEmpresa.setSelection(0);

                Toast.makeText(this, "Cliente creado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, respuesta, Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

}
