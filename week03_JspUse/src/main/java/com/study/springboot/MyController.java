package com.study.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
    @RequestMapping("/")  //local:host8181/호출 시 실행
    public @ResponseBody String root() throws Exception{  //리턴값을 직접 화면에 출력
        return "포켓몬 소개";
    }
 
    @RequestMapping("/metamong")   
    public String method1() {
        return "name : metamong";             
    }
     
    @RequestMapping("/meu")    
    public String method2() {
        return "name : meu";        
    }

    @RequestMapping("/eve")   
    public String method3() {
        return "name : eve";              
    }
}
