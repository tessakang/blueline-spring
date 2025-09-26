package blueline.blueline_spring;

import blueline.blueline_spring.repository.MemberRepository;
import blueline.blueline_spring.repository.MemoryMemberRepository;
import blueline.blueline_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    // Configuration을 읽고 스프링 빈을 등록하라는 것을 인식하고 아래 {} 로직(return 부분)을 스프링 빈에 등록해줌.
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository()); // 괄호 안에 아무것도 넣지 않았을 때에는 빨간줄 뜲 - command+p로 들어가야할 파라미터 확인하기
                                    // memberRepository 를 필요로 함
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository(); // interface는 new를 하지 못하기 때문에 구현체를 사용해줘야함
    }
}
