package com.schedular.schedualr.service;

import com.querydsl.core.QueryResults;
import com.schedular.schedualr.domain.dto.CameraResponseDto;
import com.schedular.schedualr.domain.entity.Camera;
import com.schedular.schedualr.repository.CameraQuerydslRepository;
import com.schedular.schedualr.repository.CameraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CameraService {
    private final CameraRepository cameraRepository;
    private final CameraQuerydslRepository cameraQuerydslRepository;

    public List<CameraResponseDto> findAll() {
        return cameraQuerydslRepository.findAll();
    }

    public CameraResponseDto findSpecificCamera(Long id) {
        return cameraQuerydslRepository.findById(id);
    }

    public Long changeEventListener(Long id) {
        Camera camera = cameraRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 카메라 없음"));
        camera.setEventListener("skkim3980@naver.com");
        return cameraRepository.save(camera).getId();
    }
}
