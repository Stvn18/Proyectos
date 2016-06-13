package gt.umg.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import gt.umg.dto.VentasPorPaisDTO;
import gt.umg.proyectofinal.R;

/**
 * Created by Steven Vargas, Dulce Cajas on 12/06/16.
 */
public class VentaPorPaisAdapter extends ArrayAdapter<VentasPorPaisDTO> {

    public VentaPorPaisAdapter(Context context, List<VentasPorPaisDTO> objects) {
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
            listItemView = inflater.inflate(R.layout.vendido_por_pais_template, parent, false);
        }

        DecimalFormat integerFormat = new DecimalFormat("###,###");

        VentasPorPaisDTO item = getItem(position);

        TextView pis = (TextView) listItemView.findViewById(R.id.VendidoPorPaisNombrePais);
        TextView vendido = (TextView) listItemView.findViewById(R.id.VendidoPorPaisCantidadVendida);

        pis.setText(item.getPais());
        vendido.setText(integerFormat.format(item.getVendido()));

        return listItemView;

    }

    @Override
    public VentasPorPaisDTO getItem(int position) {
        return super.getItem(position);
    }

}
