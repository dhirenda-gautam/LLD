package alertnotification.Model.model;

public class AlertType {

  public WarningType warningType;

  enum WarningType{
    INFO,
    WARNING,
    CRITICAL,
    BLOCKER
  }
}
