package ar.edu.ort.balance.balanceapp.dto;

import java.io.Serializable;
import java.util.Date;

public class Movimiento implements Serializable {
    private int id;
    private String nombre;
    private Date fecha;
    private double valor;
    private int categoriaId;

    public Movimiento() {}

    public Movimiento(int id, String nombre, Date fecha, double valor, int categoriaId) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.valor = valor;
        this.categoriaId = categoriaId;
    }

    public int getId() { return id; }

    public void  setId(int id) { this.id = id; }

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
}
