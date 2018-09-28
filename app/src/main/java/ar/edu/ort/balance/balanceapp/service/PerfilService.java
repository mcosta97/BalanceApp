package ar.edu.ort.balance.balanceapp.service;

import android.content.Context;

import ar.edu.ort.balance.balanceapp.dao.UsuarioDAO;
import ar.edu.ort.balance.balanceapp.dto.Usuario;

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
    public boolean cambiarPassword(String[] passwords, Usuario usuario) {
        boolean pudo = true;
        if (usuario != null) {
            if (passwords[1].length() > 4 && passwords[2].length() > 4 && usuario.getPass().equals(passwords[0])) {
                if (passwords[1].equals(passwords[2])) {
                    usuario.setPass(passwords[1]);
                    usuarioDAO.editar(usuario);
                } else {
                    pudo = false;
                }
            } else {
                pudo = false;
            }
        } else {
            pudo = false;
        }

        return pudo;
    }

    /**
     *
     * @param datos 0.Nombre | 1.Apellido | 2.Mail
     * @param usuario
     * @return
     */
    public boolean editarPerfil(String[] datos, Usuario usuario) {
        boolean pudo = true;
        if (usuario != null) {
            if (datos[0].length() > 5 && datos[1].length() > 5 && datos[2].length() > 5) {
                usuario.setNombre(datos[0]);
                usuario.setApellido(datos[1]);
                usuario.setMail(datos[2]);
                usuarioDAO.editar(usuario);
            } else {
                pudo = false;
            }
        } else {
            pudo = false;
        }
        return pudo;
    }

}
