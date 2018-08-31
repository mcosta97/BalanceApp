package ar.edu.ort.balance.balanceapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

import ar.edu.ort.balance.balanceapp.utils.DbConst;
import ar.edu.ort.balance.balanceapp.db.SqliteDb;
import ar.edu.ort.balance.balanceapp.dto.Categoria;
import ar.edu.ort.balance.balanceapp.utils.TipoMovimiento;

public class CategoriaDAO {

    private static final Logger logger = LogManager.getLogger("CategoriaDAO");
    private SqliteDb conexion;
    private SQLiteDatabase database;

    public CategoriaDAO(Context context) {
        conexion = new SqliteDb(context, DbConst.NOMBRE_BASE, null, DbConst.VERSION_ACTUAL);
    }

    /**
     * Obtiene las categorias
     * @return categorias
     */
    public ArrayList<Categoria> obtenerCategorias() {
        ArrayList<Categoria> categorias = new ArrayList<>();

        try {
            database = conexion.getReadableDatabase();
            String[] columns = {DbConst.CAMPO_CATEGORIA_ID, DbConst.CAMPO_CATEGORIA_NOMBRE, DbConst.CAMPO_CATEGORIA_DESCRIPCION, DbConst.CAMPO_CATEGORIA_VALOR, DbConst.CAMPO_CATEGORIA_TIPO};
            Cursor cursor = database.query(DbConst.TABLA_CATEGORIA, columns, null, null, null, null, null);
            Categoria categoria;

            while(cursor.moveToNext()) {
                categoria = new Categoria();
                categoria.setId(cursor.getInt(cursor.getColumnIndex(DbConst.CAMPO_CATEGORIA_ID)));
                categoria.setNombre(cursor.getString(cursor.getColumnIndex(DbConst.CAMPO_CATEGORIA_NOMBRE)));
                categoria.setDescripcion(cursor.getString(cursor.getColumnIndex(DbConst.CAMPO_CATEGORIA_DESCRIPCION)));
                categoria.setTipoMovimiento(TipoMovimiento.values()[cursor.getInt(cursor.getColumnIndex(DbConst.CAMPO_CATEGORIA_TIPO))]);
                categoria.setTotal(cursor.getFloat(cursor.getColumnIndex(DbConst.CAMPO_CATEGORIA_VALOR)));
                categorias.add(categoria);
            }
            cursor.close();
        } catch (Exception ex) {
            logger.error("Error al obtener categorias: " + ex.getMessage());
            return null;
        }

        return categorias;
    }

    /**
     * Inserta una nueva categoría
     * @param categoria
     * @return si pudo insertar
     */
    public boolean insertar(Categoria categoria) {
        boolean pudo = true;
        ContentValues valores;

        try {
            valores = new ContentValues();
            valores.put(DbConst.CAMPO_CATEGORIA_ID, categoria.getId());
            valores.put(DbConst.CAMPO_CATEGORIA_NOMBRE, categoria.getNombre());
            valores.put(DbConst.CAMPO_CATEGORIA_DESCRIPCION, categoria.getDescripcion());
            valores.put(DbConst.CAMPO_CATEGORIA_VALOR, categoria.getTotal());
            valores.put(DbConst.CAMPO_CATEGORIA_TIPO, categoria.getTipoMovimiento().ordinal());
            database = conexion.getWritableDatabase();
            database.insert(DbConst.TABLA_CATEGORIA, null, valores);
            database.close();
        } catch (Exception ex) {
            logger.error("Error al insertar categoria: " + ex.getMessage());
            pudo = false;
        }

        return pudo;
    }

    /**
     * Edita una categoría existente
     * @param categoria
     * @return si lo pudo editar
     */
    public boolean editar(Categoria categoria) {
        boolean pudo = true;
        ContentValues valores;

        try {
            valores = new ContentValues();
            valores.put(DbConst.CAMPO_CATEGORIA_NOMBRE, categoria.getNombre());
            valores.put(DbConst.CAMPO_CATEGORIA_DESCRIPCION, categoria.getDescripcion());
            valores.put(DbConst.CAMPO_CATEGORIA_VALOR, categoria.getTotal());
            valores.put(DbConst.CAMPO_CATEGORIA_TIPO, categoria.getTipoMovimiento().ordinal());

            database = conexion.getWritableDatabase();
            String where = DbConst.CAMPO_CATEGORIA_ID + "=" + categoria.getId();
            database.update(DbConst.TABLA_CATEGORIA, valores, where, null);
            database.close();
        } catch (Exception ex) {
            logger.error("Error al editar categoria: " + ex.getMessage());
            pudo = false;
        }

        return pudo;
    }

    /**
     * Elimina una categoría existente
     * @param categoria
     * @return si lo pudo eliminar
     */
    public boolean eliminar(Categoria categoria) {
        boolean pudo = true;

        try {
            database = conexion.getWritableDatabase();
            String where = DbConst.CAMPO_CATEGORIA_ID + "=" + categoria.getId();
            database.delete(DbConst.TABLA_CATEGORIA, where, null);
            database.close();
        } catch (Exception ex) {
            logger.error("Error al eliminar categoria: " + ex.getMessage());
            pudo = false;
        }

        return pudo;
    }

}