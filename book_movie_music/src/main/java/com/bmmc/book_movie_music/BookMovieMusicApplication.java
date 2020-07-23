package com.bmmc.book_movie_music;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@MapperScan("com.bmmc.book_movie_music.dao")
public class BookMovieMusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookMovieMusicApplication.class, args);
    }

}
