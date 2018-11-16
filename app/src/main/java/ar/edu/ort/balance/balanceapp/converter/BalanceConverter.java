package ar.edu.ort.balance.balanceapp.converter;

import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.edu.ort.balance.balanceapp.dto.Categoria;
import ar.edu.ort.balance.balanceapp.dto.Movimiento;
import ar.edu.ort.balance.balanceapp.utils.TipoMovimiento;

public class BalanceConverter {

    private static Gson gson = new Gson();

    @TypeConverter
    public static List<Movimiento> movimientoFromString(String value) {
        Type listType = new TypeToken<ArrayList<Movimiento>>() {}.getType();
        return gson.fromJson(value, listType);
    }

    @TypeConverter
    public static String movimientoFromList(List<Movimiento> list) {
        String json = gson.toJson(list);
        return json;
    }

    @TypeConverter
    public static List<Categoria> categoriaFromString(String value) {
        Type listType = new TypeToken<ArrayList<Categoria>>() {}.getType();
        return gson.fromJson(value, listType);
    }

    @TypeConverter
    public static String categoriaFromList(List<Categoria> list) {
        String json = gson.toJson(list);
        return json;
    }

    @TypeConverter
    public static TipoMovimiento tipoMovimientoFromInt(int value) {
        return TipoMovimiento.values()[value];
    }

    @TypeConverter
    public static int intFromTipoMovimiento(TipoMovimiento tipoMovimiento) {
        return tipoMovimiento.ordinal();
    }

    @TypeConverter
    public static Date dateFromString(String value) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(value);
        } catch (ParseException e) {}
        return date;
    }

    @TypeConverter
    public static String stringFromDate(Date value) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(value);
    }
}
