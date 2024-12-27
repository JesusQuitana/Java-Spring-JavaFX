package com.jq.tienda.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jq.tienda.model.Producto;
import com.jq.tienda.service.ProductoServicio;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

@Component
public class IndexController implements Initializable{

    @Autowired
    private ProductoServicio productoServicio;

    @FXML private TableView<Producto> table;
    @FXML private TableColumn<Producto, String> nameColumn;
    @FXML private TableColumn<Producto, String> descripColumn;
    @FXML private TableColumn<Producto, Integer> precioColumn;
    @FXML private TextField name;
    @FXML private TextField precio;
    @FXML private TextArea descrip;
    @FXML private Button save;
    @FXML private Button update;
    @FXML private Button delete;
    @FXML private Button clean;

    private Integer id;
    private ObservableList<Producto> listaProducto = FXCollections.observableArrayList();
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        config();
        listarProductos();
    }

    private void config() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        descripColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
    }
    
    public void save() {
        Producto producto = new Producto();
        String nombre = name.getText();
        String descripcion = descrip.getText();
        Double precy;
        try {
            precy = Double.parseDouble(precio.getText());
                    
            if(nombre == "" || descripcion== "" || precy == 0) {
                logger.info("Los Campos son obligatorios");
                mostrarMensaje("Error", "Los Campos son Obligatorios");
            }
            else {
                producto.setNombre(nombre);
                producto.setDescripcion(descripcion);
                producto.setPrecio(precy);
                if(this.id != null) {
                    logger.info("Actualizando Producto" + producto.toString());
                    producto.setId(this.id);
                    this.productoServicio.save(producto);
                    mostrarMensaje("Exito", "Producto Actualizado");
                    clean();
                }
                else {
                    logger.info("Agregando Producto" + producto.toString());
                    this.productoServicio.save(producto);
                    mostrarMensaje("Exito", "Producto Agregado");
                    clean();
                }
            }
        }
        catch(Exception e) {
            logger.info("El valor debe ser Numerico");
            mostrarMensaje("Error", "El valor debe ser numerico");
            clean();
        }
    }

    public void delete() {
        if(this.id == null) {
            mostrarMensaje("Error", "Seleccione un Producto");
        }
        else {
            Producto producto = this.productoServicio.find(this.id);
            logger.info("Eliminando Producto" + producto.toString());
            this.productoServicio.delete(producto);
            mostrarMensaje("Exito", "Producto Eliminado");
            clean();
        }
    }

    public void select() {
        Producto producto = table.getSelectionModel().getSelectedItem();
        if(producto != null) {
            this.id = producto.getId();
            name.setText(producto.getNombre());
            precio.setText(String.valueOf(producto.getPrecio()));
            descrip.setText(producto.getDescripcion());
        }
    }

    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    private void listarProductos() {
        logger.info("Listando Productos");
        listaProducto.clear();
        listaProducto.addAll(this.productoServicio.findAll());
        table.setItems(listaProducto);
    }
    public void clean() {
        name.setText("");
        descrip.setText("");
        precio.setText("");
        name.requestFocus();
        listarProductos();
    }
}
