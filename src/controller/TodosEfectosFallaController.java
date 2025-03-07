package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class TodosEfectosFallaController {

    @FXML
    private ListView<String> efectosListView;

    public void setEfectosFallaList(ArrayList<String> efectosList) {
        efectosListView.getItems().addAll(efectosList);
    }

    @FXML
    private void close() throws IOException {
        Stage stage = (Stage) efectosListView.getScene().getWindow();
        stage.close();
    }
    
}
