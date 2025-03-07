package model.utilities;

import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.CausaFalla;
import model.ModoFalla;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

public class VALIDAR {

    public static boolean hayItems(ArrayList<?> lista) {
        if (lista.isEmpty()) return false;
        return true;
    }
    public static boolean hayItems(ListView<?> lista) {
        if (lista.getItems().isEmpty()) return false;
        return true;
    }

    public static boolean estaSeleccionado(ListView<?> lista) {
        if (lista.getSelectionModel().getSelectedItem() == null) return false;
        return true;
    }

    public static boolean estanTodosLosCampos(TextField inputNombre, ChoiceBox<Integer> inputGravedad, ChoiceBox<String> inputEquipo, DatePicker inputFecha, TextArea inputAcciones, TextField inputEfectoFalla, ListView<String> causasListView) {
        if (inputNombre.getText().isEmpty() 
            || inputGravedad.getValue() == null 
            || inputEquipo.getValue() == null 
            || inputFecha.getValue() == null 
            || inputAcciones.getText().isEmpty() 
            || inputEfectoFalla.getText().isEmpty() 
            || causasListView.getItems().isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean existeIgual(ArrayList<ModoFalla> modosFalla, String nombre) {
        for (ModoFalla m : modosFalla) {
            if (nombre.equals(m.getNombre()))
                return true;
        }
        return false;
    }
    public static boolean existeIgualCausa(ArrayList<CausaFalla> causasFalla, String nombre) {
        for (CausaFalla c : causasFalla) {
            if (nombre.equals(c.getNombre()))
                return true;
        }
        return false;
    }


    public static boolean estanTodosLosCampos(TextField inputNombre, ChoiceBox<Integer> inputOcurrencia, ChoiceBox<Integer> inputDeteccion) {
        if (inputNombre.getText().isEmpty() 
            || inputOcurrencia.getValue() == null 
            || inputDeteccion.getValue() == null) {
            return false;
        }
        return true;
    }

    public static void showError(String error) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setContentText(error);
        alerta.show();
    }
    public static void showInfo(String error) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("Information");
        alerta.setContentText(error);
        alerta.show();
    }
    
}
