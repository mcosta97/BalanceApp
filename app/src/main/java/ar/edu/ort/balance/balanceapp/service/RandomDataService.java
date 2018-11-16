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

    private static List<Categoria> categorias;
    private static List<Movimiento> gastos;
    private static List<Movimiento> ingresos;

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

    private static String[][] nombresMovimientos = new String[][]{
            {"Globant","YPF","Hexacta"},{"Nafta","Aceite","ORT","Cine","Burger King","Subway"}
    };

    private static String[] nombreCategorias = new String[] {
            "Compras","Auto","Casa","Comer afuera","Servicios","Entretenimiento","Transporte","Inversion","Freelance","Trabajo","Changas"
    };

    public static List<Categoria> generarCategorias(int cantidad) {
        List<Categoria> categorias = new ArrayList<>();
        for(Long i = 0L; i < cantidad; i++) {
            Random r = new Random();
            categorias.add(new Categoria(i, nombreCategorias[r.nextInt(nombreCategorias.length)], r.nextDouble(), TipoMovimiento.values()[r.nextInt(TipoMovimiento.values().length)]));
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
        for(Long i = 0L; i < cantidad; i++) {
            Random r = new Random();
            movimientos.add(new Movimiento(i, nombresMovimientos[tipo.ordinal()][r.nextInt(nombresMovimientos.length)], new Date(), r.nextInt(1000), i.intValue()));
        }
        return movimientos;
    }

}
