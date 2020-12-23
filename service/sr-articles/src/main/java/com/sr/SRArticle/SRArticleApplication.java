package com.sr.SRArticle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sr"})
@EnableSwagger2
public class SRArticleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SRArticleApplication.class, args);
    }
}
