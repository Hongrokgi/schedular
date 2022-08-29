package com.schedular.schedualr.service;

import com.schedular.schedualr.controller.RestTemplateTestController;
import com.schedular.schedualr.domain.entity.PersonResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class RestTemplateTestServiceTest {
    @Autowired
    RestTemplateTestController controller;

    @Test
    public void restTemplateTest() {
        ResponseEntity<PersonResponse> entity = controller.restTemplateTest1();
        System.out.println(">>> entity = " + entity.getStatusCode());
        System.out.println(">>> entity = " + entity.getBody().getResult());
        System.out.println(">>> entity = " + entity.getBody().getUsername());
        Assertions.assertEquals(entity.getStatusCodeValue(), 200);
        Assertions.assertEquals(entity.getBody().getUsername(), "John");
    }
}