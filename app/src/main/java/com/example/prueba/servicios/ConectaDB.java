package com.example.prueba.servicios;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.prueba.interfaces.ConstantesApp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConectaDB extends SQLiteOpenHelper {

    private final Context context;

    public ConectaDB(@Nullable Context context) {
        super(context, ConstantesApp.BDD, null, ConstantesApp.VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear todas las tablas
        db.execSQL(ConstantesApp.TABLA_CATEGORIAS_DDL);
        db.execSQL(ConstantesApp.TABLA_PRODUCTOS_DDL);
        db.execSQL(ConstantesApp.TABLA_PEDIDOS_DDL);
        db.execSQL(ConstantesApp.TABLA_CLIENTES_DDL);
        db.execSQL(ConstantesApp.TABLA_DETALLES_PEDIDOS_DDL);

        // Ejecutar archivos de inserción de datos
        ejecutarSQLDesdeArchivo(db, "cat.sql");
        ejecutarSQLDesdeArchivo(db, "produc.sql");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Eliminar tablas si existen
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesApp.TABLA_DETALLES_PEDIDOS + ";");
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesApp.TABLA_CLIENTES + ";");
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesApp.TABLA_PEDIDOS + ";");
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesApp.TABLA_CATEGORIAS + ";");
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesApp.TABLA_PRODUCTOS + ";");

        // Crear tablas de nuevo
        onCreate(db);
    }

    private void ejecutarSQLDesdeArchivo(SQLiteDatabase db, String archivo) {
        try (InputStream is = context.getAssets().open(archivo);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            StringBuilder sql = new StringBuilder();
            String linea;

            while ((linea = reader.readLine()) != null) {
                sql.append(linea);
                // Ejecutar cada sentencia SQL al final de un comando SQL
                if (linea.trim().endsWith(";")) {
                    db.execSQL(sql.toString());
                    sql.setLength(0); // Limpiar el StringBuilder
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
