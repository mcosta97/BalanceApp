package ar.edu.ort.balance.balanceapp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import ar.edu.ort.balance.balanceapp.dto.Usuario;

@Dao
public interface UsuarioDao {

    @Query("SELECT * FROM Usuario WHERE mail=:mail AND pass=:pass")
    Usuario login(String mail, String pass);

    @Insert
    void insertar(Usuario usuario);

    @Update
    void editar(Usuario usuario);

    @Delete
    void eliminar(Usuario usuario);

}
