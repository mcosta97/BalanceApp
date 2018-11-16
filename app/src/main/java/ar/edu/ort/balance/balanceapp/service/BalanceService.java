package ar.edu.ort.balance.balanceapp.service;

import android.arch.persistence.room.Room;
import android.content.Context;

import ar.edu.ort.balance.balanceapp.dao.CategoriaDao;
import ar.edu.ort.balance.balanceapp.dao.MovimientoDao;
import ar.edu.ort.balance.balanceapp.dao.UsuarioDao;
import ar.edu.ort.balance.balanceapp.db.BalanceDatabase;
import ar.edu.ort.balance.balanceapp.dto.Categoria;
import ar.edu.ort.balance.balanceapp.dto.Movimiento;
import ar.edu.ort.balance.balanceapp.dto.Usuario;

public class BalanceService {

    private static MovimientoDao movimientoDao;
    private CategoriaDao categoriaDao;
    private UsuarioDao usuarioDao;

    public BalanceService(Context context) {
        BalanceDatabase db = Room.databaseBuilder(context.getApplicationContext(), BalanceDatabase.class, "BalanceApp").allowMainThreadQueries().build();
        categoriaDao = db.categoriaDao();
        movimientoDao = db.movimientoDao();
        usuarioDao = db.usuarioDao();
    }

    public Usuario login(String user, String pass) {
        Usuario usuario = usuarioDao.login(user, pass);
        return usuario;
    }

    public boolean registrar(String nombre, String apellido, String pass, String mail) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido(apellido);
        nuevoUsuario.setPass(pass);
        nuevoUsuario.setMail(mail);

        try {
            usuarioDao.insertar(nuevoUsuario);
        } catch (Exception ex) {
            return false;
        }

        return true;
    }

    public static boolean crearMovimiento(Movimiento movimiento) {
        boolean pudo = true;
        try {
            movimientoDao.insertar(movimiento);
        } catch (Exception be) {
            pudo = false;
        }
        return pudo;
    }

    public static boolean editarMovimiento(Movimiento movimiento) {
        boolean pudo = true;
        try {
            movimientoDao.editar(movimiento);
        } catch (Exception be) {
            pudo = false;
        }
        return pudo;
    }

    public static boolean borrarMovimiento(Movimiento movimiento) {
        boolean pudo = true;
        try {
            movimientoDao.eliminar(movimiento);
        } catch (Exception be) {
            pudo = false;
        }
        return pudo;
    }

    public boolean crearCategoria(Categoria categoria) {
        boolean pudo = true;
        try {
            categoriaDao.insertar(categoria);
        } catch (Exception be) {
            pudo = false;
        }
        return pudo;
    }

    public boolean editarCategoria(Categoria categoria) {
        boolean pudo = true;
        try {
            categoriaDao.editar(categoria);
        } catch (Exception be) {
            pudo = false;
        }
        return pudo;
    }

    public boolean borrarCategoria(Categoria categoria) {
        boolean pudo = true;
        try {
            categoriaDao.eliminar(categoria);
        } catch (Exception be) {
            pudo = false;
        }
        return pudo;
    }
}
