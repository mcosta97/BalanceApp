package ar.edu.ort.balance.balanceapp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import java.util.ArrayList;
import java.util.List;

import ar.edu.ort.balance.balanceapp.dto.Categoria;

@Dao
public interface CategoriaDao {

    @Query("SELECT * FROM Categoria WHERE Usuario_Id=:id")
    List<Categoria> obtenerCategorias(int id);

    @Insert
    void insertar(Categoria categoria);

    @Update
    void editar(Categoria categoria);

    @Delete
    void eliminar(Categoria categoria);
}
