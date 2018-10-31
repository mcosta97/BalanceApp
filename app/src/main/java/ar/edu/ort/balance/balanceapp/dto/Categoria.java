package ar.edu.ort.balance.balanceapp.dto;

import java.io.Serializable;
import java.util.List;

import ar.edu.ort.balance.balanceapp.utils.TipoMovimiento;

public class Categoria implements Serializable {

    private static final long serialVersionUID = 1234567863323467L;

    private Long id;
    private String nombre;
    private double total;
    private TipoMovimiento tipoMovimiento;
    private List<Movimiento> movimientos;

    public Categoria() {}

    public Categoria(Long id, String nombre, double total, TipoMovimiento tipoMovimiento, List<Movimiento> movimientos) {
        this.id = id;
        this.nombre = nombre;
        this.total = total;
        this.tipoMovimiento = tipoMovimiento;
        this.movimientos = movimientos;
    }

    public Categoria(Long id, String nombre, double total, TipoMovimiento tipoMovimiento) {
        this.id = id;
        this.nombre = nombre;
        this.total = total;
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
}
