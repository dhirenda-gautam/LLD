package alertnotification.Model.model.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import alertnotification.Model.model.AlertMode;
import alertnotification.Model.model.DataFile;
import javafx.scene.control.Alert;

public class NotificationService {

  AlertMode alertMode;
  DataFile logData;
  String logFilePath;

  public NotificationService(DataFile logData,AlertMode alertMode ,String logPath){
    this.logData = logData;
    this.alertMode = alertMode;
    this.logFilePath = logPath;
  }

  public void analyzeLogData(){
    analyzeAndAlertUser(logFilePath,alertMode);
  }

  private void analyzeAndAlertUser(String logFilePath, AlertMode alertMode) {
    AlterUser alterUser = new AlterUser();
    try {
      File file = new File(logFilePath);
      Scanner scanner = new Scanner(file);
      scanner.useDelimiter(",|\r\n");
      while (scanner.hasNext()){
         alterUser.checkForAlert(scanner.next(),alertMode);
      }
    } catch(FileNotFoundException ex){
      throw new RuntimeException("Log File Not Found at Location "+logFilePath);
    }

  }
}
