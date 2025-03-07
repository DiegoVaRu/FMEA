package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import model.ModoFalla;

public class GraficaFallasController {

    @FXML
    private BarChart<String, Double> grafica;

    public void setInitials(ArrayList<ModoFalla> modosFallas) {
        XYChart.Series<String, Double> serie = new XYChart.Series<>();
        serie.setName("NPR");
        for (ModoFalla m : modosFallas) {
            serie.getData().add(new XYChart.Data<>(m.getNombre(), m.getNPR()));
        }

        grafica.getData().add(serie);
    }

    @FXML
    private void close() throws IOException {
        Stage stage = (Stage) grafica.getScene().getWindow();
        stage.close();
    }
    
}
