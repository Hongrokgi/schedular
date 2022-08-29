package com.schedular.schedualr.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/*
*   API 호출 클래스
*   RestTemplate -> exchange 메서드
* */
@Service
@Slf4j
@AllArgsConstructor
//@AllArgsConstructor 여기에 필드에 쓴 모든 생성자만 만들어줌
//@NoArgsConstructor 기본 생성자를 만들어줌
//@Data getter, setter 만들어줌
public class ApiService<T> {
    private final RestTemplate restTemplate;

//    @Autowired
//    public ApiService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    public ResponseEntity<T> get(String url, HttpHeaders httpHeaders) {
        return callApiEndPoint(url, HttpMethod.GET, httpHeaders, null, (Class<T>) Object.class);
    }

    public ResponseEntity<T> get(String url, HttpHeaders httpHeaders, Class<T> clazz) {
        return callApiEndPoint(url, HttpMethod.GET, httpHeaders,null, clazz);
    }

    public ResponseEntity<T> post(String url, HttpHeaders httpHeaders, Object body) {
        return callApiEndPoint(url, HttpMethod.POST, httpHeaders, body, (Class<T>) Object.class);
    }

    public ResponseEntity<T> post(String url, HttpHeaders httpHeaders, Object body, Class<T> clazz) {
        return callApiEndPoint(url, HttpMethod.POST, httpHeaders, body, clazz);
    }

    /*
    *   exchange() 의 장점은 HTTP Method 에 상관없이 사용할 수 있다는 것.
    * */
    public ResponseEntity<T> callApiEndPoint(String url, HttpMethod httpMethod, HttpHeaders httpHeaders, Object body, Class<T> clazz) {
        return restTemplate.exchange(url, httpMethod, new HttpEntity<>(body, httpHeaders), clazz);
    }
}
