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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
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
                final Movimiento m = (Movimiento) adapter.getItem(posicion);
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(view.getContext());
                View mView = getLayoutInflater().inflate(R.layout.dialog_detail, null);
                TextView lblTitulo = (TextView) mView.findViewById(R.id.lblDetalleGasto);
                lblTitulo.setText("Detalle del Gasto");
                TextView txtNombre = (TextView) mView.findViewById(R.id.txtDetalleGastoNombre);
                TextView txtFecha = (TextView) mView.findViewById(R.id.txtDetalleGastoFecha);
                TextView txtImporte = (TextView) mView.findViewById(R.id.txtDetalleGastoImporte);
                TextView txtCategoria = (TextView) mView.findViewById(R.id.txtDetalleGastoCategoria);

                mBuilder.setView(mView);
                final AlertDialog alertDialog= mBuilder.create();

                Button btnCerrar = (Button) mView.findViewById(R.id.btnCerrar);
                btnCerrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                Button btnEditar = (Button) mView.findViewById(R.id.btnEditar);
                btnEditar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder cBuilder = new AlertDialog.Builder(view.getContext());
                        View cView = getLayoutInflater().inflate(R.layout.dialog_create, null);
                        TextView lblTitulo = (TextView) cView.findViewById(R.id.lblCreateTitle);
                        lblTitulo.setText("Editar Gasto");
                        TextView txtNombre = (TextView) cView.findViewById(R.id.txtCreateNombre);
                        TextView txtFecha = (TextView) cView.findViewById(R.id.txtCreateFecha);
                        TextView txtImporte = (TextView) cView.findViewById(R.id.txtCreateImporte);
                        Spinner txtCategoria = (Spinner) cView.findViewById(R.id.txtCreateCategoria);
                        cBuilder.setView(cView);

                        txtNombre.setText(m.getNombre());
                        txtFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(m.getFecha()));
                        txtImporte.setText("-$ " + String.valueOf(m.getValor()));
                        txtImporte.setTextColor(Color.RED);
                        //txtCategoria.setText(String.valueOf(m.getCategoriaId()));

                        final AlertDialog alertDialog1 = cBuilder.create();

                        Button btnCerrar = (Button) cView.findViewById(R.id.btnCancelar);
                        btnCerrar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alertDialog.dismiss();
                            }
                        });

                        alertDialog1.show();
                    }
                });

                txtNombre.setText(m.getNombre());
                txtFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(m.getFecha()));
                txtImporte.setText("-$ " + String.valueOf(m.getValor()));
                txtImporte.setTextColor(Color.RED);
                txtCategoria.setText(String.valueOf(m.getCategoriaId()));

                alertDialog.show();
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
