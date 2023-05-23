package com.example.linedemo.config;

import com.example.linedemo.repository.EventRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {
    //2023-05-22 Project 등록 [ 기존 프로젝트 이동 겸 복습]
    //구현체를 Bean으로 등록
    public EventRepository eventRepository(){
        return new EventRepository() {};
    }
}
