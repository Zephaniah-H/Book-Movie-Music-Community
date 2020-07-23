package com.bmmc.book_movie_music.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/bookCover/**").addResourceLocations("file:" + "e:/GraduationProject/bmmcImg/book/");
        registry.addResourceHandler("/api/musicCover/**").addResourceLocations("file:" + "e:/GraduationProject/bmmcImg/music/");
        registry.addResourceHandler("/api/movieCover/**").addResourceLocations("file:" + "e:/GraduationProject/bmmcImg/movie/");
    }
}
