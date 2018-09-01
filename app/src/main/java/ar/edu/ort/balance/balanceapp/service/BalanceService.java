package ar.edu.ort.balance.balanceapp.service;

import android.content.Context;

import ar.edu.ort.balance.balanceapp.dao.CategoriaDAO;
import ar.edu.ort.balance.balanceapp.dao.MovimientoDAO;
import ar.edu.ort.balance.balanceapp.dao.UsuarioDAO;
import ar.edu.ort.balance.balanceapp.dto.Categoria;
import ar.edu.ort.balance.balanceapp.dto.Movimiento;
import ar.edu.ort.balance.balanceapp.dto.Usuario;

public class BalanceService {

    private CategoriaDAO categoriaDAO;
    private MovimientoDAO movimientoDAO;
    private UsuarioDAO usuarioDAO;

    public BalanceService(Context context) {
        categoriaDAO = new CategoriaDAO(context);
        movimientoDAO = new MovimientoDAO(context);
        usuarioDAO = new UsuarioDAO(context);
    }

    public Usuario login(String user, String pass) {
        return usuarioDAO.login(user, pass);
    }

    public boolean registrar(String user, String pass, String mail) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUser(user);
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
        return false;
    }

    public boolean editarMovimiento(Movimiento movimiento) {
        return false;
    }

    public boolean borrarMovimiento(Movimiento movimiento) {
        return false;
    }

    public boolean crearCategoria(Categoria categoria) {
        return false;
    }

    public boolean editarCategoria(Categoria categoria) {
        return false;
    }

    public boolean borrarCategoria(Categoria categoria) {
        return false;
    }
}
