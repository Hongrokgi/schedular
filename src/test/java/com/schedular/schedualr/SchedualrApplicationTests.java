package com.schedular.schedualr;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.schedular.schedualr.domain.entity.Camera;
import com.schedular.schedualr.enumulator.CameraStatus;
import com.schedular.schedualr.repository.CameraRepository;
import com.schedular.schedualr.service.CameraService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class SchedualrApplicationTests {
	@Autowired
	CameraService cameraService;

	@Autowired
	CameraRepository cameraRepository;

	@Autowired
	EntityManager em;

	JPAQueryFactory queryFactory;

	@Test
	void contextLoads() {
	}

	@Test
	public void CameraTest() {
		//given
		queryFactory = new JPAQueryFactory(em);
		//when
		Camera camera1 = new Camera("1층정문외측앞", "dong1@kakao.com", "흡연감지", CameraStatus.ONLINE, "N");
		cameraRepository.save(camera1);

		Optional<Camera> findCamera = cameraRepository.findById(camera1.getId());
		assertThat(camera1.getId()).isEqualTo(findCamera.get().getId());

	}

}
