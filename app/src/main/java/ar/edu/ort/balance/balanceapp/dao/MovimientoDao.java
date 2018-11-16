package ar.edu.ort.balance.balanceapp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

import ar.edu.ort.balance.balanceapp.dto.Movimiento;

@Dao
public interface MovimientoDao {

    @Query("SELECT * FROM Movimiento WHERE Usuario_Id=:usuarioId")
    List<Movimiento> obtenerMovimientos(int usuarioId);

    @Query("SELECT * FROM Movimiento WHERE Usuario_Id=:usuarioId AND Categoria_Id=:categoriaId")
    List<Movimiento> obtenerMovimientosPorCategoria(int usuarioId, int categoriaId);

    @Insert
    void insertar(Movimiento movimiento);

    @Update
    void editar(Movimiento movimiento);

    @Delete
    void eliminar(Movimiento movimiento);

}
