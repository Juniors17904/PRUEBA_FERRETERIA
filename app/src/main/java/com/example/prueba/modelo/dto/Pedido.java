package com.example.prueba.modelo.dto;

import androidx.annotation.NonNull;

import java.util.Date;

public class Pedido {
    private int id; // ID del pedido
    private int clienteId; // ID del cliente que realizó el pedido
    private Date fechaPedido; // Fecha en que se realizó el pedido

    public Pedido() {
    }

    public Pedido(int id, int clienteId, Date fechaPedido) {
        this.id = id;
        this.clienteId = clienteId;
        this.fechaPedido = fechaPedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    @NonNull
    @Override
    public String toString() {
        return "Pedido ID: " + getId() + ", Cliente ID: " + getClienteId(); // Representación simple del pedido
    }
}
