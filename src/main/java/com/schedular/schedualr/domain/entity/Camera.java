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

  @Builder
  public Camera(String cameraName, String eventListener, String message, CameraStatus cameraStatus, String sendYn) {
    this.cameraName = cameraName;
    this.eventListener = eventListener;
    this.message = message;
    this.cameraStatus = cameraStatus;
    this.sendYn = sendYn;
  }

  public void Update() {
    this.sendYn="Y";
  }
}
