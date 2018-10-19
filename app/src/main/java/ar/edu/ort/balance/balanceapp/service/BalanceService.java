package ar.edu.ort.balance.balanceapp.service;

import android.content.Context;

import ar.edu.ort.balance.balanceapp.dao.CategoriaDAO;
import ar.edu.ort.balance.balanceapp.dao.MovimientoDAO;
import ar.edu.ort.balance.balanceapp.dao.UsuarioDAO;
import ar.edu.ort.balance.balanceapp.dto.Categoria;
import ar.edu.ort.balance.balanceapp.dto.Movimiento;
import ar.edu.ort.balance.balanceapp.dto.Usuario;
import ar.edu.ort.balance.balanceapp.utils.BalanceException;

public class BalanceService {

    private MovimientoDAO movimientoDAO;
    private CategoriaDAO categoriaDAO;
    private UsuarioDAO usuarioDAO;

    public BalanceService(Context context) {
        categoriaDAO = new CategoriaDAO(context);
        movimientoDAO = new MovimientoDAO(context);
        usuarioDAO = new UsuarioDAO(context);
    }

    public Usuario login(String user, String pass) {
        Usuario usuario = null;
        try {
            usuario = usuarioDAO.login(user, pass);
        } catch (BalanceException be) {}
        return usuario;
    }

    public boolean registrar(String nombre, String apellido, String pass, String mail) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido(apellido);
        nuevoUsuario.setPass(pass);
        nuevoUsuario.setMail(mail);

        try {
            usuarioDAO.insertar(nuevoUsuario);
        } catch (Exception ex) {
            return false;
        }

        return true;
    }

    public boolean crearMovimiento(Movimiento movimiento) {
        boolean pudo = true;
        try {
            movimientoDAO.insertar(movimiento);
        } catch (BalanceException be) {
            pudo = false;
        }
        return pudo;
    }

    public boolean editarMovimiento(Movimiento movimiento) {
        boolean pudo = true;
        try {
            movimientoDAO.editar(movimiento);
        } catch (BalanceException be) {
            pudo = false;
        }
        return pudo;
    }

    public boolean borrarMovimiento(Movimiento movimiento) {
        boolean pudo = true;
        try {
            movimientoDAO.eliminar(movimiento);
        } catch (BalanceException be) {
            pudo = false;
        }
        return pudo;
    }

    public boolean crearCategoria(Categoria categoria) {
        boolean pudo = true;
        try {
            categoriaDAO.insertar(categoria);
        } catch (BalanceException be) {
            pudo = false;
        }
        return pudo;
    }

    public boolean editarCategoria(Categoria categoria) {
        boolean pudo = true;
        try {
            categoriaDAO.editar(categoria);
        } catch (BalanceException be) {
            pudo = false;
        }
        return pudo;
    }

    public boolean borrarCategoria(Categoria categoria) {
        boolean pudo = true;
        try {
            categoriaDAO.eliminar(categoria);
        } catch (BalanceException be) {
            pudo = false;
        }
        return pudo;
    }
}
