package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.CausaFalla;
import model.EfectoFalla;
import model.ModoFalla;
import model.utilities.DATA;
import model.utilities.VALIDAR;

public class EditarModoFallaController implements Initializable {

    @FXML
    TextField inputNombre;
    @FXML
    ChoiceBox<Integer> inputGravedad;
    @FXML
    ChoiceBox<String> inputEquipo;
    @FXML
    DatePicker inputFecha;
    @FXML
    TextArea inputAcciones;
    @FXML
    TextField inputEfectoFalla;
    @FXML
    ListView<String> causasListView;

    public ArrayList<CausaFalla> causasArrayList = new ArrayList<CausaFalla>();
    private ArrayList<String> causasFallas = new ArrayList<String>();

    private ModoFallaController modoFallaController;
    private int index;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Integer[] oneToTen = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        String[] equipos = {
            "Equipo #1",
            "Equipo #2",
            "Equipo #3",
            "Equipo #4"
        };

        inputGravedad.getItems().addAll(oneToTen);
        inputEquipo.getItems().addAll(equipos);
    }

    public void setModoFallaController(ModoFallaController modoFallaController, int index) {
        this.modoFallaController = modoFallaController;
        this.index = index;
    }

    public void setInitials(String nombre, int gravedad, String equipo, LocalDate fecha, String acciones, String efectoFalla, ArrayList<String> causaFallas, ArrayList<CausaFalla> causasArrayList) {
        inputNombre.setText(nombre);
        inputGravedad.setValue(gravedad);
        inputEquipo.setValue(equipo);
        inputFecha.setValue(fecha);
        inputAcciones.setText(acciones);
        inputEfectoFalla.setText(efectoFalla);
        causasListView.getItems().addAll(causaFallas);
        this.causasArrayList.addAll(causasArrayList);
    }

    //ACTIONS <---
    @FXML
    private void aceptar() throws IOException {
        if(!VALIDAR.estanTodosLosCampos(inputNombre, inputGravedad, inputEquipo, inputFecha, inputAcciones, inputEfectoFalla, causasListView)) {
            VALIDAR.showError("Tienes que rellenar todos los campos.");
            return;
        }

        ModoFalla mf = new ModoFalla(null, 0, null, null, null, null, causasArrayList);
        mf.setNombre(inputNombre.getText());
        mf.setGravedad(inputGravedad.getValue());
        mf.setEquipo(inputEquipo.getValue());
        mf.setFecha(inputFecha.getValue());
        mf.setAcciones(inputAcciones.getText());
        mf.setEfectoFalla(new EfectoFalla(inputEfectoFalla.getText()));
        mf.setCausasFalla(causasArrayList);

        modoFallaController.editarModoFallaToArrayList(index, mf);
        modoFallaController.refreshModosList();
        DATA.saveData(modoFallaController.modosArrayList);

        Stage stage = (Stage) inputNombre.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void agregarCausa() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AgregarCausaFalla.fxml"));
        Parent root = loader.load();

        AgregarCausaFallaController agregarCausaFallaController = loader.getController();
        agregarCausaFallaController.setEditarModoFallaController(this);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("FMEA - Agregar Causa de Falla");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../gear.ico")));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public void agregarCausaToArrayList(CausaFalla cf) {
        causasArrayList.add(cf);
    }

    @FXML
    private void editarCausa() throws IOException {
        if(!VALIDAR.hayItems(causasListView)) {
            VALIDAR.showInfo("No hay causas.");
            return;
        }
        else if(!VALIDAR.estaSeleccionado(causasListView)) {
            VALIDAR.showError("Primero tienes que seleccionar una causa.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/EditarCausaFalla.fxml"));
        Parent root = loader.load();

        String actualCausaFallaTxt = causasListView.getSelectionModel().getSelectedItem();
        CausaFalla actualCausaFalla = new CausaFalla(actualCausaFallaTxt, 1, 1);

        for(CausaFalla c : causasArrayList) {
            if (c.getNombre().equals(actualCausaFallaTxt)) {
                actualCausaFalla.setNombre(c.getNombre());
                actualCausaFalla.setOcurrencia(c.getOcurrencia());
                actualCausaFalla.setDeteccion(c.getDeteccion());
                System.out.println(c.getNombre());
                System.out.println(c.getOcurrencia());
                System.out.println(c.getDeteccion());
            }
        }

        EditorCausaFallaController editarCausaFallaController = loader.getController();
        editarCausaFallaController.setEditarModoFallaController(this);
        editarCausaFallaController.setInitials(actualCausaFalla.getNombre(), actualCausaFalla.getOcurrencia(), actualCausaFalla.getDeteccion());

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("FMEA - Editar Causa de Falla");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../gear.ico")));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public void editarCausaToArrayList(CausaFalla cf) {
        int index = causasListView.getSelectionModel().getSelectedIndex();
        causasArrayList.set(index, cf);
    }

    @FXML
    private void eliminarCausa() {
        if(!VALIDAR.hayItems(causasListView)) {
            VALIDAR.showInfo("No hay causas.");
            return;
        }
        else if(!VALIDAR.estaSeleccionado(causasListView)) {
            VALIDAR.showError("Primero tienes que seleccionar una causa.");
            return;
        }
        
        int index = causasListView.getSelectionModel().getSelectedIndex();
        causasArrayList.remove(index);
        refreshCausasList();       
    }

    @FXML
    private void close() throws IOException {
        Stage stage = (Stage) inputNombre.getScene().getWindow();
        stage.close();
    }

    //REFRESH
    public void refreshCausasList() {
        causasFallas.clear();
        for (CausaFalla c : causasArrayList) {
            causasFallas.add(c.getNombre());
        }
        causasListView.getItems().clear();
        causasListView.getItems().addAll(causasFallas);
    }

}
