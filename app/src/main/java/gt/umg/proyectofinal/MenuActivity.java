package gt.umg.proyectofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gt.umg.adapters.MenuAdapter;
import gt.umg.dto.MenuDTO;


/**
 * Created by Steven Vargas, Dulce Cajas on 1/06/16.
 */

public class MenuActivity extends AppCompatActivity {

    private ListView listMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        listMenu = (ListView) findViewById(R.id.listMenu);

        List<MenuDTO> menu = new ArrayList<>();

        menu.add(new MenuDTO(1, R.drawable.crear_usuario, "Crear usuario"));
        menu.add(new MenuDTO(2, R.drawable.vender, "Vender"));
        menu.add(new MenuDTO(3, R.drawable.cargar, "Ventas del dia"));
        menu.add(new MenuDTO(4, R.drawable.disponibilidad, "Vendido por país"));
        menu.add(new MenuDTO(5, R.drawable.usado, "Consultar usado"));
        menu.add(new MenuDTO(6, R.drawable.clientes, "Crear cliente"));

        MenuAdapter adapter = new MenuAdapter(this, menu);

        listMenu.setAdapter(adapter);

        listMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {

                    MenuDTO item = (MenuDTO) listMenu.getItemAtPosition(position);

                    Intent i;

                    switch (item.getId()) {
                        case 1:
                            i = new Intent(view.getContext(), CrearUsuarioActivity.class);
                            startActivity(i);
                            break;

                        case 2:
                            i = new Intent(view.getContext(), CrearContratoActivity.class);
                            startActivity(i);
                            break;

                        case 3:

                            i = new Intent(view.getContext(), VentasDiaActivity.class);
                            startActivity(i);
                            break;

                        case 4:

                            i = new Intent(view.getContext(), VendidoPorPaisActivity.class);
                            startActivity(i);
                            break;

                        case 5:

                            i = new Intent(view.getContext(), ConsultarUsadoActivitty.class);
                            startActivity(i);
                            break;

                        case 6:
                            i = new Intent(view.getContext(), CrearClienteActivity.class);
                            startActivity(i);
                            break;

                        default:
                            break;
                    }

                } catch (Exception e) {
                    Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}
