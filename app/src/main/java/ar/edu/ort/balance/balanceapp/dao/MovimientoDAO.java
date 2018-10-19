package ar.edu.ort.balance.balanceapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;
import java.util.ArrayList;

import ar.edu.ort.balance.balanceapp.db.SqliteDb;
import ar.edu.ort.balance.balanceapp.dto.Categoria;
import ar.edu.ort.balance.balanceapp.dto.Movimiento;
import ar.edu.ort.balance.balanceapp.dto.Usuario;
import ar.edu.ort.balance.balanceapp.utils.BalanceException;
import ar.edu.ort.balance.balanceapp.utils.DbConst;

public class MovimientoDAO {

    private SqliteDb conexion;
    private SQLiteDatabase database;

    public MovimientoDAO(Context context) {
        conexion = new SqliteDb(context, DbConst.NOMBRE_BASE, null, DbConst.VERSION_ACTUAL);
    }

    /**
     * Obtiene los movimientos
     * @return movimientos
     */
    public ArrayList<Movimiento> obtenerMovimientos(Usuario usuario) throws BalanceException {
        ArrayList<Movimiento> movimientos = new ArrayList<>();

        try {
            database = conexion.getReadableDatabase();
            String[] columns = {DbConst.CAMPO_MOVIMIENTO_ID, DbConst.CAMPO_MOVIMIENTO_NOMBRE, DbConst.CAMPO_MOVIMIENTO_FECHA, DbConst.CAMPO_MOVIMIENTO_CATEGORIA_ID, DbConst.CAMPO_MOVIMIENTO_VALOR};
            Cursor cursor = database.query(DbConst.TABLA_MOVIMIENTO, columns, "Usuario_Id=?", new String[]{String.valueOf(usuario.getId())}, null, null, null);
            Movimiento movimiento;

            while(cursor.moveToNext()) {
                movimiento = new Movimiento();
                movimiento.setId(cursor.getInt(cursor.getColumnIndex(DbConst.CAMPO_MOVIMIENTO_ID)));
                movimiento.setNombre(cursor.getString(cursor.getColumnIndex(DbConst.CAMPO_MOVIMIENTO_NOMBRE)));
                movimiento.setFecha(Date.valueOf(cursor.getString(cursor.getColumnIndex(DbConst.CAMPO_MOVIMIENTO_FECHA))));
                movimiento.setValor(cursor.getFloat(cursor.getColumnIndex(DbConst.CAMPO_MOVIMIENTO_VALOR)));
                movimiento.setCategoriaId(cursor.getInt(cursor.getColumnIndex(DbConst.CAMPO_MOVIMIENTO_CATEGORIA_ID)));
                movimientos.add(movimiento);
            }
            cursor.close();
        } catch (Exception ex) {
            throw new BalanceException("No se pudieron obtener los movimientos", ex);
        }

        return movimientos;
    }

    /**
     * Obtiene los movimientos segun la categoria especificada
     * @return movimientos
     */
    public ArrayList<Movimiento> obtenerMovimientosPorCategoria(Usuario usuario, Categoria categoria) throws BalanceException {
        ArrayList<Movimiento> movimientos = new ArrayList<>();

        try {
            database = conexion.getReadableDatabase();
            String[] columns = {DbConst.CAMPO_MOVIMIENTO_ID, DbConst.CAMPO_MOVIMIENTO_NOMBRE, DbConst.CAMPO_MOVIMIENTO_FECHA, DbConst.CAMPO_MOVIMIENTO_CATEGORIA_ID, DbConst.CAMPO_MOVIMIENTO_VALOR};
            Cursor cursor = database.query(DbConst.TABLA_MOVIMIENTO, columns, "Usuario_Id=? AND Categoria_Id=?", new String[]{String.valueOf(usuario.getId()), String.valueOf(categoria.getId())}, null, null, null);
            Movimiento movimiento;

            while(cursor.moveToNext()) {
                movimiento = new Movimiento();
                movimiento.setId(cursor.getInt(cursor.getColumnIndex(DbConst.CAMPO_MOVIMIENTO_ID)));
                movimiento.setNombre(cursor.getString(cursor.getColumnIndex(DbConst.CAMPO_MOVIMIENTO_NOMBRE)));
                movimiento.setFecha(Date.valueOf(cursor.getString(cursor.getColumnIndex(DbConst.CAMPO_MOVIMIENTO_FECHA))));
                movimiento.setValor(cursor.getFloat(cursor.getColumnIndex(DbConst.CAMPO_MOVIMIENTO_VALOR)));
                movimiento.setCategoriaId(cursor.getInt(cursor.getColumnIndex(DbConst.CAMPO_MOVIMIENTO_CATEGORIA_ID)));
                movimientos.add(movimiento);
            }
            cursor.close();
        } catch (Exception ex) {
            throw new BalanceException("No se pudieron obtener los movimientos por categoria", ex);
        }

        return movimientos;
    }

    /**
     * Inserta una nueva categoría
     * @param movimiento
     * @return si pudo insertar
     */
    public void insertar(Movimiento movimiento) throws BalanceException {
        ContentValues valores;
        try {
            valores = new ContentValues();
            valores.put(DbConst.CAMPO_MOVIMIENTO_ID, movimiento.getId());
            valores.put(DbConst.CAMPO_MOVIMIENTO_NOMBRE, movimiento.getNombre());
            valores.put(DbConst.CAMPO_MOVIMIENTO_FECHA, movimiento.getFecha().toString());
            valores.put(DbConst.CAMPO_MOVIMIENTO_VALOR, movimiento.getValor());
            database = conexion.getWritableDatabase();
            database.insert(DbConst.TABLA_CATEGORIA, null, valores);
            database.close();
        } catch (Exception ex) {
            throw new BalanceException("No se pudo insertar el movimiento", ex);
        }
    }

    /**
     * Edita un movimiento existente
     * @param movimiento
     * @return si lo pudo editar
     */
    public void editar(Movimiento movimiento) throws BalanceException {
        ContentValues valores;
        try {
            valores = new ContentValues();
            valores.put(DbConst.CAMPO_MOVIMIENTO_NOMBRE, movimiento.getNombre());
            valores.put(DbConst.CAMPO_MOVIMIENTO_FECHA, movimiento.getFecha().toString());
            valores.put(DbConst.CAMPO_MOVIMIENTO_VALOR, movimiento.getValor());

            database = conexion.getWritableDatabase();
            String where = DbConst.CAMPO_MOVIMIENTO_ID + "=" + movimiento.getId();
            database.update(DbConst.TABLA_MOVIMIENTO, valores, where, null);
            database.close();
        } catch (Exception ex) {
            throw new BalanceException("No se pudo editar el movimiento", ex);
        }
    }

    /**
     * Elimina un movimiento existente
     * @param movimiento
     * @return si lo pudo eliminar
     */
    public void eliminar(Movimiento movimiento) throws BalanceException {
        try {
            database = conexion.getWritableDatabase();
            String where = DbConst.CAMPO_MOVIMIENTO_ID + "=?" + movimiento.getId();
            database.delete(DbConst.TABLA_MOVIMIENTO, where, null);
            database.close();
        } catch (Exception ex) {
            throw new BalanceException("No se pudo eliminar el movimiento", ex);
        }
    }
}
