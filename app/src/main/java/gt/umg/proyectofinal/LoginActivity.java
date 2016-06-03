package gt.umg.proyectofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import gt.umg.bd.Consultas;

public class LoginActivity extends AppCompatActivity {

    private Button botonLogin;

    private EditText txtUsuario;
    private EditText txtPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        botonLogin = (Button) findViewById(R.id.loginBotonIngresar);
        txtUsuario = (EditText) findViewById(R.id.loginUsuario);
        txtPass = (EditText) findViewById(R.id.loginPassword);

        txtPass.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    login();
                }

                return false;
            }
        });

        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


    }


    public void login() {

        try {
            if (txtUsuario.getText().toString().equals("") && txtPass.getText().toString().toString().equals("")) {


                Toast.makeText(this, "Ingrese Usuario y contraseña", Toast.LENGTH_SHORT).show();
            } else {

                Consultas c = new Consultas();

                String respuesta = c.login(txtUsuario.getText().toString(), txtPass.getText().toString());


                if (respuesta.equals("OK")) {

                    Intent i = new Intent(this, MenuActivity.class);
                    startActivity(i);

                } else {

                    Toast.makeText(this, respuesta, Toast.LENGTH_SHORT).show();

                }
            }


        } catch (Exception e) {

            Log.e("Login", e.getMessage());


        }

    }

}
