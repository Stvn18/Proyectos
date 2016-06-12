package gt.umg.proyectofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import gt.umg.bd.Consultas;

public class CrearUsuarioActivity extends AppCompatActivity {

    private EditText txtUsuarioNombre;
    private EditText txtUsuarioPassword;
    private EditText txtUsuarioLimiteVenta;
    private Button botonCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        txtUsuarioNombre = (EditText) findViewById(R.id.crearUsuarioNombre);
        txtUsuarioPassword = (EditText) findViewById(R.id.crearUsuarioPassword);
        botonCrear = (Button) findViewById(R.id.crearUsuarioBotonCrear);
        txtUsuarioLimiteVenta = (EditText) findViewById(R.id.crearUsuarioLimiteVenta);

        botonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                crearUsuario();

            }
        });

    }

    private void crearUsuario() {

        try {

            if (txtUsuarioNombre.getText().toString().equals("")) {
                Toast.makeText(this, "Ingrese el usuario", Toast.LENGTH_SHORT).show();
                return;
            }

            if (txtUsuarioPassword.getText().toString().equals("")) {
                Toast.makeText(this, "Ingrese el password", Toast.LENGTH_SHORT).show();
                return;
            }

            if (txtUsuarioLimiteVenta.getText().toString().equals("")) {
                Toast.makeText(this, "Ingrese el limite de venta del usuario", Toast.LENGTH_SHORT).show();
                return;
            }

            Consultas c = new Consultas();

            String respuesta = c.crearUsuario(txtUsuarioNombre.getText().toString(), txtUsuarioPassword.getText().toString(), Integer.parseInt(txtUsuarioLimiteVenta.getText().toString()));

            if (respuesta.equals("OK")) {
                Toast.makeText(this, "Usuario creado correctamente", Toast.LENGTH_SHORT).show();

                txtUsuarioNombre.setText("");
                txtUsuarioPassword.setText("");
                txtUsuarioLimiteVenta.setText("");

                txtUsuarioNombre.requestFocus();

            } else {
                Toast.makeText(this, respuesta, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

}
