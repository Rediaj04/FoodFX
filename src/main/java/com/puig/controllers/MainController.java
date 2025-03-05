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
    private VBox pedidosContent;

    @FXML
    private VBox estadisticasContent;

    @FXML
    private TextField nombreComidaField;

    @FXML
    private TextField precioComidaField;

    @FXML
    private TextArea descripcionComidaField;

    @FXML
    private ListView<Comida> comidasListView;

    @FXML
    private ListView<Comida> pedidosListView;

    @FXML
    private PieChart estadisticasPieChart;

    @FXML
    private Label totalPedidoLabel;

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
                new Comida("Ensalada", 4.99, "Ensalada fresca")
        );
    }

    @FXML
    private void showHome() {
        homeContent.setVisible(true);
        comidasContent.setVisible(false);
        pedidosContent.setVisible(false);
        estadisticasContent.setVisible(false);
    }

    @FXML
    private void showComidas() {
        homeContent.setVisible(false);
        comidasContent.setVisible(true);
        pedidosContent.setVisible(false);
        estadisticasContent.setVisible(false);
    }

    @FXML
    private void showPedidos() {
        homeContent.setVisible(false);
        comidasContent.setVisible(false);
        pedidosContent.setVisible(true);
        estadisticasContent.setVisible(false);
    }

    @FXML
    private void showEstadisticas() {
        homeContent.setVisible(false);
        comidasContent.setVisible(false);
        pedidosContent.setVisible(false);
        estadisticasContent.setVisible(true);
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
    private void agregarAlPedido() {
        Comida comidaSeleccionada = comidasListView.getSelectionModel().getSelectedItem();
        if (comidaSeleccionada != null) {
            pedidoActual.agregarComida(comidaSeleccionada);
            pedidosListView.getItems().add(comidaSeleccionada);
            totalPedidoLabel.setText("Total: $" + String.format("%.2f", pedidoActual.getTotal()));
        }
    }

    @FXML
    private void confirmarPedido() {
        if (pedidoActual.getComidas().isEmpty()) {
            mostrarError("No hay comidas en el pedido");
            return;
        }

        mostrarMensaje("Pedido confirmado. Total: $" + String.format("%.2f", pedidoActual.getTotal()));
        historialPedidos.add(pedidoActual);
        pedidoActual = new Pedido();
        pedidosListView.getItems().clear();
        totalPedidoLabel.setText("Total: $0.00");
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