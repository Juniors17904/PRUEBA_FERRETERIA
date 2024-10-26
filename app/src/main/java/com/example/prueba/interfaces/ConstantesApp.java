package com.example.prueba.interfaces;

public interface ConstantesApp {
    String BDD = "ferreteria.db";
    int VERSION = 1;

    // Tabla Categorias
    String TABLA_CATEGORIAS = "categorias";
    String TABLA_CATEGORIAS_DDL = "CREATE TABLE categorias (\n" +
            "    id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,\n" +
            "    nombre VARCHAR(60) NOT NULL\n" +
            ");\n";

    // Tabla Productos
    String TABLA_PRODUCTOS = "productos";
    String TABLA_PRODUCTOS_DDL = "CREATE TABLE productos (\n" +
            "    id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,\n" +
            "    nombre VARCHAR(60) UNIQUE NOT NULL,\n" +
            "    descripcion VARCHAR(150),\n" +
            "    precio NUMERIC(10, 2) NOT NULL,\n" +
            "    stock INTEGER NOT NULL,\n" +
            "    categoriaId INTEGER REFERENCES categorias(id)\n" +
            ");\n";

    // Tabla Pedidos
    String TABLA_PEDIDOS = "pedidos";
    String TABLA_PEDIDOS_DDL = "CREATE TABLE pedidos (\n" +
            "    id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,\n" +
            "    clienteID INTEGER,\n" +
            "    fechaPedido DATE\n" +
            ");\n";

    // Tabla DetallesPedidos
    String TABLA_DETALLES_PEDIDOS = "detalles_pedidos";
    String TABLA_DETALLES_PEDIDOS_DDL = "CREATE TABLE detalles_pedidos (\n" +
            "    id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,\n" +
            "    idPedido INTEGER REFERENCES pedidos(id) NOT NULL,\n" +
            "    idProducto INTEGER REFERENCES productos(id) NOT NULL,\n" +
            "    cantidad INTEGER NOT NULL,\n" +
            "    precioUnit NUMERIC(10, 2) NOT NULL\n" +
            ");\n";


    // Tabla Clientes
    String TABLA_CLIENTES = "clientes";
    String TABLA_CLIENTES_DDL = "CREATE TABLE clientes (\n" +
            "    id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,\n" +
            "    nombre VARCHAR(60) NOT NULL,\n" +
            "    correo VARCHAR(100) UNIQUE NOT NULL,\n" +
            "    telefono VARCHAR(15),\n" +
            "    direccion VARCHAR(250)\n" +
            ");\n";
}
