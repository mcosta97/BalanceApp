package ar.edu.ort.balance.balanceapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import ar.edu.ort.balance.balanceapp.db.SqliteDb;
import ar.edu.ort.balance.balanceapp.dto.Usuario;
import ar.edu.ort.balance.balanceapp.utils.BalanceException;
import ar.edu.ort.balance.balanceapp.utils.DbConst;

public class UsuarioDAO {

    private CategoriaDAO categoriaDAO;
    private SQLiteDatabase database;
    private SqliteDb conexion;

    public UsuarioDAO(Context context) {
        categoriaDAO = new CategoriaDAO(context);
        conexion = new SqliteDb(context, DbConst.NOMBRE_BASE, null, DbConst.VERSION_ACTUAL);
    }

    /**
     * Obtiene las categorias
     * @return categorias
     */
    public Usuario login(String user, String pass) throws BalanceException {
        Usuario usuario = null;

        try {
            database = conexion.getReadableDatabase();
            String[] columns = {DbConst.CAMPO_USUARIO_ID, DbConst.CAMPO_USUARIO_NOMBRE, DbConst.CAMPO_USUARIO_APELLIDO, DbConst.CAMPO_USUARIO_PASS, DbConst.CAMPO_USUARIO_MAIL};
            Cursor cursor = database.query(DbConst.TABLA_USUARIO, columns, "MAIL=? AND PASS=?", new String[] {user, pass}, null, null, null);

            while(cursor.moveToNext()) {
                usuario = new Usuario();
                usuario.setId(cursor.getInt(cursor.getColumnIndex(DbConst.CAMPO_USUARIO_ID)));
                usuario.setNombre(cursor.getString(cursor.getColumnIndex(DbConst.CAMPO_USUARIO_NOMBRE)));
                usuario.setApellido(cursor.getString(cursor.getColumnIndex(DbConst.CAMPO_USUARIO_APELLIDO)));
                usuario.setPass(cursor.getString(cursor.getColumnIndex(DbConst.CAMPO_USUARIO_PASS)));
                usuario.setMail(cursor.getString(cursor.getColumnIndex(DbConst.CAMPO_USUARIO_MAIL)));
                usuario.setCategorias(categoriaDAO.obtenerCategorias(usuario));
            }
            cursor.close();
        } catch (Exception ex) {
            Log.e("ERROR", "EN LOGIN: " + ex.getMessage());
            throw new BalanceException("No se pudo ingresar", ex);
        }

        return usuario;
    }

    /**
     * Inserta un nuevo usuario
     * @param usuario
     * @return si pudo insertar
     */
    public void insertar(Usuario usuario) throws BalanceException {
        ContentValues valores;

        try {
            valores = new ContentValues();
            valores.put(DbConst.CAMPO_USUARIO_NOMBRE, usuario.getNombre());
            valores.put(DbConst.CAMPO_USUARIO_APELLIDO, usuario.getApellido());
            valores.put(DbConst.CAMPO_USUARIO_PASS, usuario.getPass());
            valores.put(DbConst.CAMPO_USUARIO_MAIL, usuario.getMail());
            database = conexion.getWritableDatabase();
            database.insert(DbConst.TABLA_USUARIO, null, valores);
            database.close();
        } catch (Exception ex) {
            Log.e("ERROR", "AL INSERTAR USUARIO: " + ex.getMessage());
            throw new BalanceException("No se pudo guardar el usuario", ex);
        }
    }

    /**
     * Edita un usuario existente
     * @param usuario
     * @return si lo pudo editar
     */
    public void editar(Usuario usuario) throws BalanceException {
        ContentValues valores;
        try {
            valores = new ContentValues();
            valores.put(DbConst.CAMPO_USUARIO_NOMBRE, usuario.getNombre());
            valores.put(DbConst.CAMPO_USUARIO_APELLIDO, usuario.getApellido().toString());
            valores.put(DbConst.CAMPO_USUARIO_MAIL, usuario.getMail());
            valores.put(DbConst.CAMPO_USUARIO_PASS, usuario.getPass());

            database = conexion.getWritableDatabase();
            String where = DbConst.CAMPO_USUARIO_ID + "=" + usuario.getId();
            database.update(DbConst.TABLA_USUARIO, valores, where, null);
            database.close();
        } catch (Exception ex) {
            throw new BalanceException("No se pudo editar el usuario", ex);
        }
    }

    /**
     * Elimina un usuario existente
     * @param usuario
     * @return si lo pudo eliminar
     */
    public void eliminar(Usuario usuario) throws  BalanceException {
        try {
            database = conexion.getWritableDatabase();
            String where = DbConst.CAMPO_USUARIO_ID + "=?" + usuario.getId();
            database.delete(DbConst.TABLA_USUARIO, where, null);
            database.close();
        } catch (Exception ex) {
            Log.e("ERROR", "AL ELIMINAR: " + ex.getMessage());
            throw new BalanceException("No se pudo eliminar el usuario", ex);
        }
    }
}
