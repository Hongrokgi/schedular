package com.schedular.schedualr.domain.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional //No EntityManager with actual transaction available for current thread - cannot reliably process 'persist' call 해당 어노테이션 생략시 오류 발생
@Rollback(value = false)
class BookTest {

    @Autowired
    EntityManager em;

    @Test
    public void 유니크_인덱스() throws Exception {
        //given
        Book book1 = new Book("수학의 정석","홍성대", LocalDateTime.now());
        Book book2 = new Book("SSen수학", "홍성대", LocalDateTime.now());
        em.persist(book1);
        em.persist(book2);
        //when

        //then
    }

}