package gt.umg.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import gt.umg.dto.MenuDTO;
import gt.umg.dto.VentaDiariaDTO;
import gt.umg.proyectofinal.R;

/**
 * Created by Steven Vargas, Dulce Cajas on 2/06/16.
 */
public class VentaDiariaAdapter extends ArrayAdapter<VentaDiariaDTO> {

    public VentaDiariaAdapter(Context context, List<VentaDiariaDTO> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Obteniendo una instancia del inflater
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Salvando la referencia del View de la fila
        View listItemView = convertView;

        //Comprobando si el View no existe
        if (null == convertView) {
            //Si no existe, entonces inflarlo con image_list_view.xml
            listItemView = inflater.inflate(R.layout.venta_diaria_template, parent, false);
        }

        DecimalFormat decimalFormat = new DecimalFormat("###,###.##");
        DecimalFormat integerFormat = new DecimalFormat("###,###");

        VentaDiariaDTO item = getItem(position);

        TextView empresaNombre = (TextView) listItemView.findViewById(R.id.VentaDiariaEmpresaNombre);
        TextView servicioNombre = (TextView) listItemView.findViewById(R.id.VentaDiariaServicioNombre);
        TextView costoTotal = (TextView) listItemView.findViewById(R.id.VentaDiariaCostoTotal);
        TextView cantidadContratada = (TextView) listItemView.findViewById(R.id.VentaDiariaCantidadContratado);

        empresaNombre.setText(item.getEmpresaNombre());
        servicioNombre.setText(item.getServicioNombre());
        costoTotal.setText(decimalFormat.format(item.getContratoCostoTotal()));
        cantidadContratada.setText(integerFormat.format(item.getCantidadContratada()));

        return listItemView;

    }

    @Override
    public VentaDiariaDTO getItem(int position) {
        return super.getItem(position);
    }
}