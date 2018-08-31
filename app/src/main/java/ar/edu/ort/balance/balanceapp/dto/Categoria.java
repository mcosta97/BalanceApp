package ar.edu.ort.balance.balanceapp.dto;

import ar.edu.ort.balance.balanceapp.utils.TipoMovimiento;

public class Categoria {
    private int id;
    private String nombre;
    private String descripcion;
    private double total;
    private TipoMovimiento tipoMovimiento;

    public Categoria() {}

    public Categoria(int id, String nombre, String descripcion, double total, TipoMovimiento tipoMovimiento) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
