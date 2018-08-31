package ar.edu.ort.balance.balanceapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ar.edu.ort.balance.balanceapp.utils.DbConst;

public class SqliteDb extends SQLiteOpenHelper {

    private final String CREATE_SQL = "CREATE TABLE " + DbConst.TABLA_CATEGORIA + " (" +
                                        DbConst.CAMPO_CATEGORIA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        DbConst.CAMPO_CATEGORIA_NOMBRE + " TEXT, " +
                                        DbConst.CAMPO_CATEGORIA_DESCRIPCION +  " TEXT, " +
                                        DbConst.CAMPO_CATEGORIA_TIPO + " INTEGER, " +
                                        DbConst.CAMPO_CATEGORIA_VALOR + " REAL)";

    public SqliteDb(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
