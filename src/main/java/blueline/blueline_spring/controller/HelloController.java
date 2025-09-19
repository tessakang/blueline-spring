package blueline.blueline_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // 정적
    @GetMapping("hello") //웹 애플리케이션에서 /hello가 들어오면 아래 메서드를 호출해줌
                         // url과 매칭됨
    public String hello(Model model){
        model.addAttribute("data", "Hello!!");  // html에서 ${data} 된 부분을 Hello!!로 치환해줌
                                                                         // 직접 받는 형식
        return "hello"; // 템플릿 안에 있는 hello 파일을 찾아서 매핑
    }


    // MVC
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) { // 외부에서 파라미터를 바꿔옴
                                                                             // required 필드가 기본적으로 true로 들어가서 파라미터 값이 들어오지 않는 경우 오류가 남
        model.addAttribute("name", name);
        return "hello-template";
    }


    // API-기본(단순 문자 전달)
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // API-많이 사용하는 방식(객제 전달)
    @GetMapping("hello-api")
    @ResponseBody  // http body 부에 데이터를 직접 넣겠다는 의미
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello {
        private String name;
        // private라 못 쓰는 파라미터를 사용하기 위해 getter setter 사용하기
        // getter, setter방식, 프로퍼티 방식
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
