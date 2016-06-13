package gt.umg.proyectofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

import gt.umg.adapters.VentaPorPaisAdapter;
import gt.umg.bd.Consultas;
import gt.umg.dto.VentasPorPaisDTO;

public class VendidoPorPaisActivity extends AppCompatActivity {

    ListView list;
    TextView totalVendido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendido_por_pais);

        list = (ListView) findViewById(R.id.VendidoPorPaisList);
        totalVendido = (TextView) findViewById(R.id.VendidoPorPaisTotalVendido);

        Consultas c = new Consultas();

        try {

            int _totalVendido = 0;

            List<VentasPorPaisDTO> result = c.getVendidoPorPais();

            VentaPorPaisAdapter adapter = new VentaPorPaisAdapter(this, result);

            list.setAdapter(adapter);

            for (VentasPorPaisDTO vendido : result) {
                _totalVendido += vendido.getVendido();
            }

            DecimalFormat integerFormat = new DecimalFormat("###,###");
            totalVendido.setText(integerFormat.format(_totalVendido));

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
