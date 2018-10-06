package ar.edu.ort.balance.balanceapp.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {
    private int id;
    private String nombre;
    private String apellido;
    private String pass;
    private String mail;
    private List<Movimiento> movimientos;
    private List<Categoria> categorias;

    public Usuario() {
        movimientos = new ArrayList<>();
        categorias = new ArrayList<>();
    }

    public Usuario(String nombre, String apellido, String mail, String pass, List<Movimiento> movimientos, List<Categoria> categorias) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.pass = pass;
        this.mail = mail;
        this.movimientos = movimientos;
        this.categorias = categorias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }

    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
}
