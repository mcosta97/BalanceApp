package ar.edu.ort.balance.balanceapp.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.io.Serializable;
import java.util.Date;

import ar.edu.ort.balance.balanceapp.converter.BalanceConverter;

@Entity
public class Movimiento implements Serializable {

    @PrimaryKey
    private Long id;

    @ColumnInfo(name = "Nombre")
    private String nombre;

    @TypeConverters(BalanceConverter.class)
    private Date fecha;

    @ColumnInfo(name = "Valor")
    private double valor;

    @ColumnInfo(name = "Usuario_Id")
    private int usuarioId;

    @ColumnInfo(name = "Categoria_Id")
    private int categoriaId;

    public Movimiento() {}

    @Ignore
    public Movimiento(Long id, String nombre, Date fecha, double valor, int categoriaId) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.valor = valor;
        this.categoriaId = categoriaId;
    }

    public Long getId() { return id; }

    public void  setId(Long id) { this.id = id; }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}
