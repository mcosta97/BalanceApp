package ar.edu.ort.balance.balanceapp.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.io.Serializable;
import java.util.List;

import ar.edu.ort.balance.balanceapp.converter.BalanceConverter;
import ar.edu.ort.balance.balanceapp.db.BalanceDatabase;
import ar.edu.ort.balance.balanceapp.utils.TipoMovimiento;

@Entity
public class Categoria implements Serializable {

    @PrimaryKey
    private Long id;

    @ColumnInfo(name = "Nombre")
    private String nombre;

    @ColumnInfo(name = "Total")
    private double total;

    @TypeConverters(BalanceConverter.class)
    private TipoMovimiento tipoMovimiento;

    @ColumnInfo(name = "Usuario_Id")
    private String usuarioId;

    @TypeConverters(BalanceConverter.class)
    private List<Movimiento> movimientos;

    public Categoria() {}

    @Ignore
    public Categoria(Long i, String nombreCategoria, double v, TipoMovimiento tipoMovimiento) {
        this.id = i;
        this.nombre = nombreCategoria;
        this.total = v;
        this.tipoMovimiento = tipoMovimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }
}
