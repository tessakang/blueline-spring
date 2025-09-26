package blueline.blueline_spring.service;

import blueline.blueline_spring.domain.Member;
import blueline.blueline_spring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


// @Autowired private MemberService memberService; // 필드를 이용하여 DI 적용




// 아무런 @이 없는 상태는 순수한 자바클래스로 스프링이 해당 클래스를 안고 있을 수가 없음
// -> **오류 발생**
// Consider defining a bean of type 'hello.hellospring.service.MemberService' in your configuration.
// -> 그래서 아래와 같이 @Service를 넣어줘야함.
//@Service
public class MemberService {

    // @Autowired private MemberService memberService; // 필드를 이용하여 DI 적용하는 방법(비추)

    // 서비스를 만드려면 레포지토리가 필요함
    // 아래 주석 처리된 코드는 직접 new 새로 생성하는 경우
//    private final MemberRepository memberRepository = new
//            MemoryMemberRepository();
    // 아래 경우는 외부에서 넣어주는 방식
    private final MemberRepository memberRepository;


    // 멤버 서비 스를 만들 때 아래 생성차를 호출함.
    // 이때 AutoWired가 있으면 MemberRepository가 필요함을 인식하고 MemberRepository를 컨테이너에 넣어줌
    // 멤버 서비스는 멤버 지포지토리가 필요하기 때문
//    @Autowired
    public MemberService(MemberRepository memberRepository) { // 생성자임
        this.memberRepository = memberRepository;
    }

    /** 회원가입 **/
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }
    // 로직이 쭉 계속 나오는 경우 메소드로 작성해주는 것이 좋음
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) // 얘가 optional 이기 때문에 바로 ispresent를 사용함
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }


    /** 전체 회원 조회 **/
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
