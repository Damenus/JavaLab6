/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javalab6;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ProgressBarTableCell;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

/**
 *
 * @author Damian Darczuk
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML 
    TableColumn<ImageProcessingJob, String> imageNameColumn;
    @FXML 
    TableColumn<ImageProcessingJob, Double> progressColumn;
    @FXML 
    TableColumn<ImageProcessingJob, String> statusColumn;
    
    @FXML
    private TableView photoTableView;
    
    List<ImageProcessingJob> photoList = new ArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imageNameColumn.setCellValueFactory( //nazwa pliku
        p -> new SimpleStringProperty(p.getValue().getFile().getName()));
        statusColumn.setCellValueFactory( //status przetwarzania
        p -> p.getValue().getStatusProperty());
        progressColumn.setCellValueFactory( //postęp przetwarzania
        p -> p.getValue().getProgressProperty().asObject());
        progressColumn.setCellFactory( //wykorzystanie paska postępu
        ProgressBarTableCell.<ImageProcessingJob>forTableColumn());
    }    
    
    @FXML
    private void chooseFileAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter =
            new FileChooser.ExtensionFilter("JPG images", "*.jpg");
        fileChooser.getExtensionFilters().add(filter);
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        
        for (File file:selectedFiles)
            photoList.add(new ImageProcessingJob(file));
    }
    
   @FXML 
    private void chooseDestinationDirectoryAction (ActionEvent e) {                       
        DirectoryChooser directoryChooser = new DirectoryChooser();        
        File file = directoryChooser.showDialog(null);
        
      
    }

    //metoda obsługująca kliknięcie przycisku rozpoczynającego przetwarzanie
    @FXML
    void processFiles(ActionEvent event) {
        new Thread(this::backgroundJob).start();
    }
    
    //metoda uruchamiana w tle (w tej samej klasie)    
    private void backgroundJob(){
        //operacje w tle
    }
    
}
