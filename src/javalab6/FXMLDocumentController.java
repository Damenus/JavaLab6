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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

/**
 *
 * @author Damian Darczuk
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn progressColumn;
    @FXML
    private TableColumn statusColumn;
    @FXML
    private TextField numberField;
    
    @FXML
    private TableView photoTableView;
    
    List<File> photoList = new ArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void chooseFileAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter =
            new FileChooser.ExtensionFilter("JPG images", "*.jpg");
        fileChooser.getExtensionFilters().add(filter);
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        
        
    }
    
   @FXML 
    private void chooseDestinationDirectoryAction (ActionEvent e) {                       
        DirectoryChooser directoryChooser = new DirectoryChooser();        
        File file = directoryChooser.showDialog(null);
        
      
    }

    
}
