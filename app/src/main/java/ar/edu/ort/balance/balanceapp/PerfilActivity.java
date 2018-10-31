package ar.edu.ort.balance.balanceapp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import ar.edu.ort.balance.balanceapp.dto.Usuario;
import ar.edu.ort.balance.balanceapp.service.BalanceService;
import ar.edu.ort.balance.balanceapp.service.PerfilService;
import ar.edu.ort.balance.balanceapp.utils.GenConst;
import ar.edu.ort.balance.balanceapp.utils.PerfilEnumResponse;

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
    public void onBackPressed() {
        Intent intent = new Intent(PerfilActivity.this, MainActivity.class);
        intent.putExtra(GenConst.PARAMETRO_USUARIO, new Gson().toJson(usuario));
        startActivity(intent);
    }

    private TextWatcher getTextWatcher(final TypeWatcher tipo) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (tipo.equals(TypeWatcher.PERFIL_WATCHER)) {
                    btnModificar.setEnabled(true);
                } else {
                    btnCambiarPassword.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        btnModificar = (Button) findViewById(R.id.btnModificar);
        btnModificar.setEnabled(false);
        btnCambiarPassword = (Button) findViewById(R.id.btnCambiarPassword);
        btnCambiarPassword.setEnabled(false);

        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtApellido = (TextView) findViewById(R.id.txtApellido);
        txtMail = (TextView) findViewById(R.id.txtMail);

        txtClaveAnterior = (TextView) findViewById(R.id.txtPasswordOld);
        txtClaveNueva = (TextView) findViewById(R.id.txtPasswordNew);
        txtRepetirClave = (TextView) findViewById(R.id.txtRePasswordNew);

        inicializarPantalla();

        TextWatcher perfilWatcher = getTextWatcher(TypeWatcher.PERFIL_WATCHER);
        TextWatcher passwordWatcher = getTextWatcher(TypeWatcher.PASSWORD_WATCHER);
        txtNombre.addTextChangedListener(perfilWatcher);
        txtApellido.addTextChangedListener(perfilWatcher);
        txtMail.addTextChangedListener(perfilWatcher);
        txtClaveAnterior.addTextChangedListener(passwordWatcher);
        txtClaveNueva.addTextChangedListener(passwordWatcher);
        txtRepetirClave.addTextChangedListener(passwordWatcher);

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] datos = new String[] {txtNombre.getText().toString(), txtApellido.getText().toString(), txtMail.getText().toString()};
                PerfilEnumResponse valido = perfilService.editarPerfil(datos, usuario);
                if (!valido.equals(PerfilEnumResponse.PERFIL_OK)) {
                    switch (valido) {
                        case PERFIL_INVALID_NOMBRE:
                            txtNombre.setError("Nombre invalido");
                            break;
                        case PERFIL_INVALID_APELLIDO:
                            txtApellido.setError("Apellido invalido");
                            break;
                        case PERFIL_INVALID_MAIL:
                            txtMail.setError("Mail invalido");
                            break;
                        case PERFIL_ERR:
                            Snackbar.make(view, "No se pudo editar el perfil", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                            break;
                    }
                } else {
                    Snackbar.make(view, "Perfil modificado!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });

        btnCambiarPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] datos = new String[] {txtClaveAnterior.getText().toString(), txtClaveNueva.getText().toString(), txtRepetirClave.getText().toString()};
                PerfilEnumResponse valido = perfilService.cambiarPassword(datos, usuario);
                if (!valido.equals(PerfilEnumResponse.PASSWORD_OK)) {
                    switch (valido) {
                        case PASSWORD_INVALID_OLD:
                            txtClaveAnterior.setError("Clave anterior invalida");
                            break;
                        case PASSWORD_INVALID_NEW:
                            txtClaveNueva.setError("Clave invalida");
                            break;
                        case PASSWORD_INVALID_RENEW:
                            txtRepetirClave.setError("Clave invalida");
                            break;
                        case PASSWORD_NOT_EQUALS:
                            txtRepetirClave.setError("Las claves no coinciden");
                            break;
                        case PASSWORD_ERR:
                            Snackbar.make(view, "No se pudo cambiar la clave", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                            break;
                    }
                } else {
                    Snackbar.make(view, "Clave modificada!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });
    }
}

enum TypeWatcher {
    PERFIL_WATCHER, PASSWORD_WATCHER
}