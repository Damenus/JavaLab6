/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javalab6;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ProgressBarTableCell;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

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

    @FXML
    void processFiles(ActionEvent event) {
        new Thread(this::backgroundJob).start();
    }
    
    //metoda uruchamiana w tle (w tej samej klasie)    
    private void backgroundJob(){
        //operacje w tle
    }
    
    private void convertToGrayscale(File orginalFile, File outputDir, DoubleProperty progressProp) {
           try {
               BufferedImage orginal = ImageIO.read(orginalFile);
               
               BufferedImage greyscale = new BufferedImage(orginal.getWidth(), orginal.getHeight(), orginal.getType());
               
               for(int i = 0; i < orginal.getWidth(); i++) {
                   for(int j = 0; j < orginal.getHeight(); j++) {
                       int red = new Color(orginal.getRGB(i, j)).getRed();
                       int green = new Color(orginal.getRGB(i, j)).getGreen();
                       int blue = new Color(orginal.getRGB(i, j)).getBlue();
                       
                       int luminosity = (int) (0.21*red + 0.71*green + 0.07* blue);                       
                       int newPixel = new Color(luminosity,luminosity,luminosity).getRGB();                       
                       greyscale.setRGB(i, j, newPixel);                       
                   }
                   double progress = (1.0 + i)/orginal.getWidth();
                   Platform.runLater(() -> progressProp.set(progress));
               }
               
               Path outputPath = Paths.get(outputDir.getAbsolutePath(), orginalFile.getName());
               ImageIO.write(greyscale, "jpg", outputPath.toFile());              
                          
           } catch (IOException ex) {
               throw new RuntimeException(ex);
           }
   
    }
   
    
}
