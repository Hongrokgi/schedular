package com.schedular.schedualr.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.schedular.schedualr.enumulator.CameraStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter @Setter
@ToString(of = {"id","cameraName","eventListener","message","cameraStatus","sendYn"})
public class CameraResponseDto {
    private Long id;
    private String cameraName;
    private String eventListener;
    private String message;
    @Enumerated(EnumType.STRING)
    private CameraStatus cameraStatus;
    private String sendYn;

    @QueryProjection
    public CameraResponseDto(Long id, String cameraName, String eventListener, String message, CameraStatus cameraStatus, String sendYn) {
        this.id = id;
        this.cameraName = cameraName;
        this.eventListener = eventListener;
        this.message = message;
        this.cameraStatus = cameraStatus;
        this.sendYn = sendYn;
    }
}
