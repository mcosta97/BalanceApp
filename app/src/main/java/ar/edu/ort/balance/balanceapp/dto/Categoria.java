package ar.edu.ort.balance.balanceapp.dto;

import java.io.Serializable;

import ar.edu.ort.balance.balanceapp.utils.TipoMovimiento;

public class Categoria implements Serializable {
    private int id;
    private String nombre;
    private String fecha;
    private double total;
    private TipoMovimiento tipoMovimiento;

    public Categoria() {}

    public Categoria(int id, String nombre, String fecha, double total, TipoMovimiento tipoMovimiento) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.total = total;
        this.tipoMovimiento = tipoMovimiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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
}
