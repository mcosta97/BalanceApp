package ar.edu.ort.balance.balanceapp.utils;

public class DbConst {
    //Parametros para configuracion de la Base de Datos
    public static final String NOMBRE_BASE = "BalanceApp";
    public static final int VERSION_ACTUAL = 1;

    //Parametros de la Tabla CATEGORIA
    public static final String TABLA_CATEGORIA = "Categoria";
    public static final String CAMPO_CATEGORIA_ID = "CATEGORIA_ID";
    public static final String CAMPO_CATEGORIA_USUARIO_ID = "USUARIO_ID";
    public static final String CAMPO_CATEGORIA_NOMBRE = "NOMBRE";
    public static final String CAMPO_CATEGORIA_VALOR = "VALOR";
    public static final String CAMPO_CATEGORIA_TIPO = "TIPO";

    //Parametros de la Tabla USUARIO
    public static final String TABLA_USUARIO = "Usuario";
    public static final String CAMPO_USUARIO_ID = "USUARIO_ID";
    public static final String CAMPO_USUARIO_NOMBRE = "NOMBRE";
    public static final String CAMPO_USUARIO_APELLIDO = "APELLIDO";
    public static final String CAMPO_USUARIO_PASS = "PASS";
    public static final String CAMPO_USUARIO_MAIL = "MAIL";

    //Parametros de la Tabla MOVIMIENTO
    public static final String TABLA_MOVIMIENTO = "Movimiento";
    public static final String CAMPO_MOVIMIENTO_ID = "MOVIMIENTO_ID";
    public static final String CAMPO_MOVIMIENTO_USUARIO_ID = "USUARIO_ID";
    public static final String CAMPO_MOVIMIENTO_CATEGORIA_ID = "CATEGORIA_ID";
    public static final String CAMPO_MOVIMIENTO_NOMBRE = "NOMBRE";
    public static final String CAMPO_MOVIMIENTO_FECHA = "FECHA";
    public static final String CAMPO_MOVIMIENTO_VALOR = "VALOR";

}
