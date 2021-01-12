package alertnotification.Model.model.Dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import alertnotification.Model.model.AlertMode;
import alertnotification.Model.model.DataFile;
import alertnotification.Model.model.exception.ConfigFileNotPresentException;
import alertnotification.Model.model.service.NotificationService;


public class AlertData {

  String configFileName = null;
  DataFile logData;
  NotificationService notificationService;

  public AlertData(String configFileName) {
    this.configFileName = configFileName;
  }

  Properties configFileData;

  {
    try {
      configFileData = readConfigFile(configFileName);
      AlertMode alertMode = new AlertMode();
      alertMode.setFrequency(Integer.parseInt(configFileData.getProperty("frequency")));
      alertMode.setAlertType(configFileData.getProperty("alertSubscribeFor"));
      alertMode.setDuration(Integer.parseInt(configFileData.getProperty("duration")));
      alertMode.setWaitTime(Integer.parseInt(configFileData.getProperty("waitTime")));
      String logFilePath = configFileData.getProperty("logPath");
      notificationService = new NotificationService(logData,alertMode,logFilePath);
      notificationService.analyzeLogData();

    } catch (IOException e) {
      throw new RuntimeException("Some IO Excepton Occur");
    }
  }


  static Properties readConfigFile(String filePath) throws IOException{

    FileInputStream stream = null;
    Properties properties = null;

    try{
      stream = new FileInputStream(filePath);
      properties = new Properties();
      properties.load(stream);
    } catch(FileNotFoundException ex){
      throw new ConfigFileNotPresentException("Config File is not Present");
    } catch (IOException io){
      io.printStackTrace();
    } finally {
      stream.close();
    }

    return properties;
  }
}
