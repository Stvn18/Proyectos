package gt.umg.proyectofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

import gt.umg.bd.Consultas;

public class ConsultarUsadoActivitty extends AppCompatActivity {

    TextView vendido;
    TextView espacioPorVender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_usado_activitty);

        vendido = (TextView) findViewById(R.id.ConsultarUsadoVendido);
        espacioPorVender = (TextView) findViewById(R.id.ConsultarUsadoEspacioPorVender);

        try {
            Consultas c = new Consultas();

            Object[] resultado = c.getEspacioUsado();

            DecimalFormat integerFormat = new DecimalFormat("###,###");

            vendido.setText(integerFormat.format(Integer.parseInt(resultado[0].toString())));
            espacioPorVender.setText(integerFormat.format(Integer.parseInt(resultado[1].toString())));

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}
