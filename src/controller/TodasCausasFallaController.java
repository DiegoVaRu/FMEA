package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class TodasCausasFallaController {

    @FXML
    private ListView<String> causasListView;

    public void setCausasFallaList(ArrayList<String> causasList) {
        causasListView.getItems().addAll(causasList);
    }

    @FXML
    private void close() throws IOException {
        Stage stage = (Stage) causasListView.getScene().getWindow();
        stage.close();
    }
    
}
