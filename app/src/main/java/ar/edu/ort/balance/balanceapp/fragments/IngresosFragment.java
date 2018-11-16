package ar.edu.ort.balance.balanceapp.fragments;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ar.edu.ort.balance.balanceapp.R;
import ar.edu.ort.balance.balanceapp.adapter.CategoriaAdapter;
import ar.edu.ort.balance.balanceapp.adapter.MovimientoAdapter;
import ar.edu.ort.balance.balanceapp.dto.Movimiento;
import ar.edu.ort.balance.balanceapp.service.BalanceService;
import ar.edu.ort.balance.balanceapp.service.RandomDataService;
import ar.edu.ort.balance.balanceapp.utils.GenConst;
import ar.edu.ort.balance.balanceapp.utils.TipoMovimiento;

public class IngresosFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Obtenemos la vista de Ingresos
        View view = inflater.inflate(R.layout.content_ingresos, container, false);

        //Obtenemos la referencia de la listView
        ListView listView = (ListView) view.findViewById(R.id.ingresosListView);

        FloatingActionButton fab = view.findViewById(R.id.btnCrearIngreso);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder cBuilder = new AlertDialog.Builder(view.getContext());
                final View cView = getLayoutInflater().inflate(R.layout.dialog_create, null);
                setFormData(null, cView, 2);
                cBuilder.setView(cView);
                final AlertDialog cAlertDialog = cBuilder.create();

                Button btnCancelar = (Button) cView.findViewById(R.id.btnCancelar);
                btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cAlertDialog.dismiss();
                    }
                });

                Button btnAceptar = (Button) cView.findViewById(R.id.btnAceptar);
                btnAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Movimiento movimientoNuevo = new Movimiento();
                        movimientoNuevo.setNombre(((TextView) cView.findViewById(R.id.txtCreateNombre)).getText().toString());
                        movimientoNuevo.setValor(Double.parseDouble(((TextView) cView.findViewById(R.id.txtCreateImporte)).getText().toString()));
                        SimpleDateFormat dateFormat = new SimpleDateFormat(GenConst.FORMATO_FECHA);
                        Date date = null;
                        try {
                            date = dateFormat.parse(((TextView) cView.findViewById(R.id.txtCreateFecha)).getText().toString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        movimientoNuevo.setFecha(date);
                        BalanceService.crearMovimiento(movimientoNuevo);
                    }
                });
                cAlertDialog.show();
            }
        });

        //Una vez que tenemos los datos del usuario
        List<Movimiento> ingresos = RandomDataService.generarMovimientos(TipoMovimiento.Ingreso, 44);

        //Agregamos los datos + la actividad al movimiento adapter para insertarlo en el listView
        final MovimientoAdapter adapter = new MovimientoAdapter(getActivity(), ingresos);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long id) {
                final Movimiento m = (Movimiento) adapter.getItem(posicion);
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(view.getContext());
                View mView = getLayoutInflater().inflate(R.layout.dialog_detail, null);
                setFormData(m, mView, 0);
                mBuilder.setView(mView);
                final AlertDialog alertDialog= mBuilder.create();

                Button btnCerrar = mView.findViewById(R.id.btnCerrar);
                btnCerrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                Button btnEditar = mView.findViewById(R.id.btnEditar);
                btnEditar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder cBuilder = new AlertDialog.Builder(view.getContext());
                        View cView = getLayoutInflater().inflate(R.layout.dialog_create, null);
                        setFormData(m, cView, 1);
                        cBuilder.setView(cView);

                        final AlertDialog alertDialog1 = cBuilder.create();

                        Button btnCancelar = (Button) cView.findViewById(R.id.btnCancelar);
                        btnCancelar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alertDialog1.dismiss();
                            }
                        });

                        alertDialog1.show();
                    }
                });

                alertDialog.show();
            }
        });

        listView.setAdapter(adapter);

        //Devolvemos la view
        return view;
    }

    //Tipo: 0-Detalle | 1-Crear
    private void setFormData(Movimiento m, View view, int tipo) {
        if (tipo == 0) {
            TextView lblDetalle = view.findViewById(R.id.lblDetalleGasto);
            TextView txtNombre = view.findViewById(R.id.txtDetalleGastoNombre);
            TextView txtFecha = view.findViewById(R.id.txtDetalleGastoFecha);
            TextView txtImporte = view.findViewById(R.id.txtDetalleGastoImporte);
            TextView txtCategoria = view.findViewById(R.id.txtDetalleGastoCategoria);
            if (m != null) {
                txtNombre.setText(m.getNombre());
                txtFecha.setText(new SimpleDateFormat(GenConst.FORMATO_FECHA).format(m.getFecha()));
                txtImporte.setText("$ " + String.valueOf(m.getValor()));
                txtCategoria.setText(String.valueOf(m.getCategoriaId()));
                lblDetalle.setText("Detalle del Ingreso");
            }
        } else {
            TextView lblTitulo = view.findViewById(R.id.lblCreateTitle);
            TextView txtNombre = view.findViewById(R.id.txtCreateNombre);
            TextView txtFecha = view.findViewById(R.id.txtCreateFecha);
            TextView txtImporte = view.findViewById(R.id.txtCreateImporte);
            TextView txtCategoria = view.findViewById(R.id.txtCreateCategoria);
            if (m != null) {
                txtNombre.setText(m.getNombre());
                txtFecha.setText(new SimpleDateFormat(GenConst.FORMATO_FECHA).format(m.getFecha()));
                txtImporte.setText("$ " + String.valueOf(m.getValor()));
                txtImporte.setTextColor(Color.RED);
                txtCategoria.setText(String.valueOf(m.getCategoriaId()));
                lblTitulo.setText("Editar Ingreso");
            } else {
                lblTitulo.setText("Crear Ingreso");
            }
            txtCategoria.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder cBuilder = new AlertDialog.Builder(view.getContext());
                    View cView = getLayoutInflater().inflate(R.layout.dialog_create, null);
                    cBuilder.setView(cView);
                    ListView categoriasListView = new ListView(view.getContext());
                    CategoriaAdapter adapter = new CategoriaAdapter(getActivity(), RandomDataService.generarCategorias(10));
                    categoriasListView.setAdapter(adapter);
                    AlertDialog catDialog = cBuilder.create();
                    catDialog.create();
                }
            });
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Cambio titulo de la barra
        getActivity().setTitle("Ingresos");
    }
}
