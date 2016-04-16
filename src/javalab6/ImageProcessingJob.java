/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javalab6;

import java.io.File;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Damian Darczuk
 */
public class ImageProcessingJob {
    
    private File file;
    private SimpleStringProperty status = new SimpleStringProperty();  
    private SimpleDoubleProperty progress = new SimpleDoubleProperty();
    
    
    public ImageProcessingJob() {
        
    }

    public ImageProcessingJob(File file) {
        this.file = file;
        this.progress.set(0.0);
        this.status.set("P");
    }

    public File getFile() {
        return this.file;
    }
    
    public SimpleStringProperty getStatusProperty() {        
        return this.status;
    }

    public ObservableValue<String> getStatusProperty2() {
        ObservableValue<String> result = null;
        result.addListener(new ChangeListener<String>() {        
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                 //result.set((ObservableList<String>) newValue.);
            }
        });
        
        return result;
    }

    public DoubleProperty getProgressProperty() {
        return this.progress;
    }
    
}
