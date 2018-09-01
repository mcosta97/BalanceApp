package ar.edu.ort.balance.balanceapp.dto;

import java.util.ArrayList;

public class Usuario {
    private int id;
    private String user;
    private String pass;
    private String mail;
    private ArrayList<Movimiento> movimientos;
    private ArrayList<Categoria> categorias;

    public Usuario() {
        movimientos = new ArrayList<>();
        categorias = new ArrayList<>();
    }

    public Usuario(String user, String pass, String mail, ArrayList<Movimiento> movimientos, ArrayList<Categoria> categorias) {
        this.user = user;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

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
