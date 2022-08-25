package com.schedular.schedualr.repository;

import com.schedular.schedualr.domain.entity.Camera;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CameraRepository extends JpaRepository<Camera, Long> {
}
