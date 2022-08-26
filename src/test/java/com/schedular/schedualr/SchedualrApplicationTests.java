package com.schedular.schedualr;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.schedular.schedualr.domain.entity.Camera;
import com.schedular.schedualr.domain.entity.QCamera;
import com.schedular.schedualr.enumulator.CameraStatus;
import com.schedular.schedualr.repository.CameraRepository;
import com.schedular.schedualr.service.CameraService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static com.schedular.schedualr.domain.entity.QCamera.camera;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
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

//	@BeforeEach
//	public void before() {
//		queryFactory = new JPAQueryFactory(em);
//		Camera camera1 = new Camera("1층정문외측앞", "dong1@kakao.com", "흡연감지", CameraStatus.ONLINE, "N");
//		Camera camera2 = new Camera("1층정문내측좌", "dong1@kakao.com", "전화감지", CameraStatus.ONLINE, "Y");
//		Camera camera3 = new Camera("1층정문내측우", "dong1@kakao.com", "안전모미착용", CameraStatus.ONLINE, "N");
//		Camera camera4 = new Camera("1층안내데스크좌", "dong1@kakao.com", "작업복미착용", CameraStatus.ONLINE, "N");
//		Camera camera5 = new Camera("1층안내데스크우", "dong2@kakao.com", "흡연감지", CameraStatus.ONLINE, "N");
//		Camera camera6 = new Camera("1층로비복도입구", "dong2@kakao.com", "전화감지", CameraStatus.ONLINE, "Y");
//		Camera camera7 = new Camera("1층로비복도출구", "dong2@kakao.com", "안전모미착용", CameraStatus.ONLINE, "N");
//		Camera camera8 = new Camera("1층로비화장실앞", "dong2@kakao.com", "작업복미착용", CameraStatus.ONLINE, "N");
//		Camera camera9 = new Camera("1층뒷문앞", "dong3@kakao.com", "흡연감지", CameraStatus.ONLINE, "N");
//		Camera camera10 = new Camera("2층정문외측앞", "dong3@kakao.com", "전화감지", CameraStatus.ONLINE, "Y");
//		Camera camera11 = new Camera("2층정문내측좌", "dong3@kakao.com", "안전모미착용", CameraStatus.ONLINE, "N");
//		Camera camera12 = new Camera("2층정문내측우", "dong3@kakao.com", "작업복미착용", CameraStatus.ONLINE, "N");
//		Camera camera13 = new Camera("2층로비복도입구", "dong4@kakao.com", "흡연감지", CameraStatus.ONLINE, "N");
//		Camera camera14 = new Camera("2층로비복도출구", "dong4@kakao.com", "전화감지", CameraStatus.ONLINE, "Y");
//		Camera camera15 = new Camera("2층로비화장실앞", "dong4@kakao.com", "안전모미착용", CameraStatus.ONLINE, "N");
//		Camera camera16 = new Camera("2층뒷문앞", "dong4@kakao.com", "작업복미착용", CameraStatus.ONLINE, "N");
//
//		em.persist(camera1);
//		em.persist(camera2);
//		em.persist(camera3);
//		em.persist(camera4);
//		em.persist(camera5);
//		em.persist(camera6);
//		em.persist(camera7);
//		em.persist(camera8);
//		em.persist(camera9);
//		em.persist(camera10);
//		em.persist(camera11);
//		em.persist(camera12);
//		em.persist(camera13);
//		em.persist(camera14);
//		em.persist(camera15);
//		em.persist(camera16);
//	}

//	@Test
//	public void CameraTest() {
//
//		Camera findCamera = queryFactory
//				.selectFrom(camera)
//				.where(camera.sendYn.eq("Y").and(camera.eventListener.eq("dong4@kakao.com")))
//				.fetchOne();
//
//		assertThat(findCamera.getCameraStatus()).isEqualTo(CameraStatus.ONLINE);
//		assertThat(findCamera.getId()).isEqualTo(14);
//		assertThat(findCamera.getCameraName()).isEqualTo("2층로비복도출구");
//		assertThat(findCamera.getMessage()).isEqualTo("전화감지");
//
//	}

}
