package ar.edu.ort.balance.balanceapp.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

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

        //Obtenemos la referencia de la listView
        ListView listView = (ListView) view.findViewById(R.id.gastosListView);

        //Una vez que tenemos los datos del usuario
        List<Movimiento> egresos = RandomDataService.generarMovimientos(TipoMovimiento.Egreso, 44);

        //Agregamos los datos + la actividad al movimiento adapter para insertarlo en el listView
        MovimientoAdapter adapter = new MovimientoAdapter(getActivity(), egresos);
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
