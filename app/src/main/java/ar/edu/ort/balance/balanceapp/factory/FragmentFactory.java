package ar.edu.ort.balance.balanceapp.factory;

import android.content.Context;
import android.support.v4.app.Fragment;

import ar.edu.ort.balance.balanceapp.R;
import ar.edu.ort.balance.balanceapp.fragments.EstadisticaFragment;
import ar.edu.ort.balance.balanceapp.fragments.GastosFragment;
import ar.edu.ort.balance.balanceapp.fragments.IngresosFragment;
import ar.edu.ort.balance.balanceapp.fragments.InicioFragment;

public class FragmentFactory {

    private static Context context;

    public static Fragment getFragment(int id) {

        Fragment fragment = null;

        switch(id) {
            case R.id.nav_inicio:
                fragment = new InicioFragment();
                break;

            case R.id.nav_gastos:
                fragment = new GastosFragment();
                break;

            case R.id.nav_ingresos:
                fragment = new IngresosFragment();
                break;

            case R.id.nav_estadistica:
                fragment = new EstadisticaFragment();
                break;
        }

        return fragment;
    }

    public static void setContext(Context context) {
        FragmentFactory.context = context;
    }
}
