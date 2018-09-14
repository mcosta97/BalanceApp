package ar.edu.ort.balance.balanceapp.dto;

import java.util.Date;

public class Movimiento {
    private int id;
    private String nombre;
    private Date fecha;
    private double valor;
    private Categoria categoria;

    public Movimiento() {}

    public Movimiento(int id, String nombre, Date fecha, String descripcion, double valor, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.valor = valor;
        this.categoria = categoria;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
