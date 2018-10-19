package ar.edu.ort.balance.balanceapp.fragments;

import android.app.AlertDialog;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import ar.edu.ort.balance.balanceapp.MainActivity;
import ar.edu.ort.balance.balanceapp.R;
import ar.edu.ort.balance.balanceapp.adapters.MovimientoAdapter;
import ar.edu.ort.balance.balanceapp.dto.Movimiento;
import ar.edu.ort.balance.balanceapp.service.RandomDataService;
import ar.edu.ort.balance.balanceapp.utils.TipoMovimiento;

public class GastosFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Obtenemos la vista de Egresos
        View view = inflater.inflate(R.layout.content_gastos, container, false);

        //Una vez que tenemos los datos del usuario
        List<Movimiento> egresos = RandomDataService.generarMovimientos(TipoMovimiento.Egreso, 44);

        //Agregamos los datos + la actividad al movimiento adapter para insertarlo en el listView
        final MovimientoAdapter adapter = new MovimientoAdapter(getActivity(), egresos);

        //Obtenemos la referencia de la listView
        ListView listView = (ListView) view.findViewById(R.id.gastosListView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long id) {
                Movimiento m = (Movimiento) adapter.getItem(posicion);
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(view.getContext());
                View mView = getLayoutInflater().inflate(R.layout.dialog_detail, null);
                TextView lblDetalle = (TextView) mView.findViewById(R.id.lblDetalleGasto);
                lblDetalle.setText("Detalle del Gasto");
                TextView txtNombre = (TextView) mView.findViewById(R.id.txtDetalleGastoNombre);
                TextView txtFecha = (TextView) mView.findViewById(R.id.txtDetalleGastoFecha);
                TextView txtImporte = (TextView) mView.findViewById(R.id.txtDetalleGastoImporte);
                TextView txtCategoria = (TextView) mView.findViewById(R.id.txtDetalleGastoCategoria);

                txtNombre.setText(m.getNombre());
                txtFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(m.getFecha()));
                txtImporte.setText("-$ " + String.valueOf(m.getValor()));
                txtImporte.setTextColor(Color.RED);
                txtCategoria.setText(String.valueOf(m.getCategoriaId()));

                mBuilder.setView(mView);
                mBuilder.create().show();
            }
        });


        listView.setAdapter(adapter);

        //Devolvemos la view
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Cambio titulo de la barra
        getActivity().setTitle("Gastos");
    }
}
