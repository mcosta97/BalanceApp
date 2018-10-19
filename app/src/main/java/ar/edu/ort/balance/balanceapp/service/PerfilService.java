package ar.edu.ort.balance.balanceapp.service;

import android.content.Context;

import ar.edu.ort.balance.balanceapp.dao.UsuarioDAO;
import ar.edu.ort.balance.balanceapp.dto.Usuario;
import ar.edu.ort.balance.balanceapp.utils.BalanceException;
import ar.edu.ort.balance.balanceapp.utils.PerfilEnumResponse;

public class PerfilService {

    private UsuarioDAO usuarioDAO;

    public PerfilService(Context context) {
        usuarioDAO = new UsuarioDAO(context);
    }

    /**
     *
     * @param passwords 0.Vieja | 1.Nueva | 2.ReNueva
     * @param usuario
     */
    public PerfilEnumResponse cambiarPassword(String[] passwords, Usuario usuario) {
        PerfilEnumResponse pudo = PerfilEnumResponse.PASSWORD_OK;
        if (usuario != null) {
            PerfilEnumResponse passwordValida = validarPassword(passwords, usuario);
            if (passwordValida.equals(PerfilEnumResponse.PASSWORD_OK)) {
                usuario.setPass(passwords[1]);
                try {
                    usuarioDAO.editar(usuario);
                } catch (BalanceException be) {
                    pudo = PerfilEnumResponse.PASSWORD_ERR;
                }
            } else {
                pudo = passwordValida;
            }
        } else {
            pudo = PerfilEnumResponse.PASSWORD_ERR;
        }

        return pudo;
    }

    /**
     *
     * @param datos 0.Nombre | 1.Apellido | 2.Mail
     * @param usuario
     * @return
     */
    public PerfilEnumResponse editarPerfil(String[] datos, Usuario usuario) {
        PerfilEnumResponse pudo = PerfilEnumResponse.PERFIL_OK;
        if (usuario != null) {
            PerfilEnumResponse perfilValido = validarPerfil(datos);
            if (perfilValido.equals(PerfilEnumResponse.PERFIL_OK)) {
                usuario.setNombre(datos[0]);
                usuario.setApellido(datos[1]);
                usuario.setMail(datos[2]);
                try {
                    usuarioDAO.editar(usuario);
                } catch (BalanceException be) {
                    pudo = PerfilEnumResponse.PERFIL_ERR;
                }
            } else {
                pudo = perfilValido;
            }
        } else {
            pudo = PerfilEnumResponse.PERFIL_ERR;
        }
        return pudo;
    }

    private PerfilEnumResponse validarPassword(String[] datos, Usuario usuario) {
        PerfilEnumResponse valido = PerfilEnumResponse.PASSWORD_OK;
        if (datos[1].length() <= 4) {
            valido = PerfilEnumResponse.PASSWORD_INVALID_NEW;
        } else if (datos[2].length() <= 4) {
            valido = PerfilEnumResponse.PASSWORD_INVALID_RENEW;
        } else if (!usuario.getPass().equals(datos[0])) {
            valido = PerfilEnumResponse.PASSWORD_INVALID_OLD;
        } else if (!datos[1].equals(datos[2])) {
            valido = PerfilEnumResponse.PASSWORD_NOT_EQUALS;
        }
        return valido;
    }

    private PerfilEnumResponse validarPerfil(String[] datos) {
        PerfilEnumResponse valido = PerfilEnumResponse.PERFIL_OK;
        if (datos[0].length() == 0) {
            valido = PerfilEnumResponse.PERFIL_INVALID_NOMBRE;
        } else if (datos[1].length() == 0) {
            valido = PerfilEnumResponse.PERFIL_INVALID_APELLIDO;
        } else if (datos[2].length() <= 8) {
            valido = PerfilEnumResponse.PERFIL_INVALID_MAIL;
        }
        return valido;
    }

}
