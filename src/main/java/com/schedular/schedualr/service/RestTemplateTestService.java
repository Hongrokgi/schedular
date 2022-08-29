package com.schedular.schedualr.service;

import com.schedular.schedualr.domain.entity.Person;
import com.schedular.schedualr.domain.entity.PersonResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class RestTemplateTestService {
    private final ApiService<PersonResponse> apiService;

    public PersonResponse callPostExternalServer() {
        Person person = Person.builder()
                .age(22)
                .name("John")
                .build();

        HttpHeaders headers = new HttpHeaders();
        PersonResponse response = apiService.post("https://d05a3a84-7d1a-4336-a7e4-07a5c9655c1f.mock.pstmn.io/testapi/first", headers, person, PersonResponse.class)
                .getBody();

        System.out.println("response = " + response);
        return response;
    }
}
