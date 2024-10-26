package com.example.prueba.modelo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.prueba.interfaces.ConstantesApp;
import com.example.prueba.modelo.dto.Categoria;
import com.example.prueba.servicios.ConectaDB;

import java.util.ArrayList;
import java.util.List;

// Clase para manejar operaciones sobre la tabla de Categorías
public class CategoriaDAO {
    private SQLiteDatabase db;

    // Constructor que inicializa la conexión a la base de datos
    public CategoriaDAO(Context context) {
        db = new ConectaDB(context).getWritableDatabase();
    }

    // Método para insertar una nueva categoría
    public String insertar(Categoria categoria) {
        String resp = "";
        ContentValues registro = new ContentValues();
        registro.put("Nombre", categoria.getNombre());

        try {
            db.insertOrThrow(ConstantesApp.TABLA_CATEGORIAS, null, registro);
        } catch (SQLException ex) {
            resp = ex.getMessage();
        }
        return resp;
    }

    // Método para obtener una lista de todas las categorías
    public List<Categoria> getList() {
        List<Categoria> lista = new ArrayList<>();
        String cadSQL = "SELECT * FROM " + ConstantesApp.TABLA_CATEGORIAS + ";";
        Cursor c = db.rawQuery(cadSQL, null);

        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    Categoria categoria = new Categoria();
                    categoria.setId(c.getInt(c.getColumnIndexOrThrow("ID")));
                    categoria.setNombre(c.getString(c.getColumnIndexOrThrow("Nombre")));
                    lista.add(categoria);
                } while (c.moveToNext());
            }
            c.close();
        }
        return lista;
    }
}
