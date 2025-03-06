package com.puig.controllers;

import com.puig.models.Comida;
import com.puig.models.Pedido;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.chart.PieChart;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MainController {

    @FXML
    private VBox homeContent;

    @FXML
    private VBox comidasContent;

    @FXML
    private VBox carritoContent;

    @FXML
    private VBox estadisticasContent;

    @FXML
    private VBox adminContent;

    @FXML
    private TextField nombreComidaField;

    @FXML
    private TextField precioComidaField;

    @FXML
    private TextArea descripcionComidaField;

    @FXML
    private ListView<Comida> comidasListView;

    @FXML
    private ListView<Comida> carritoListView;

    @FXML
    private ListView<String> cuponesListView;

    @FXML
    private ListView<Comida> favoritosListView;

    @FXML
    private ListView<Comida> masPedidosListView;

    @FXML
    private PieChart estadisticasPieChart;

    @FXML
    private Label totalCarritoLabel;

    @FXML
    private ImageView logoImage;

    private Pedido pedidoActual = new Pedido();
    private List<Pedido> historialPedidos = new ArrayList<>();

    @FXML
    private void initialize() {
        logoImage.setImage(new Image(getClass().getResourceAsStream("/com/puig/images/logo.png")));
        comidasListView.getItems().addAll(
                new Comida("Hamburguesa", 5.99, "Hamburguesa clásica con queso"),
                new Comida("Pizza", 8.99, "Pizza de pepperoni"),
                new Comida("Ensalada", 4.99, "Ensalada fresca"),
                new Comida("Tacos", 3.99, "Tacos de carne asada"),
                new Comida("Sushi", 12.99, "Sushi variado"),
                new Comida("Pasta", 7.99, "Pasta alfredo"),
                new Comida("Pollo Frito", 6.99, "Pollo frito crujiente"),
                new Comida("Burrito", 5.49, "Burrito de pollo"),
                new Comida("Sopa", 4.49, "Sopa de pollo"),
                new Comida("Sandwich", 4.99, "Sandwich de jamón y queso"),
                new Comida("Helado", 2.99, "Helado de vainilla"),
                new Comida("Brownie", 3.49, "Brownie de chocolate"),
                new Comida("Café", 1.99, "Café americano")
        );

        cuponesListView.getItems().addAll(
                "10% de descuento en tu primer pedido",
                "2x1 en hamburguesas los viernes",
                "15% de descuento en pedidos mayores a $20"
        );

        favoritosListView.getItems().addAll(
                new Comida("Pizza", 8.99, "Pizza de pepperoni"),
                new Comida("Sushi", 12.99, "Sushi variado"),
                new Comida("Hamburguesa", 5.99, "Hamburguesa clásica con queso")
        );

        masPedidosListView.getItems().addAll(
                new Comida("Hamburguesa", 5.99, "Hamburguesa clásica con queso"),
                new Comida("Pizza", 8.99, "Pizza de pepperoni"),
                new Comida("Tacos", 3.99, "Tacos de carne asada")
        );
    }

    @FXML
    private void showHome() {
        homeContent.setVisible(true);
        comidasContent.setVisible(false);
        carritoContent.setVisible(false);
        estadisticasContent.setVisible(false);
        adminContent.setVisible(false);
    }

    @FXML
    private void showComidas() {
        homeContent.setVisible(false);
        comidasContent.setVisible(true);
        carritoContent.setVisible(false);
        estadisticasContent.setVisible(false);
        adminContent.setVisible(false);
    }

    @FXML
    private void showCarrito() {
        homeContent.setVisible(false);
        comidasContent.setVisible(false);
        carritoContent.setVisible(true);
        estadisticasContent.setVisible(false);
        adminContent.setVisible(false);
    }

    @FXML
    private void showEstadisticas() {
        homeContent.setVisible(false);
        comidasContent.setVisible(false);
        carritoContent.setVisible(false);
        estadisticasContent.setVisible(true);
        adminContent.setVisible(false);
    }

    @FXML
    private void showAdmin() {
        homeContent.setVisible(false);
        comidasContent.setVisible(false);
        carritoContent.setVisible(false);
        estadisticasContent.setVisible(false);
        adminContent.setVisible(true);
    }

    @FXML
    private void agregarComida() {
        String nombre = nombreComidaField.getText();
        String precioText = precioComidaField.getText();
        String descripcion = descripcionComidaField.getText();

        if (!Pattern.matches("\\d+(\\.\\d{1,2})?", precioText)) {
            mostrarError("El precio debe ser un número válido (ej: 5.99)");
            return;
        }

        double precio = Double.parseDouble(precioText);
        Comida nuevaComida = new Comida(nombre, precio, descripcion);
        comidasListView.getItems().add(nuevaComida);

        nombreComidaField.clear();
        precioComidaField.clear();
        descripcionComidaField.clear();
    }

    @FXML
    private void agregarAlCarrito() {
        Comida comidaSeleccionada = comidasListView.getSelectionModel().getSelectedItem();
        if (comidaSeleccionada != null) {
            pedidoActual.agregarComida(comidaSeleccionada);
            carritoListView.getItems().add(comidaSeleccionada);
            totalCarritoLabel.setText("Total: $" + String.format("%.2f", pedidoActual.getTotal()));
        }
    }

    @FXML
    private void confirmarPedido() {
        if (pedidoActual.getComidas().isEmpty()) {
            mostrarError("No hay comidas en el carrito");
            return;
        }

        mostrarMensaje("Pedido confirmado. Total: $" + String.format("%.2f", pedidoActual.getTotal()));
        historialPedidos.add(pedidoActual);
        pedidoActual = new Pedido();
        carritoListView.getItems().clear();
        totalCarritoLabel.setText("Total: $0.00");
    }

    @FXML
    private void actualizarEstadisticas() {
        Map<String, Long> conteoComidas = historialPedidos.stream()
                .flatMap(pedido -> pedido.getComidas().stream())
                .collect(Collectors.groupingBy(Comida::getNombre, Collectors.counting()));

        estadisticasPieChart.getData().clear();
        conteoComidas.forEach((nombre, conteo) -> estadisticasPieChart.getData().add(new PieChart.Data(nombre, conteo)));
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}