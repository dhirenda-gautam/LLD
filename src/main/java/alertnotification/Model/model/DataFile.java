package alertnotification.Model.model;

import java.sql.Timestamp;

public class DataFile {

   Timestamp timestamp;
   AlertType alertType;
   String data;

  public DataFile(Timestamp timestamp, AlertType alertType, String data) {
    this.timestamp = timestamp;
    this.alertType = alertType;
    this.data = data;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }

  public AlertType getAlertType() {
    return alertType;
  }

  public void setAlertType(AlertType alertType) {
    this.alertType = alertType;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }
}
