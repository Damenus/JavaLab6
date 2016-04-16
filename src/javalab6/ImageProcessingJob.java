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
        this.status.set("waiting");
        this.progress.addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(progress.get() > 0.0) {
                    status.set("processing...");
                } else if (progress.get() >= 1.0) {
                    status.set("completed");
                }
            }             
        });
        
    }

    public File getFile() {
        return this.file;
    }
    
    public SimpleStringProperty getStatusProperty() {        
        return this.status;
    }

    public DoubleProperty getProgressProperty() {
        return this.progress;
    }
    
}
