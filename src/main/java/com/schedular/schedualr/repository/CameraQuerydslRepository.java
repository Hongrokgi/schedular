package com.schedular.schedualr.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.schedular.schedualr.domain.dto.CameraResponseDto;
import com.schedular.schedualr.domain.dto.QCameraResponseDto;
import com.schedular.schedualr.enumulator.CameraStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.schedular.schedualr.domain.entity.QCamera.camera;


@Repository
public class CameraQuerydslRepository {
    /*
    *   JpaRepository 와 QueryDslRepository(Dto 전용) 분리
    *   지금은 해당 클래스내에서 EntityManager 를 JpaQueryFactory 에 주입하고 있지만
    *   추후에는 config 설정을 따로 만들어서 구동시에 빈으로 인식할 수 있게하고
    *   Repository -> private final JpaQueryFactory 만 선언해서 사용하면 됨
    * */
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public CameraQuerydslRepository(EntityManager em) {
        this.em=em;
        this.queryFactory= new JPAQueryFactory(em);
    }
    public List<CameraResponseDto> findAll() {
       return queryFactory
               .select(new QCameraResponseDto(
                       camera.id,
                       camera.cameraName,
                       camera.eventListener,
                       camera.message,
                       camera.cameraStatus,
                       camera.sendYn))
               .from(camera)
               .where(camera.cameraStatus.eq(CameraStatus.ONLINE))
               .fetch();
    }

    public CameraResponseDto findById(Long id) {
        return queryFactory
                .select(new QCameraResponseDto(
                        camera.id,
                        camera.cameraName,
                        camera.eventListener,
                        camera.message,
                        camera.cameraStatus,
                        camera.sendYn))
                .from(camera)
                .where(camera.id.eq(id).and(camera.cameraStatus.eq(CameraStatus.ONLINE)))
                .fetchOne();
    }
}
