package ar.edu.ort.balance.balanceapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ar.edu.ort.balance.balanceapp.dao.CategoriaDao;
import ar.edu.ort.balance.balanceapp.dao.MovimientoDao;
import ar.edu.ort.balance.balanceapp.dao.UsuarioDao;
import ar.edu.ort.balance.balanceapp.dto.Categoria;
import ar.edu.ort.balance.balanceapp.dto.Movimiento;
import ar.edu.ort.balance.balanceapp.dto.Usuario;

@Database(entities = {Usuario.class, Categoria.class, Movimiento.class}, version = 1, exportSchema = false)
public abstract class BalanceDatabase extends RoomDatabase {

    public abstract UsuarioDao usuarioDao();

    public abstract CategoriaDao categoriaDao();

    public abstract MovimientoDao movimientoDao();

}
