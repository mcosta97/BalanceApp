package ar.edu.ort.balance.balanceapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import ar.edu.ort.balance.balanceapp.R;
import ar.edu.ort.balance.balanceapp.dto.Movimiento;

public class MovimientoAdapter extends BaseAdapter {

    protected Activity activity;
    protected List<Movimiento> movimientos;

    public MovimientoAdapter(Activity activity, List<Movimiento> movimientos) {
        this.activity = activity;
        this.movimientos = movimientos;
    }

    @Override
    public int getCount() {
        return movimientos.size();
    }

    public void clear(){
        movimientos.clear();
    }

    public void addAll(List<Movimiento> movimientos) {
        movimientos.addAll(movimientos);
    }

    @Override
    public Object getItem(int index) {
        return  movimientos.get(index);
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;
    }

    @Override
    public View getView(int posicion, View convertView, ViewGroup parent) {
        View v = convertView;
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.custom_list_view, null);
        }

        Movimiento mov = movimientos.get(posicion);

        //Seteo nombre del movimiento
        TextView titulo = (TextView) v.findViewById(R.id.descripcion);
        titulo.setText(mov.getNombre());

        //Armo la descripcion del movimiento
        String importe = "$" + mov.getValor();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = dateFormat.format(mov.getFecha());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(importe);
        stringBuilder.append(" - ");
        stringBuilder.append(fecha);

        //Seteo descripcion del gasto
        TextView descripcion = (TextView) v.findViewById(R.id.detalle);
        descripcion.setText(stringBuilder.toString());

        return v;
    }

}
