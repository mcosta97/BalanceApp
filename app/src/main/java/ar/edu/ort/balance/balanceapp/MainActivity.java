package ar.edu.ort.balance.balanceapp;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;

import ar.edu.ort.balance.balanceapp.dto.Usuario;
import ar.edu.ort.balance.balanceapp.factory.FragmentFactory;
import ar.edu.ort.balance.balanceapp.utils.GenConst;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Usuario usuario = null;
    private int anterior = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recuperarUsuario(navigationView);
        setFragment(R.id.nav_inicio);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (anterior != -1) {
            setFragment(anterior);
        } else {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Apartado para crear menu
        return true;
    }

    private void recuperarUsuario(NavigationView navigationView) {
        Gson gson = new Gson();
        String usuarioJson = getIntent().getStringExtra(GenConst.PARAMETRO_USUARIO);
        usuario = gson.fromJson(usuarioJson, Usuario.class);

        if (usuario != null) {
            View view = navigationView.getHeaderView(0);
            TextView userName = (TextView) view.findViewById(R.id.userName);
            TextView userMail = (TextView) view.findViewById(R.id.userMail);
            userName.setText(usuario.getNombre() + " " + usuario.getApellido());
            userMail.setText(usuario.getMail());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Apartado para mostrar opciones del item seleccionado
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        setFragment(id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragment(int id) {
        Fragment fragment = null;
        Intent intent = null;

        if (anterior != -1) anterior = -1;

        FragmentFactory.setContext(getApplicationContext());
        fragment = FragmentFactory.getFragment(id);

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        } else {
            switch(id) {
                case R.id.nav_perfil:
                    intent = new Intent(MainActivity.this, PerfilActivity.class);
                    Gson gson = new Gson();
                    String usuarioJson = gson.toJson(usuario);
                    intent.putExtra(GenConst.PARAMETRO_USUARIO, usuarioJson);
                    break;

                case R.id.nav_cerrar:
                    intent = new Intent(MainActivity.this, LoginActivity.class);
                    break;
            }
            if (intent != null) startActivity(intent);
        }

        anterior = id;
    }


}
