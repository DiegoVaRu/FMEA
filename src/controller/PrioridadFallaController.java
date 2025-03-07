package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class PrioridadFallaController {

    @FXML
    private ListView<String> causasListView;
    @FXML
    private Label fallaLabel;
    @FXML
    private Label nprLabel;

    public void setLabels(String nombre, double npr) {
        fallaLabel.setText("Falla Crítica: " + nombre);
        nprLabel.setText("NPR: " + npr);
    }

    public void setCausasFallaList(ArrayList<String> causasList) {
        causasListView.getItems().addAll(causasList);
    }

    @FXML
    private void close() throws IOException {
        Stage stage = (Stage) causasListView.getScene().getWindow();
        stage.close();
    }
    
}
