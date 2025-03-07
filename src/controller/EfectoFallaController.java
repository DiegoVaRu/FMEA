package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EfectoFallaController {
    
    @FXML
    Label efectoFalla;

    public void setEfectoFalla(String efectoFalla) {
        this.efectoFalla.setText(efectoFalla);
    }

    @FXML
    private void close() throws IOException {
        Stage stage = (Stage) efectoFalla.getScene().getWindow();
        stage.close();
    }

}
