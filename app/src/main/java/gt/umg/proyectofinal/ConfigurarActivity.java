package gt.umg.proyectofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import gt.umg.configuracion.configuracion;


/**
 * Created by Steven Vargas, Dulce Cajas on 1/06/16.
 */
public class ConfigurarActivity extends AppCompatActivity {

    private Button botonGuardar;
    EditText ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar);


        botonGuardar = (Button) findViewById(R.id.configurarGuardar);
        ip = (EditText) findViewById(R.id.configurarIp);

        ip.setText(configuracion.ipServidor);

        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                configuracion.ipServidor = ip.getText().toString();

                Toast.makeText(v.getContext(), "Configuracion guardada", Toast.LENGTH_SHORT).show();


            }
        });

    }

}
