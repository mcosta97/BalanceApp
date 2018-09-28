package ar.edu.ort.balance.balanceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import ar.edu.ort.balance.balanceapp.dto.Usuario;
import ar.edu.ort.balance.balanceapp.service.BalanceService;
import ar.edu.ort.balance.balanceapp.service.PerfilService;
import ar.edu.ort.balance.balanceapp.utils.GenConst;

public class PerfilActivity extends AppCompatActivity {

    private Button btnModificar;
    private Button btnCambiarPassword;
    private TextView txtNombre;
    private TextView txtApellido;
    private TextView txtMail;
    private TextView txtClaveAnterior;
    private TextView txtClaveNueva;
    private TextView txtRepetirClave;

    private PerfilService perfilService;
    private Usuario usuario;

    private void inicializarPantalla() {
        perfilService = new PerfilService(getApplicationContext());

        Gson gson = new Gson();
        String usuarioJson = getIntent().getStringExtra(GenConst.PARAMETRO_USUARIO);
        usuario = gson.fromJson(usuarioJson, Usuario.class);
        if (usuario != null) {
            txtNombre.setText(usuario.getNombre());
            txtApellido.setText(usuario.getApellido());
            txtMail.setText(usuario.getMail());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        btnModificar = (Button) findViewById(R.id.btnModificar);
        btnCambiarPassword = (Button) findViewById(R.id.btnCambiarPassword);
        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtApellido = (TextView) findViewById(R.id.txtApellido);
        txtMail = (TextView) findViewById(R.id.txtMail);
        txtClaveAnterior = (TextView) findViewById(R.id.txtPasswordOld);
        txtClaveNueva = (TextView) findViewById(R.id.txtPasswordNew);
        txtRepetirClave = (TextView) findViewById(R.id.txtRePasswordNew);

        inicializarPantalla();

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] datos = new String[] {txtNombre.getText().toString(), txtApellido.getText().toString(), txtMail.getText().toString()};
                perfilService.editarPerfil(datos, usuario);
            }
        });

        btnCambiarPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] datos = new String[] {txtClaveAnterior.getText().toString(), txtClaveNueva.getText().toString(), txtRepetirClave.getText().toString()};
                perfilService.cambiarPassword(datos, usuario);
            }
        });
    }
}