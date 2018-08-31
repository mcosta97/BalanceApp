package ar.edu.ort.balance.balanceapp.utils;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class BalanceException extends Exception {

    /*
    Manejo de excepciones:
    Se debe usar la que tiene mensaje unicamente cuando sea un error que queremos exponer en forma de Excepcion
    La que tiene mensaje y causa va siempre que estemos atrapando cualquier otro tipo de Excepcion para re-lanzarlo
     */

    /**
     * Excepcion con mensaje personalizado
     * @param mensaje
     */
    public BalanceException(String mensaje) {
        this(mensaje, null);
    }

    /**
     * Excepcion con mensaje personalizado y causa de error
     * @param mensaje
     * @param causa
     */
    public BalanceException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

}
