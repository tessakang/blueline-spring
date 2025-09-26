package blueline.blueline_spring.service;

import blueline.blueline_spring.domain.Member;
import blueline.blueline_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    //new를 사용하지 않음
    //아무래도 새로운 객체로 클래스의 인스턴스와 다름 -> 때문에 안의 값이 약간 다를 수 있음
    //현재 예시에서는 static이기 때문에 괜찮으나 static이 아닌 경우 바로 오류남
    MemberService memberService;
    MemoryMemberRepository memberRepository; // 멤버 clear를 위해 가져옴


    // 테스트를 시행하기 전 실행되어 생성해줌
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository(); // MemoryMemberRepository를 생성하여 memberRepository에 넣어준 뒤
        memberService = new MemberService(memberRepository); // MemberService에 생성된 memberRepository을 넣어 memberService를 생성
        // -> 같은 메모리 멤버 레포지토리를 사용하게 된다.
    }

    // 돌 때마다 db의 값을 다 날려줌
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }


    // 테스트에 들어가는 메소드명은 한글로 바꾸어줘도 무방 ex) 회원가입
    @Test
    public void 회원가입() throws Exception {
        //Given
        Member member = new Member();
        member.setName("hello");

        //When
        Long saveId = memberService.join(member);

        //Then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }


    @Test
    public void 중복_회원_예외() throws Exception {
        //Given
        //중복된 이름의 멤버 2명 생성
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        //When
        //에러를 감지할 때 try-catch문을 쓸 수 있지만 좀 애매할 수 있음.
        //-> assertThrows 사용
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2)); //예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
