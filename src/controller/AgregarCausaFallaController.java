package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.CausaFalla;
import model.utilities.VALIDAR;

public class AgregarCausaFallaController implements Initializable {
    
    @FXML
    TextField inputNombre;
    @FXML
    ChoiceBox<Integer> inputOcurrencia;
    @FXML
    ChoiceBox<Integer> inputDeteccion;

    private AgregarModoFallaController agregarModoFallaController;
    private EditarModoFallaController editarModoFallaController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Integer[] oneToTen = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        inputOcurrencia.getItems().addAll(oneToTen);
        inputDeteccion.getItems().addAll(oneToTen);
    }

    public void setAgregarModoFallaController(AgregarModoFallaController agregarModoFallaController) {
        this.agregarModoFallaController = agregarModoFallaController;
    }
    public void setEditarModoFallaController(EditarModoFallaController editarModoFallaController) {
        this.editarModoFallaController = editarModoFallaController;
    }

    //ACTIONS <---
    @FXML
    private void aceptar() {
        if(!VALIDAR.estanTodosLosCampos(inputNombre, inputOcurrencia, inputDeteccion)) {
            VALIDAR.showError("Tienes que rellenar todos los campos.");
            return;
        }
        else if (agregarModoFallaController != null 
            && VALIDAR.existeIgualCausa(agregarModoFallaController.causasArrayList, inputNombre.getText())) {
            VALIDAR.showError("Ya existe una causa con ese nombre.");
            return;
        }
        else if (editarModoFallaController != null 
            && VALIDAR.existeIgualCausa(editarModoFallaController.causasArrayList, inputNombre.getText())) {
            VALIDAR.showError("Ya existe una causa con ese nombre.");
            return;
        }

        CausaFalla cf = new CausaFalla(null, 0, 0);
        cf.setNombre(inputNombre.getText());
        cf.setOcurrencia(inputOcurrencia.getValue());
        cf.setDeteccion(inputDeteccion.getValue());

        if(editarModoFallaController == null) {
            agregarModoFallaController.agregarCausaToArrayList(cf);
            agregarModoFallaController.refreshCausasList();
        } else {
            editarModoFallaController.agregarCausaToArrayList(cf);
            editarModoFallaController.refreshCausasList();
        }

        Stage stage = (Stage) inputNombre.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void close() {
        Stage stage = (Stage) inputNombre.getScene().getWindow();
        stage.close();
    }

}
