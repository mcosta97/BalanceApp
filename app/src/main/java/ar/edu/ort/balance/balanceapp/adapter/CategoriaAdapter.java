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
import ar.edu.ort.balance.balanceapp.dto.Categoria;
import ar.edu.ort.balance.balanceapp.dto.Movimiento;

public class CategoriaAdapter extends BaseAdapter {

    protected Activity activity;
    protected List<Categoria> categorias;

    public CategoriaAdapter(Activity activity, List<Categoria> categorias) {
        this.activity = activity;
        this.categorias = categorias;
    }

    @Override
    public int getCount() {
        return categorias.size();
    }

    public void clear(){
        categorias.clear();
    }

    public void addAll(List<Categoria> categorias) {
        categorias.addAll(categorias);
    }

    @Override
    public Object getItem(int index) {
        return  categorias.get(index);
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

        Categoria cat = categorias.get(posicion);
        TextView titulo = (TextView) v.findViewById(R.id.descripcion);
        titulo.setText(cat.getNombre());

        return v;
    }

}
