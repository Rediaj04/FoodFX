package com.puig.models;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<Comida> comidas;
    private double total;

    public Pedido() {
        this.comidas = new ArrayList<>();
        this.total = 0.0;
    }

    public void agregarComida(Comida comida) {
        comidas.add(comida);
        total += comida.getPrecio();
    }

    public List<Comida> getComidas() {
        return comidas;
    }

    public double getTotal() {
        return total;
    }

    public void limpiarPedido() {
        comidas.clear();
        total = 0.0;
    }
}