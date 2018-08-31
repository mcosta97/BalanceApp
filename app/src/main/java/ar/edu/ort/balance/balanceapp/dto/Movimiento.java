package ar.edu.ort.balance.balanceapp.dto;

public class Movimiento {
    private String nombre;
    private String descripcion;
    private double valor;
    private Categoria categoria;

    public Movimiento() {}

    public Movimiento(String nombre, String descripcion, double valor, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valor = valor;
        this.categoria = categoria;
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
