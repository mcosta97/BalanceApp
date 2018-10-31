package ar.edu.ort.balance.balanceapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import ar.edu.ort.balance.balanceapp.dto.Usuario;
import ar.edu.ort.balance.balanceapp.service.BalanceService;
import ar.edu.ort.balance.balanceapp.service.RandomDataService;
import ar.edu.ort.balance.balanceapp.utils.BalanceException;
import ar.edu.ort.balance.balanceapp.utils.GenConst;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private BalanceService balanceService = null;

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private AlertDialog registerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        balanceService = new BalanceService(getApplicationContext());

        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_log_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        TextView mRegisterButton = (TextView) findViewById(R.id.email_sign_in_button);
        mRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(LoginActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_register, null);
                final EditText mNombre = (EditText) mView.findViewById(R.id.txtNombre);
                final EditText mApellido = (EditText) mView.findViewById(R.id.txtApellido);
                final EditText mMail = (EditText) mView.findViewById(R.id.txtMail);
                final EditText mPass = (EditText) mView.findViewById(R.id.txtPassword);
                final EditText mRePass = (EditText) mView.findViewById(R.id.txtRePassword);
                Button btnRegister = (Button) mView.findViewById(R.id.btnRegistrar);
                btnRegister.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        attemptSubmit(mNombre, mApellido, mMail, mPass, mRePass);
                    }
                });
                mBuilder.setView(mView);
                registerDialog = mBuilder.create();
                registerDialog.show();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void attemptSubmit(EditText nombre, EditText apellido, EditText mail, EditText pass, EditText rePass) {
        // Reset errors.
        mail.setError(null);
        pass.setError(null);
        rePass.setError(null);

        View focusView = validarCampos(mail, pass, rePass);

        if (focusView != null) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            boolean registrado = balanceService.registrar(nombre.getText().toString(), apellido.getText().toString(), pass.getText().toString(), mail.getText().toString());
            if (registrado) {
                mEmailView.setText(mail.getText().toString());
                Snackbar.make(mLoginFormView, R.string.register_user_created, Snackbar.LENGTH_LONG).setAction("Action", null).show();
            } else {
                Snackbar.make(mLoginFormView, R.string.register_user_error, Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            showProgress(false);
            if (registerDialog != null) registerDialog.dismiss();
        }
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        View focusView = validarCampos(mEmailView, mPasswordView, null);

        if (focusView != null) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            validarUsuario(mEmailView.getText().toString(), mPasswordView.getText().toString());
        }
    }

    private View validarCampos(TextView email, TextView password, TextView rePassword) {
        View focusView = null;

        // Valida la clave
        if (TextUtils.isEmpty(password.getText().toString())) {
            password.setError(getString(R.string.error_field_required));
            focusView = password;
        } else if (!isPasswordValid(password.getText().toString())) {
            password.setError(getString(R.string.error_invalid_password));
            focusView = password;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email.getText().toString())) {
            email.setError(getString(R.string.error_field_required));
            focusView = email;
        } else if (!isEmailValid(email.getText().toString())) {
            email.setError(getString(R.string.error_invalid_email));
            focusView = email;
        }

        if (rePassword != null) {
            if (TextUtils.isEmpty(rePassword.getText().toString())) {
                rePassword.setError(getString(R.string.error_field_required));
                focusView = rePassword;
            } else if (!isPasswordValid(rePassword.getText().toString())) {
                rePassword.setError(getString(R.string.error_invalid_password));
                focusView = rePassword;
            } else if (!password.getText().toString().equals(rePassword.getText().toString())) {
                rePassword.setError(getString(R.string.error_not_coincident_passwords));
                focusView = rePassword;
            }
        }

        return focusView;
    }

    private void validarUsuario(final String mEmail, final String mPassword) {
        try {
            Usuario usuario = balanceService.login(mEmail, mPassword);
            if (usuario != null) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra(GenConst.PARAMETRO_USUARIO, new Gson().toJson(usuario));
                startActivity(intent);
            } else {
                Snackbar.make(mLoginFormView, R.string.error_user_not_found, Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        } catch(Exception ex) {
            Log.e("ERROR", ex.getMessage());
        }

        showProgress(false);
    }

    /**
     * Verifica que el mail ingresado sea correcto
     * @param email
     * @return
     */
    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    /**
     * Valida que la contraseña ingresada sea valida
     * @param password
     * @return
     */
    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

}

