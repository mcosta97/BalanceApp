package ar.edu.ort.balance.balanceapp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import ar.edu.ort.balance.balanceapp.db.SqliteDb;
import ar.edu.ort.balance.balanceapp.dto.Usuario;
import ar.edu.ort.balance.balanceapp.utils.BalanceException;
import ar.edu.ort.balance.balanceapp.utils.DbConst;

@Dao
public interface UsuarioDAO {

    @Query("SELECT * FROM Usuario WHERE mail=:mail AND pass=:pass")
    Usuario login(String mail, String pass);

    @Insert
    void insertar(Usuario usuario);

    @Update
    void editar(Usuario usuario);

    @Delete
    void eliminar(Usuario usuario);

}
