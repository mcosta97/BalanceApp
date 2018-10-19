package ar.edu.ort.balance.balanceapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

import ar.edu.ort.balance.balanceapp.dto.Movimiento;
import ar.edu.ort.balance.balanceapp.dto.Usuario;
import ar.edu.ort.balance.balanceapp.utils.BalanceException;
import ar.edu.ort.balance.balanceapp.utils.DbConst;
import ar.edu.ort.balance.balanceapp.db.SqliteDb;
import ar.edu.ort.balance.balanceapp.dto.Categoria;
import ar.edu.ort.balance.balanceapp.utils.TipoMovimiento;

public class CategoriaDAO {

    private SqliteDb conexion;
    private SQLiteDatabase database;

    public CategoriaDAO(Context context) {
        conexion = new SqliteDb(context, DbConst.NOMBRE_BASE, null, DbConst.VERSION_ACTUAL);
    }

    /**
     * Obtiene las categorias
     * @return categorias
     */
    public ArrayList<Categoria> obtenerCategorias(Usuario usuario) throws BalanceException {
        ArrayList<Categoria> categorias = new ArrayList<>();

        try {
            database = conexion.getReadableDatabase();
            String[] columns = {DbConst.CAMPO_CATEGORIA_ID, DbConst.CAMPO_CATEGORIA_NOMBRE, DbConst.CAMPO_CATEGORIA_VALOR, DbConst.CAMPO_CATEGORIA_TIPO};
            Cursor cursor = database.query(DbConst.TABLA_CATEGORIA, columns, "Usuario_Id=?", new String[]{String.valueOf(usuario.getId())}, null, null, null);
            Categoria categoria;

            while(cursor.moveToNext()) {
                categoria = new Categoria();
                categoria.setId(cursor.getInt(cursor.getColumnIndex(DbConst.CAMPO_CATEGORIA_ID)));
                categoria.setNombre(cursor.getString(cursor.getColumnIndex(DbConst.CAMPO_CATEGORIA_NOMBRE)));
                categoria.setTipoMovimiento(TipoMovimiento.values()[cursor.getInt(cursor.getColumnIndex(DbConst.CAMPO_CATEGORIA_TIPO))]);
                categoria.setTotal(cursor.getFloat(cursor.getColumnIndex(DbConst.CAMPO_CATEGORIA_VALOR)));
                categorias.add(categoria);
            }
            cursor.close();
        } catch (Exception ex) {
            throw new BalanceException("No se pudieron obtener las catergorias", ex);
        }

        return categorias;
    }

    /**
     * Inserta una nueva categoría
     * @param categoria
     * @return si pudo insertar
     */
    public void insertar(Categoria categoria) throws BalanceException {
        ContentValues valores;
        try {
            valores = new ContentValues();
            valores.put(DbConst.CAMPO_CATEGORIA_ID, categoria.getId());
            valores.put(DbConst.CAMPO_CATEGORIA_NOMBRE, categoria.getNombre());
            valores.put(DbConst.CAMPO_CATEGORIA_VALOR, categoria.getTotal());
            valores.put(DbConst.CAMPO_CATEGORIA_TIPO, categoria.getTipoMovimiento().ordinal());
            database = conexion.getWritableDatabase();
            database.insert(DbConst.TABLA_CATEGORIA, null, valores);
            database.close();
        } catch (Exception ex) {
            throw new BalanceException("No se pudo insertar la categoria", ex);
        }
    }

    /**
     * Edita una categoría existente
     * @param categoria
     * @return si lo pudo editar
     */
    public void editar(Categoria categoria) throws BalanceException {
        ContentValues valores;

        try {
            valores = new ContentValues();
            valores.put(DbConst.CAMPO_CATEGORIA_NOMBRE, categoria.getNombre());
            valores.put(DbConst.CAMPO_CATEGORIA_VALOR, categoria.getTotal());
            valores.put(DbConst.CAMPO_CATEGORIA_TIPO, categoria.getTipoMovimiento().ordinal());

            database = conexion.getWritableDatabase();
            String where = DbConst.CAMPO_CATEGORIA_ID + "=" + categoria.getId();
            database.update(DbConst.TABLA_CATEGORIA, valores, where, null);
            database.close();
        } catch (Exception ex) {
            throw new BalanceException("No se pudo editar la categoria", ex);
        }
    }

    /**
     * Elimina una categoría existente
     * @param categoria
     * @return si lo pudo eliminar
     */
    public void eliminar(Categoria categoria) throws BalanceException {
        try {
            database = conexion.getWritableDatabase();
            String where = DbConst.CAMPO_CATEGORIA_ID + "=?" + categoria.getId();
            database.delete(DbConst.TABLA_CATEGORIA, where, null);
            database.close();
        } catch (Exception ex) {
            throw new BalanceException("No se pudo eliminar la categoria", ex);
        }
    }
}
