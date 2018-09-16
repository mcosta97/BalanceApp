package ar.edu.ort.balance.balanceapp;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import ar.edu.ort.balance.balanceapp.dto.Usuario;
import ar.edu.ort.balance.balanceapp.fragments.EstadisticaFragment;
import ar.edu.ort.balance.balanceapp.fragments.GastosFragment;
import ar.edu.ort.balance.balanceapp.fragments.IngresosFragment;
import ar.edu.ort.balance.balanceapp.fragments.InicioFragment;
import ar.edu.ort.balance.balanceapp.utils.GenConst;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Usuario usuario = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Bienvenido", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        usuario = new Usuario("Primer","Usuario","primerusuario@test.com","pass1234",null,null);
        recuperarUsuario(navigationView);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Apartado para crear menu
        return true;
    }

    private void recuperarUsuario(NavigationView navigationView) {
        Intent i = getIntent();
        //TODO: sacar cuando este andando el Login
        //usuario = (Usuario) i.getSerializableExtra(GenConst.PARAMETRO_USUARIO);
        View view = navigationView.getHeaderView(0);
        TextView userName = (TextView) view.findViewById(R.id.userName);
        TextView userMail = (TextView) view.findViewById(R.id.userMail);
        userName.setText(usuario.getNombre() + " " + usuario.getApellido());
        userMail.setText(usuario.getMail());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Apartado para mostrar opciones del item seleccionado
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        Intent intent = null;

        if (id == R.id.nav_inicio) {
            fragment = new InicioFragment();
        } else if (id == R.id.nav_gastos) {
            fragment = new GastosFragment();
        } else if (id == R.id.nav_ingresos) {
            fragment = new IngresosFragment();
        } else if (id == R.id.nav_estadistica) {
            fragment = new EstadisticaFragment();
        } else if (id == R.id.nav_perfil) {
            intent = new Intent(MainActivity.this, PerfilActivity.class);
        } else if (id == R.id.nav_cerrar) {
            intent = new Intent(MainActivity.this, LoginActivity.class);
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        if (intent != null) {
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
