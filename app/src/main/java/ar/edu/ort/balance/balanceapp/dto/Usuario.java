package ar.edu.ort.balance.balanceapp.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {
    private int id;
    private String nombre;
    private String apellido;
    private String pass;
    private String mail;
    private ArrayList<Movimiento> movimientos;
    private ArrayList<Categoria> categorias;

    public Usuario() {
        movimientos = new ArrayList<>();
        categorias = new ArrayList<>();
    }

    public Usuario(String nombre, String apellido, String mail, String pass, ArrayList<Movimiento> movimientos, ArrayList<Categoria> categorias) {
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

    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(ArrayList<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(ArrayList<Categoria> categorias) {
        this.categorias = categorias;
    }
}
