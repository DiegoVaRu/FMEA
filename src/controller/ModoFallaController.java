package controller;

import java.util.ArrayList;
import model.CausaFalla;
import model.EfectoFalla;
import model.ModoFalla;
import model.utilities.DATA;
import model.utilities.VALIDAR;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ModoFallaController implements Initializable {

    public ArrayList<ModoFalla> modosArrayList = new ArrayList<ModoFalla>();
    private ArrayList<String> modosFallas = new ArrayList<String>();
    ArrayList<CausaFalla> causasFallasAux = new ArrayList<CausaFalla>();
    
    @FXML
    private ListView<String> modosFallaList;
    @FXML
    private Label nprLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (DATA.loadData() != null)
            modosArrayList.addAll(DATA.loadData());

        for(ModoFalla m : modosArrayList)
            modosFallas.add(m.getNombre());

        modosFallaList.getItems().addAll(modosFallas);

        modosFallaList.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                String modoNombre = modosFallaList.getSelectionModel().getSelectedItem();
                    if (modoNombre != null) {
                        ModoFalla mf = new ModoFalla(null, 0, null, null, null, null, causasFallasAux);
                        for (ModoFalla m : modosArrayList) {
                        if (modoNombre.equals(m.getNombre())) {
                            mf.setCausasFalla(m.getCausasFalla());
                            mf.setGravedad(m.getGravedad());
                            mf.setNpr(m.getCausasFalla());
                        }
                    }
                    nprLabel.setText("NPR: " + mf.getNPR() );
                }                
                else nprLabel.setText("");
            }            
            
        });
    }

    //AGREGAR
    @FXML
    private void agregarModoFalla() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AgregarModoFalla.fxml"));
        Parent root = loader.load();

        AgregarModoFallaController agregarModoFallaController = loader.getController();
        agregarModoFallaController.setModoFallaController(this);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("FMEA - Agregar Modo de Falla");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../gear.ico")));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public void agregarModoFallaToArrayList(ModoFalla mf) {
        modosArrayList.add(mf);
    }

    //EDITAR
    @FXML
    private void editarModoFalla() throws IOException {
        if(!VALIDAR.hayItems(modosFallaList)) {
            VALIDAR.showInfo("No hay fallas.");
            return;
        }
        else if(!VALIDAR.estaSeleccionado(modosFallaList)) {
            VALIDAR.showError("Primero tienes que seleccionar una falla.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/EditarModoFalla.fxml"));
        Parent root = loader.load();

        int index = modosFallaList.getSelectionModel().getSelectedIndex();
        ModoFalla mf = modosArrayList.get(index);
        ArrayList<String> causasStrings = new ArrayList<String>();
        for (CausaFalla c : mf.getCausasFalla()) {
            causasStrings.add(c.getNombre());
        }

        EditarModoFallaController editarModoFallaController = loader.getController();
        editarModoFallaController.setModoFallaController(this, index);
        editarModoFallaController.setInitials(mf.getNombre(), mf.getGravedad(), mf.getEquipo(), mf.getFecha(), mf.getAcciones(), mf.getEfectoFalla().getNombre(), causasStrings, mf.getCausasFalla());

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("FMEA - Editar Modo de Falla");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../gear.ico")));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public void editarModoFallaToArrayList(int index, ModoFalla mf) {
        modosArrayList.set(index, mf);
    }

    //ELIMINAR
    @FXML
    private void eliminarModoFalla() {
        if(!VALIDAR.hayItems(modosFallaList)) {
            VALIDAR.showInfo("No hay fallas.");
            return;
        }
        else if(!VALIDAR.estaSeleccionado(modosFallaList)) {
            VALIDAR.showError("Primero tienes que seleccionar una falla.");
            return;
        }

        int index = modosFallaList.getSelectionModel().getSelectedIndex();
        modosArrayList.remove(index);
        refreshModosList();
        DATA.saveData(modosArrayList);
    }

    @FXML // SHOW - EFECTOS
    private void mostrarEfectosFalla() throws IOException {
        if(!VALIDAR.hayItems(modosFallaList)) {
            VALIDAR.showInfo("No hay fallas.");
            return;
        }
        else if(!VALIDAR.estaSeleccionado(modosFallaList)) {
            VALIDAR.showError("Primero tienes que seleccionar una falla.");
            return;
        }        

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/EfectoFalla.fxml"));
        Parent root = loader.load();

        String actualModoFalla = modosFallaList.getSelectionModel().getSelectedItem();
        String actualEfectoFalla = "--";
        for (ModoFalla m : modosArrayList) {
            if (actualModoFalla.equals(m.getNombre()))
                actualEfectoFalla = m.getEfectoFalla().getNombre();
        }

        EfectoFallaController efectoFallaController = loader.getController();
        efectoFallaController.setEfectoFalla(actualEfectoFalla);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("FMEA - Efectos de Falla");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../gear.ico")));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void mostrarTodosEfectosFalla() throws IOException {
        if(!VALIDAR.hayItems(modosFallaList)) {
            VALIDAR.showInfo("No hay efectos de falla.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TodosEfectosFalla.fxml"));
        Parent root = loader.load();

        ArrayList<String> efectoFallas = new ArrayList<String>();
        for (ModoFalla m : modosArrayList) {
            efectoFallas.add(m.getEfectoFalla().getNombre());
        }
        TodosEfectosFallaController todosEfectosFalla = loader.getController();
        todosEfectosFalla.setEfectosFallaList(efectoFallas);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("FMEA - Todos los Efectos de Falla");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../gear.ico")));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void mostrarTodasCausasFalla() throws IOException {
        if(!VALIDAR.hayItems(modosFallaList)) {
            VALIDAR.showInfo("No hay causas de falla.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TodasCausasFalla.fxml"));
        Parent root = loader.load();

        ArrayList<String> causasFallas = new ArrayList<String>();
        for (ModoFalla m : modosArrayList) {
            for (CausaFalla c : m.getCausasFalla())
                causasFallas.add(c.getNombre());
        }

        TodasCausasFallaController todasCausasFalla = loader.getController();
        todasCausasFalla.setCausasFallaList(causasFallas);        

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("FMEA - Todas las Causas de Falla");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../gear.ico")));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void mostrarPrioridadFalla() throws IOException {
        if(!VALIDAR.hayItems(modosFallaList)) {
            VALIDAR.showInfo("No hay fallas.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/PrioridadRiesgo.fxml"));
        Parent root = loader.load();

        ArrayList<String> causasFallas = new ArrayList<String>();        
        ModoFalla mf = new ModoFalla(null, 0, null, null, null, null, causasFallasAux);
        
        calcNPR(mf, causasFallas);

        PrioridadFallaController prioridadFallaController = loader.getController();
        prioridadFallaController.setLabels(mf.getNombre(), mf.getNPR());
        prioridadFallaController.setCausasFallaList(causasFallas);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("FMEA - Prioridad de Riesgo");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../gear.ico")));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void mostrarGrafica() throws IOException {
        if(!VALIDAR.hayItems(modosFallaList)) {
            VALIDAR.showInfo("No hay fallas.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/GraficaNPR.fxml"));
        Parent root = loader.load();  

        ArrayList<String> causasFallas = new ArrayList<String>();        
        ModoFalla mf = new ModoFalla(null, 0, null, null, null, null, causasFallasAux);
        
        calcNPR(mf, causasFallas);

        GraficaFallasController graficaFallasController = loader.getController();
        graficaFallasController.setInitials(modosArrayList);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("FMEA - Gráfica de NPR");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../gear.ico")));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML // SHOW - CAUSAS
    private void mostrarCausasFalla() throws IOException {
        if(!VALIDAR.hayItems(modosFallaList)) {
            VALIDAR.showInfo("No hay fallas.");
            return;
        }
        else if(!VALIDAR.estaSeleccionado(modosFallaList)) {
            VALIDAR.showError("Primero tienes que seleccionar una falla.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/CausasFalla.fxml"));
        Parent root = loader.load();

        String actualModoFalla = modosFallaList.getSelectionModel().getSelectedItem();
        ArrayList<CausaFalla> actualCausasFalla = new ArrayList<CausaFalla>();
        ModoFalla modo = new ModoFalla(null, 0, null, null, null, null, causasFallasAux);

        for (ModoFalla m : modosArrayList) {
            if (actualModoFalla.equals(m.getNombre())) {
                actualCausasFalla = m.getCausasFalla();
                modo = m;
                break;
            }    
        }

        CausasFallaController causasFallaController = loader.getController();
        causasFallaController.setCausasFalla(actualCausasFalla, modo);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("FMEA - Causas de Falla");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../gear.ico")));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void refreshModosList() {
        modosFallas.clear();
        for (ModoFalla c : modosArrayList)
            modosFallas.add(c.getNombre());

        modosFallaList.getItems().clear();
        modosFallaList.getItems().addAll(modosFallas);
    }

    //CALCULAR NPR
    private void calcNPR(ModoFalla mf, ArrayList<String> causasFallas) {
        for (ModoFalla m : modosArrayList) {
            m.setNpr(m.getCausasFalla());

            mf.setNpr(m.getNPR());
            mf.setCausasFalla(m.getCausasFalla());
            mf.setNombre(m.getNombre());

            if (mf.getNPR() == 0.0)
                mf.setNpr(m.getNPR());            
        }

        for (CausaFalla c : mf.getCausasFalla())
                causasFallas.add(c.getNombre());
    }
}
