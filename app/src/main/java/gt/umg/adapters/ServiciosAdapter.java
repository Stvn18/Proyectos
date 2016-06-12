package gt.umg.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import gt.umg.dto.PaisDTO;
import gt.umg.dto.ServiciosDTO;
import gt.umg.dto.TipoEmpresaDTO;
import gt.umg.proyectofinal.R;

/**
 * Created by Steven Vargas, Dulce Cajas on 2/06/16.
 */
public class ServiciosAdapter extends BaseAdapter {

    private List<ServiciosDTO> lst;
    private Context c;

    public ServiciosAdapter(Context c, List<ServiciosDTO> lst) {
        this.c = c;
        this.lst = lst;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.template_item, null);
        }

        ServiciosDTO t = lst.get(position);

        if (t != null) {
            TextView descripcion = (TextView) v.findViewById(R.id.templateItemDescripcion);

            descripcion.setText(t.getServicio());
        }

        return v;
    }

    @Override
    public int getCount() {
        return lst.size();
    }

    @Override
    public Object getItem(int position) {
        return lst.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}