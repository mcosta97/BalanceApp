package ar.edu.ort.balance.balanceapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ar.edu.ort.balance.balanceapp.dao.UsuarioDAO;
import ar.edu.ort.balance.balanceapp.dto.Usuario;

@Database(entities = {Usuario.class}, version = 1)
public abstract class BalanceDatabase extends RoomDatabase {

    public abstract UsuarioDAO usuarioDAO();

}
