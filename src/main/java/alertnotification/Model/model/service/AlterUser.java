package alertnotification.Model.model.service;

import java.sql.Timestamp;

import alertnotification.Model.model.AlertMode;

public class AlterUser {

  private static  int alertCounter = 0;
  public AlterUser() {
  }

  void checkForAlert(String logData, AlertMode alertMode) {

    String userAlertPreference = alertMode.getAlertType();
    int frequency = alertMode.getFrequency();
    Timestamp firstAlertOccurence = new Timestamp(System.currentTimeMillis());
    Timestamp previousAlertSentTime = new Timestamp(System.currentTimeMillis());
    String [] data = logData.split(" ");

    String logAlertType = data[1];
    long waitTime =0;
    if(logAlertType.equalsIgnoreCase(userAlertPreference)){
      if(alertCounter == 0){
        firstAlertOccurence = Timestamp.valueOf(data[0]);
      }
      waitTime = Timestamp.valueOf(data[0]).getTime() - previousAlertSentTime.getTime() ;
      while(waitTime > alertMode.getWaitTime()){
        continue;
      }
      Timestamp timeAlertOccur = Timestamp.valueOf(data[0]);
      long timeDiff = timeAlertOccur.getTime() - firstAlertOccurence.getTime();
      if(timeDiff > alertMode.getDuration()){
        alertCounter =0;
      }
      if(frequency == alertCounter){
         previousAlertSentTime = Timestamp.valueOf(data[0]);
        System.out.println("Error "+userAlertPreference +"has reached its threshold");
      }
      alertCounter++;
    }
  }
}



