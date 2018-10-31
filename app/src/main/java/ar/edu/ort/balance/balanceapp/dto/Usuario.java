package ar.edu.ort.balance.balanceapp.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario implements Serializable {

    @PrimaryKey
    private Long id;

    @ColumnInfo(name="Nombre")
    private String nombre;

    @ColumnInfo(name="Apellido")
    private String apellido;

    @ColumnInfo(name="Pass")
    private String pass;

    @ColumnInfo(name="Mail")
    private String mail;

    @Ignore
    private List<Categoria> categorias;

    @Ignore
    public Usuario(String nombre, String apellido, String mail, String pass, List<Categoria> categorias) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.pass = pass;
        this.mail = mail;
        this.categorias = categorias;
    }

    public Usuario() {
        categorias = new ArrayList<>();
    }
    
    public Usuario(Long id, String nombre, String apellido, String pass, String mail) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.pass = pass;
        this.mail = mail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
}
