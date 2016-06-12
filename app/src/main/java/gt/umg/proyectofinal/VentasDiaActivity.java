package gt.umg.proyectofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import gt.umg.adapters.VentaDiariaAdapter;
import gt.umg.bd.Consultas;
import gt.umg.dto.VentaDiariaDTO;

/**
 * Created by Steven Vargas, Dulce Cajas on 1/06/16.
 */
public class VentasDiaActivity extends AppCompatActivity {

    ListView list;
    EditText fecha;
    Button botonConsultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas_dia);

        list = (ListView) findViewById(R.id.VentaDiaListResultado);
        fecha = (EditText) findViewById(R.id.VentaDiaFecha);
        botonConsultar = (Button) findViewById(R.id.VentaDiaBotonConsultar);

        Date fechaActual = new Date();

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        fecha.setText(df.format(fechaActual));

        botonConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!fecha.getText().toString().equals("")) {

                        Consultas c = new Consultas();

                        List<VentaDiariaDTO> ventas = c.getVentaDiariaByFecha(fecha.getText().toString());

                        VentaDiariaAdapter adapter = new VentaDiariaAdapter(v.getContext(), ventas);

                        list.setAdapter(adapter);

                    }
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
