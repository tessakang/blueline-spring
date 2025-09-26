package blueline.blueline_spring.controller;

import blueline.blueline_spring.domain.Member;
import blueline.blueline_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MemberController {
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member); //member 넘김

        return "redirect:/"; // 홈 화면으로 보내기
    }

}








//// 스프링이 처음에 뜰 때 스프링 컨테이너 라는 스프링 통이 생성됨
//// 그 통 안에 @Controller이 있으면 멤머 컨트롤 객체를 생성해서 스프링에 넣어두고 스프링이 관리함.
//// 이렇게 스프링 컨테이서에서 스프링 빈이 관리한다고 표현함
//// 즉 MemberController을 스프링이 관리함
//@Controller
//public class MemberController {
//
//    // 스프링이 관리하는 경우 스프링 컨테이너에 등록하여 스프링 컨테이너에서 받아서 쓸수록 아래와 같이 생성자를 만들어야함
//    // new로 새로 만들어거 사용 x
//    private final MemberService memberService;
//
//    // setter로 DI 적용
////    @Autowired
////    public void setMemberService(MemberService memberService) {
////        this.memberService = memberService;
////    }
//
//    // 생성자로 연결
//    // 이 클래스에 선언된 @Controller로 인해 MemberController가 생성될 때 생성자가 호출됨
//    // 이때 생성자에 AutoWired가 있으면 MemberService를 스프링이 스프링 컨테이너 안에 있는 MemberService에 연결시켜줌
//    // 이것 또한 DI임 = 의존관계를 주입해줌
//    @Autowired // 컨트롤러랑 Service 연결
////    연결 MemberService에도 @Autowired 추가해줘야함.
//    public MemberController(MemberService memberService) {  // 생성자임
//        // 만일 여기서 memberService 부분에 빨간 줄이 뜨는 경우
//        // -> MemberServi ce를 확인해보기
//        // -> MemeberService가 순수한 자바 클래스일 가능성 있음
//        // 즉 따로 @이 없는 클래스이기 때문에 스프링에서 따로 컨테이너에 등록을 안 시켜줌
//        // -> MemberService에 @Service를 추가하면 스프링이 등록시켜줌
//        //    -> 연결 가능한 상태
//        // *Repository도 동일
//        this.memberService = memberService;
//    }
//}