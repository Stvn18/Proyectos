package gt.umg.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import gt.umg.dto.MenuDTO;
import gt.umg.proyectofinal.R;

/**
 * Created by Steven Vargas, Dulce Cajas on 2/06/16.
 */
public class MenuAdapter extends ArrayAdapter<MenuDTO> {

    public MenuAdapter(Context context, List<MenuDTO> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Obteniendo una instancia del inflater
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Salvando la referencia del View de la fila
        View listItemView = convertView;

        //Comprobando si el View no existe
        if (null == convertView) {
            //Si no existe, entonces inflarlo con image_list_view.xml
            listItemView = inflater.inflate(
                    R.layout.menu_template,
                    parent,
                    false);
        }

        //Obteniendo instancias de los elementos
        TextView descripcion = (TextView) listItemView.findViewById(R.id.menuDescripcion);
        //TextView subtitulo = (TextView)listItemView.findViewById(R.id.text2);
        ImageView icono = (ImageView) listItemView.findViewById(R.id.menuIcono);


        //Obteniendo instancia de la Tarea en la posición actual
        MenuDTO item = getItem(position);

        descripcion.setText(item.getDescripcion());
        //subtitulo.setText(item.getHora());
        icono.setImageResource(item.getIcono());

        //Devolver al ListView la fila creada
        return listItemView;

    }

    @Override
    public MenuDTO getItem(int position) {
        return super.getItem(position);
    }
}