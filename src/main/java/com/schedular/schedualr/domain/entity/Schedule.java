package com.schedular.schedualr.domain.entity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.schedular.schedualr.enumulator.CameraStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static com.schedular.schedualr.domain.entity.QCamera.*;

@Component
@Slf4j
@RequiredArgsConstructor
@Transactional
public class Schedule {
    private final EntityManager em;
    private JPAQueryFactory queryFactory;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    //10초마다 실행 fixedDelay = 1000
//    @Scheduled(cron = "0 0/10 * * * *")
    public void testSchedule() {
        queryFactory = new JPAQueryFactory(em);
        List<Camera> cameras = queryFactory
                .selectFrom(camera)
                .where(camera.sendYn.eq("N").and(camera.cameraStatus.eq(CameraStatus.ONLINE)))
                .fetch();
        logger.info("해당 코드는 이 작업이 끝나고 다시 5분 후에 실행", LocalDateTime.now());
        logger.info("카메라 16대중 메시지를 보내야 하는 것은"+cameras.size()+" 대 입니다.", LocalDateTime.now());

        if(cameras.size() != 0) {
            for (Camera sendableCamera : cameras) {
                //실제 메시지 번호 보내는 로직
                sendableCamera.Update();
                em.persist(sendableCamera);
                // 성공 & 실패 check
                logger.info("해당 카메라 관리자에게 메시지가 전송됐습니다.");
            }
        }else {
                logger.info("지금은 해당사항이 없습니다.");
        }
        logger.info("작업완료",LocalDateTime.now());
    }
}
