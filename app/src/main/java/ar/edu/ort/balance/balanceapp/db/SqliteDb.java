package ar.edu.ort.balance.balanceapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ar.edu.ort.balance.balanceapp.utils.DbConst;

public class SqliteDb extends SQLiteOpenHelper {

    private final String TABLA_CATEGORIA_SQL = "CREATE TABLE IF NOT EXISTS " + DbConst.TABLA_CATEGORIA + " (" +
                                        DbConst.CAMPO_CATEGORIA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        DbConst.CAMPO_CATEGORIA_NOMBRE + " TEXT, " +
                                        DbConst.CAMPO_CATEGORIA_USUARIO_ID + " TEXT, " +
                                        DbConst.CAMPO_CATEGORIA_FECHA +  " TEXT, " +
                                        DbConst.CAMPO_CATEGORIA_TIPO + " INTEGER, " +
                                        DbConst.CAMPO_CATEGORIA_VALOR + " REAL)";

    private final String TABLA_USUARIO_SQL = "CREATE TABLE IF NOT EXISTS " + DbConst.TABLA_USUARIO + " (" +
                                        DbConst.CAMPO_USUARIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        DbConst.CAMPO_USUARIO_NOMBRE + " TEXT, " +
                                        DbConst.CAMPO_USUARIO_APELLIDO + " TEXT, " +
                                        DbConst.CAMPO_USUARIO_PASS + " TEXT, " +
                                        DbConst.CAMPO_USUARIO_MAIL + " TEXT)";

    private final String TABLA_MOVIMIENTO_SQL = "CREATE TABLE IF NOT EXISTS " + DbConst.TABLA_MOVIMIENTO + " (" +
                                        DbConst.CAMPO_MOVIMIENTO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        DbConst.CAMPO_MOVIMIENTO_USUARIO_ID + " INTEGER, " +
                                        DbConst.CAMPO_MOVIMIENTO_CATEGORIA_ID + " INTEGER, " +
                                        DbConst.CAMPO_MOVIMIENTO_NOMBRE + " TEXT, " +
                                        DbConst.CAMPO_MOVIMIENTO_FECHA + " TEXT, " +
                                        DbConst.CAMPO_MOVIMIENTO_VALOR + " REAL)";

    public SqliteDb(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + DbConst.TABLA_CATEGORIA);
        db.execSQL("DROP TABLE IF EXISTS " + DbConst.TABLA_MOVIMIENTO);
        db.execSQL("DROP TABLE IF EXISTS " + DbConst.TABLA_USUARIO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL(TABLA_CATEGORIA_SQL);
            db.execSQL(TABLA_USUARIO_SQL);
            db.execSQL(TABLA_MOVIMIENTO_SQL);
            db.setTransactionSuccessful();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.endTransaction();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
