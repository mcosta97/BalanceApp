package ar.edu.ort.balance.balanceapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import ar.edu.ort.balance.balanceapp.dto.Categoria;
import ar.edu.ort.balance.balanceapp.dto.Movimiento;
import ar.edu.ort.balance.balanceapp.dto.Usuario;
import ar.edu.ort.balance.balanceapp.utils.TipoMovimiento;

/*
 * Esta clase es solo para generar datos para hacer pruebas sin persistencia
 */
public class RandomDataService {

    private static List<Usuario> usuarios;
    private static List<Categoria> categorias;
    private static List<Movimiento> gastos;
    private static List<Movimiento> ingresos;

    private static List<Usuario> getUsuarios() {
        if (usuarios == null) return generarUsuarios(10);
        return usuarios;
    }

    private static List<Movimiento> getGastos() {
        if (gastos == null) return generarMovimientos(TipoMovimiento.Egreso, 50);
        return gastos;
    }

    private static List<Categoria> getCategorias() {
        if (categorias == null) return generarCategorias(20);
        return categorias;
    }

    private static List<Movimiento> getIngresos() {
        if (ingresos == null) return generarMovimientos(TipoMovimiento.Ingreso, 50);
        return ingresos;
    }

    private static String[][] nombresLocos = new String[][] {
            {"Daniel","Carlos","Julia","Nicolas","Juan","Pepe","Roberto","Elsa","Juana","Micaela"},
            {"Gomez","Costa","Perez","Sandoval","Bernal","Diaz","Pereira","Scarincha","Ortiz","Soto"},
            {"ruben","milano","pelela","elmascapito","fafita","cintt","kisher","elsa","service","fault"},
            {"pereyra","kilern","delorto","pato","patero","fafera","arbusto","sinclair","volador","peyo"},
            {"gmail","hotmail","live","outlook","yahoo","ymail","tuhermana","sion","fibertel","arnet"}
    };

    private static String[][] nombresMovimientos = new String[][]{
            {"Globant","YPF","Hexacta"},{"Nafta","Aceite","ORT","Cine","Burger King","Subway"}
    };

    private static String[] nombreCategorias = new String[] {
            "Compras","Auto","Casa","Comer afuera","Servicios","Entretenimiento","Transporte","Inversion","Freelance","Trabajo","Changas"
    };

    public static List<Usuario> generarUsuarios(int cantidad) {
        List<Usuario> usuarios = new ArrayList<>();
        for(int i = 0; i < cantidad; i++) {
            Random r = new Random();
            Usuario u = new Usuario();
            u.setId(i);
            u.setNombre(nombresLocos[0][r.nextInt(nombresLocos[0].length)]);
            u.setApellido(nombresLocos[1][r.nextInt(nombresLocos[1].length)]);
            u.setMail(nombresLocos[2][r.nextInt(nombresLocos[2].length)] + "." + nombresLocos[3][r.nextInt(nombresLocos[3].length)] + "@" + nombresLocos[4][r.nextInt(nombresLocos[4].length)] + ".com");
            u.setCategorias(generarCategorias(10));
            List<Movimiento> movimientos = new ArrayList<>();
            movimientos.addAll(generarMovimientos(TipoMovimiento.Egreso, 20));
            movimientos.addAll(generarMovimientos(TipoMovimiento.Ingreso, 20));
            u.setMovimientos(movimientos);
            usuarios.add(u);
        }
        return usuarios;
    }

    public static List<Categoria> generarCategorias(int cantidad) {
        List<Categoria> categorias = new ArrayList<>();
        for(int i = 0; i < cantidad; i++) {
            Categoria c = new Categoria();
            Random r = new Random();
            c.setId(i);
            c.setNombre(nombreCategorias[r.nextInt(nombreCategorias.length)]);
            c.setTipoMovimiento(TipoMovimiento.values()[r.nextInt(TipoMovimiento.values().length)]);
            c.setTotal(r.nextDouble());
            categorias.add(c);
        }
        return categorias;
    }

    /**
     * Valores esperados Tipos: 0-Gastos | 1-Ingresos
     * @param tipo
     * @param cantidad
     * @return
     */
    public static List<Movimiento> generarMovimientos(TipoMovimiento tipo, int cantidad) {
        List<Movimiento> movimientos = new ArrayList<>();
        for(int i = 0; i < cantidad; i++) {
            Movimiento m = new Movimiento();
            Random r = new Random();
            m.setId(i);
            m.setValor(r.nextDouble());
            m.setFecha(new Date());
            m.setCategoria(getCategorias().get(r.nextInt(getCategorias().size())));
            m.setNombre(nombresMovimientos[tipo.ordinal()][r.nextInt(nombresMovimientos.length)]);
            movimientos.add(m);
        }
        return movimientos;
    }

}
