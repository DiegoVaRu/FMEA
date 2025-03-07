package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import model.CausaFalla;
import model.ModoFalla;
import model.utilities.NPR;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CausasFallaController implements Initializable {
    
    @FXML
    ListView<String> causasListView;
    @FXML
    Label nprLabel;
    @FXML
    Label nprMayor;

    private ArrayList<Double> npr = new ArrayList<Double>();
    private ArrayList<CausaFalla> causasFallaArrayList = new ArrayList<CausaFalla>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        causasListView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                String actualCausaFalla = causasListView.getSelectionModel().getSelectedItem();
                int index = causasListView.getSelectionModel().getSelectedIndex();
                if (actualCausaFalla != null) nprLabel.setText("NPR: " + npr.get(index) );
                else nprLabel.setText("");
            }            
            
        });

        double maxNpr = 0;
        for (CausaFalla c : causasFallaArrayList) {
            if (c.getNPR() > maxNpr) maxNpr = c.getNPR();

            System.out.println(c.getNPR());
        }
        for (CausaFalla c : causasFallaArrayList) {
            if (c.getNPR().equals(maxNpr))
                nprMayor.setText("Máxima Prioridad de Riesgo: " + c.getNombre());
            else nprMayor.setText("Máxima Prioridad de Riesgo: --");
        }
    }

    public void setCausasFalla(ArrayList<CausaFalla> causasFallas, ModoFalla modo) {
        causasListView.getItems().clear();
        for (CausaFalla c : causasFallas) {
            npr.add(NPR.calcNPR(modo.getGravedad(), c.getOcurrencia(), c.getOcurrencia()));
            c.setNPR(modo.getGravedad(), c.getOcurrencia(), c.getDeteccion());
            causasListView.getItems().add(c.getNombre());
        }
        causasFallaArrayList.addAll(causasFallas);
    }

    @FXML
    private void close() throws IOException {
        Stage stage = (Stage) causasListView.getScene().getWindow();
        stage.close();
    }

}
