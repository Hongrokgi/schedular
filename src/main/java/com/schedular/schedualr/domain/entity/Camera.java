package com.schedular.schedualr.domain.entity;

import com.schedular.schedualr.enumulator.CameraStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Camera {
  @Id @GeneratedValue
  private Long id;
  private String cameraName;
  private String eventListener;
  private String message;
  @Enumerated(EnumType.STRING)
  private CameraStatus cameraStatus;
  private String sendYn;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCameraName() {
    return cameraName;
  }

  public void setCameraName(String cameraName) {
    this.cameraName = cameraName;
  }

  public String getEventListener() {
    return eventListener;
  }

  public void setEventListener(String eventListener) {
    this.eventListener = eventListener;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public CameraStatus getCameraStatus() {
    return cameraStatus;
  }

  public void setCameraStatus(CameraStatus cameraStatus) {
    this.cameraStatus = cameraStatus;
  }

  public String getSendYn() {
    return sendYn;
  }

  public void setSendYn(String sendYn) {
    this.sendYn = sendYn;
  }

  public Camera(String cameraName, String eventListener, String message, CameraStatus cameraStatus, String sendYn) {
    this.cameraName = cameraName;
    this.eventListener = eventListener;
    this.message = message;
    this.cameraStatus = cameraStatus;
    this.sendYn = sendYn;
  }
}
