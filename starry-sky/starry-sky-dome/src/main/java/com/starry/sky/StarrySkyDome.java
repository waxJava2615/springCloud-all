package com.starry.sky;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wax
 * @description: TODO
 * @date 2021-10-08
 */
@RestController
@SpringBootApplication
public class StarrySkyDome {
    public static void main(String[] args) {
        PathMatcher pathMatcher = new AntPathMatcher();
        System.out.println(pathMatcher.match("/a/b/c.do", "/b/c.do"));
//        SpringApplication.run(StarrySkyDome.class,args);
    }

    @GetMapping("/a/list")
    public String getAList(){
        return "a is list";
    }

    @GetMapping("/b/list")
    public String getBList(){
        return "b is list";
    }

    @GetMapping("/d/list")
    public String getCList(){
        return "c is list";
    }

}
