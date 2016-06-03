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
    private Button botonCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        txtUsuarioNombre = (EditText) findViewById(R.id.crearUsuarioNombre);
        txtUsuarioPassword = (EditText) findViewById(R.id.crearUsuarioPassword);
        botonCrear = (Button) findViewById(R.id.crearUsuarioBotonCrear);

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

            Consultas c = new Consultas();

            String respuesta = c.crearUsuario(txtUsuarioNombre.getText().toString(), txtUsuarioPassword.getText().toString());

            if (respuesta.equals("OK")) {
                Toast.makeText(this, "Usuario creado correctamente", Toast.LENGTH_SHORT).show();

                txtUsuarioNombre.setText("");
                txtUsuarioPassword.setText("");

            } else {
                Toast.makeText(this, respuesta, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

}
