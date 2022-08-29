package com.schedular.schedualr.controller;

import com.schedular.schedualr.domain.entity.PersonResponse;
import com.schedular.schedualr.service.RestTemplateTestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
/*
*   HttpEntity -> HttpHeader + Body
*   ResponseEntity -> 사용자의 요청에 의한 응답을 HttpEntity 구조에 맞게 보여주는 역할을 한다.
*   이 때 요청은 HttpRequest 에서 이루어진다. HttpRequest 를 다룬 부분이 없는데 이건 RestTemplate 에서 해결
*
* */
@RestController
@AllArgsConstructor
@Slf4j
public class RestTemplateTestController {
    private final RestTemplateTestService restTemplateTestService;

    @PostMapping
    public ResponseEntity<PersonResponse> restTemplateTest1() {
        return ResponseEntity.ok(restTemplateTestService.callPostExternalServer());
    }
}
